import java.util.ArrayList;
import java.util.Arrays;

public class Data 
{
    public static int playerCount;
    public static ArrayList<String> names = new ArrayList<String>();
    public static ArrayList<Integer> playerX = new ArrayList<Integer>();
    public static ArrayList<Integer> playerY = new ArrayList<Integer>();
    public static ArrayList<Double> playerRot = new ArrayList<Double>();
    public static ArrayList<String> playerHeld = new ArrayList<String>();
    public static ArrayList<Integer> playerHealth = new ArrayList<Integer>();

    public static ArrayList<Item> droppedItems = new ArrayList<Item>();
    public static ArrayList<Item> pickedUpItems = new ArrayList<Item>();

    public static void actOnItems(String actData)
    {
        String[] parsed = actData.split("-");
        //System.out.println(Arrays.toString(parsed));
        for(int i = 0; i < parsed.length; i += 2)
        {
            //System.out.println(i);
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
