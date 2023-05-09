import java.io.*;
import java.net.*;
//import java.util.Scanner;

public class Client implements Runnable
{
    private boolean running;
    private String ip;
    private Game game;
    private String name = "Player";

    public Client(String hostIp, Game g)
    {
        ip = hostIp;
        game = g;
        running = true;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void setName(String newName)
    {
        name = newName;
    }

    public void run()
    {
        Socket s = null;
        System.out.println("Looking for server");
        try
        {
            s = new Socket(ip/*"192.168.1.38"*/, 0125);
            s.setTcpNoDelay(true);
            s.setSendBufferSize(40000);
        }
        catch(Exception e)
        {
            game.removeClient();
            Thread.currentThread().stop();
        }

        if(s == null)
        {
            game.removeClient();
            Thread.currentThread().stop();
        }
        else
        {
            game.getBRMenu().getCGUI().connectionEstablished();
        }

        while(running)
        {
            //int[] locals = {0, 0};

            try
            {
                // Scanner scan = new Scanner(System.in);
                // String str = scan.nextLine();
                //scan.close();

                PrintStream dout = new PrintStream(s.getOutputStream());
                //BufferedReader reader = new BufferedReader(new InputStreamReader(s.getInputStream()));
                if(game.getState().equals("battle royale menu"))
                {
                    dout.println(name);
                }
                //System.out.println(reader.readLine());
                dout.flush();
                //dout.close();
                //reader.close();

                Thread.sleep(30);
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }

        // try
        // {
        //     s.close();
        // }
        // catch(Exception e)
        // {
        //     System.out.println(e);
        // }
    }
}