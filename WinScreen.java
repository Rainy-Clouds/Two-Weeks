import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class WinScreen 
{
    private int alpha;
    private int speed;
    private BufferedImage blevins;

    public WinScreen(int sp)
    {
        speed = sp;

        try
        {
            blevins = ImageIO.read(new File("assets\\ninja.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void render(Graphics g)
    {
        if(alpha < 150)
        {
            alpha += speed;
        }
        g.setColor(new Color(105, 215, 245, alpha));
        g.fillRect(0, 0, 800, 600);

        g.setFont(new Font("Arial", Font.PLAIN, 72));
        g.setColor(Color.WHITE);
        Algo.centerString(g, "VICTORY ROYALE!", 0, 0, 800, 475);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        Algo.centerString(g, "Ended in 1st place with " + Data.kills + " kill(s)", 0, 0, 800, 600);
        Algo.centerString(g, "Tyler \"Ninja\" Blevins approves", 0, 0, 800, 675);

        g.drawImage(blevins, 335, 420, null);
    }
}
