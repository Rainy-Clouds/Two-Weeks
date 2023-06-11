import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class TextureLoader 
{
    private static BufferedImage[] water = new BufferedImage[5];
    private static BufferedImage grass;
    private static BufferedImage[] path = new BufferedImage[11];
    private static BufferedImage[] bridge = new BufferedImage[2];

    private static BufferedImage rock, wood, leaves, floor;
    private static BufferedImage[] wall = new BufferedImage[2];
    private static BufferedImage[] bush = new BufferedImage[2];

    public TextureLoader()
    {
        try
        {
            grass = ImageIO.read(new File("assets\\textures\\grass.png"));
            rock = ImageIO.read(new File("assets\\obstacles\\rock.png"));
            wood = ImageIO.read(new File("assets\\obstacles\\wood.png"));
            leaves = ImageIO.read(new File("assets\\obstacles\\leaves.png"));
            floor = ImageIO.read(new File("assets\\obstacles\\floor.png"));
            
            for(int i = 0; i < water.length; i++)
            {
                water[i] = ImageIO.read(new File("assets\\textures\\watertest.png")).getSubimage(200 * i, 0, 200, 200);
            }
            for(int i = 0; i < path.length; i++)
            {
                path[i] = ImageIO.read(new File("assets\\textures\\pathvar.png")).getSubimage(200 * i, 0, 200, 200);
            }
            for(int i = 0; i < bridge.length; i++)
            {
                bridge[i] = ImageIO.read(new File("assets\\textures\\bridge.png")).getSubimage(200 * i, 0, 200, 200);
            }
            for(int i = 0; i < wall.length; i++)
            {
                wall[i] = ImageIO.read(new File("assets\\obstacles\\wall" + i + ".png"));
            }
            for(int i = 0; i < bush.length; i++)
            {
                bush[i] = ImageIO.read(new File("assets\\obstacles\\bush" + i + ".png"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public static BufferedImage getWater()
    {
        return water[0];
    }

    public static BufferedImage getGrass()
    {
        return grass;
    }

    public static BufferedImage getPath(int idx)
    {
        return path[idx];
    }

    public static BufferedImage getBridge(int idx)
    {
        return bridge[idx];
    }

    public static BufferedImage rock()
    {
        return rock;
    }

    public static BufferedImage wood()
    {
        return wood;
    }

    public static BufferedImage leaves()
    {
        return leaves;
    }

    public static BufferedImage floor()
    {
        return floor;
    }

    public static BufferedImage wall(int idx)
    {
        return wall[idx];
    }

    public static BufferedImage bush(int idx)
    {
        return bush[idx];
    }
}
