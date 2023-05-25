import java.awt.*;

public class Bridge extends Tile
{
    public Bridge(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(super.getScreenX(p), super.getScreenY(p), 200, 200);
    }
}
