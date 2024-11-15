import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;
public class Patron {
	public static final int WIDTH = 120;
	public static final int HEIGHT = 120;
	private static BufferedImage[] images; //holds all the images
	private int points;
	private int[] price;
	private BufferedImage image;
	public static BufferedImage[] costs;
	
	private Button button;
    static {
        images = new BufferedImage[10];
        costs = new BufferedImage[5];
        try {
	        images[0] = ImageIO.read(Patron.class.getResource("/Images/Patron1.png"));
	        images[1] = ImageIO.read(Patron.class.getResource("/Images/Patron2.png"));
	        images[2] = ImageIO.read(Patron.class.getResource("/Images/Patron3.png"));
	        images[3] = ImageIO.read(Patron.class.getResource("/Images/Patron4.png"));
	        images[4] = ImageIO.read(Patron.class.getResource("/Images/Patron5.png"));
	        images[5] = ImageIO.read(Patron.class.getResource("/Images/Patron6.png"));
	        images[6] = ImageIO.read(Patron.class.getResource("/Images/Patron7.png"));
	        images[7] = ImageIO.read(Patron.class.getResource("/Images/Patron8.png"));
	        images[8] = ImageIO.read(Patron.class.getResource("/Images/Patron9.png"));
	        images[9] = ImageIO.read(Patron.class.getResource("/Images/Patron10.png"));
	        //initilaize images for the patrons
	        costs[0] = ImageIO.read(Card.class.getResource("/Images/BlackCostCards.png"));
	        costs[1] = ImageIO.read(Card.class.getResource("/Images/WhiteCostCards.png"));
	        costs[2] = ImageIO.read(Card.class.getResource("/Images/GreenCostCards.png"));
	        costs[3] = ImageIO.read(Card.class.getResource("/Images/RedCostCards.png"));
	        costs[4] = ImageIO.read(Card.class.getResource("/Images/BlueCostCards.png"));

        }
        catch (Exception E) {
            System.out.println("Patron images failed to load");
        }
    }

    public Patron(int p, int[] pr, int i, Button b) {  //points/price/button as well as integer that corresponds with images array(0-9)
        points = p;
        price = pr;
        image = images[i];
        button = b;
    }

    public Patron(int p, int[] pr, int i) {  //points/price/button as well as integer that corresponds with images array(0-9)
        points = p;
        price = pr;
        image = images[i];
        button = new Button(0,0,WIDTH, HEIGHT, image);
    }
    
    public int getPoints() {
        return points;
    }

    public int[] getPrice() {
        return price;
    }

    public Button getButton() {
        return button;
    }
    
    private int scaleX(double scale)
    {
    	return (int)(button.getWidth()*scale);
    }
    private int scaleY(double scale)
    {
    	return (int)(button.getHeight()*scale);
    }
    public void setPosition(int x, int y)
    {
    	button.setPosition(x, y);
    }
    public void scale(double x, double y)
    {
    	button.setSize((int)(button.getWidth() * x), (int)(button.getHeight() * y));
    }
    
    public void paint(Graphics g) {
        
        int x = button.getX();
    	int y = button.getY();
    	int width = button.getWidth();
    	int height = button.getHeight();
        
        button.paint(g);
        
        if(points > 0)
        {
        	g.drawImage(Card.numbers[points], x + scaleX(0.05), y + scaleY(0.04), scaleX(0.28), scaleY(0.28), null);
        }
        
        int offset = 1;
        for(int i = 0; i < price.length; i++)
        {
        	if(price[i] != 0)
        	{
        		g.drawImage(costs[i], x + scaleX(0.1), y + height - scaleY(0.03) - offset * scaleY(0.22), scaleX(0.18), scaleY(0.2), null);
        		g.drawImage(Card.numbers[price[i]], x + scaleX(0.1), y + height - scaleY(0.015) - offset * scaleY(0.22), scaleX(0.17), scaleY(0.17), null);
        		offset++;
        	}
        }
    }
    public String toString() {
        return "Patron is worth 4 points, its price is " + Arrays.toString(price);
    }
}
