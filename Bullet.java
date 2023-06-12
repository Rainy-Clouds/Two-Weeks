import java.awt.*;

public class Bullet 
{
    private Rectangle rect;
    private int x, y, width, height, ang, speed, maxDistance, damage, playerFired;
    private String type;

    public Bullet(String type, int x, int y, double angle, int playerNum)
    {
        this.x = x;
        this.y = y;
        this.type = type;
        ang = Algo.round(angle);
        playerFired = playerNum;

        if(type.equals("pistol"))
        {
            width = 5;
            height = 10;
            speed = 17;
            maxDistance = 800;
            damage = 25;
        }
        else if(type.equals("smg"))
        {
            width = 5;
            height = 10;
            speed = 16;
            maxDistance = 600;
            damage = 20;
        }
        else if(type.equals("shotgun"))
        {
            width = 6;
            height = 9;
            speed = 15;
            maxDistance = 400;
            damage = 50;
        }
        else if(type.equals("rifle"))
        {
            width = 5;
            height = 12;
            speed = 20;
            maxDistance = 1000;
            damage = 40;
        }

        int newWidth = (int) (Math.abs(Math.sin(Math.toRadians(angle)) * height) + Math.abs(Math.cos(Math.toRadians(angle)) * width));
        int newHeight = (int) (Math.abs(Math.cos(Math.toRadians(angle)) * height) + Math.abs(Math.sin(Math.toRadians(angle)) * width));

        rect = new Rectangle(x - newWidth / 2, y - newHeight / 2, newWidth, newHeight); // ugh
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public int getMaxDist()
    {
        return maxDistance;
    }

    public int getDamage()
    {
        return damage;
    }

    public Rectangle getRect()
    {
        return rect;
    }

    public int getPlayerFired()
    {
        return playerFired;
    }

    public void update()
    {
        x += speed * Math.cos(Math.toRadians(ang - 90));
        y += speed * Math.sin(Math.toRadians(ang - 90));
        rect = new Rectangle(x - (int)rect.getWidth() / 2, y - (int)rect.getHeight() / 2, (int)rect.getWidth(), (int)rect.getHeight());

        maxDistance -= speed;
    }

    public String getData()
    {
        return type + "&" + x + "&" + y + "&" + ang;
    }
}
