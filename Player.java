import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Player 
{
    private int x, y, width, height;
    private double angle;
    private Nametag tag = new Nametag();
    private Rectangle rect, rectL, rectR, rectT, rectB;
    private BufferedImage img;

    private Pickaxe pick = new Pickaxe();
    private Inventory inv = new Inventory(4, pick);
    private String itemUpdate;
    
    public Player(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;

        rect = new Rectangle(400 - width / 2, 300 - height / 2, w, h);
        rectL = new Rectangle((int) rect.getX() - 1, (int) rect.getY(), 1, height);
        rectR = new Rectangle((int) (rect.getX() + rect.getWidth()), (int) rect.getY(), 1, height);
        rectT = new Rectangle((int) rect.getX(), (int) rect.getY() - 1, width, 1);
        rectB = new Rectangle((int) rect.getX(), (int) (rect.getY() + rect.getHeight()), width, 1);

        try
        {
            img = ImageIO.read(new File("assets\\betaplayer.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public double getAng()
    {
        return angle;
    }

    public Inventory getInventory()
    {
        return inv;
    }

    public String itemUpdate()
    {
        String returnStr = itemUpdate;
        itemUpdate = null;
        return returnStr;
    }

    public int getScreenX()
    {
        return (x + width / 2) - 400;
    }

    public int getScreenY()
    {
        return (y + height / 2) - 300;
    }

    public void update(Game game, ArrayList<Obstacle> obs)
    {
        keyActions(obs);
        tag.setName(game.getClient().getName());

        inv.update(this);
        pick.update(this);

        angle = Math.toDegrees(Math.atan(((double)Panel.mousex - 400) / (-1 * ((double)Panel.mousey - 300))));
        if((double)Panel.mousey - 300 > 0)
        {
            angle += 180;
        }
        if(Math.abs(angle) == 90)
        {
            angle *= -1;
        }
        // try
        // {
        //     angle = Math.toDegrees(Math.atan(((double)Panel.mousex - 400) / (-1 * ((double)Panel.mousey - 300))));
        //     if((double)Panel.mousey - 300 > 0)
        //     {
        //         angle += 180;
        //     }
        // }
        // catch(Exception e)
        // {
        //     System.out.println("divide by zero");
        //     angle = -90;
        // }
        //System.out.println(angle);
        //System.out.println(x + ", " + y);

        for(int i = 0; i < Data.droppedItems.size(); i++)
        {
            if(Data.droppedItems.get(i).touchingRect(rect) && Panel.keyMap[5])
            {
                if(inv.pickUp(Data.droppedItems.get(i)) == 0)
                {
                    if(itemUpdate == null)
                    {
                        itemUpdate = "remove-" + Data.droppedItems.get(i).getID();
                    }
                    else
                    {
                        itemUpdate += "-remove-" + Data.droppedItems.get(i).getID();
                    }
                    Data.droppedItems.get(i).pickedUp();
                    Data.pickedUpItems.add(Data.droppedItems.get(i));
                    Data.droppedItems.remove(i);
                }
            }
        }

        // this code sucks man what am I doing with my life
        if(Panel.keyMap[4])
        {
            Item droppedItem = inv.dropItem(this);
            if(droppedItem != null)
            {
                if(itemUpdate == null)
                {
                    itemUpdate = "add-" + droppedItem.getType() + "&" + droppedItem.getID() + "&" + droppedItem.getX() + "&" + droppedItem.getY();
                }
                else
                {
                    itemUpdate += "-add-" + droppedItem.getType() + "&" + droppedItem.getID() + "&" + droppedItem.getX() + "&" + droppedItem.getY();
                }

                if(Data.getItemIndex(droppedItem.getID()) != -1)
                {
                    Data.pickedUpItems.remove(Data.getItemIndex(droppedItem.getID()));
                }
            }
        }
    }

    public void keyActions(ArrayList<Obstacle> obs)
    {
        if(Panel.keyMap[0] && !touchingArray(rectT, obs))
        {
            y -= 5;
        }
        if(Panel.keyMap[1] && !touchingArray(rectB, obs))
        {
            y += 5;
        }
        if(Panel.keyMap[2] && !touchingArray(rectL, obs))
        {
            x -= 5;
        }
        if(Panel.keyMap[3] && !touchingArray(rectR, obs))
        {
            x += 5;
        }
    }

    public boolean touchingArray(Rectangle rec, ArrayList<Obstacle> obs)
    {
        for(int i = 0; i < obs.size(); i++)
        {
            if(rec.intersects(obs.get(i).getRect()))
            {
                return true;
            }
        }
        return false;
    }

    public void renderRect(Rectangle r, Graphics g)
    {
        g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
    }

    public void render(Graphics g)
    {
        pick.render(g, this);
        //tag.render(g, 400, 250);
        g.setColor(Color.RED);
        //g.fillRect(400 - width / 2, 300 - height / 2, width, height);
        if(inv.currentItem() != null)
        {
            g.drawImage(Algo.rotateImage(inv.currentItem().getHeld(), angle), 400 - 60 - 25, 300 - 60 - 25, null);
        }
        g.drawImage(Algo.rotateImage(img, angle), 400 - width / 2 - 25, 300 - height / 2 - 25, null);
        // g.setColor(Color.PINK);
        // renderRect(rectB, g);
        // renderRect(rectL, g);
        // renderRect(rectR, g);
        // renderRect(rectT, g);
    }
}
