import java.awt.*;

public class Rock 
{
    private int globalX;
    private int globalY;
    private Rectangle rect;

    public Rock(int x, int y)
    {
        globalX = x;
        globalY = y;

        rect = new Rectangle(x, y, 100, 100);
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public void update(Player p)
    {
        rect.setBounds(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), 100, 100);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.GRAY);
        g.fillRect(Algo.getLocalX(globalX, p), Algo.getLocalY(globalY, p), 100, 100);
    }
}
