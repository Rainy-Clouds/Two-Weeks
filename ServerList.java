import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class ServerList
{
    private BufferedImage img;
    
    public ServerList()
    {
        try
        {
            img = ImageIO.read(new File("assets\\playerList.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(img, x, y, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 28));
        Algo.centerString(g, "Joined Players", x + 10, y + 10, 455, 40);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        for(int i = 0; i < 8; i++)
        {
            if(i < Data.names.size())
            {
                g.setColor(Color.WHITE);
                Algo.centerString(g, Data.names.get(i), x + 10, y + 50 + 35 * i, 455, 35);
            }
            else
            {
                g.setColor(Color.GRAY);
                Algo.centerString(g, "-----", x + 10, y + 50 + 35 * i, 455, 35);
            }
        }
    }
}
