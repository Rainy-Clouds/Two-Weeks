import java.awt.*;
import java.io.File;
import java.util.*;

public class Map 
{
    private String[][] map;
    private ArrayList<Tile> tiles = new ArrayList<Tile>();

    public Map()
    {
        map = mapTextToArray("map1.txt");
        arrayToList(map);
    }

    public void update()
    {

    }

    public void render(Graphics g, Player p)
    {
        //drawArray(map, g, p);

        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).render(g, p);
        }
    }

    public String[][] mapTextToArray(String path)
    {
        try
        {
            Scanner valueGetter = new Scanner(new File("maps\\" + path));
            int width = valueGetter.nextLine().length();
            int height = 1;
            while(valueGetter.hasNextLine())
            {
                valueGetter.nextLine();
                height++;
            }
            valueGetter.close();

            Scanner textScan = new Scanner(new File("maps\\" + path));
            String[][] arr = new String[height][width];
            for(int r = 0; r < height; r++)
            {
                String str = textScan.nextLine();
                for(int c = 0; c < str.length(); c++)
                {
                    arr[r][c] = String.valueOf(str.charAt(c));
                }
            }
            textScan.close();
            return arr;
        }
        catch(Exception e)
        {
            System.out.println("Deleted or corrupt file!");
            return null;
        }
    }

    // public void drawArray(String[][] map, Graphics g, Player p)
    // {
    //     for(int i = 0; i < map.length; i++)
    //     {
    //         for(int j = 0; j < map[i].length; j++)
    //         {
    //             if(map[i][j].equals("g"))
    //             {
    //                 g.setColor(Color.GREEN);
    //                 g.fillRect(Algo.getLocalX(j * 200, p), Algo.getLocalY(i * 200, p), 200, 200);
    //             } 
    //             if(map[i][j].equals("w"))
    //             {
    //                 g.setColor(Color.CYAN);
    //                 g.fillRect(Algo.getLocalX(j * 200, p), Algo.getLocalY(i * 200, p), 200, 200);
    //             } 
    //         }
    //     }
    // }

    public void arrayToList(String[][] map)
    {
        for(int i = 0; i < map.length; i++)
        {
            for(int j = 0; j < map[i].length; j++)
            {
                if(map[i][j].equals("g"))
                {
                    tiles.add(new Grass(j, i));
                }
                if(map[i][j].equals("w"))
                {
                    tiles.add(new Water(j, i));
                }
            }
        }
    }

    public ArrayList<Obstacle> updateObstacles()
    {
        ArrayList<Obstacle> returnList = new ArrayList<Obstacle>();
        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).addObstacles(returnList);
        }
        return returnList;
    }
}
