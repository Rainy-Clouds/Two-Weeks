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
        try
        {
            double rads = Math.toRadians(angle);
            double sin = Math.abs(Math.sin(rads));
            double cos = Math.abs(Math.cos(rads));
            int w = (int) Math.floor(image.getWidth() * cos + image.getHeight() * sin) + 50;
            int h = (int) Math.floor(image.getHeight() * cos + image.getWidth() * sin) + 50;
            BufferedImage rotatedImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            //at.translate(w / 2, h / 2);
            at.translate(image.getWidth() / 2 + 25, image.getHeight() / 2 + 25);
            at.rotate(rads, 0, 0);
            at.translate(-image.getWidth() / 2, -image.getHeight() / 2);
            AffineTransformOp rotateOp = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            return rotateOp.filter(image, rotatedImage);
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
            return null;
        }
    }

    public static double standardizeDiagonalAngle(double angle)
    {
        while(!(angle >= 45 && angle < 405))
        {
            if(angle < 45)
            {
                angle += 360;
            }
            else
            {
                angle -= 360;
            }
        }
        return angle;
    }

    public static String numText(int num)
    {
        if(num == 1)
        {
            return "1st";
        }
        else if(num == 2)
        {
            return "2nd";
        }
        else if(num == 3)
        {
            return "3rd";
        }
        else
        {
            return num + "th";
        }
    }

    public static int round(double num)
    {
        if(num >= 0)
        {
            return (int) (num + 0.5);
        }
        else
        {
            return (int) (num - 0.5);
        }
    }

    public static boolean anyTrue(boolean[] arr)
    {
        for(boolean b : arr)
        {
            if(b)
            {
                return true;
            }
        }
        return false;
    }
}
