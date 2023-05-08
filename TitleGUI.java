import java.awt.*;

public class TitleGUI 
{
    private String[] names = {"SAVE THE WORLD", "BATTLE ROYALE", "BONUS"};
    private Button[] btns = new Button[3];
    
    public TitleGUI()
    {
        for(int i = 0; i < names.length; i++)
        {
            btns[i] = new Button(names[i], 125 + i * 100, 300 + i * 100, i + 1);
        }
    }

    public void update(Game game)
    {
        for(int i = 0; i < btns.length; i++)
        {
            btns[i].update(game);
        }
    }

    public void render(Graphics g)
    {
        for(int i = 0; i < btns.length; i++)
        {
            btns[i].render(g);
        }
    }

    public void activate()
    {
        for(int i = 0; i < btns.length; i++)
        {
            btns[i].setActive(true);
        }
    }
}
