import java.awt.*;

public class TextField 
{
    private int x, y, width, height, actNum;
    private boolean active, permActive;
    private Font font;
    private FontMetrics metrics;
    private String currentString;
    private ClientGUI parentGUI;

    public TextField(int x, int y, int w, int h, int fontSize, int action, ClientGUI gui)
    {
        permActive = true;
        this.x = x;
        this.y = y;
        parentGUI = gui;
        actNum = action;
        width = w;
        height = h;
        currentString = "192.168.1.38";
        font = new Font("Arial", Font.PLAIN, fontSize);

        active = true;
    }

    public int getX()
    {
        return x;
    }

    public int getY()
    {
        return y;
    }

    public void setLocation(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public void setPermActive(boolean bool)
    {
        permActive = bool;
    }

    public void addToString(char c)
    {
        if(currentString.length() <= 24)
        {
            currentString += c;
        }
    }

    public void removeChar()
    {
        if(currentString.length() > 0)
        {
            currentString = currentString.substring(0, currentString.length() - 1);
        }
    }

    public void entered(Game game)
    {
        if(actNum == 0)
        {
            parentGUI.startConnecting();
            game.createClient(currentString);
        }
        if(actNum == 1)
        {
            if(currentString.contains(" "))
            {
                parentGUI.invalidateName();
            }
            else
            {
                parentGUI.validateName();
                game.getClient().setName(currentString);
            }
        }
    }

    public void update()
    {
        if(Panel.mouseDown && permActive)
        {
            if(Panel.mousex > x && Panel.mousex < x + width && Panel.mousey > y && Panel.mousey < y + height)
            {
                active = true;
                Panel.currentField = this;
            }
            else
            {
                active = false;
                if(Panel.currentField == this)
                {
                    Panel.currentField = null;
                }
            }
        }
        else if(!permActive)
        {
            active = false;
            if(Panel.currentField == this)
            {
                Panel.currentField = null;
            }
        }
    }

    public void render(Graphics g)
    {
        metrics = g.getFontMetrics(font);

        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
        if(Panel.frame % 60 < 30 && active)
        {
            g.fillRect(x + metrics.stringWidth(currentString) + 3, y + 2, 2, metrics.getHeight());
        }

        g.setFont(font);
        g.drawString(currentString, x + 3, y + metrics.getHeight() - 5);
    }
}
