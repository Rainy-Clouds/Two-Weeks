import java.io.*;
import java.net.*;

public class EchoThread extends Thread
{
    private Socket soc;
    private boolean running;
    private int playerNum;

    public EchoThread(Socket s, int player)
    {
        soc = s;
        running = true;
        playerNum = player;
    }

    public void run()
    {
        while(running)
        {
            try
            {
                soc.setTcpNoDelay(true);
                //PrintStream printer = new PrintStream(s.getOutputStream());
                String str = null;
                while(str == null)
                {
                    BufferedReader dis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                    str = dis.readLine();
                }
                int[] data = Converter.stringToIntArr(str);
                Main.data[0 + 2 * playerNum] = data[0];
                Main.data[1 + 2 * playerNum] = data[1];
                //System.out.println("Client Says = " + str);
                //printer.println("ur a zest fest");

                Thread.sleep(5);
            }
            catch(Exception e)
            {
                e.printStackTrace(System.out);
            }
        }
    }
}
