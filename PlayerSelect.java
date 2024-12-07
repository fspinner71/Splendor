import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class PlayerSelect extends JPanel implements MouseListener  {
	private static BufferedImage background;
    private static BufferedImage elGato;
    private static BufferedImage player;
    private static BufferedImage wins;
    private static BufferedImage period;
    private SplendorFrame parentFrame;
    private int winner;
    private Player[] players;
    public static Button[] numbers;

	 static {
	        try {
	        	numbers = new Button[3];
	            background = ImageIO.read(SplendorEnd.class.getResource("/Images/background.png"));
	            int width = ImageIO.read(Card.class.getResource("/Images/4.png")).getWidth()/4;
	            int height = ImageIO.read(Card.class.getResource("/Images/4.png")).getHeight()/4;
	            numbers = new Button[10];
	            
	            numbers[0] = new Button(285, 330, width, height, ImageIO.read(Card.class.getResource("/Images/2.png")));
	            numbers[1] = new Button(585, 330, width, height, ImageIO.read(Card.class.getResource("/Images/3.png")));
	            numbers[2] = new Button(885, 330, width, height, ImageIO.read(Card.class.getResource("/Images/4.png")));
	       
	            
	           // ImageIO.read(Card.class.getResource("/Images/4.png"));
	        
	            
	            player = ImageIO.read(Card.class.getResource("/Images/Player.png"));
	        
	            
	        } catch (Exception e) {
	            System.out.println("play selecy  end images failed to load");
	            e.printStackTrace();
	        }
}
	 public PlayerSelect(SplendorFrame frame) {
		 parentFrame = frame;
	    	addMouseListener(this);
		 
	 }
	 public void mousePressed(MouseEvent e) {
		 if(e.getButton() != MouseEvent.BUTTON1) {return;}
	    	
	    	int x = e.getX();
	    	int y = e.getY();
	    			
	    for(int c = 0; c< numbers.length; c++) {
	    	if(numbers[c].isInside(x,  y)) {
	    		//start the game with that much players
	    		parentFrame.addGame(c+2);
	    		return;
	    	}
	    	
	    }
		 
		 
		 
	 }
	 public void paint(Graphics g) {
		 
		 super.paint(g);
		 g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
		 numbers[0].paint(g);
		 numbers[1].paint(g);
		 numbers[2].paint(g);
	
	 }
	 public void mouseClicked(MouseEvent e) {
		 
			
		 
		 
	 }
	 
	 
	 
	    public void mouseEntered(MouseEvent e) {}
	    public void mouseExited(MouseEvent e) {}
	    public void mouseReleased(MouseEvent e) {}
	 }
