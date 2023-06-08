import java.awt.*;
import java.util.*;

public class Processor 
{
    private ArrayList<Rectangle> playerRects = new ArrayList<Rectangle>();

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
    }

    public void playerAct(String action, int playerNum)
    {
        if(action.equals("pickaxe"))
        {
            Rectangle hitbox = new Rectangle(0, 0, 0, 0);
            daggerHitbox(hitbox, Data.playerX.get(playerNum) + 30, Data.playerY.get(playerNum) + 30, Data.playerRot.get(playerNum));

            for(int i = 0; i < playerRects.size(); i++)
            {
                if(i != playerNum)
                {
                    if(playerRects.get(i).intersects(hitbox))
                    {
                        Data.playerHealth.set(i, Data.playerHealth.get(i) - 5);
                    }
                }
            }
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
