import java.awt.*;

public class Grass extends Tile
{
    public Grass(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.setColor(Color.GREEN);
        g.fillRect(super.getScreenX(p), super.getScreenY(p), 200, 200);
    }
}
