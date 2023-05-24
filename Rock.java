import java.awt.*;

public class Rock extends Obstacle
{
    public Rock(int x, int y, int size)
    {
        super(x, y, size, size);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.GRAY);
        g.fillRect((int)super.getRect().getX(), (int)super.getRect().getY(), (int)super.getRect().getWidth(), (int)super.getRect().getHeight());
    }
}
