import java.awt.*;

public class Tree extends Obstacle
{
    public Tree(int x, int y)
    {
        super(x, y, 50, 50);
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.wood(), (int)super.getRect().getX(), (int)super.getRect().getY(), null);
        g.drawImage(TextureLoader.leaves(), (int)super.getRect().getX() - 50, (int)super.getRect().getY() - 50, null);
    }
}
