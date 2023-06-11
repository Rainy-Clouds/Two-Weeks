import java.util.*;

public class ServerMap 
{
    private String[][] mapData;
    private String obsString;
    private ArrayList<String> usedIDs = new ArrayList<String>();
    private ArrayList<int[]> spawnCoords = new ArrayList<int[]>();

    public ServerMap(String path)
    {
        mapData = Map.mapTextToArray(path);
        obsString = generateObstacleString();
    }

    public String getObstacleString()
    {
        return obsString;
    }

    public String generateObstacleString()
    {
        String base = "";
        for(int i = 0; i < mapData.length; i++)
        {
            for(int j = 0; j < mapData[i].length; j++)
            {
                if(mapData[i][j].equals("g") || mapData[i][j].equals("i"))
                {
                    base += "~" + j + " " + i + " " + generateObstacles() + " " + generateItems(mapData[i][j]);
                }
                if(mapData[i][j].equals("s"))
                {
                    int[] coords = {j * 200 + 70, i * 200 + 70};
                    spawnCoords.add(coords);
                }
            }
        }
        return base;
    }

    public int[] getSpawnCoords(int playerNum)
    {
        return spawnCoords.get(playerNum);
    }

    public String generateItems(String gi)
    {
        if(gi.equals("i"))
        {
            String[] items = {"pistol", "smg", "shotgun", "rifle"};
            return items[Algo.randInt(0, items.length - 1)] + "-" + Algo.randInt(0, 140) + "-" + Algo.randInt(0, 140) + "-" + getID();
        }
        else
        {
            if(Algo.randInt(0, 6) == 2)
            {
                int num = Algo.randInt(1, 21);
                String item = "";
                if(num >= 1 && num <= 3)
                {
                    item = "smg";
                }
                else if(num >= 4 && num <= 6)
                {
                    item = "shotgun";
                }
                else if(num >= 7 && num <= 9)
                {
                    item = "rifle";
                }
                else if(num >= 10 && num <= 12)
                {
                    item = "jug";
                }
                else if(num >= 13 && num <= 16)
                {
                    item = "pistol";
                }
                else
                {
                    item = "burger";
                }
                return item + "-" + Algo.randInt(0, 140) + "-" + Algo.randInt(0, 140) + "-" + getID();
            }
        }
        return null;
    }

    public String getID()
    {
        String id = "" + Algo.randInt(1, 9) + Algo.randInt(1, 9) + Algo.randInt(1, 9);
        while(usedIDs.contains(id))
        {
            id = "" + Algo.randInt(1, 9) + Algo.randInt(1, 9) + Algo.randInt(1, 9);
        }
        usedIDs.add(id);
        return id;
    }

    public String generateObstacles()
    {
        int num = Algo.randInt(1, 35);
        if(num == 1) // house
        {
            return "H-0-0-" + Algo.randInt(1, 6);
        }
        if(num >= 2 && num <= 3) // rock
        {
            return "R-" + (Algo.randInt(0, 4) * 25) + "-" + (Algo.randInt(0, 4) * 25) + "-1";
        }
        if(num >= 5 && num <= 6) // bush
        {
            int subnum = Algo.randInt(1, 2);
            if(subnum == 1)
            {
                return "B-0-" + (Algo.randInt(0, 2) * 50) + "-" + subnum;
            }
            else
            {
                return "B-" + (Algo.randInt(0, 2) * 50) + "-0-" + subnum;
            }
        }
        if(num >= 8 && num <= 9) // tree
        {
            return "T-" + (50 + Algo.randInt(0, 2) * 25) + "-" + (50 + Algo.randInt(0, 2) * 25) + "-1";
        }
        return "N-0-0-0"; // nothing
    }
}
