import java.awt.*;

public class ServerGUI 
{
    private String ip;
    private ServerList list;
    private Button start;

    public ServerGUI()
    {
        list = new ServerList();
        start = new Button("Start", 0, 0, "startbutton.png", "startbuttonT.png", "startbutton.png", 6);
    }

    public void setIP(String newIP)
    {
        ip = newIP;
    }

    public void update(Game game)
    {
        start.update(game);
    }

    public void render(Graphics g, int offset)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 72));
        Algo.centerString(g, Data.playerCount + "/8", 820 + offset, 0, 200, 500);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        Algo.centerString(g, "players joined", 820 + offset, 60, 200, 500);
        g.setFont(new Font("Arial", Font.PLAIN, 48));
        Algo.centerString(g, "Join code: " + ip, 800 + offset, 475, 800, 125);

        // g.setColor(Color.GRAY);
        // g.fillRect(1050 + offset, 135, 475, 340);
        list.render(g, 1050 + offset, 135);

        start.setLocation(845 + offset, 375);
        if(Data.playerCount <= 1)
        {
            start.setActive(false);
        }
        else
        {
            start.setActive(true);
            start.render(g);
        }
    }    
}
