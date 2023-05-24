import java.awt.*;
import java.util.ArrayList;

public class Tile 
{
    private int gridX;
    private int gridY;
    private ArrayList<Obstacle> myObstacles = new ArrayList<Obstacle>();
    
    public Tile() {}

    public Tile(int x, int y)
    {
        gridX = x;
        gridY = y;
    }

    public void render(Graphics g, Player p) {}

    public int getScreenX(Player p)
    {
        return Algo.getLocalX(gridX * 200, p);
    }

    public int getScreenY(Player p)
    {
        return Algo.getLocalY(gridY * 200, p);
    }

    public ArrayList<Obstacle> getObstacles()
    {
        return myObstacles;
    }

    public void addObstacles(ArrayList<Obstacle> obs)
    {
        for(int i = 0; i < myObstacles.size(); i++)
        {
            obs.add(myObstacles.get(i));
        }
    }
}
