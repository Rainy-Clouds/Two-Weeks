//import java.awt.*;

public class Jug extends Item
{
    public Jug(int x, int y, String id)
    {
        super(x, y, id, 10000);

        super.setIcon("icons\\jug.png");
        super.setHeld("helds\\jug.png");
        super.setDrop("icons\\jugdrop.png");
        super.setAnim("helds\\juganim.png");
    }

    public String getType()
    {
        return "jug";
    }

    public void update(Player p)
    {
        super.update(p);

        if(Algo.anyTrue(Panel.keyMap) || Algo.anyTrue(Panel.numMap))
        {
            super.setActOnce(false);
            super.quitAnimating();
        }
    }

    public void action()
    {
        if(super.canAct())
        {
            super.animate(600);
            super.action();
        }
    }
}
