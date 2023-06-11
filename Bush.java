import java.awt.*;

public class Bush extends Obstacle
{
    private int type;

    public Bush(int x, int y, int type)
    {
        // type 1
        super(x, y, 200, 100);

        this.type = type;

        if(type == 2)
        {
            super.setSize(100, 200);
        }
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.bush(type - 1), (int)super.getRect().getX(), (int)super.getRect().getY(), null);
    }
}
