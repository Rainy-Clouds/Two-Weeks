import java.net.*;

public class Server implements Runnable
{
    private boolean running = false;
    // private int[] ports = {125, 128, 211, 309, 417, 617, 906, 1117};
    // private boolean[] activePorts = new boolean[8];

    public Server()
    {
        running = true;
        Thread thread = new Thread(this);
        thread.start();
        
        // Thread assignThread = new Thread(this);
        // assignThread.setName("Assigner");
        // assignThread.start();

        // Thread p1Thread = new Thread(this);
        // p1Thread.setName("0");
        // p1Thread.start();

        // Thread p2Thread = new Thread(this);
        // p2Thread.setName("1");
        // p2Thread.start();

        // try
        // {
        //     ServerSocket ss = new ServerSocket(0125);
        //     Socket s = ss.accept();
        //     DataInputStream dis = new DataInputStream(s.getInputStream());
        //     String str = (String) dis.readUTF();
        //     System.out.println("Client Says = " + str);
        //     ss.close();
        // }
        // catch(Exception e)
        // {
        //     System.out.println(e);
        // }
    }

    public void run()
    {
        ServerSocket svSoc = null;
        Socket soc = null;

        try
        {
            svSoc = new ServerSocket(0125);
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        int player = 0;
        while(running)
        {
            Data.playerCount = player;
            try
            {
                System.out.println(("" + InetAddress.getLocalHost()).split("/")[1]);
                soc = svSoc.accept();
                System.out.println("Client found!");
            }
            catch(Exception e)
            {
                System.out.println(e);
            }

            new EchoThread(soc, player).start();
            player++;
        }

        // while(running)
        // {
        //     try
        //     {
        //         if(Thread.currentThread().getName().equals("Assigner"))
        //         {
        //             ServerSocket ss = new ServerSocket(6564);
        //             Socket s = ss.accept();
        //             //s.setTcpNoDelay(true);
        //             PrintStream printer = new PrintStream(s.getOutputStream());
        //             for(int i = 0; i < ports.length; i++)
        //             {
        //                 if(!activePorts[i])
        //                 {
        //                     printer.println(String.valueOf(ports[i]));
        //                     activePorts[i] = true;
        //                     break;
        //                 }
        //             }
        //             printer.close();
        //             ss.close();

        //             Thread.sleep(5);
        //         }
        //         else
        //         {
        //             int index = Integer.valueOf(Thread.currentThread().getName());
        //             ServerSocket ss = new ServerSocket(ports[index]);
        //             Socket s = ss.accept();
        //             System.out.println(ports[index]);
        //             s.setTcpNoDelay(true);
        //             //PrintStream printer = new PrintStream(s.getOutputStream());
        //             DataInputStream dis = new DataInputStream(s.getInputStream());
        //             String str = (String) dis.readUTF();
        //             int[] data = Converter.stringToIntArr(str);
        //             Main.data[0 + 2 * index] = data[0 + 2 * index];
        //             Main.data[1 + 2 * index] = data[1 + 2 * index];
        //             //System.out.println("Client Says = " + str);
        //             //printer.println("ur a zest fest");
        //             ss.close();

        //             Thread.sleep(5);
        //         }
        //     }
        //     catch(Exception e)
        //     {
        //         e.printStackTrace(System.out);
        //     }
        // }
    }
}
