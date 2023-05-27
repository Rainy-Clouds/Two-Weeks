import java.awt.*;

public class Bush extends Obstacle
{
    public Bush(int x, int y, int type)
    {
        // type 1
        super(x, y, 200, 100);

        if(type == 2)
        {
            super.setSize(100, 200);
        }
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(new Color(50, 168, 82));
        g.fillRect((int)super.getRect().getX(), (int)super.getRect().getY(), (int)super.getRect().getWidth(), (int)super.getRect().getHeight());
    }
}
