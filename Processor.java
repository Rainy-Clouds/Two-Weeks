import java.awt.*;
import java.util.*;

public class Processor 
{
    private ArrayList<Rectangle> playerRects = new ArrayList<Rectangle>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Game game;

    public Processor(Game g)
    {
        game = g;
    }

    public void initializeRects()
    {
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            playerRects.add(new Rectangle(0, 0, 60, 60));
        }
    }

    public void daggerHitbox(Rectangle r, int x, int y, double angle)
    {
        double sAngle = Algo.standardizeDiagonalAngle(angle);
        if(sAngle >= 45 && sAngle <= 135)
        {
            r.setBounds(x - 10, y + (int) (-60 + (((sAngle - 45) / 90) * 50)), 70, 70); 
        }
        else if(sAngle >= 135 && sAngle <= 225)
        {
            r.setBounds(x - (int) (10 + (((sAngle - 135) / 90) * 50)), y - 10, 70, 70); 
        }
        else if(sAngle >= 225 && sAngle <= 315)
        {
            r.setBounds(x - 60, y - (int) (10 + (((sAngle - 225) / 90) * 50)), 70, 70); 
        }
        else
        {
            r.setBounds(x + (int) (-60 + (((sAngle - 315) / 90) * 50)), y - 60, 70, 70); 
        }
        
        // at angle 45: x - 10, y - 60, 70, 70
        // at angle 135: x - 10, y - 10, 70, 70
        // at angle 225: x - 60, y - 10, 70, 70
        // at angle 315: x - 60, y - 60, 70, 70
    }

    public void update()
    {
        for(int i = 0; i < Data.playerX.size(); i++)
        {
            try
            {
                playerRects.get(i).setBounds(Data.playerX.get(i), Data.playerY.get(i), 60, 60);
            }
            catch (Exception e) {}
        }

        ArrayList<Integer> removals = new ArrayList<Integer>();
        for(int i = 0; i < bullets.size(); i++)
        {
            bullets.get(i).update();
            if(bullets.get(i).getMaxDist() <= 0)
            {
                removals.add(i);
            }
        }

        int chacha = 0;
        for(int i : removals)
        {
            bullets.remove(i - chacha);
            chacha++;
        }

        removals.clear();
        for(int i = 0; i < playerRects.size(); i++)
        {
            for(int j = 0; j < bullets.size(); j++)
            {
                if(i != bullets.get(j).getPlayerFired() && playerRects.get(i).intersects(bullets.get(j).getRect()))
                {
                    Data.playerHealth.set(i, Data.playerHealth.get(i) - bullets.get(j).getDamage());
                    removals.add(j);
                    if(Data.playerHealth.get(i) <= 0)
                    {
                        try
                        {
                            game.getServer().getEcho(i).setKiller(Data.names.get(bullets.get(j).getPlayerFired()));
                        }
                        catch(Exception e) {}
                    }
                }
            }
        }

        chacha = 0;
        for(int i : removals)
        {
            bullets.remove(i - chacha);
            chacha++;
        }
    }

    public void playerAct(String action, int playerNum)
    {
        if(action.equals("pickaxe"))
        {
            Rectangle hitbox = new Rectangle(0, 0, 0, 0);
            daggerHitbox(hitbox, Data.playerX.get(playerNum) + 30, Data.playerY.get(playerNum) + 30, Data.playerRot.get(playerNum));

            for(int i = 0; i < playerRects.size(); i++)
            {
                if(i != playerNum && Data.playerHealth.get(i) > 0)
                {
                    if(playerRects.get(i).intersects(hitbox))
                    {
                        Data.playerHealth.set(i, Data.playerHealth.get(i) - 5);
                        if(Data.playerHealth.get(i) <= 0)
                        {
                            try
                            {
                                game.getServer().getEcho(i).setKiller(Data.names.get(playerNum));
                            }
                            catch(Exception e) {}
                        }
                    }
                }
            }
        }
        else if(action.equals("pistol"))
        {
            double ang =  Data.playerRot.get(playerNum);
            bullets.add(new Bullet(action, Data.playerX.get(playerNum) + 27 + (int)(27 * Math.cos(Math.toRadians(ang))), Data.playerY.get(playerNum) + 25 + (int)(27 * Math.sin(Math.toRadians(ang))), ang, playerNum));
        }
    }

    public String getBulletData(int playerNum)
    {
        String ret = "";
        if(bullets.size() > 0)
        {
            for(int i = 0; i < bullets.size(); i++)
            {
                if(bullets.get(i).getX() >= Data.playerX.get(playerNum) - 420 && bullets.get(i).getX() <= Data.playerX.get(playerNum) + 480 && bullets.get(i).getY() >= Data.playerY.get(playerNum) - 320 && bullets.get(i).getY() <= Data.playerY.get(playerNum) + 380)
                {
                    ret += bullets.get(i).getData();
                    if(i < bullets.size() - 1)
                    {
                        ret += ":";
                    }
                }
            }
        }
        if(ret.equals(""))
        {
            return null;
        }
        else
        {
            return ret;
        }
    }

    // for debugging
    public void render(Graphics g)
    {
        for(Rectangle rect : playerRects)
        {
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        }
    }
}
