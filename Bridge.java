import java.awt.*;

public class Bridge extends Tile
{
    public Bridge(int x, int y)
    {
        super(x, y);
    }

    public void render(Graphics g, Player p)
    {
        g.drawImage(TextureLoader.getBridge(super.getAniNum()), super.getScreenX(p), super.getScreenY(p), null);
    }

    public String getType()
    {
        return "bridge";
    }
}
