public class SMG extends Item
{
    public SMG(int x, int y, String id)
    {
        super(x, y, id, 300); //change

        super.setIcon("icons\\smg.png");
        super.setHeld("helds\\smg.png");
        super.setDrop("icons\\smgdrop.png");
        super.setAnim("helds\\smganim.png");
    }

    public String getType()
    {
        return "smg";
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
