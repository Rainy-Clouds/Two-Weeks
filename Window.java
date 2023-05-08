import javax.swing.*;
import java.awt.BorderLayout;

public class Window extends JFrame
{
    public Window(String title)
    {
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.add(new Panel(), BorderLayout.CENTER);
        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}