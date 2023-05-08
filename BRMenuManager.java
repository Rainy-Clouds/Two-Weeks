import java.awt.*;

public class BRMenuManager 
{
    private Button[] buttons = {new Button("Host Game", 250, 200, "clearbutton.png", "clearbuttonT.png", "clearbutton.png", 4), new Button("Join Game", 250, 375, "clearbutton.png", "clearbuttonT.png", "clearbutton.png", 5)};
    private boolean screenChange;
    private ClientGUI cgui;
    private ServerGUI sgui;
    private int xOffset = 0;

    public void update(Game game)
    {
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].setLocation(250 + xOffset, buttons[i].getY());
            buttons[i].update(game);
        }

        if(screenChange)
        {
            xOffset -= 40;
            if(xOffset < -800)
            {
                xOffset = -800;
            }
        }

        if(cgui != null)
        {
            cgui.update();
        }
    }   
    
    public void render(Graphics g)
    {
        g.setColor(InteractiveColor.redBlue());
        g.fillRect(0, 0, 800, 600);

        g.setFont(new Font("Monospaced", Font.BOLD, 48));
        g.setColor(Color.WHITE);
        Algo.centerString(g, "Two Weeks Battle Royale", 0, 0, 800, 150);

        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].render(g);
        }

        if(sgui != null)
        {
            sgui.render(g, xOffset);
        }
        if(cgui != null)
        {
            cgui.render(g, xOffset);
        }
    }

    public void changeScreen(String newScreen)
    {
        screenChange = true;
        for(int i = 0; i < buttons.length; i++)
        {
            buttons[i].setActive(false);
        }
        if(newScreen.equals("Server"))
        {
            sgui = new ServerGUI();
        }
        else
        {
            cgui = new ClientGUI();
        }
    }

    public void warnCGUI()
    {
        if(cgui != null)
        {
            cgui.invalidated();
        }
    }
}
