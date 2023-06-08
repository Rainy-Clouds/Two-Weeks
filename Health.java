import java.awt.*;

// you NEED to make these look better
public class Health 
{
    private int maxHealth = 100;
    private int health;
    private int width = 250;
    private int height = 50;
    private int border = 5;
    
    public Health(int hp)
    {
        health = hp;
    }

    public void setHealth(int newHealth)
    {
        health = newHealth;
    }

    public void render(Graphics g, int x, int y)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);
        g.setColor(Color.GREEN);
        g.fillRect(x + border, y + border, (int) ((width - 2 * border) * ((double) health / maxHealth)), height - 2 * border);
    }
}
