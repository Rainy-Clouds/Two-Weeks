import java.io.*;
import java.net.*;
import java.util.Arrays;

public class EchoThread extends Thread
{
    private Socket soc;
    private boolean running;
    private int playerNum;
    private Game game;
    private String message;
    private String itemUpdates;

    public EchoThread(Socket s, Game g, int player)
    {
        game = g;
        soc = s;
        running = true;
        playerNum = player;
        Data.names.add("Player");
    }

    public void relayMessage(String msg)
    {
        message = msg;
    }

    public void updateItems(String msg)
    {
        if(itemUpdates == null)
        {
            itemUpdates = msg;
        }
        else
        {
            itemUpdates += "-" + msg;
        }
    }

    public void run()
    {
        while(running)
        {
            try
            {
                soc.setTcpNoDelay(true);
                PrintStream printer = new PrintStream(soc.getOutputStream());

                String str = null;
                //while(str == null)
                //{
                BufferedReader dis = new BufferedReader(new InputStreamReader(soc.getInputStream()));
                str = dis.readLine();
                if(str != null)
                {
                    //}
                    String[] parsed = str.split(" ");

                    System.out.println(Arrays.toString(parsed));
                    if(game.getState().equals("battle royale menu") && parsed[0].equals("A"))
                    {
                        Data.names.set(playerNum, parsed[1]);
                        if(message != null && message.equals("start"))
                        {
                            printer.println(message + "~" + playerNum + game.getServer().getMap().getObstacleString());
                        }
                        else
                        {
                            printer.println(message + "~" + playerNum);
                        }
                        if(message != null)
                        {
                            message = null;
                        }
                    }
                    else if(game.getState().equals("battle royale") && parsed[0].equals("B"))
                    {
                        Data.playerX.set(playerNum, Integer.valueOf(parsed[1]));
                        Data.playerY.set(playerNum, Integer.valueOf(parsed[2]));
                        Data.playerRot.set(playerNum, Double.valueOf(parsed[3]));
                        if(!parsed[4].equals("null"))
                        {
                            game.getServer().updateItems(parsed[4]);
                        }
                        Data.playerHeld.set(playerNum, parsed[5]);
                        game.getBRServer().getProcessor().playerAct(parsed[6], playerNum);
                        //System.out.println(Converter.intArrLToString(Data.playerX) + "~" + Converter.intArrLToString(Data.playerY));
                        printer.println(Converter.stringListToString(Data.names) + "~" + Converter.intArrLToString(Data.playerX) + "~" + Converter.intArrLToString(Data.playerY) + "~" + Converter.doubleArrLToString(Data.playerRot) + "~" + itemUpdates + "~" + Converter.stringListToString(Data.playerHeld) + "~" + Data.playerHealth.get(playerNum));
                        if(itemUpdates != null)
                        {
                            itemUpdates = null;
                        }
                    }
                    else
                    {
                        printer.println("");
                    }
                    // int[] data = Converter.stringToIntArr(str);
                    // Main.data[0 + 2 * playerNum] = data[0];
                    // Main.data[1 + 2 * playerNum] = data[1];
                    //System.out.println("Client Says = " + str);
                    //printer.close();
                }

                Thread.sleep(5);
            }
            catch(Exception e)
            {
                e.printStackTrace(System.out);
            }
        }
    }
}
