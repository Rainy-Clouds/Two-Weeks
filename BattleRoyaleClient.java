import java.awt.*;

public class BattleRoyaleClient
{
    public static int playerSize = 60;

    private Player player = new Player(0, 0, playerSize, playerSize);
    private Environment env = new Environment();
    private DeathScreen death = new DeathScreen(5);
    private WinScreen win = new WinScreen(5);

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
        player.getInventory().render(g);
        player.getHealth().render(g, 10, 10);

        if(Data.win)
        {
            win.render(g);
        }
        else if(player.getHealth().getHealth() <= 0)
        {
            death.render(g);
        }
    }
}