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

        g.setColor(Color.BLUE);
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            if(i != Client.playerNum)
            {
                g.fillRect(Data.playerX.get(i) + (0 - player.getScreenX()), Data.playerY.get(i) + (0 - player.getScreenY()), 40, 40);
            }
        }
    }
}