import java.awt.*;

public class Grass extends Tile
{
    public Grass(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.getGrass(), super.getScreenX(p), super.getScreenY(p), null);
        super.render(g, p);
    }

    public String getType()
    {
        return "grass";
    }
}
