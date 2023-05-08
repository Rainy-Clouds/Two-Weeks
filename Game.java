import java.awt.*;

public class Game 
{
    private String state;
    private Server server;
    private Client client;

    private TitleManager title = new TitleManager();
    private BRMenuManager brmenu = new BRMenuManager();

    public Game(String state)
    {
        this.state = state; 
    }

    public void setState(String newState)
    {
        state = newState;
    }

    public void update()
    {
        InteractiveColor.update();
        if(state.equals("title"))
        {
            title.update(this);
        }
        if(state.equals("battle royale menu"))
        {
            brmenu.update(this);
        }
    }

    public void render(Graphics g)
    {
        if(state.equals("title"))
        {
            title.render(g);
        }
        if(state.equals("battle royale menu"))
        {
            brmenu.render(g);
        }
        Transition.draw(g, this);
    }

    public void becomeServer()
    {
        server = new Server();
        brmenu.changeScreen("Server");
    }

    public void becomeClient()
    {
        //client = new Client();
        brmenu.changeScreen("Client");
    }

    public void createClient(String ip)
    {
        client = new Client(ip, this);
    }

    public void removeClient()
    {
        client = null;
        brmenu.warnCGUI();
    }
}
