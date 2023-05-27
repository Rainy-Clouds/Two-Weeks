import java.awt.*;

public class Algo 
{
    public static void centerString(Graphics g, String str, int x, int y, int w, int h)
    {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int strx = x + (w - metrics.stringWidth(str)) / 2;
        int stry = y + ((h - metrics.getHeight()) / 2) + metrics.getAscent();
        g.drawString(str, strx, stry);
    }    

    public static int getLocalX(int globalX, Player p)
    {
        return globalX + (0 - p.getScreenX());
    }

    public static int getLocalY(int globalY, Player p)
    {
        return globalY + (0 - p.getScreenY());
    }

    public static int randInt(int min, int max)
    {
        return (int) (Math.random() * (max - min + 1)) + min;
    }
}
