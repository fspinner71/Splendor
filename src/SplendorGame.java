import java.awt.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;
public class SplendorGame extends JPanel implements MouseListener{
    public static final int BLACK = 0;
    public static final int WHITE = 1;
    public static final int GREEN = 2;
    public static final int RED = 3;
    public static final int BLUE = 4;
    public static final int YELLOW = 5;
    
    private static final int CARDS = 0;
    private static final int PATRONS = 1;
    private static final int RESERVED = 2;

    private int turn;
    private boolean turnOver;
    private Player[] players;
    private boolean gameInProgress;
    private boolean gameStarted;
    private Card[] cards1;
    private Card[] cards2;
    private Card[] cards3;
    private Patron[] patrons;
    private ArrayList<Card> draw1;
    private ArrayList<Card> draw2;
    private ArrayList<Card> draw3;
    private Button draw1Button;
    private Button draw2Button;
    private Button draw3Button;
    private Button[] tokenButtons;
    public int[] tokens;
    public int size;
    public int cardHeight, cardLength;
    int currentPlayerViewing = CARDS;
    public int[] tokenClickCount;
    public int tokenClickCounter;
    private boolean canClickMoreTokens;
    
    private static BufferedImage[] tokenImages;
    public static BufferedImage[] cardBacks;
    public static BufferedImage playerImage;
    static
    {
    	cardBacks = new BufferedImage[3];
    	tokenImages = new BufferedImage[6];
    	try
    	{
	    	cardBacks[0] = ImageIO.read(Patron.class.getResource("/Images/Level1Back.png"));
	        cardBacks[1] = ImageIO.read(Patron.class.getResource("/Images/Level2Back.png"));
	        cardBacks[2] = ImageIO.read(Patron.class.getResource("/Images/Level3Back.png"));
	        
	        tokenImages[0] = ImageIO.read(Patron.class.getResource("/Images/BlackToken.png"));
	        tokenImages[1] = ImageIO.read(Patron.class.getResource("/Images/WhiteToken.png"));
	        tokenImages[2] = ImageIO.read(Patron.class.getResource("/Images/GreenToken.png"));
	        tokenImages[3] = ImageIO.read(Patron.class.getResource("/Images/RedToken.png"));
	        tokenImages[4] = ImageIO.read(Patron.class.getResource("/Images/BlueToken.png"));
	        tokenImages[5] = ImageIO.read(Patron.class.getResource("/Images/YellowToken.png"));
	        
	        playerImage = ImageIO.read(Patron.class.getResource("/Images/Player.png"));
    	} catch(Exception e)
    	{
    		System.out.println("Failed to load SplendorGame images");
    	}
    }
    public SplendorGame(int size)
    {
        this.size = size; //how many players are playign the game

        
        makeTokens();
        makeLevel1();
        makeLevel2();
        makeLevel3();
        makePatrons();
        players = new Player[size];
        
        for(int i = 0; i < size; i++)
        {
        	players[i] = new Player();
        }
        
        System.out.println(Arrays.toString(cards1)); //TEST PRINTLINE
        System.out.println(Arrays.toString(cards2)); //TEST PRINTLINE
        System.out.println(Arrays.toString(cards3)); //TEST PRINTLINE

        draw1Button = new Button(0, 0, Card.WIDTH, Card.HEIGHT, cardBacks[0]);
        draw2Button = new Button(0, 0, Card.WIDTH, Card.HEIGHT, cardBacks[1]);
        draw3Button = new Button(0, 0, Card.WIDTH, Card.HEIGHT, cardBacks[2]);
        
        tokenClickCount = new int[6];
        tokenClickCounter = 0;
        tokenButtons = new Button[6];
        canClickMoreTokens = true;
        for(int i = 0; i < tokenButtons.length; i++)
        {
        	tokenButtons[i] = new Button(0, 0, 100, 100, tokenImages[i]);
        }

        turn = 0;
        turnOver = false; //set turn to 0
        gameStarted = true;
        gameInProgress = true;
        repaint();
        addMouseListener(this);
    }

    public void makeTokens() {

        tokens = new int[6]; //initalize tokens and add 6 of each except yellow
        for(int c  = 0; c < tokens.length-1; c++) {
            if(size == 2) { // change token count for different player sizes
                tokens[c] = 4;
            }
            if(size == 3) {
                tokens[c] = 5;
           }
            if(size == 4) {
                tokens[c] = 7;
            }

        }
        tokens[5] =  5; //5 yellow tokens always


    }
    public void makePatrons() {
        ArrayList<Patron> patronDeck = new ArrayList<Patron>(); //temporary patron deck that will contain all patrons from the csv file
        patrons = new Patron[size+1];  //curent usable patrons
        
        String line; 

        try {
           
            URL tem = SplendorGame.class.getResource("/csv/csvpatron.csv"); //create file reader
            BufferedReader r = new BufferedReader(new InputStreamReader(tem.openStream()));
         
            while((line = r.readLine()) != null) {
                String[] info = line.split(","); //array of the stuff in csv file
                
               
                int[] price = new int[5]; //price array temp

                for(int i = 0; i < info.length-1; i ++) { //loop to convert price to int

                    price[i] = Integer.parseInt(info[i]); //convert to int
                }
                
                Patron temp = new Patron(4, price, Integer.parseInt(info[5])); //create new patron
                patronDeck.add(temp); // add patron
                
            }
            
        }
        catch( Exception E){
            System.out.println("Patron csv file doesn't ork");
          
        }

        
        

        // shuffle patron deck 
        Collections.shuffle(patronDeck);
        for(int a= 0; a < patrons.length; a++) {
            patrons[a] = patronDeck.get(a); //fill usable patrons
        }
        
    }

