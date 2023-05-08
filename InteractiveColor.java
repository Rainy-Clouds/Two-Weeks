import java.awt.*;

public class InteractiveColor 
{
    private static double rbRed = 255;
    private static double rbBlue = 160;
    private static boolean rbRedUp;

    public static void update()
    {
        if(!rbRedUp)
        {
            rbRed -= 0.2;
            rbBlue += 0.2;
            if(rbRed < 160 || rbBlue > 255)
            {
                rbRed = 160;
                rbBlue = 255;
                rbRedUp = true;
            }
        }
        else
        {
            rbRed += 0.2;
            rbBlue -= 0.2;
            if(rbBlue < 160 || rbRed > 255)
            {
                rbRed = 255;
                rbBlue = 160;
                rbRedUp = false;
            }
        }
    }

    public static Color redBlue()
    {
        return new Color((int)rbRed, 175, (int)rbBlue);
    }
}
