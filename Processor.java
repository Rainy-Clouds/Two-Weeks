import java.awt.*;
import java.util.*;

public class Processor 
{
    private ArrayList<Rectangle> playerRects = new ArrayList<Rectangle>();
    private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    private Game game;
    private ServerEye eye = new ServerEye(-400, -400, 7200, 4400, 2450, 1850, 100, 100, 300000);
    private boolean winner;

    public Processor(Game g)
    {
        game = g;
    }

    public ServerEye getServerEye()
    {
        return eye;
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
        if(!winner)
        {
            eye.update();
        }

        for(int i = 0; i < Data.playerX.size(); i++)
        {
            try
            {
                playerRects.get(i).setBounds(Data.playerX.get(i), Data.playerY.get(i), 60, 60);
            }
            catch (Exception e) {}
        }

        for(int i = 0; i < playerRects.size(); i++)
        {
            if(!playerRects.get(i).intersects(eye.getRect()) && !winner)
            {
                Data.playerHealth.set(i, Data.playerHealth.get(i) - 0.25);
                if(Data.playerHealth.get(i) <= 0)
                {
                    try
                    {
                        game.getServer().getEcho(i).setKiller("the Storm");
                    }
                    catch(Exception e) {}

                    checkForWin();
                }
            }
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
                if(i != bullets.get(j).getPlayerFired() && playerRects.get(i).intersects(bullets.get(j).getRect()) && !winner)
                {
                    Data.playerHealth.set(i, Data.playerHealth.get(i) - bullets.get(j).getDamage());
                    removals.add(j);
                    if(Data.playerHealth.get(i) <= 0)
                    {
                        Data.playerKills.set(bullets.get(j).getPlayerFired(), Data.playerKills.get(bullets.get(j).getPlayerFired()) + 1);
                        try
                        {
                            game.getServer().getEcho(i).setKiller(Data.names.get(bullets.get(j).getPlayerFired()));
                        }
                        catch(Exception e) {}
                    }
                }
            }

            checkForWin();
        }

        chacha = 0;
        for(int i : removals)
        {
            bullets.remove(i - chacha);
            chacha++;
        }
    }

    public void checkForWin()
    {
        if(game.getServer().getEchoes().size() < 2)
        {
            winner = true;
        }
    }

    public void playerAct(String action, int playerNum)
    {
        if(action.equals("dagger"))
        {
            Rectangle hitbox = new Rectangle(0, 0, 0, 0);
            daggerHitbox(hitbox, Data.playerX.get(playerNum) + 30, Data.playerY.get(playerNum) + 30, Data.playerRot.get(playerNum));

            for(int i = 0; i < playerRects.size(); i++)
            {
                if(i != playerNum && Data.playerHealth.get(i) > 0 && !winner)
                {
                    if(playerRects.get(i).intersects(hitbox))
                    {
                        Data.playerHealth.set(i, Data.playerHealth.get(i) - 5);
                        if(Data.playerHealth.get(i) <= 0)
                        {
                            Data.playerKills.set(playerNum, Data.playerKills.get(playerNum) + 1);
                            try
                            {
                                game.getServer().getEcho(i).setKiller(Data.names.get(playerNum));
                            }
                            catch(Exception e) {}

                            checkForWin();
                            if(winner)
                            {
                                break;
                            }
                        }
                    }
                }
            }
        }
        else if(action.equals("pistol") || action.equals("smg") || action.equals("shotgun") || action.equals("rifle"))
        {
            double ang =  Data.playerRot.get(playerNum);
            bullets.add(new Bullet(action, Data.playerX.get(playerNum) + 27 + (int)(27 * Math.cos(Math.toRadians(ang))), Data.playerY.get(playerNum) + 25 + (int)(27 * Math.sin(Math.toRadians(ang))), ang, playerNum));
        }
        else if(action.equals("jug-finish"))
        {
            Data.playerHealth.set(playerNum, Data.playerHealth.get(playerNum) + 50);
            if(Data.playerHealth.get(playerNum) > 200)
            {
                Data.playerHealth.set(playerNum, 200.0);
            }
        }
        else if(action.equals("burger-finish"))
        {
            if(Data.playerHealth.get(playerNum) < 100)
            {
                Data.playerHealth.set(playerNum, Data.playerHealth.get(playerNum) + 30);
                if(Data.playerHealth.get(playerNum) > 100)
                {
                    Data.playerHealth.set(playerNum, 100.0);
                }
            }
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

    public String getEyeData()
    {
        return eye.getData();
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
