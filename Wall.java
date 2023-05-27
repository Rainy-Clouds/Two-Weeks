import java.awt.*;

public class Wall extends Obstacle
{
    public Wall(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(new Color(191, 121, 59));
        g.fillRect((int)super.getRect().getX(), (int)super.getRect().getY(), (int)super.getRect().getWidth(), (int)super.getRect().getHeight());
    }
}
