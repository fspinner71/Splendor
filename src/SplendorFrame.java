import javax.swing.*;

public class SplendorFrame extends JFrame {
    //Width and height
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;

    public final int BLACK = 0;
    public final int WHITE = 1;
    public final int GREEN = 2;
    public final int RED = 3;
    public final int BLUE = 4;
    public final int YELLOW = 5;

    public SplendorFrame()
    {
        //Setting basic settings of the window
        super("Splendor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        //adding the frames
        add(new SplendorGame());
        add(new SplendorMenu());
        add(new SplendorEnd());
    }
}
