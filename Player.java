import java.awt.*;

public class Player 
{
    private int x, y, width, height;
    private Nametag tag = new Nametag();
    
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

    public int getScreenX()
    {
        return (x + width / 2) - 400;
    }

    public int getScreenY()
    {
        return (y + height /2 ) - 300;
    }

    public void update(Game game)
    {
        keyActions();
        tag.setName(game.getClient().getName());
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
        tag.render(g, 400, 200);
        g.setColor(Color.RED);
        g.fillRect(400 - width / 2, 300 - height / 2, width, height);
    }
}
