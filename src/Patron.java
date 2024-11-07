import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import java.util.*;
public class Patron {
private static BufferedImage[] images; //holds all the images
private int points;
private int[] price;
private BufferedImage image;

private Button button;
    static {


        images = new BufferedImage[10];

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


        }
        catch (Exception E) {
            System.out.println("Patron images failed to lod");
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
    
    public void paint(Graphics g) {
        //painting the card and stuff
    }
    public String toString() {
        return "Patron is worth 4 points, its price is " + Arrays.toString(price);
    }
}
