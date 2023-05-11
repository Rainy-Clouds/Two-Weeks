import java.util.ArrayList;

public class Converter 
{
    public static String intArrToString(int[] arr)
    {
        String str = "";
        if(arr.length > 1)
        {
            for(int i = 0; i < arr.length - 1; i++)
            {
                str += arr[i] + " ";
            }
            str += arr[arr.length - 1];
        }
        return str;
    }

    public static int[] stringToIntArr(String str)
    {
        String[] strArr = str.split(" ");
        int[] arr = new int[strArr.length];
        for(int i = 0; i < strArr.length; i++)
        {
            arr[i] = Integer.valueOf(strArr[i]);
        }
        return arr;
    }

    public static String intArrLToString(ArrayList<Integer> arr)
    {
        String str = "";
        if(arr.size() > 0)
        {
            for(int i = 0; i < arr.size() - 1; i++)
            {
                str += arr.get(i) + " ";
            }
            str += arr.get(arr.size() - 1);
        }
        return str;
    }

    public static ArrayList<Integer> stringToIntArrL(String str)
    {
        String[] strArr = str.split(" ");
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i = 0; i < strArr.length; i++)
        {
            arr.add(Integer.valueOf(strArr[i]));
        }
        return arr;
    }

    public static String stringListToString(ArrayList<String> arr)
    {
        String str = "";
        if(arr.size() > 0)
        {
            for(int i = 0; i < arr.size() - 1; i++)
            {
                str += arr.get(i) + " ";
            }
            str += arr.get(arr.size() - 1);
        }
        return str;
    }

    public static ArrayList<String> stringToStringList(String str)
    {
        String[] strArr = str.split(" ");
        ArrayList<String> arr = new ArrayList<String>();
        for(int i = 0; i < strArr.length; i++)
        {
            arr.add(strArr[i]);
        }
        return arr;
    }
}
