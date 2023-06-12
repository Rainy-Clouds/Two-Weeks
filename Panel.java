import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Panel extends JPanel implements Runnable, MouseListener, MouseMotionListener, KeyListener, MouseWheelListener
{
    private static final int width = 800;
    private static final int height = 600; 

    private Game game = new Game("title");

    private boolean running = false;
    private Thread thread;
    private int fps = 60;
    private long time = 1000 / fps;

    public static int frame;
    public static int mousex, mousey;
    public static boolean mouseDown;
    public static TextField currentField;
    public static boolean[] keyMap = new boolean[6]; // w s a d q e
    public static boolean[] numMap = new boolean[10]; // u have to guess for this one LMAOOOOO
    public static boolean[] arrowMap = new boolean[4]; // up down left right

    public Panel()
    {
        this.setPreferredSize(new Dimension(width, height));
        start();
    }

    private void start()
    {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
        this.addMouseWheelListener(this);

        running = true;
        thread = new Thread(this);
        thread.start();
        setFocusable(true);
        requestFocus();
    } 

    @Override
    public void run()
    {
        long start;
        long elapsed;
        long wait;
        while(running)
        {
            frame++;
            start = System.nanoTime();

            tick();
            repaint();

            elapsed = System.nanoTime() - start;
            wait = time - elapsed / 1000000;

            if(wait <= 0)
            {
                wait = 5;
            }

            try
            {
                Thread.sleep(wait);
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    public void tick()
    {
        game.update();
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        game.render(g);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mousex = e.getX();
        mousey = e.getY();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //mouseDown = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        mouseDown = true;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        mouseDown = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Nothing
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // Nothing
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(currentField != null && (int)e.getKeyChar() >= 32 && (int)e.getKeyChar() <= 125)
        {
            currentField.addToString(e.getKeyChar());
        }
        if(currentField != null && (int)e.getKeyChar() == 8)
        {
            currentField.removeChar();
        }
        if(currentField != null && (int)e.getKeyChar() == 10)
        {
            currentField.entered(game);
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("W"))
        {
            keyMap[0] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("S"))
        {
            keyMap[1] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("A"))
        {
            keyMap[2] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("D"))
        {
            keyMap[3] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Q"))
        {
            keyMap[4] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("E"))
        {
            keyMap[5] = true;
        }

        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Up"))
        {
            arrowMap[0] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Down"))
        {
            arrowMap[1] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Left"))
        {
            arrowMap[2] = true;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Right"))
        {
            arrowMap[3] = true;
        }
        
        try
        {
            numMap[Integer.valueOf(KeyEvent.getKeyText(e.getKeyCode()))] = true;
        }
        catch(Exception ex) {}
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("W"))
        {
            keyMap[0] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("S"))
        {
            keyMap[1] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("A"))
        {
            keyMap[2] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("D"))
        {
            keyMap[3] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Q"))
        {
            keyMap[4] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("E"))
        {
            keyMap[5] = false;
        }

        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Up"))
        {
            arrowMap[0] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Down"))
        {
            arrowMap[1] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Left"))
        {
            arrowMap[2] = false;
        }
        if(KeyEvent.getKeyText(e.getKeyCode()).equals("Right"))
        {
            arrowMap[3] = false;
        }

        try
        {
            numMap[Integer.valueOf(KeyEvent.getKeyText(e.getKeyCode()))] = false;
        }
        catch(Exception ex) {}
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) 
    {
        if(game.getRole() != null)
        {
            game.wheelAction(e.getWheelRotation());
        }
    }
}
