import java.awt.*;
import java.util.ArrayList;

public class Tile 
{
    private int gridX;
    private int gridY;
    private ArrayList<Obstacle> myObstacles = new ArrayList<Obstacle>();
    private House house;
    private int aniNum;
    
    public Tile() {}

    public Tile(int x, int y)
    {
        gridX = x;
        gridY = y;
    }

    public void render(Graphics g, Player p) 
    {
        if(house != null)
        {
            house.render(g, p);
        }
    }

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

    public int getAniNum()
    {
        return aniNum;
    }

    public void setAniNum(int num)
    {
        aniNum = num;
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
        if(house != null)
        {
            ArrayList<Wall> walls = house.getWalls();
            for(int i = 0; i < walls.size(); i++)
            {
                obs.add(walls.get(i));
            }
        }
    }
    
    /*
     * An Obstacle String will start with the letter of the obstacle, x, y, and type (only applicable for some obstacles) all divided by -
     * N represents nothing, R represents rocks, T represents trees, B represents a bush, H represents a house
     * So an obstacle string for a Bush at (40, 40) within the tile of type 2 would look like:
     * B-40-40-2
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
        else if(splitObs[0].equals("T"))
        {
            myObstacles.add(new Tree(gridX * 200 + Integer.valueOf(splitObs[1]), gridY * 200 + Integer.valueOf(splitObs[2])));
        }
        else if(splitObs[0].equals("B"))
        {
            myObstacles.add(new Bush(gridX * 200 + Integer.valueOf(splitObs[1]), gridY * 200 + Integer.valueOf(splitObs[2]), Integer.valueOf(splitObs[3])));
        }
        else if(splitObs[0].equals("H"))
        {
            //giveMeHouse();
            house = new House(gridX * 200, gridY * 200, Integer.valueOf(splitObs[3]));
        }
    }

    public String getType()
    {
        return "tile";
    }
}
