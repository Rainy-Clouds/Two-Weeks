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
}
