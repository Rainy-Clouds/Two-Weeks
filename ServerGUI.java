import java.awt.*;

public class ServerGUI 
{
    public void render(Graphics g, int offset)
    {
        g.setFont(new Font("Arial", Font.PLAIN, 36));
        Algo.centerString(g, Data.playerCount + "/8 joined", 800 + offset, 0, 800, 600);
    }    
}
