public class Burger extends Item
{
    public Burger(int x, int y, String id)
    {
        super(x, y, id, 2000);

        super.setIcon("icons\\burger.png");
        super.setHeld("helds\\burger.png");
        super.setDrop("icons\\burgerdrop.png");
        super.setAnim("helds\\burgeranim.png");
    }

    public String getType()
    {
        return "burger";
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
            super.animate(120);
            super.action();
        }
    }
}
