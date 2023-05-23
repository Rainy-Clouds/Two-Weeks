import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Player 
{
    private int x, y, width, height;
    private Nametag tag = new Nametag();
    private Rectangle rect, rectL, rectR, rectT, rectB;
    
    public Player(int x, int y, int w, int h)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;

        rect = new Rectangle(400 - width / 2, 300 - height / 2, w, h);
        rectL = new Rectangle((int) rect.getX() - 1, (int) rect.getY(), 1, height);
        rectR = new Rectangle((int) (rect.getX() + rect.getWidth()), (int) rect.getY(), 1, height);
        rectT = new Rectangle((int) rect.getX(), (int) rect.getY() - 1, width, 1);
        rectB = new Rectangle((int) rect.getX(), (int) (rect.getY() + rect.getHeight()), width, 1);
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
        return (y + height / 2) - 300;
    }

    public void update(Game game, Rock r)
    {
        keyActions(r);
        tag.setName(game.getClient().getName());
        //System.out.println(x + ", " + y);
        if(rect.intersects(r.getRect()))
        {
            System.out.println("look at what you did");
        }
    }

    public void keyActions(Rock r)
    {
        if(Panel.keyMap[0] && !rectT.intersects(r.getRect()))
        {
            y -= 5;
        }
        if(Panel.keyMap[1] && !rectB.intersects(r.getRect()))
        {
            y += 5;
        }
        if(Panel.keyMap[2] && !rectL.intersects(r.getRect()))
        {
            x -= 5;
        }
        if(Panel.keyMap[3] && !rectR.intersects(r.getRect()))
        {
            x += 5;
        }
    }

    public void renderRect(Rectangle r, Graphics g)
    {
        g.fillRect((int)r.getX(), (int)r.getY(), (int)r.getWidth(), (int)r.getHeight());
    }

    public void render(Graphics g)
    {
        //tag.render(g, 400, 250);
        g.setColor(Color.RED);
        g.fillRect(400 - width / 2, 300 - height / 2, width, height);
        // g.setColor(Color.PINK);
        // renderRect(rectB, g);
        // renderRect(rectL, g);
        // renderRect(rectR, g);
        // renderRect(rectT, g);
    }
}
