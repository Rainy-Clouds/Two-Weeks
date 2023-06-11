import java.awt.*;

public class Path extends Tile
{
    public Path(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.getPath(super.getAniNum()), super.getScreenX(p), super.getScreenY(p), null);
    }

    public String getType()
    {
        return "path";
    }
}
