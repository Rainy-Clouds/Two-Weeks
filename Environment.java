import java.awt.*;

public class Environment 
{
    private Nametag[] tags = new Nametag[8];
    
    public Environment()
    {
        for(int i = 0; i < tags.length; i++)
        {
            tags[i] = new Nametag();
        }
    }

    public void render(Graphics g, Player player)
    {
        g.setColor(Color.BLUE);
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            if(i != Client.playerNum)
            {    
                g.fillRect(getLocalX(i, player), getLocalY(i, player), 40, 40);
                tags[i].setName(Data.names.get(i));
                tags[i].render(g, getLocalX(i, player) + 20, getLocalY(i, player) - 30);
            }
        }
    }

    public int getLocalX(int index, Player p)
    {
        return Data.playerX.get(index) + (0 - p.getScreenX());
    }

    public int getLocalY(int index, Player p)
    {
        return Data.playerY.get(index) + (0 - p.getScreenY());
    }
}
