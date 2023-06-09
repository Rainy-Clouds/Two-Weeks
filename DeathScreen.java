import java.awt.*;

public class DeathScreen 
{
    private int alpha;
    private int speed;
    private String[] deathMessages = {"Wait how", "You were literally almost there... that's tough LMAO", "Skill issue", "Not even top 3... get better bro", "You're mid", "Welp. Better luck next time!", "Hey, at least you're not last!", "Holy mother of Christ you're TERRIBLE at this"};

    public DeathScreen(int sp)
    {
        speed = sp;
    }

    public void render(Graphics g)
    {
        if(alpha < 150)
        {
            alpha += speed;
        }
        g.setColor(new Color(255, 0, 0, alpha));
        g.fillRect(0, 0, 800, 600);

        g.setFont(new Font("Arial", Font.PLAIN, 72));
        g.setColor(Color.WHITE);
        Algo.centerString(g, "You Died!", 0, 0, 800, 475);
        g.setFont(new Font("Arial", Font.PLAIN, 24));
        Algo.centerString(g, "Killed in " + Algo.numText(Data.placement) + " place by " + Data.killer, 0, 0, 800, 600);
        Algo.centerString(g, deathMessages[Data.placement - 1], 0, 0, 800, 675);
    }
}
