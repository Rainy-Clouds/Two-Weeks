import java.awt.*;

public class Game 
{
    private String state;
    private Server server;
    private Client client;
    private String role;

    private TitleManager title = new TitleManager();
    private BRMenuManager brmenu = new BRMenuManager();
    private BattleRoyaleServer brs;
    private BattleRoyaleClient brc;

    public Game(String state)
    {
        this.state = state; 
    }

    public void setState(String newState)
    {
        state = newState;
    }

    public String getState()
    {
        return state;
    }

    public void setRole(String newRole)
    {
        role = newRole;
    }

    public String getRole()
    {
        return role;
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
        if(state.equals("battle royale") && role != null)
        {
            if(role.equals("Client"))
            {
                brc.update(this);
            }
            else
            {
                brs.update(this);
            }
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
        if(state.equals("battle royale") && role != null)
        {
            if(role.equals("Client"))
            {
                brc.render(g);
            }
            else
            {
                brs.render(g, this);
            }
        }
        Transition.draw(g, this);
    }

    public void becomeServer()
    {
        role = "Server";
        brs = new BattleRoyaleServer(this);
        brmenu.changeScreen("Server");
        server = new Server(this);
    }

    public void becomeClient()
    {
        //client = new Client();
        role = "Client";
        brc = new BattleRoyaleClient();
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

    public void startBattleRoyale()
    {
        if(server != null)
        {
            server.clientsStart();
        }
    }

    public void wheelAction(int rot)
    {
        if(role.equals("Client") && state.equals("battle royale"))
        {
            brc.getPlayer().getInventory().scroll(rot, getBRClient().getPlayer());
        }
    }

    public BRMenuManager getBRMenu()
    {
        return brmenu;
    }

    public BattleRoyaleClient getBRClient()
    {
        return brc;
    }

    public BattleRoyaleServer getBRServer()
    {
        return brs;
    }

    public Server getServer()
    {
        return server;
    }

    public Client getClient()
    {
        return client;
    }
}
