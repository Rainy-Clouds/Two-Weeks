import java.awt.*;

public class BattleRoyaleClient
{
    private Player player = new Player(0, 0, 40, 40);

    public Player getPlayer()
    {
        return player;
    }

    public void update(Game game)
    {
        player.update();
    }

    public void render(Graphics g)
    {
        player.render(g);
    }
}