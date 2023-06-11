import java.awt.*;

public class Rock extends Obstacle
{
    public Rock(int x, int y, int type)
    {
        // type 1
        super(x, y, 100, 100);

        if(type == 2)
        {
            super.setSize(50, 50);
        }
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.rock(), (int)super.getRect().getX(), (int)super.getRect().getY(), null);
    }
}
