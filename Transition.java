import java.awt.*;

public class Transition 
{
    private static int alpha;
    private static boolean alphaUp;
    private static boolean transitioning;
    private static String stateTo;

    public static void switchState(String newState)
    {
        if(!transitioning)
        {
            stateTo = newState;
            transitioning = true;
            alphaUp = true;
        }
    }

    public static void draw(Graphics g, Game game)
    {
        if(transitioning)
        {
            if(alphaUp)
            {
                alpha += 5;
                if(alpha > 255)
                {
                    alpha = 255;
                    alphaUp = false;
                    game.setState(stateTo);
                }
            }
            else
            {
                alpha -= 5;
                if(alpha < 0)
                {
                    alpha = 0;
                    transitioning = false;
                }
            }
            g.setColor(new Color(0, 0, 0, alpha));
            g.fillRect(0, 0, 800, 600);
        }
    }
}
