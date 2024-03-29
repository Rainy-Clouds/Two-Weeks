import java.awt.*;

public class Wall extends Obstacle
{
    public Wall(int x, int y, int w, int h)
    {
        super(x, y, w, h);
    }

    public void render(Graphics g, Player p)
    {
        if(super.getRect().getWidth() > 100)
        {
            g.drawImage(TextureLoader.wall(0), (int)super.getRect().getX(), (int)super.getRect().getY(), null);
        }
        else
        {
            g.drawImage(TextureLoader.wall(1), (int)super.getRect().getX(), (int)super.getRect().getY(), null);
        }
    }
}
