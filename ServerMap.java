import java.util.*;

public class ServerMap 
{
    private String[][] mapData;
    private String obsString;
    private ArrayList<String> usedIDs = new ArrayList<String>();

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
                if(mapData[i][j].equals("g"))
                {
                    base += "~" + j + " " + i + " " + generateObstacles() + " " + generateItems();
                }
            }
        }
        return base;
    }

    public String generateItems()
    {
        String id = "" + Algo.randInt(1, 9) + Algo.randInt(1, 9) + Algo.randInt(1, 9);
        while(usedIDs.contains(id))
        {
            id = "" + Algo.randInt(1, 9) + Algo.randInt(1, 9) + Algo.randInt(1, 9);
        }
        usedIDs.add(id);
        return "pistol-0-0-" + id;
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
