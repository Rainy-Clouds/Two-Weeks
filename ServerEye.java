import java.time.*;
import java.awt.Rectangle;

public class ServerEye 
{
    private int x, y, width, height;
    private int maxX, maxY, maxW, maxH;
    private int minX, minY, minW, minH;
    private int maxGameTime; // in milliseconds  
    private Instant start;  
    private Rectangle rect;

    public ServerEye(int x, int y, int w, int h, int minX, int minY, int minW, int minH, int maxTime)
    {
        this.x = x;
        this.y = y;
        width = w;
        height = h;

        this.minX = minX;
        this.minY = minY;
        this.minW = minW;
        this.minH = minH;

        maxX = x;
        maxY = y;
        maxW = w;
        maxH = h;

        maxGameTime = maxTime;
        start = Instant.now();
        rect = new Rectangle(x, y, width, height);
    }

    public String getData()
    {
        return x + "&" + y + "&" + width + "&" + height;
    }

    public void update()
    {
        double timeFraction = (Duration.between(start, Instant.now()).toMillis() / (double)maxGameTime);
        if(timeFraction > 1)
        {
            timeFraction = 1;
        }
        x = (int) (timeFraction * (minX - maxX) + maxX);
        y = (int) (timeFraction * (minY - maxY) + maxY);
        width = maxW - (int) (timeFraction * (maxW - minW));
        height = maxH - (int) (timeFraction * (maxH - minH));

        rect.setBounds(x, y, width, height);
    }

    public Rectangle getRect()
    {
        return rect;
    }
}
