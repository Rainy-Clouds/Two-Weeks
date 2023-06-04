//import java.awt.*;

public class Jug extends Item
{
    public Jug(int x, int y, String id)
    {
        super(x, y, id, 0);

        super.setIcon("betaicon2.png");
        super.setHeld("betaheld2.png");
        super.setDrop("betadrop2.png");
    }

    public String getType()
    {
        return "jug";
    }
}
