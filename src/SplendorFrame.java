import javax.swing.*;

public class SplendorFrame extends JFrame {
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    public SplendorFrame()
    {
        super("Splendor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
}
