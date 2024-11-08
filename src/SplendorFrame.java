import javax.swing.*;

public class SplendorFrame extends JFrame {
    //Width and height
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public SplendorFrame()
    {
        //Setting basic settings of the window
        super("Splendor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(new SplendorMenu(this));
        setVisible(true);
    }
    public void addGame()
    {
    	add(new SplendorGame(4));
    }
    public void addEnd()
    {
    	add (new SplendorEnd());
    }
}
