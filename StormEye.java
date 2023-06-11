import java.awt.*;

public class StormEye
{
    private int x, y, width, height, mapW, mapH;

    public StormEye(int x, int y, int w, int h, int mapWidth, int mapHeight)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
        mapW = mapWidth;
        mapH = mapHeight;
    }

    public void updateBounds(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.RED);
        g.drawRect(Algo.getLocalX(x, p), Algo.getLocalY(y, p), width, height);
        g.setColor(new Color(250, 92, 255, 150));
        g.fillRect(Algo.getLocalX(-400, p), Algo.getLocalY(-400, p), mapW + 800, Math.abs(y + 400));
        g.fillRect(Algo.getLocalX(-400, p), Algo.getLocalY(y + height, p), mapW + 800, Math.abs((mapH + 400) - (height + y)));
        g.fillRect(Algo.getLocalX(-400, p), Algo.getLocalY(y, p), Math.abs(x + 400), height);
        g.fillRect(Algo.getLocalX(x + width, p), Algo.getLocalY(y, p), Math.abs((mapW + 400) - (x + width)), height);
    }
}