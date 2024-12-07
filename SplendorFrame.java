import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class SplendorFrame extends JFrame {
    //Width and height
    private final int WIDTH = 1280;
    private final int HEIGHT = 720;
    
    SplendorMenu menu;
    PlayerSelect select;
    SplendorGame game;
    SplendorEnd end;

    public SplendorFrame()
    {
        //Setting basic settings of the window
        super("Splendor");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        menu = new SplendorMenu(this);
        
        try
        {
        	BufferedImage icon = ImageIO.read(Card.class.getResource("/Images/Icon.png"));
            setIconImage(icon);
        } catch(Exception e)
        {
        	System.out.println("Could not load icon");
        }
        
        add(menu);
        
        setVisible(true);
    }
    public void addSelect() {
    	
    	select = new PlayerSelect(this);
   
    	menu.setVisible(false);
    	remove(menu);
    	add(select);
    }
    public void addGame(int size)
    {
    	game = new SplendorGame(size, this);
    	select.setVisible(false);
    	remove(select);
    	add(game);
    }
    public void addEnd(Player[] a)
    {
    	end = new SplendorEnd(a);
    	game.setVisible(false);
    	
    	remove(game);
    	add(end);
    }
}
