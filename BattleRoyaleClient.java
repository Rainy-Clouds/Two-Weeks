import java.awt.*;

public class BattleRoyaleClient
{
    public static int playerSize = 60;

    private Player player = new Player(0, 0, playerSize, playerSize);
    private Environment env = new Environment();

    public Player getPlayer()
    {
        return player;
    }

    public Environment getEnvironment()
    {
        return env;
    }

    public void update(Game game)
    {
        player.update(game, env.getObstacles());
        env.update(player);
    }

    public void render(Graphics g)
    {
        env.renderMap(g, player);
        player.render(g);
        env.renderElse(g, player);
    }
}