//import java.awt.*;

public class Pickaxe extends Item
{
    public Pickaxe()
    {
        super(false, "player-specific");

        super.setIcon("betaicon.png");
        super.setHeld("betaheld.png");
        super.setDrop("betadrop.png");
    }

    public String getType()
    {
        return "pickaxe";
    }
}
