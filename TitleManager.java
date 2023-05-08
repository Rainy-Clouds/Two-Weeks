import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class TitleManager 
{
    //String[] backgroundArray1 = {"T", "I", "T", "L", "E", " ", "S", "C", "R", "E", "E", "N"};
    ArrayList<String> backgroundArray2 = new ArrayList<String>(Arrays.asList("T", "W", "O", " ", "W", "E", "E", "K", "S"));

    private int titleAnim = 130;
    private boolean flash;
    private boolean flashover;
    private int alpha;
    private TitleGUI gui = new TitleGUI();

    public void update(Game game)
    {
        if(!flashover)
        {
            titleAnim -= 4;

            if(titleAnim < 50)
            {
                flash = true;
            }
        }

        if(flashover)
        {
            alpha -= 20;
            if(alpha < 0)
            {
                alpha = 0;
            }
        }

        if(flash && !flashover)
        {
            alpha += 20;
            if(alpha > 255)
            {
                alpha = 255;
                flashover = true;
                gui.activate();
            }
        }

        gui.update(game);
    }

    public void render(Graphics g)
    {
        // g.setFont(new Font("Monospaced", Font.BOLD, 48));
        // moveArray(g, backgroundArray1, 0, bgx, 50, 35, 5);
        if(!flashover)
        {
            g.setFont(new Font("Monospaced", Font.BOLD, 96));
            moveArrayList(g, backgroundArray2, 0, 50, 130, titleAnim, 0);
        }
        else
        {
            g.setColor(new Color(89, 194, 255));
            g.fillRect(0, 0, 800, 600);
            g.setFont(new Font("Monospaced", Font.BOLD, 96));
            g.setColor(Color.WHITE);
            g.drawString("TWO WEEKS", 150, 130);

            gui.render(g);
        }

        if(flash)
        {
            g.setColor(new Color(255, 255, 255, alpha));
            g.fillRect(0, 0, 800, 600);
        }   
    }

    public void moveArray(Graphics g, String[] arr, int index, int x, int y, int xOffset, int yOffset)
    {
        if(index == arr.length)
        {
            return;
        }
        if(index > arr.length)
        {
            System.out.println("how did you get here");
        }

        g.drawString(arr[index], x, y);
        moveArray(g, arr, index + 1, x + xOffset, y + yOffset, xOffset, yOffset);
    }

    public void moveArrayList(Graphics g, ArrayList<String> arr, int index, int x, int y, int xOffset, int yOffset)
    {
        if(index == arr.size())
        {
            return;
        }
        if(index > arr.size())
        {
            System.out.println("how did you get here");
        }

        g.drawString(arr.get(index), x, y);
        moveArrayList(g, arr, index + 1, x + xOffset, y + yOffset, xOffset, yOffset);
    }
}
