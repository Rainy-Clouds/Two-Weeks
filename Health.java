import java.awt.*;

// you NEED to make these look better
public class Health 
{
    private double maxHealth = 100;
    private double maxShield = 100;
    private double health;
    private int width = 250;
    private int height = 50;
    private int border = 5;
    
    public Health(double hp)
    {
        health = hp;
    }

    public void setHealth(double newHealth)
    {
        health = newHealth;
    }

    public double getHealth()
    {
        return health;
    }

    public void render(Graphics g, int x, int y)
    {
        g.setColor(Color.BLACK);
        g.fillRect(x, y, width, height);

        g.setColor(Color.CYAN);
        if(health > maxHealth)
        {
            g.fillRect(x, y, (int) (width * ((health - maxHealth) / maxShield)), height);
        }

        g.setColor(Color.GREEN);
        double drawHealth = health;
        if(health >= maxHealth)
        {
            drawHealth = maxHealth;
        }
        g.fillRect(x + border, y + border, (int) ((width - 2 * border) * ((double) drawHealth / maxHealth)), height - 2 * border);
    }
}