    public void makeLevel1() {
        String line;
        cards1 = new Card[4]; //create stuff
        draw1 = new ArrayList<>();
        try {
            URL tem = SplendorGame.class.getResource("/csv/lvl1cards.csv"); //create file reader
            BufferedReader r = new BufferedReader(new InputStreamReader(tem.openStream()));
                
            while((line = r.readLine()) != null) {
                String[] info = line.split(","); //array of the stuff in csv file
                
               
                int[] price = new int[5]; //price array temp

                for(int i = 2; i < info.length; i ++) { //loop to convert price to int

                    price[i-2] = Integer.parseInt(info[i]); //convert to int
                }
                
                Card temp = new Card(Integer.parseInt(info[0]), Integer.parseInt(info[1]), 1, price); //create new card
                draw1.add(temp); // add patron
                
            }
        } catch (Exception E) {
            System.out.println("Error on lvl1 cards ");
            E.printStackTrace();
        }

        Collections.shuffle(draw1);

        for(int c = 0; c < cards1.length; c++) {
            cards1[c] = draw1.get(c);
           cards1[c].getButton().setPosition(c*100 + 500, 500);
           
        }
        //remove cards from draw pile
        draw1.remove(0);
        draw1.remove(1);
        draw1.remove(2);
        draw1.remove(3);
        draw1.remove(4);
        
    }
    
    public void makeLevel2() {
        String line;
        cards2 = new Card[4]; //create stuff
        draw2 = new ArrayList<>();
        try {
            URL tem = SplendorGame.class.getResource("/csv/lvl2cards.csv"); //create file reader
            BufferedReader r = new BufferedReader(new InputStreamReader(tem.openStream()));

            while((line = r.readLine()) != null) {
                String[] info = line.split(","); //array of the stuff in csv file
                
               
                int[] price = new int[5]; //price array temp

                for(int i = 2; i < info.length; i ++) { //loop to convert price to int

                    price[i-2] = Integer.parseInt(info[i]); //convert to int
                }
                
                Card temp = new Card(Integer.parseInt(info[0]), Integer.parseInt(info[1]), 2, price); //create new card
                draw2.add(temp); // add patron
                
            }
        } catch (Exception E) {
            System.out.println("Error on lvl2 cards ");
        }

        Collections.shuffle(draw2);

        for(int c = 0; c < cards2.length; c++) {
            cards2[c] = draw2.get(c);
            
            //add cards to stuff 
        }
        //remove cards from draw pile
        draw2.remove(0);
        draw2.remove(1);
        draw2.remove(2);
        draw2.remove(3);
        draw2.remove(4);

    }

