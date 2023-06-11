public class Shotgun extends Item
{
    public Shotgun(int x, int y, String id)
    {
        super(x, y, id, 1500);

        super.setIcon("icons\\shotgun.png");
        super.setHeld("helds\\shotgun.png");
        super.setDrop("icons\\shotgundrop.png");
        super.setAnim("helds\\shotgunanim.png");
    }

    public String getType()
    {
        return "shotgun";
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
