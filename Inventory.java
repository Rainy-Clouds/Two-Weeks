import java.awt.*;

public class Inventory 
{
    private int spots;
    private int boxSize = 70;
    private int barWidth = 7;
    private int spacing = 15;
    private int activeSpot = 1;
    private Item[] items;

    public Inventory(int sp, Pickaxe pick)
    {
        spots = sp;
        items = new Item[sp];
        items[0] = pick;
    }

    public void update(Player p)
    {
        for(int i = 1; i <= spots; i++)
        {
            if(Panel.numMap[i])
            {
                activeSpot = i;
            }
        }

        // if(Panel.keyMap[4] && items[activeSpot - 1] != null)
        // {
        //     if(items[activeSpot - 1].canDrop())
        //     {
        //         items[activeSpot - 1].drop(p.getX(), p.getY());
        //         items[activeSpot - 1] = null;
        //     }
        // }
    }

    public Item dropItem(Player p)
    {
        if(items[activeSpot - 1] != null && items[activeSpot - 1].canDrop())
        {
            Item returnItem = items[activeSpot - 1];
            items[activeSpot - 1].drop(p.getX(), p.getY());
            items[activeSpot - 1] = null;
            return returnItem;
        }
        return null;
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < spots; i++)
        {
            if(i + 1 == activeSpot)
            {
                g.setColor(new Color(54, 199, 201, 150));
            }
            else
            {
                g.setColor(new Color(173, 173, 173, 150));
            }
            g.fillRect((400 - ((boxSize * spots + spacing * (spots - 1)) / 2)) + ((boxSize + spacing) * i), 600 - (boxSize + spacing), boxSize, boxSize);

            if(items[i] != null)
            {
                items[i].renderIcon(g, (400 - ((boxSize * spots + spacing * (spots - 1)) / 2)) + ((boxSize + spacing) * i), 600 - (boxSize + spacing));
                if(items[i].getCooldown() > 0 && !items[i].canAct())
                {
                    //System.out.println((int) (70 * (items[i].getCooltime() / (double) items[i].getCooldown())));
                    g.setColor(Color.RED);
                    g.fillRect((400 - ((boxSize * spots + spacing * (spots - 1)) / 2)) + ((boxSize + spacing) * i), 600 - (barWidth + spacing), (int) (70 * (items[i].getCooltime() / (double) items[i].getCooldown())), barWidth);
                }
            }

            g.setColor(Color.WHITE);
            g.setFont(new Font("Arial", Font.PLAIN, 16));
            g.drawString(String.valueOf(i + 1), (400 - ((boxSize * spots + spacing * (spots - 1)) / 2)) + ((boxSize + spacing) * i) + 3, 600 - (boxSize + spacing) + 16);
        }
    }

    public void scroll(int rot)
    {
        if(rot < 0)
        {
            activeSpot--;
        }
        else
        {
            activeSpot++;
        }

        if(activeSpot < 1)
        {
            activeSpot = spots;
        }
        if(activeSpot > spots)
        {
            activeSpot = 1;
        }
    }

    public Item currentItem()
    {
        return items[activeSpot - 1];
    }

    public boolean isFull()
    {
        for(int i = 0; i < items.length; i++)
        {
            if(items[i] == null)
            {
                return false;
            }
        }
        return true;
    }

    // returns 1 if the inventory is full, 0 otherwise
    public int pickUp(Item item)
    {
        if(!isFull())
        {
            if(items[activeSpot - 1] == null)
            {
                items[activeSpot - 1] = item;
            }
            else
            {
                for(int i = 0; i < items.length; i++)
                {
                    if(items[i] == null)
                    {
                        items[i] = item;
                        break;
                    }
                }
            }
            return 0;
        }
        return 1;
    }
}
