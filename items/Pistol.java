public class Pistol extends Item
{
    public Pistol(int x, int y, String id)
    {
        super(x, y, id, 500);

        super.setIcon("icons\\pistol.png");
        super.setHeld("helds\\pistol.png");
        super.setDrop("icons\\pistoldrop.png");
        super.setAnim("helds\\pistolanim.png");
    }

    public String getType()
    {
        return "pistol";
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