    public void makeLevel3() {
        String line;
        cards3 = new Card[4]; //create stuff
        draw3 = new ArrayList<>();
        try {
            URL tem = SplendorGame.class.getResource("/csv/lvl3cards.csv"); //create file reader
            BufferedReader r = new BufferedReader(new InputStreamReader(tem.openStream()));

            while((line = r.readLine()) != null) {
                String[] info = line.split(","); //array of the stuff in csv file
                
               
                int[] price = new int[5]; //price array temp

                for(int i = 2; i < info.length; i ++) { //loop to convert price to int

                    price[i-2] = Integer.parseInt(info[i]); //convert to int
                }
                
                Card temp = new Card(Integer.parseInt(info[0]), Integer.parseInt(info[1]), 3, price); //create new card
                draw3.add(temp); // add patron
                
            }
        } catch (Exception E) {
            System.out.println("Error on lvl3 cards ");
        }

        Collections.shuffle(draw3);

        for(int c = 0; c < cards3.length; c++) {
            cards3[c] = draw3.get(c);
            
            //add cards to stuff 
        }
        //remove cards from draw pile
        draw3.remove(0);
        draw3.remove(1);
        draw3.remove(2);
        draw3.remove(3);
        draw3.remove(4);

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(SplendorMenu.background, 0, 0, getWidth(), getHeight(), null);
        
        //VARIABLES FOR WHERE CARDS SHOULD BE PLACED
        int cardsX = 300;
        int cardsY = 120;
        
        int paddingX = 100;
        int paddingY = 136;
        //PAINT CARDS
        for(int i = 0; i < cards1.length; i++)
        {
        	cards1[i].setPosition(cardsX + paddingX * i, cardsY);
        	cards1[i].paint(g);
        }
        
        for(int i = 0; i < cards2.length; i++)
        {
        	cards2[i].setPosition(cardsX + paddingX * i, cardsY + paddingY);
        	cards2[i].paint(g);
        }
        
        for(int i = 0; i < cards3.length; i++)
        {
        	cards3[i].setPosition(cardsX + paddingX * i, cardsY + 2 * paddingY);
        	cards3[i].paint(g);
        }
        //PAINT DECKS
        if(draw1.size() > 0)
        {
        	draw1Button.setPosition(cardsX + paddingX * cards1.length, cardsY);
        	draw1Button.paint(g);
        }
        
        if(draw2.size() > 0)
        {
        	draw2Button.setPosition(cardsX + paddingX * cards2.length, cardsY + paddingY);
        	draw2Button.paint(g);
        }
        
        if(draw3.size() > 0)
        {
        	draw3Button.setPosition(cardsX + paddingX * cards3.length, cardsY + 2 * paddingY);
        	draw3Button.paint(g);
        }
        
        //VARIABLES FOR WHERE TO PLACE TOKENS
        int tokensX = 200;
        int tokensY = 10;
        
        int tokenPadding = 110;
        
        int textSize = 50;
        
        //DRAW TOKENS
        for(int i = 0; i < tokens.length; i++)
        {
        	if(tokens[i] > 0)
        	{
        		tokenButtons[i].setPosition(tokensX + tokenPadding * i, tokensY);
            	tokenButtons[i].paint(g);
            	
            	g.drawImage(Card.numbers[tokens[i]], (tokensX + tokenPadding * i) + ((tokenButtons[i].getWidth() - textSize)/2), tokensY + ((tokenButtons[i].getHeight() - textSize)/2), textSize, textSize, null);
        	}
        }
        
        int patronsX = 30;
        int patronsY = 125;
        int patronsPadding = 130;
        int patronsRectHeight = patronsPadding * 3;
        
        
        if(patrons[0] != null)
        {
        	patrons[0].setPosition(patronsX, patronsY + patronsRectHeight/2 - patronsPadding);
        	patrons[0].paint(g);
        }
        if(patrons[1] != null)
        {
        	patrons[1].setPosition(patronsX, patronsY + patronsRectHeight/2);
        	patrons[1].paint(g);
        }
        if(patrons[2] != null)
        {
        	patrons[2].setPosition(patronsX + patronsPadding, patronsY);
        	patrons[2].paint(g);
        }
        if(patrons[3] != null)
        {
        	patrons[3].setPosition(patronsX + patronsPadding, patronsY + patronsRectHeight/2 - patronsPadding/2);
        	patrons[3].paint(g);
        }
        if(patrons[4] != null)
        {
        	patrons[4].setPosition(patronsX + patronsPadding, patronsY + patronsRectHeight/2 + patronsPadding/2);
        	patrons[4].paint(g);
        }
        
        int currentTokensX = 900;
        int currentTokensY = 10;
        int currentTokenSize = 100;
        int currentTokenPadding = 110;
        int currentTokenTextSize = 50;
        
        int[] currentTokens = players[turn].getTokens();
        
        for(int i = 0; i < currentTokens.length; i++)
        {
        	if(currentTokens[i] >= 0)
        	{
        		int xPos = currentTokensX;
        		int yPos = currentTokensY + currentTokenPadding * i;
        		g.drawImage(tokenImages[i], xPos, currentTokensY + currentTokenPadding * i, currentTokenSize, currentTokenSize, null);
        		g.drawImage(Card.numbers[currentTokens[i]], xPos + (currentTokenSize - currentTokenTextSize)/2, yPos + (currentTokenSize - currentTokenTextSize)/2, currentTokenTextSize, currentTokenTextSize, null);
        	}
        }
    }

    public void errorScreen(){

    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1) {return;}

        int x = e.getX();
    	int y = e.getY();

        for (int i = 0; i < tokenButtons.length - 1; i++){ // Normal tokens
            if (tokenButtons[i].isInside(x, y)) {
                if (tokenClickCounter == 0){
                    if (tokens[i] == 0) errorScreen();
                    else {
                        tokens[i]--;
                        players[turn].addToken(i);
                        
                        tokenClickCount[i]++;
                    }
                } else if (tokenClickCounter == 1) {
                    if (tokenClickCount[i] == 1){
                        if (tokens[i] < 4) errorScreen();
                        canClickMoreTokens = false;
                    }
                    
                    tokens[i]--;
                    players[turn].addToken(i);
                    tokenClickCount[i]++;
                } else {
                    if (tokenClickCount[i] == 1 || tokens[i] == 0 || !canClickMoreTokens) errorScreen();
                    else {
                        tokens[i]--;
                        players[turn].addToken(i);
                        tokenClickCount[i]++;
                        canClickMoreTokens = false;
                    }
                }
            }

            tokenClickCounter++;
        }

        if (tokenButtons[5].isInside(x, y)){ // Golden tokens, WIP
            tokens[5]--;
        }

        //for ()

        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
