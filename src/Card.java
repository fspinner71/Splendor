import java.awt.image.*;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class Card {
    public static BufferedImage[] lvl1cards;
    public static BufferedImage[] lvl2cards;
    public static BufferedImage[] lvl3cards;

    public static BufferedImage[] cardBacks;

    static {
        lvl1cards = new BufferedImage[5];
        lvl2cards = new BufferedImage[5];
        lvl3cards = new BufferedImage[5];

        cardBacks = new BufferedImage[3];

        // black white green red blue 
        try {
            cardBacks[0] = ImageIO.read(Patron.class.getResource("/Images/Level1Back.png"));
            lvl1cards[0] = ImageIO.read(Patron.class.getResource("/Images/BlackLevel1Card.png"));
            lvl1cards[1] = ImageIO.read(Patron.class.getResource("/Images/WhiteLevel1Card.png"));
            lvl1cards[2] = ImageIO.read(Patron.class.getResource("/Images/GreenLevel1Card.png"));
            lvl1cards[3] = ImageIO.read(Patron.class.getResource("/Images/RedLevel1Card.png"));
            lvl1cards[4] = ImageIO.read(Patron.class.getResource("/Images/BlueLevel1Card.png"));

            cardBacks[1] = ImageIO.read(Patron.class.getResource("/Images/Level2Back.png"));
            lvl2cards[0] = ImageIO.read(Patron.class.getResource("/Images/BlackLevel2Card.png"));
            lvl2cards[1] = ImageIO.read(Patron.class.getResource("/Images/WhiteLevel2Card.png"));
            lvl2cards[2] = ImageIO.read(Patron.class.getResource("/Images/GreenLevel2Card.png"));
            lvl2cards[3] = ImageIO.read(Patron.class.getResource("/Images/RedLevel2Card.png"));
            lvl2cards[4] = ImageIO.read(Patron.class.getResource("/Images/BlueLevel2Card.png"));

            cardBacks[2] = ImageIO.read(Patron.class.getResource("/Images/Level3Back.png"));
            lvl3cards[0] = ImageIO.read(Patron.class.getResource("/Images/BlackLevel3Card.png"));
            lvl3cards[1] = ImageIO.read(Patron.class.getResource("/Images/WhiteLevel3Card.png"));
            lvl3cards[2] = ImageIO.read(Patron.class.getResource("/Images/GreenLevel3Card.png"));
            lvl3cards[3] = ImageIO.read(Patron.class.getResource("/Images/RedLevel3Card.png"));
            lvl3cards[4] = ImageIO.read(Patron.class.getResource("/Images/BlueLevel3Card.png"));
        } catch (Exception e) {
            System.out.println("you trippin yo images yo");
        }
    }


    private int points;
    private int gemColor;
    private int level;
    private int[] price; 
    private Button button;
    private boolean isFlipped;

    public Card(int points, int gemColor, int level, int[] price){
        this.points = points;
        this.gemColor = gemColor;
        this.level = level;
        this.price = price;
        this.isFlipped = false;
    }

    public int getPoints(){
        return this.points;
    }

    public int getGem(){
        return this.gemColor;
    }

    public int[] getPrice(){
        return this.price;
    }



    public void flip(){
        this.isFlipped = (!isFlipped) ? true : false;
    }
    public String toString() {
        return "Card is worth " +  points + " points. its, gem color is " + gemColor + " its level is " + level + " its price is " + Arrays.toString(price) ;
    }
}
