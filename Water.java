import java.awt.*;
import java.awt.image.BufferedImage;

public class Water extends Tile
{
    public Water(int x, int y)
    {
        super(x, y);

        super.getObstacles().add(new Obstacle(x * 200, y * 200, 200, 200));
    }

    public void render(Graphics g, Player p)
    {
        // LATER
        
        // int aniNum = Panel.frame / 5 % 40;
        // g.drawImage(TextureLoader.getWater().getSubimage(0, aniNum * 5, 200 - aniNum * 5, 200 - aniNum * 5), super.getScreenX(p) + aniNum * 5, super.getScreenY(p), null);
        // g.drawImage(TextureLoader.getWater().getSubimage(200 - aniNum * 5, aniNum * 5, aniNum * 5, 200 - aniNum * 5), super.getScreenX(p), super.getScreenY(p), null);
        // g.drawImage(TextureLoader.getWater().getSubimage(200 - aniNum * 5, 0, aniNum * 5, aniNum * 5), super.getScreenX(p), super.getScreenY(p) + 200 - aniNum * 5, null);
        // g.drawImage(TextureLoader.getWater().getSubimage(0, 0, 200 - aniNum * 5, aniNum * 5), super.getScreenX(p) + aniNum * 5, super.getScreenY(p) + 200 - aniNum * 5, null);
        g.drawImage(TextureLoader.getWater(), super.getScreenX(p), super.getScreenY(p), null);
    }

    public String getType()
    {
        return "water";
    }
}
