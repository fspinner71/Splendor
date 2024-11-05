import java.awt.image.*;

public class Patron {
private static BufferedImage[] images; //holds all the imes
private int points;
private int[] price;
private int image;
private Button button;
    static {
        images = new BufferedImage[10];
        //NEEDED CODE ONCE WE IMPORT THE IMAGES
    }

    public Patron(int p, int[] pr, int i, Button b) {  //points/price/integer that corresponds with the array for the image
        points = p;
        price = pr;
        image = i;
        button = b;
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
    
    public void paint() {
        //xx
    }

}
