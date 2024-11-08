import javax.swing.*;

public class SplendorFrame extends JFrame {
    //Width and height
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    
    SplendorMenu menu;
    SplendorGame game;
    SplendorEnd end;

    public SplendorFrame()
    {
        //Setting basic settings of the window
        super("Splendor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menu = new SplendorMenu(this);
        
        add(menu);
        
        setVisible(true);
    }
    public void addGame()
    {
    	game = new SplendorGame(4);
    	menu.setVisible(false);
    	
    	remove(menu);
    	add(game);
    }
    public void addEnd()
    {
    	end = new SplendorEnd();
    	game.setVisible(false);
    	
    	remove(game);
    	add(end);
    }
}
