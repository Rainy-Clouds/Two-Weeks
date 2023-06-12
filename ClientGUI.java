import java.awt.*;

public class ClientGUI 
{
    private boolean connecting, invalid, invalidname;
    private TextField ipField = new TextField(150, 300, 500, 50, 36, 0, this);
    private TextField nameField = new TextField(150, 250, 500, 50, 36, 1, this);
    private String currentMenu = "ip";
    private int selfOffset = 0;
    private SkinChooser chooser = new SkinChooser();

    public void update()
    {
        ipField.update();
        nameField.update();
        if(currentMenu == "name" && selfOffset > -800)
        {
            selfOffset -= 40;
            if(selfOffset < -800)
            {
                selfOffset = -800;
            }
        }
        chooser.update();
    }

    public void startConnecting()
    {
        connecting = true;
        invalid = false;
        ipField.setPermActive(false);
    }

    public void invalidated()
    {
        connecting = false;
        invalid = true;
        ipField.setPermActive(true);
    }

    public void invalidateName()
    {
        invalidname = true;
    }

    public void validateName()
    {
        invalidname = false;
    }

    public void connectionEstablished()
    {
        connecting = false;
        invalid = false;
        currentMenu = "name";
    }

    public void render(Graphics g, int offset)
    {
        ipField.setLocation(950 + offset + selfOffset, 300);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Enter the host code below", 950 + offset + selfOffset, 290);
        ipField.render(g);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        if(connecting)
        {
            g.setColor(Color.WHITE);
            g.drawString("Connecting...", 950 + offset + selfOffset, 375);
        }
        if(invalid)
        {
            g.setColor(Color.RED);
            g.drawString("Invalid code!", 950 + offset + selfOffset, 375);
        }

        nameField.setPermActive(currentMenu.equals("name"));
        nameField.setLocation(1750 + offset + selfOffset, 170);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Enter your in-game name below", 1750 + offset + selfOffset, 160);
        nameField.render(g);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        if(invalidname)
        {
            g.setColor(Color.RED);
            g.drawString("Name cannot include spaces!", 1750 + offset + selfOffset, 245);
        }

        //System.out.println(offset + ", " + selfOffset);
        chooser.render(g, offset + selfOffset);
    }
}
