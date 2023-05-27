import java.awt.*;

public class Path extends Tile
{
    public Path(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(new Color(235, 188, 113));
        g.fillRect(super.getScreenX(p), super.getScreenY(p), 200, 200);
    }
}
