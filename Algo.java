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
}
