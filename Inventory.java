import java.awt.*;

public class Inventory 
{
    private int spots;
    private int boxSize = 70;
    private int spacing = 15;
    private int activeSpot = 1;
    private Item[] items;

    public Inventory(int sp)
    {
        spots = sp;
        items = new Item[sp];
        items[0] = new Pickaxe();
    }

    public void update()
    {
        for(int i = 1; i <= spots; i++)
        {
            if(Panel.numMap[i])
            {
                activeSpot = i;
            }
        }
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
}
