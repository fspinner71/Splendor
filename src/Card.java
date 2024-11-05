import java.awt.image.*;

public class Card {
    public static BufferedImage[] lvl1cards;
    public static BufferedImage[] lvl2cards;
    public static BufferedImage[] lvl3cards;

    public static BufferedImage[] cardBacks;

    static {
        lvl1cards = new BufferedImage[5];
        lvl2cards = new BufferedImage[10];
        lvl3cards = new BufferedImage[10];

        cardBacks = new BufferedImage[3];
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
}
