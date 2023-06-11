//import java.awt.*;

public class Pickaxe extends Item
{
    public Pickaxe()
    {
        super(false, "player-specific", 150);

        super.setIcon("icons\\dagger.png");
        super.setHeld("helds\\dagger.png");
        super.setDrop("icons\\daggerdrop.png");
        super.setAnim("helds\\daggeranim.png");
    }

    public String getType()
    {
        return "dagger";
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
