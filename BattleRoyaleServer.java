import java.awt.*;

public class BattleRoyaleServer
{
    private Processor processor;
    //private int zest;

    public BattleRoyaleServer(Game game)
    {
        processor = new Processor(game);
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
        g.setFont(new Font("Arial", Font.PLAIN, 36));
        Algo.centerString(g, "Players Alive: " + game.getServer().getEchoes().size(), 0, 0, 800, 600);
    }
}
