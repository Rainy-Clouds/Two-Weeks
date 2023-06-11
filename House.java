import java.util.ArrayList;
import java.awt.*;

public class House 
{
    private ArrayList<Wall> walls = new ArrayList<Wall>();
    private int globalX, globalY;

    public House(int glx, int gly, int type)
    {
        globalX = glx;
        globalY = gly;
        if(type == 1)
        {
            walls.add(new Wall(glx, gly, 20, 200));
            walls.add(new Wall(glx, gly, 200, 20));
            walls.add(new Wall(glx + 180, gly, 20, 200));
        }
        else if(type == 2)
        {
            walls.add(new Wall(glx, gly, 20, 200));
            walls.add(new Wall(glx, gly, 200, 20));
            walls.add(new Wall(glx, gly + 180, 200, 20));
        }
        else if(type == 3)
        {
            walls.add(new Wall(glx, gly, 20, 200));
            walls.add(new Wall(glx, gly + 180, 200, 20));
            walls.add(new Wall(glx + 180, gly, 20, 200));
        }
        else if(type == 4)
        {
            walls.add(new Wall(glx + 180, gly, 20, 200));
            walls.add(new Wall(glx, gly, 200, 20));
            walls.add(new Wall(glx, gly + 180, 200, 20));
        }
        else if(type == 5)
        {
            walls.add(new Wall(glx, gly, 200, 20));
            walls.add(new Wall(glx, gly + 180, 200, 20));
        }
        else
        {
            walls.add(new Wall(glx, gly, 20, 200));
            walls.add(new Wall(glx + 180, gly, 20, 200));
        }
    }

    public ArrayList<Wall> getWalls()
    {
        return walls;
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.floor(), Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), null);
        for(Wall w : walls)
        {
            w.render(g, p);
        }
    }
}
