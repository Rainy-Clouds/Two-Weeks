import java.awt.*;

public class Water extends Tile
{
    public Water(int x, int y)
    {
        super(x, y);

        super.getObstacles().add(new Obstacle(x * 200, y * 200, 200, 200));
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.CYAN);
        g.fillRect(super.getScreenX(p), super.getScreenY(p), 200, 200);
    }
}
