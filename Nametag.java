import java.awt.*;

public class Nametag 
{
    //public int x, y;
    public String name;

    public void setName(String myName)
    {
        name = myName;
    }

    public void render(Graphics g, int x, int y)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 14));
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        g.setColor(new Color(155, 155, 155, 100));
        g.fillRect((x - metrics.stringWidth(name) / 2) - 2, y - metrics.getHeight() / 2, metrics.stringWidth(name) + 4, metrics.getHeight());
        g.setColor(Color.WHITE);
        g.drawString(name, x - metrics.stringWidth(name) / 2, y + metrics.getAscent() - metrics.getHeight() / 2);
    }
}
