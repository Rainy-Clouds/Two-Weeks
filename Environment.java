import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Environment 
{
    private Nametag[] tags = new Nametag[8];
    private TextureLoader tx = new TextureLoader();
    private Map map = new Map();
    private ArrayList<Obstacle> obs = new ArrayList<Obstacle>();
    private BufferedImage otherimg;

    private String[] itemNames = {"pickaxe", "jug", "pistol"}; // item rel
    private BufferedImage[] helds = new BufferedImage[itemNames.length];
    private BufferedImage[] heldsAni = new BufferedImage[itemNames.length];

    private String[] bulletNames = {"pistol"};
    private BufferedImage[] bullets = new BufferedImage[bulletNames.length];
    
    public Environment()
    {
        for(int i = 0; i < tags.length; i++)
        {
            tags[i] = new Nametag();
        }

        // item rel
        try 
        {
            otherimg = ImageIO.read(new File("assets\\betaother.png"));

            helds[0] = ImageIO.read(new File("assets\\betaheld.png"));
            helds[1] = ImageIO.read(new File("assets\\betaheld2.png"));
            helds[2] = ImageIO.read(new File("assets\\betaheld3.png"));

            heldsAni[0] = ImageIO.read(new File("assets\\betaheldanim.png"));
            heldsAni[1] = ImageIO.read(new File("assets\\betaheld2.png"));
            heldsAni[2] = ImageIO.read(new File("assets\\betaheldanim3.png"));

            bullets[0] = ImageIO.read(new File("assets\\betabullet.png"));
        }
        catch(Exception e)
        {
            e.printStackTrace(System.out);
        }
    }

    public void loadTiles(String[] tileData)
    {
        //System.out.println(Arrays.toString(tileData));
        map.loadTiles(tileData);
        obs = map.updateObstacles();

        // obs.add(new Rock(200, 200, 100));
        // obs.add(new Rock(400, 400, 100));
        // obs.add(new Bush(100, 0));
        // obs.add(new Tree(500, 500));

        
    }

    public ArrayList<Obstacle> getObstacles()
    {
        return obs;
    }

    public void update(Player player)
    {
        for(int i = 0; i < obs.size(); i++)
        {
            obs.get(i).update(player);
        }

        for(int i = 0; i < Data.droppedItems.size(); i++)
        {
            Data.droppedItems.get(i).update(player);
        }
    }

    public void renderMap(Graphics g, Player player)
    {
        map.render(g, player);

        for(int i = 0; i < Data.droppedItems.size(); i++)
        {
            Data.droppedItems.get(i).render(g, player);
        }

        drawBullets(g, player);
    }

    public void renderElse(Graphics g, Player player)
    {
        for(int i = 0; i < obs.size(); i++)
        {
            if(obs.get(i).getScreenX(player) >= -200 && obs.get(i).getScreenX(player) <= 1000 && obs.get(i).getScreenY(player) >= -200 && obs.get(i).getScreenY(player) <= 800)
            {
                obs.get(i).render(g, player);
            }
        }
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            if(i != Client.playerNum && !Data.playerHeld.get(i).equals("dead"))
            {    
                //g.setColor(Color.BLUE);
                //g.fillRect(getLocalX(i, player), getLocalY(i, player), BattleRoyaleClient.playerSize, BattleRoyaleClient.playerSize);
                drawHeld(g, player, i, Data.playerHeld.get(i));
                g.drawImage(Algo.rotateImage(otherimg, Data.playerRot.get(i)), getLocalX(i, player) - 25, getLocalY(i, player) - 25, null);
                tags[i].setName(Data.names.get(i));
                tags[i].render(g, getLocalX(i, player) + BattleRoyaleClient.playerSize / 2, getLocalY(i, player) - 30);
            }
        }
        //drawBullets(g, player); // TESTING ONLY
    }

    public void drawHeld(Graphics g, Player p, int ind, String name)
    {
        if(!name.equals("null") && !name.equals("dead"))
        {
            String[] parsed = name.split("-");
            //System.out.println(Arrays.toString(parsed));
            try
            {
                if(parsed[1].equals("A"))
                {
                    g.drawImage(Algo.rotateImage(heldsAni[Arrays.asList(itemNames).indexOf(parsed[0])], Data.playerRot.get(ind)), getLocalX(ind, p) - 25 - 30, getLocalY(ind, p) - 25 - 30, null);
                }
                else
                {
                    g.drawImage(Algo.rotateImage(helds[Arrays.asList(itemNames).indexOf(parsed[0])], Data.playerRot.get(ind)), getLocalX(ind, p) - 25 - 30, getLocalY(ind, p) - 25 - 30, null);
                }
            }
            catch(Exception e) {}
        }
    }

    public void drawBullets(Graphics g, Player p)
    {
        if(Data.bulletData != null)
        {
            String[] superparsed = Data.bulletData.split(":");
            System.out.println(Arrays.toString(superparsed));
            for(String str : superparsed)
            {
                String[] parsed = str.split("&");
                if(Arrays.asList(bulletNames).indexOf(parsed[0]) != -1)
                {
                    //System.out.println(Integer.valueOf(parsed[3]));
                    g.drawImage(Algo.rotateImage(bullets[Arrays.asList(bulletNames).indexOf(parsed[0])], Integer.valueOf(parsed[3])), Algo.getLocalX(Integer.valueOf(parsed[1]), p) - 25, Algo.getLocalY(Integer.valueOf(parsed[2]), p) - 25, null);
                }
            }
        }
    }

    // considering deleting for Algo 
    public int getLocalX(int index, Player p)
    {
        return Data.playerX.get(index) + (0 - p.getScreenX());
    }

    public int getLocalY(int index, Player p)
    {
        return Data.playerY.get(index) + (0 - p.getScreenY());
    }
}
