import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class BattleRoyaleServer
{
    private Processor processor;
    private BufferedImage chart;
    //private int zest;

    public BattleRoyaleServer(Game game)
    {
        processor = new Processor(game);
        try
        {
            chart = ImageIO.read(new File("assets\\serverchart.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public Processor getProcessor()
    {
        return processor;
    }

    public void update(Game game)
    {
        processor.update();
    }

    public void render(Graphics g, Game game)
    {
        g.setColor(InteractiveColor.redBlue());
        g.fillRect(0, 0, 800, 600);
        //Algo.centerString(g, "Players Alive: " + game.getServer().getEchoes().size(), 0, 0, 800, 600);

        g.drawImage(chart, 100, 100, null);

        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 48));
        Algo.centerString(g, "Current Standings", 0, 0, 800, 100);

        g.setFont(new Font("Arial", Font.BOLD, 26));
        Algo.centerString(g, "#", 110, 110, 63, 47);
        Algo.centerString(g, "Players", 178, 110, 409, 47);
        Algo.centerString(g, "Kills", 592, 110, 98, 47);

        g.setFont(new Font("Arial", Font.PLAIN, 24));
        for(int i = 0; i < Data.names.size(); i++)
        {
            if(Data.playerHealth.get(i) > 0)
            {
                g.setColor(Color.WHITE);
            }
            else
            {
                g.setColor(Color.RED);
            }
            Algo.centerString(g, "" + (i + 1), 110, 162 + i * 41, 63, 41);
            Algo.centerString(g, Data.names.get(i), 178, 162 + i * 41, 409, 41);
            Algo.centerString(g, "" + Data.playerKills.get(i), 592, 162 + i * 41, 98, 41);
        }
    }
}
