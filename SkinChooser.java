import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class SkinChooser 
{
    private int currentSkin;
    public static String[] skins = {"jonesy", "quantavious", "ramirez", "karl", "pete", "jesus", "lorax", "ariana", "beta"};
    private String[] names = {"Jonesy", "Big Quantavious", "Ramirez", "Karl Marx", "Possibly Platty Pete", "Jesus Christ", "The Lorax", "Ariana Grande", "Beta Sprite"};
    private String[] desc = {"Buddy, I think you're in the wrong game", "He really likes Popeyes, for no particular reason", "The defaultest default", "Will catch a dub for the proletariat", "Pale and male but not sta-- wait, this seems familiar", "He's back and he's NOT playing this time", "He speaks for the trees, and he cranks 90s", "The crossover event we've all been waiting for", "idk man I was running out of ideas"};
    public static BufferedImage[] imgs = new BufferedImage[skins.length];
    private boolean moved;

    public SkinChooser()
    {
        try
        {
            for(int i = 0; i < skins.length; i++)
            {
                imgs[i] = ImageIO.read(new File("assets\\skins\\" + skins[i] + ".png"));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void update()
    {
        if(!moved)
        {
            if(Panel.arrowMap[2])
            {
                currentSkin--;
                if(currentSkin < 0)
                {
                    currentSkin = skins.length - 1;
                }
                moved = true;
            }
            if(Panel.arrowMap[3])
            {
                currentSkin++;
                if(currentSkin >= skins.length)
                {
                    currentSkin = 0;
                }
                moved = true;
            }
        }

        if(moved && !Panel.arrowMap[2] && !Panel.arrowMap[3])
        {
            moved = false;
        }

        Player.skinNum = currentSkin;
    }

    public void render(Graphics g, int offset)
    {
        g.drawImage(imgs[currentSkin].getScaledInstance(120, 120, Image.SCALE_DEFAULT), 340 + 1600 + offset, 325, null);
        g.drawImage(imgs[nextSkin()], 620 + 1600 + offset, 355, null);
        g.drawImage(imgs[lastSkin()], 120 + 1600 + offset, 355, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 36));
        Algo.centerString(g, "Skin Locker", 0 + offset + 1600, 250, 800, 50);
        g.setFont(new Font("Arial", Font.PLAIN, 36));
        Algo.centerString(g, names[currentSkin], 0 + offset + 1600, 470, 800, 50);
        g.setFont(new Font("Arial", Font.ITALIC, 24));
        Algo.centerString(g, desc[currentSkin], 0 + offset + 1600, 520, 800, 50);
    }

    public int nextSkin()
    {
        if(currentSkin + 1 >= skins.length)
        {
            return 0;
        }
        else
        {
            return currentSkin + 1;
        }
    }

    public int lastSkin()
    {
        if(currentSkin - 1 < 0)
        {
            return skins.length - 1;
        }
        else
        {
            return currentSkin - 1;
        }
    }
}
