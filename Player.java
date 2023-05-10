import java.awt.*;

public class Player 
{
    private int x, y, width, height;
    
    public Player(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void update()
    {
        keyActions();
        //System.out.println(x + ", " + y);
    }

    public void keyActions()
    {
        if(Panel.keyMap[0])
        {
            y -= 5;
        }
        if(Panel.keyMap[1])
        {
            y += 5;
        }
        if(Panel.keyMap[2])
        {
            x -= 5;
        }
        if(Panel.keyMap[3])
        {
            x += 5;
        }
    }

    public void render(Graphics g)
    {
        g.setColor(Color.RED);
        g.fillRect(400 - width / 2, 300 - height / 2, width, height);
    }
}
