import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Item 
{
    private BufferedImage icon, held, drop;
    private boolean dropped, canDrop;
    private int globalX, globalY;
    private Rectangle dropRect = new Rectangle(0, 0, 0, 0);
    private String id;

    public Item(boolean droppable, String id)
    {
        canDrop = droppable;
        this.id = id;
    }

    public Item(int x, int y, String id)
    {
        canDrop = true;
        dropped = true;
        globalX = x;
        globalY = y;
        this.id = id;
    }

    public String getID()
    {
        return id;
    }

    public int getX()
    {
        return globalX;
    }

    public int getY()
    {
        return globalY;
    }

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

    public void setDrop(String path)
    {
        try
        {
            drop = ImageIO.read(new File("assets\\" + path));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void drop(int x, int y)
    {
        if(canDrop)
        {
            dropped = true;
            globalX = x;
            globalY = y;
        }
    }

    public void pickedUp()
    {
        dropped = false;
    }

    public void renderIcon(Graphics g, int x, int y)
    {
        g.drawImage(icon, x, y, null);
    }

    public void update(Player p)
    {
        if(dropped)
        {
            dropRect.setBounds(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), drop.getWidth(), drop.getHeight());
        }
    }

    public void render(Graphics g, Player p)
    {
        if(dropped)
        {
            g.drawImage(drop, Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), null);
        }
    }

    public boolean touchingRect(Rectangle other)
    {
        return dropped && dropRect.intersects(other);
    }

    public BufferedImage getHeld()
    {
        return held;
    }

    public boolean canDrop()
    {
        return canDrop;
    }

    public String getType()
    {
        return "item";
    }
}
