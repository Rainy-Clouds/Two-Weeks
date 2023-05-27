public class ServerMap 
{
    private String[][] mapData;
    private String obsString;

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
                    base += "~" + j + " " + i + " " + generateObstacles() + " " + "null";
                }
            }
        }
        return base;
    }

    public String generateObstacles()
    {
        int num = Algo.randInt(0, 10);
        if(num > 5)
        {
            return "R-100-100-100-100";
        }
        return "N-0-0-0-0";
    }
}
