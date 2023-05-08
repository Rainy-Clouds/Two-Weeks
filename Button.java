import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import java.awt.*;

public class Button
{
    private int x, y, width, height, action;
    private String label, state;
    private boolean active = true;
    private BufferedImage img, touchingimg, pressedimg;

    public Button(String label, int x, int y, String path1, String path2, String path3, int action)
    {
        this.x = x;
        this.y = y;
        this.label = label;
        this.action = action;
        try
        {
            img = ImageIO.read(new File("assets\\" + path1));
            width = img.getWidth();
            height = img.getHeight();
            touchingimg = ImageIO.read(new File("assets\\" + path2));
            pressedimg = ImageIO.read(new File("assets\\" + path3));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public Button(String label, int x, int y, int action)
    {
        this.x = x;
        this.y = y;
        this.label = label;
        this.action = action;
        try
        {
            img = ImageIO.read(new File("assets\\regbutton.png"));
            width = img.getWidth();
            height = img.getHeight();
            touchingimg = ImageIO.read(new File("assets\\toubutton.png"));
            pressedimg = ImageIO.read(new File("assets\\prebutton.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    public void setActive(boolean b)
    {
        active = b;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void update(Game game)
    {
        if(Panel.mousex > x && Panel.mousex < x + width && Panel.mousey > y && Panel.mousey < y + height)
        {
            if(Panel.mouseDown)
            {
                state = "pressed";
                if(active)
                {
                    act(game);
                    active = false;
                }
            }
            else
            {
                state = "touching";
            }
        }
        else
        {
            state = "nothing";
        }
    }

    public void render(Graphics g)
    {
        if(state.equals("pressed"))
        {
            g.drawImage(pressedimg, x, y, null);
        }
        else if(state.equals("touching"))
        {
            g.drawImage(touchingimg, x, y, null);
        }
        else
        {
            g.drawImage(img, x, y, null);
        } 
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        Algo.centerString(g, label, x, y, width, height);
    }

    public void act(Game game)
    {
        if(action == 2)
        {
            Transition.switchState("battle royale menu");
        }
        if(action == 4)
        {
            game.becomeServer();
        }
        if(action == 5)
        {
            game.becomeClient();
        }
    }
}
