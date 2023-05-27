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

    public void setSize(int w, int h)
    {
        width = w;
        height = h;
    }

    public void update(Player p)
    {
        rect.setBounds(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), width, height);
    }

    public int getScreenX(Player p)
    {
        return Algo.getLocalX(globalX, p);
    }

    public int getScreenY(Player p)
    {
        return Algo.getLocalY(globalY, p);
    }

    public void render(Graphics g, Player p)
    {
        // debugging
        // g.setColor(Color.PINK);
        // g.fillRect((int)getRect().getX(), (int)getRect().getY(), (int)getRect().getWidth(), (int)getRect().getHeight());
    }
}
