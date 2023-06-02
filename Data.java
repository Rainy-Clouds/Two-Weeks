import java.util.ArrayList;

public class Data 
{
    public static int playerCount;
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<Integer> playerX = new ArrayList<Integer>();
    public static ArrayList<Integer> playerY = new ArrayList<Integer>();
    public static ArrayList<Double> playerRot = new ArrayList<Double>();

    public static ArrayList<Item> droppedItems = new ArrayList<Item>();
    public static ArrayList<Item> pickedUpItems = new ArrayList<Item>();

    public static void actOnItems(String actData)
    {
        String[] parsed = actData.split("-");
        for(int i = 0; i < parsed.length / 2; i += 2)
        {
            if(parsed[i].equals("remove"))
            {
                if(getItemIndex(parsed[i + 1]) != -1)
                {
                    droppedItems.remove(getItemIndex(parsed[i + 1]));
                }
            }
            if(parsed[i].equals("add"))
            {
                String[] subparsed = parsed[i + 1].split("&");
                if(subparsed[0].equals("jug"))
                {
                    droppedItems.add(new Jug(Integer.valueOf(subparsed[2]), Integer.valueOf(subparsed[3]), subparsed[1]));
                }
            }
        }
    }

    public static int getItemIndex(String id)
    {
        for(int i = 0; i < droppedItems.size(); i++)
        {
            if(droppedItems.get(i).getID().equals(id))
            {
                return i;
            }
        }
        return -1;
    }
}
