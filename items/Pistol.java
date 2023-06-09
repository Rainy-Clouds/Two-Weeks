public class Pistol extends Item
{
    public Pistol(int x, int y, String id)
    {
        super(x, y, id, 300);

        super.setIcon("betaicon3.png");
        super.setHeld("betaheld3.png");
        super.setDrop("betadrop3.png");
        super.setAnim("betaheldanim3.png");
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
