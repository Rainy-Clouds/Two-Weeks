import java.awt.*;

public class BattleRoyaleClient
{
    private Player player = new Player(0, 0, 40, 40);
    private Environment env = new Environment();

    public Player getPlayer()
    {
        return player;
    }

    public void update(Game game)
    {
        player.update(game);
    }

    public void render(Graphics g)
    {
        env.render(g, player);
        player.render(g);
    }
}