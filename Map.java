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
            if(tiles.get(i).getScreenX(p) >= -200 && tiles.get(i).getScreenX(p) <= 1000 && tiles.get(i).getScreenY(p) >= -200 && tiles.get(i).getScreenY(p) <= 800)
            {
                tiles.get(i).render(g, p);
            }
        }
    }

    public static String[][] mapTextToArray(String path)
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
                if(map[i][j].equals("b"))
                {
                    tiles.add(new Bridge(j, i));
                }
                if(map[i][j].equals("p"))
                {
                    tiles.add(new Path(j, i));
                }
            }
        }
    }

    public ArrayList<Obstacle> updateObstacles()
    {
        ArrayList<Obstacle> returnList = new ArrayList<Obstacle>();

        //tiles.get(1).giveMeHouse();

        // tiles
        for(int i = 0; i < tiles.size(); i++)
        {
            tiles.get(i).addObstacles(returnList);
        }

        // borders
        returnList.add(new Obstacle(-50, 0, 50, map.length * 200));
        returnList.add(new Obstacle(0, -50, map[0].length * 200, 50));
        returnList.add(new Obstacle(map[0].length * 200, 0, 50, map.length * 200));
        returnList.add(new Obstacle(0, map.length * 200, map[0].length * 200, 50));

        return returnList;
    }

    public void loadTiles(String[] tileData)
    {
        for(String str : tileData)
        {
            String[] parsed = str.split(" ");
            //System.out.println(Arrays.toString(parsed));
            getTileAt(Integer.valueOf(parsed[0]), Integer.valueOf(parsed[1])).spawnObstacles(parsed[2]);
            spawnItems(Integer.valueOf(parsed[0]), Integer.valueOf(parsed[1]), parsed[3]);
        }
    }

    public void spawnItems(int gridX, int gridY, String str)
    {
        String[] parsed = str.split("-");
        if(parsed[0].equals("jug"))
        {
            Data.droppedItems.add(new Jug(gridX * 200 + Integer.valueOf(parsed[1]), gridY * 200 + Integer.valueOf(parsed[2]), parsed[3]));
        }
    }

    public Tile getTileAt(int gridx, int gridy) // HIT THE GRIDDY
    {
        for(int i = 0; i < tiles.size(); i++)
        {
            if(tiles.get(i).getGridX() == gridx && tiles.get(i).getGridY() == gridy)
            {
                return tiles.get(i);
            }
        }
        return null;
    }
}
