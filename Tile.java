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

    public int getGridX()
    {
        return gridX;
    }

    public int getGridY()
    {
        return gridY;
    }

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
    
    /*
     * An Obstacle String will start with the letter of the obstacle, x, y, w and h all divided by -
     * N represents nothing, R represents rocks, T represents trees, B represents a bush
     * So an obstacle string for a Bush at (40, 40) within the tile and a width of 10 and height of 20 would look like:
     * B-40-40-10-20
     */
    public void spawnObstacles(String obsString)
    {
        String[] splitObs = obsString.split("-");

        if(splitObs[0].equals("N"))
        {
            return;
        }

        if(splitObs[0].equals("R"))
        {
            myObstacles.add(new Rock(gridX * 200 + Integer.valueOf(splitObs[1]), gridY * 200 + Integer.valueOf(splitObs[2]), Integer.valueOf(splitObs[3])));
        }
    }
}
