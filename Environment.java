import java.awt.*;

public class Environment 
{
    private Nametag[] tags = new Nametag[8];
    private Map map = new Map();
    private Rock test = new Rock(200, 200);
    
    public Environment()
    {
        for(int i = 0; i < tags.length; i++)
        {
            tags[i] = new Nametag();
        }
    }

    public Rock getRock()
    {
        return test;
    }

    public void update(Player player)
    {
        test.update(player);
    }

    public void render(Graphics g, Player player)
    {
        map.render(g, player);
        g.setColor(Color.BLUE);
        test.render(g, player);
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            if(i != Client.playerNum)
            {    
                g.fillRect(getLocalX(i, player), getLocalY(i, player), BattleRoyaleClient.playerSize, BattleRoyaleClient.playerSize);
                tags[i].setName(Data.names.get(i));
                tags[i].render(g, getLocalX(i, player) + BattleRoyaleClient.playerSize / 2, getLocalY(i, player) - 30);
            }
        }
    }

    // considering deleting for Algo 
    public int getLocalX(int index, Player p)
    {
        return Data.playerX.get(index) + (0 - p.getScreenX());
    }

    public int getLocalY(int index, Player p)
    {
        return Data.playerY.get(index) + (0 - p.getScreenY());
    }
}
