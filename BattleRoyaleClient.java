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

    public void update(Game game)
    {
        env.update(player);
        player.update(game, env.getObstacles());
    }

    public void render(Graphics g)
    {
        env.render(g, player);
        player.render(g);
    }
}