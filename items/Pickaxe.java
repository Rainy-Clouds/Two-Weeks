//import java.awt.*;

public class Pickaxe extends Item
{
    public Pickaxe()
    {
        super(false, "player-specific", 150);

        super.setIcon("betaicon.png");
        super.setHeld("betaheld.png");
        super.setDrop("betadrop.png");
        super.setAnim("betaheldanim.png");
    }

    public String getType()
    {
        return "pickaxe";
    }

    public void action()
    {
        if(super.canAct())
        {
            super.animate(5);
            super.action();
        }
    }
}
