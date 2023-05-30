import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Item 
{
    private BufferedImage icon;
    private BufferedImage held;

    public void setIcon(String path)
    {
        try
        {
            icon = ImageIO.read(new File("assets\\" + path));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void setHeld(String path)
    {
        try
        {
            held = ImageIO.read(new File("assets\\" + path));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void renderIcon(Graphics g, int x, int y)
    {
        g.drawImage(icon, x, y, null);
    }

    public BufferedImage getHeld()
    {
        return held;
    }
}
