import java.awt.*;

public class Bullet 
{
    private Rectangle rect;
    private int x, y, width, height, ang, speed, maxDistance;
    private String type;

    public Bullet(String type, int x, int y, double angle)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        ang = Algo.round(angle);

        if(type.equals("pistol"))
        {
            width = 5;
            height = 10;
            speed = 5;
            maxDistance = 800;
        }

        int newWidth = (int) (Math.abs(Math.sin(Math.toRadians(angle)) * height) + Math.abs(Math.cos(Math.toRadians(angle)) * width));
        int newHeight = (int) (Math.abs(Math.cos(Math.toRadians(angle)) * height) + Math.abs(Math.sin(Math.toRadians(angle)) * width));

        rect = new Rectangle(x - newWidth / 2, y - newWidth / 2, newWidth, newHeight); // ugh
    }

    public int getMaxDist()
    {
        return maxDistance;
    }

    public void update()
    {
        x += speed * Math.cos(Math.toRadians(ang - 90));
        y += speed * Math.sin(Math.toRadians(ang - 90));
        maxDistance -= speed;
    }

    public String getData()
    {
        return type + "&" + x + "&" + y + "&" + ang;
    }
}
