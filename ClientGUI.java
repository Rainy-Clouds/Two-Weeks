import java.awt.*;

public class ClientGUI 
{
    private boolean connecting, invalid;
    private TextField ipField = new TextField(150, 300, 500, 50, 36, 0, this);

    public void update()
    {
        ipField.update();
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

    public void render(Graphics g, int offset)
    {
        ipField.setLocation(950 + offset, 300);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        g.drawString("Enter the host code below", 950 + offset, 290);
        ipField.render(g);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        if(connecting)
        {
            g.setColor(Color.WHITE);
            g.drawString("Connecting...", 950 + offset, 375);
        }
        if(invalid)
        {
            g.setColor(Color.RED);
            g.drawString("Invalid code!", 950 + offset, 375);
        }
    }
}
