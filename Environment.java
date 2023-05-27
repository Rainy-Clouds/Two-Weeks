import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Environment 
{
    private Nametag[] tags = new Nametag[8];
    private Map map = new Map();
    private ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
    
    public Environment()
    {
        for(int i = 0; i < tags.length; i++)
        {
            tags[i] = new Nametag();
        }
    }

    public void loadTiles(String[] tileData)
    {
        //System.out.println(Arrays.toString(tileData));
        map.loadTiles(tileData);
        obs = map.updateObstacles();

        // obs.add(new Rock(200, 200, 100));
        // obs.add(new Rock(400, 400, 100));
        // obs.add(new Bush(100, 0));
        // obs.add(new Tree(500, 500));
    }

    public ArrayList<Obstacle> getObstacles()
    {
        return obs;
    }

    public void update(Player player)
    {
        for(int i = 0; i < obs.size(); i++)
        {
            obs.get(i).update(player);
        }
    }

    public void renderMap(Graphics g, Player player)
    {
        map.render(g, player);
    }

    public void renderElse(Graphics g, Player player)
    {
        for(int i = 0; i < obs.size(); i++)
        {
            if(obs.get(i).getScreenX(player) >= -200 && obs.get(i).getScreenX(player) <= 1000 && obs.get(i).getScreenY(player) >= -200 && obs.get(i).getScreenY(player) <= 800)
            {
                obs.get(i).render(g, player);
            }
        }
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            if(i != Client.playerNum)
            {    
                g.setColor(Color.BLUE);
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
