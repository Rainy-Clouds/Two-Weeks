import java.awt.*;

public class BattleRoyaleServer
{
    private Processor processor = new Processor();

    public Processor getProcessor()
    {
        return processor;
    }

    public void update(Game game)
    {
        processor.update();
    }

    public void render(Graphics g)
    {
        
    }
}
