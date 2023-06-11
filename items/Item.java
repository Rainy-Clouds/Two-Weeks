import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import java.time.*;

// IMPORTANT: When making a new item, make sure to look up "item rel" and edit everything you have to edit
public class Item 
{
    private BufferedImage icon, held, drop, anim;
    private boolean dropped, canDrop, animating, actOnce;
    private int globalX, globalY, delay, cooldown;
    private Rectangle dropRect = new Rectangle(0, 0, 0, 0);
    private String id;
    private Instant actionStart = Instant.now();

    // cooldown in milliseconds
    public Item(boolean droppable, String id, int cooldown)
    {
        canDrop = droppable;
        this.id = id;
        this.cooldown = cooldown;
    }

    // cooldown in milliseconds
    public Item(int x, int y, String id, int cooldown)
    {
        canDrop = true;
        dropped = true;
        globalX = x;
        globalY = y;
        this.id = id;
        this.cooldown = cooldown;
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

    public int getCooldown()
    {
        return cooldown;
    }

    public boolean getActOnce()
    {
        return actOnce;
    }

    public boolean animating()
    {
        return animating;
    }

    public void quitAnimating()
    {
        animating = false;
    }

    public long getCooltime()
    {
        return Duration.between(actionStart, Instant.now()).toMillis();
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

    public void setAnim(String path)
    {
        try
        {
            anim = ImageIO.read(new File("assets\\" + path));
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

    public void animate(int frames)
    {
        delay = frames;
        animating = true;
    }

    public void setActOnce(boolean newAct)
    {
        actOnce = newAct;
    } 

    public void renderIcon(Graphics g, int x, int y)
    {
        g.drawImage(icon, x, y, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 16));
        g.drawString(id, x + 30, y + 30); // play testing
    }

    public void update(Player p)
    {
        if(dropped)
        {
            dropRect.setBounds(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), drop.getWidth(), drop.getHeight());
        }

        if(animating)
        {
            delay--;
        }

        if(delay <= 0)
        {
            animating = false;
        }
    }

    public void render(Graphics g, Player p)
    {
        if(dropped)
        {
            g.drawImage(drop, Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), null);
        }
    }

    public void action() 
    {
        actOnce = true;
        actionStart = Instant.now();
    }

    public boolean canAct()
    {
        return !actOnce || Duration.between(actionStart, Instant.now()).toMillis() > cooldown;
    }

    public boolean touchingRect(Rectangle other)
    {
        return dropped && dropRect.intersects(other);
    }

    public BufferedImage getHeld()
    {
        if(animating)
        {
            return anim;
        }
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
