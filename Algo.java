import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

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

    // IMPORTANT: adds a border of 50, so you must subtract 25 from x and y when rendering
    public static BufferedImage rotateImage(BufferedImage image, double angle)
    {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads));
        double cos = Math.abs(Math.cos(rads));
        int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin) + 50;
        int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin) + 50;
        BufferedImage rotatedImage = new BufferedImage(w, h, image.getType());
        AffineTransform at = new AffineTransform();
        //at.translate(w / 2, h / 2);
        at.translate(image.getWidth() / 2 + 25, image.getHeight() / 2 + 25);
        at.rotate(rads, 0, 0);
        at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
        AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
        return rotateOp.filter(image, rotatedImage);
    }
}
