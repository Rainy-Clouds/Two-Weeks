import java.awt.*;

public class Tree extends Obstacle
{
    public Tree(int x, int y)
    {
        super(x, y, 50, 50);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(new Color(168, 105, 50));
        g.fillRect((int)super.getRect().getX(), (int)super.getRect().getY(), (int)super.getRect().getWidth(), (int)super.getRect().getHeight());
        g.setColor(new Color(50, 168, 82, 100));
        g.fillRect((int)super.getRect().getX() - 50, (int)super.getRect().getY() - 50, (int)super.getRect().getWidth() + 100, (int)super.getRect().getHeight() + 100);
    }
}
