public class Rifle extends Item
{
    public Rifle(int x, int y, String id)
    {
        super(x, y, id, 600); // change

        super.setIcon("icons\\rifle.png");
        super.setHeld("helds\\rifle.png");
        super.setDrop("icons\\rifledrop.png");
        super.setAnim("helds\\rifleanim.png");
    }

    public String getType()
    {
        return "rifle";
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
