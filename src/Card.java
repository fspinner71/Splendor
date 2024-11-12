import java.awt.*;
import java.awt.image.*;
import java.util.Arrays;
import javax.imageio.ImageIO;

public class Card {
    public static int width;
    public static int height;
    public static BufferedImage[] lvl1cards;
    public static BufferedImage[] lvl2cards;
    public static BufferedImage[] lvl3cards;
    public static BufferedImage[] numbers;
    public static BufferedImage[] cardBacks;
    public static BufferedImage[] costCards;
    public static BufferedImage[] costs;
    
    static {
        lvl1cards = new BufferedImage[5];
        lvl2cards = new BufferedImage[5];
        lvl3cards = new BufferedImage[5];
        numbers = new BufferedImage[10];
        cardBacks = new BufferedImage[3];
        costCards = new BufferedImage[5];
        costs = new BufferedImage[5];
        costs = new BufferedImage[5];
        
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

            numbers[0] = ImageIO.read(Card.class.getResource("/Images/0.png"));
            numbers[1] = ImageIO.read(Card.class.getResource("/Images/1.png"));
            numbers[2] = ImageIO.read(Card.class.getResource("/Images/2.png"));
            numbers[3] = ImageIO.read(Card.class.getResource("/Images/3.png"));
            numbers[4] = ImageIO.read(Card.class.getResource("/Images/4.png"));
            numbers[5] = ImageIO.read(Card.class.getResource("/Images/5.png"));
            numbers[6] = ImageIO.read(Card.class.getResource("/Images/6.png"));
            numbers[7] = ImageIO.read(Card.class.getResource("/Images/7.png"));
            numbers[8] = ImageIO.read(Card.class.getResource("/Images/8.png"));
            numbers[9] = ImageIO.read(Card.class.getResource("/Images/9.png"));
       
            costCards[0] = ImageIO.read(Card.class.getResource("/Images/BlackCost.png"));
            costCards[1] = ImageIO.read(Card.class.getResource("/Images/WhiteCost.png"));
            costCards[2] = ImageIO.read(Card.class.getResource("/Images/GreenCost.png"));
            costCards[3] = ImageIO.read(Card.class.getResource("/Images/RedCost.png"));
            costCards[4] = ImageIO.read(Card.class.getResource("/Images/BlueCost.png"));
            
            costs[0] = ImageIO.read(Card.class.getResource("/Images/BlackCost.png"));
            costs[1] = ImageIO.read(Card.class.getResource("/Images/WhiteCost.png"));
            costs[2] = ImageIO.read(Card.class.getResource("/Images/GreenCost.png"));
            costs[3] = ImageIO.read(Card.class.getResource("/Images/RedCost.png"));
            costs[4] = ImageIO.read(Card.class.getResource("/Images/BlueCost.png"));

            
                } catch (Exception e) {
            System.out.println("you trippin yo images yo");
        }
        width = cardBacks[0].getWidth();
        height = cardBacks[0].getHeight();
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
        button = new Button(0, 0, width/2, height/2, getImage());
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
    
    public BufferedImage getImage() {
        if(isFlipped) {
	        if(level==1) {
	            return lvl1cards[gemColor];
	        }
	        if(level==2) {
	            return lvl2cards[gemColor];
	        }
	        return lvl3cards[gemColor];
	    }
	
        return cardBacks[level-1]; //return card back if not flipped

    }

    public Button getButton() {
        return button;
    }

    public void flip(){
        this.isFlipped = (!isFlipped) ? true : false;
    }
    public String toString() {
        return "Card is worth " +  points + " points. its, gem color is " + gemColor + " its level is " + level + " its price is " + Arrays.toString(price) ;
    }
    private int scaleX(double scale)
    {
    	return (int)(button.getWidth()*scale);
    }
    private int scaleY(double scale)
    {
    	return (int)(button.getHeight()*scale);
    }
    public void paint(Graphics g) {
    	int x = button.getX();
    	int y = button.getY();
    	int width = button.getWidth();
    	int height = button.getHeight();
    	
        if(isFlipped)
        {
        	switch(level)
        	{
        	case 1:
        		button.setImage(lvl1cards[gemColor]);
        		break;
        	case 2:
        		button.setImage(lvl2cards[gemColor]);
        		break;
        	case 3:
        		button.setImage(lvl3cards[gemColor]);
        		break;
        	}
        	
        	button.paint(g);
        	
        	g.drawImage(numbers[points], x, y + scaleY(0.04), scaleX(0.29), scaleY(0.2), null);
        	
        	int offset = 1;
        	for(int i = 0; i < price.length; i++)
        	{
        		if(price[i] != 0)
        		{
        			g.drawImage(costCards[i], x + scaleX(0.04), y + height - scaleY(0.03) - offset * scaleY(0.14), scaleX(0.2), scaleY(0.14), null);
        			g.drawImage(numbers[price[i]], x + scaleX(0.05), y + height - scaleY(0.02) - offset * scaleY(0.14), scaleX(0.19), scaleY(0.12), null);
        			offset++;
        		}
        	}
        } else {
        	button.setImage(cardBacks[level - 1]);
        	button.paint(g);
        }
    }
}
