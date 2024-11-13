import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class SplendorEnd extends JPanel {
    private static BufferedImage background;
    private static BufferedImage elGato;
    private static BufferedImage player;
    private static BufferedImage wins;
    private static BufferedImage one, two, three, four, period;
    private SplendorFrame parentFrame;
    static {
        try {
            background = ImageIO.read(SplendorEnd.class.getResource("/Images/background.png"));
            
            one = ImageIO.read(Card.class.getResource("/Images/1.png"));
            two = ImageIO.read(Card.class.getResource("/Images/2.png"));
            three= ImageIO.read(Card.class.getResource("/Images/3.png"));
            four = ImageIO.read(Card.class.getResource("/Images/4.png"));

            elGato = ImageIO.read(Card.class.getResource("/Images/ElGato.png"));
            wins = ImageIO.read(Card.class.getResource("/Images/Wins.png"));
            player = ImageIO.read(Card.class.getResource("/Images/Player.png"));
            period = ImageIO.read(Card.class.getResource("/Images/Period.png"));
        } catch (Exception e) {
            System.out.println("splenodr end images failed to load");
            e.printStackTrace();
        }
        
    }
    public SplendorEnd () 
    {     
    	
    	
    	
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(elGato, -600, 200, elGato.getWidth(), elGato.getHeight(), null);
    }

    


}
