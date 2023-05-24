import java.awt.*;

public class Obstacle 
{
    private int globalX;
    private int globalY;
    private int width;
    private int height;
    private Rectangle rect;

    public Obstacle(int x, int y, int w, int h)
    {
        globalX = x;
        globalY = y;
        width = w;
        height = h;

        rect = new Rectangle(x, y, w, h);
    }

    public Obstacle() {}

    public Rectangle getRect()
    {
        return rect;
    }

    public void update(Player p)
    {
        rect.setBounds(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), width, height);
    }

    public void render(Graphics g, Player p)
    {

    }
}
