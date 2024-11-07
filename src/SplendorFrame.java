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
        setVisible(true);

        //adding the frames
        add(new SplendorGame(4));
    }
}
