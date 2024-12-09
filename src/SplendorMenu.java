import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class SplendorMenu extends JPanel implements MouseListener {
    public static BufferedImage background;
    private static BufferedImage titleText;
    private static BufferedImage startButtonImage;
    private static BufferedImage cat;
    
    private SplendorFrame parentFrame;
    
    private Button startButton;
    static 
    {
    	try 
    	{
            background = ImageIO.read(SplendorMenu.class.getResource("/Images/Background.png"));
            titleText = ImageIO.read(SplendorMenu.class.getResource("/Images/TitleText.png"));
            startButtonImage = ImageIO.read(SplendorMenu.class.getResource("/Images/StartButton.png"));
            cat = ImageIO.read(SplendorMenu.class.getResource("/Images/TitleCat.png"));
        }
    	catch (Exception E) {
    		System.out.println("menu images failed to load");
    		E.printStackTrace();
    	}
    }

    public SplendorMenu (SplendorFrame frame) 
    {     
    	startButton = new Button(440, 275, 400, 160, startButtonImage);
    	parentFrame = frame;
    	addMouseListener(this);
    }
	public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(titleText, 140, 15, 1000, 180, null);
        g.drawImage(cat, -165, 460, 1100, 1100, null);
        
       startButton.paint(g);
    }
    
    public void mousePressed(MouseEvent e) 
    {
    	if(e.getButton() != MouseEvent.BUTTON1) {return;}
    	
    	int x = e.getX();
    	int y = e.getY();
 
    	if(startButton.isInside(x, y))
    	{
    		parentFrame.addSelect();
    	}
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
