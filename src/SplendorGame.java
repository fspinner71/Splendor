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
    private int currentPlayerViewing = CARDS;
    private int otherPlayerViewing = RESERVED;
    private int otherPlayer = 1;
    private boolean showOtherTab = false;
    public int[] tokenClickCount;
    public int tokenClickCounter;
    private Button otherTabButton;
    private Button otherLeftButton;
    private Button otherRightButton;
    private Button cardTab;
    private Button reservedTab;
    private Button patronTab;
    private Button otherCardTab;
    private Button otherReservedTab;
    private Button otherPatronTab;
    private Button endTurnButton;
    private boolean canClickMoreTokens;
    public boolean canBuyCard;
    private boolean tokenerror; //token erorr 
    private static BufferedImage[] tokenImages;
    public static BufferedImage[] cardBacks;
    private static BufferedImage[] playerTabs;
    private static BufferedImage[] otherTabs;
    private static BufferedImage leftButton;
    private static BufferedImage rightButton;
    private static BufferedImage upButton;
    private static BufferedImage downButton;
    public static BufferedImage playerImage;
    private static BufferedImage endTurnImage;
    static
    {
    	cardBacks = new BufferedImage[3];
    	tokenImages = new BufferedImage[6];
    	playerTabs = new BufferedImage[3];
    	otherTabs = new BufferedImage[3];
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
	        
	        playerTabs[CARDS] = ImageIO.read(Patron.class.getResource("/Images/PlayerTabCards.png"));
	        playerTabs[PATRONS] = ImageIO.read(Patron.class.getResource("/Images/PlayerTabPatrons.png"));
	        playerTabs[RESERVED] = ImageIO.read(Patron.class.getResource("/Images/PlayerTabReserved.png"));
	        
	        otherTabs[CARDS] = ImageIO.read(Patron.class.getResource("/Images/OtherTabCards.png"));
	        otherTabs[PATRONS] = ImageIO.read(Patron.class.getResource("/Images/OtherTabPatrons.png"));
	        otherTabs[RESERVED] = ImageIO.read(Patron.class.getResource("/Images/OtherTabReserved.png"));
	        
	        leftButton = ImageIO.read(Patron.class.getResource("/Images/LeftButton.png"));
	        rightButton = ImageIO.read(Patron.class.getResource("/Images/RightButton.png"));
	        upButton = ImageIO.read(Patron.class.getResource("/Images/UpButton.png"));
	        downButton = ImageIO.read(Patron.class.getResource("/Images/DownButton.png"));
	        
	        endTurnImage = ImageIO.read(Patron.class.getResource("/Images/EndTurnButton.png"));
	        
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
        
        otherTabButton  = new Button(0, 0, 25, 25, upButton);
        
        otherLeftButton = new Button(0, 0, 40, 40, leftButton);
        otherRightButton = new Button(0, 0, 40, 40, rightButton);
        
        endTurnButton = new Button(100,100,100,100,endTurnImage);
        
        cardTab = new Button(1085, 75, 30, 75, null);
        patronTab = new Button(1085, 150, 30, 90, null);
        reservedTab = new Button(1085, 240, 30, 100, null);
        
        otherCardTab = new Button(235, 520, 85, 35, null);
        otherPatronTab = new Button(135, 520, 100, 35, null);
        otherReservedTab = new Button(15, 520, 120, 35, null);
        
        tokenClickCount = new int[6];
        tokenClickCounter = 0;
        tokenButtons = new Button[6];
        for(int i = 0; i < tokenButtons.length; i++)
        {
        	tokenButtons[i] = new Button(0, 0, 100, 100, tokenImages[i]);
        }

        turn = 0;
        turnOver = false; //set turn to 0
        gameStarted = true;
        gameInProgress = true;
        canBuyCard = true;
        canClickMoreTokens = true;
        tokenerror = false;
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
        int cardsX = 400;
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
        int tokensX = 250;
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
        
        int patronsX = 100;
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
        
        int currentTokensX = 960;
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
        		g.drawImage(tokenImages[i], xPos, yPos, currentTokenSize, currentTokenSize, null);
        		g.drawImage(Card.numbers[currentTokens[i]], xPos + (currentTokenSize - currentTokenTextSize)/2, yPos + (currentTokenSize - currentTokenTextSize)/2, currentTokenTextSize, currentTokenTextSize, null);
        	}
        }
        
        int viewTabX = 1080;
        int viewTabY = 75;
        int viewTabWidth = 200;
        int viewTabHeight = 595;
        
        g.drawImage(playerTabs[currentPlayerViewing], viewTabX, viewTabY, viewTabWidth, viewTabHeight, null);
        
        int currentPlayerX = 1070;
        int currentPlayerY = 20;
        int currentPlayerHeight = 40;
        int currentPlayerWidth = 135;
        int currentPlayerSpace = 10;
        
        g.drawImage(playerImage, currentPlayerX, currentPlayerY, currentPlayerWidth, currentPlayerHeight, null);
        g.drawImage(Card.numbers[turn], currentPlayerX + currentPlayerWidth + currentPlayerSpace, currentPlayerY, currentPlayerHeight, currentPlayerHeight, null);
        
        int otherTokensX = 70;
        int otherTokenxY = 550;
        int otherTokensSize = 100;
        int otherTokensPadding = 110;
        int otherTokensTextSize = 50;
        
        int[] otherTokens = players[otherPlayer].getTokens();
        
        for(int i = 0; i < otherTokens.length; i++)
        {
        	if(otherTokens[i] >= 0)
        	{
        		int xPos = otherTokensX + otherTokensPadding * i;
        		int yPos = otherTokenxY;
        		g.drawImage(tokenImages[i], xPos, yPos, otherTokensSize, otherTokensSize, null);
        		g.drawImage(Card.numbers[otherTokens[i]], xPos + (otherTokensSize - otherTokensTextSize)/2, yPos + (otherTokensSize - otherTokensTextSize)/2, otherTokensTextSize, otherTokensTextSize, null);
        	}
        }
        
        int otherPlayerX = 20;
        int otherPlayerY = 485;
        int otherPlayerHeight = 40;
        int otherPlayerWidth = 135;
        int otherPlayerSpace = 10;
        
        g.drawImage(playerImage, otherPlayerX, otherPlayerY, otherPlayerWidth, otherPlayerHeight, null);
        g.drawImage(Card.numbers[otherPlayer], otherPlayerX + otherPlayerWidth + otherPlayerSpace, otherPlayerY, otherPlayerHeight, otherPlayerHeight, null);
        
        int scrollButtonsY = 580;
        int leftButtonX = 25;
        int rightButtonX = 725;
        
        otherLeftButton.setPosition(leftButtonX, scrollButtonsY);
        otherRightButton.setPosition(rightButtonX, scrollButtonsY);
        
        otherLeftButton.paint(g);
        otherRightButton.paint(g);
        
        int otherTabX = 10;
        int otherTabY = 520;
        int otherTabWidth = 700;
        int otherTabHeight = 200;
        
        int otherTabHiddenY = 650;
        
        int tabButtonX = 325;
        int tabButtonY = 525;
        int tabButtonHiddenY = 655;
        
        if(showOtherTab)
        {
        	g.drawImage(otherTabs[otherPlayerViewing], otherTabX, otherTabY, otherTabWidth, otherTabHeight, null);
        	otherTabButton.setImage(downButton);
        	otherTabButton.setPosition(tabButtonX, tabButtonY);
        	otherTabButton.paint(g);
        	
        } else {
        	g.drawImage(otherTabs[otherPlayerViewing], otherTabX, otherTabHiddenY, otherTabWidth, otherTabHeight, null);
        	otherTabButton.setImage(upButton);
        	otherTabButton.setPosition(tabButtonX,  tabButtonHiddenY);
        	otherTabButton.paint(g);
        }
        
        switch(currentPlayerViewing)
        {
        case CARDS:
        	
        	int currentCardsX = 1120;
        	int currentCardsY = 100;
        	int currentCardsPaddingX = 20;
        	int currentCardsPaddingY = 110;
        	double currentCardsScale = 0.8;
        	
        	ArrayList<Card>[] currentCards = players[turn].getCards();
        	
        	for(int i = 0; i < currentCards.length; i++)
        	{
        		for(int j = 0; j < currentCards[i].size(); j++)
        		{
        			Card card = currentCards[i].get(j);
        			card.scale(currentCardsScale, currentCardsScale);
        			card.setPosition(currentCardsX + currentCardsPaddingX * j, currentCardsY + currentCardsPaddingY * i);
        			card.paint(g);
        		}
        	}
        	
        	break;
        case PATRONS:
        	int currentPatronsX = 1140;
        	int currentPatronsY = 100;
        	int currentPatronsPadding = 110;
        	double currentPatronsScale = 0.8;
        	
        	ArrayList<Patron> currentPatrons = players[turn].getPatrons();
        	
        	for(int i = 0; i < currentPatrons.size(); i++)
        	{
        			Patron patron = currentPatrons.get(i);
        			patron.scale(currentPatronsScale, currentPatronsScale);
        			patron.setPosition(currentPatronsX, currentPatronsY + currentPatronsPadding * i);
        			patron.paint(g);
        	}
        	
        	break;
        case RESERVED:
        	
        	int currentReservedX = 1155;
        	int currentReservedY = 100;
        	int currentReservedPadding = 110;
        	double currentReservedScale = 0.8;
        	
        	ArrayList<Card> currentReserved = players[turn].getReservedCards();
        	
        	for(int i = 0; i < currentReserved.size(); i++)
        	{
        			Card card = currentReserved.get(i);
        			card.scale(currentReservedScale, currentReservedScale);
        			card.setPosition(currentReservedX, currentReservedY + currentReservedPadding * i);
        			card.paint(g);
        	}
        	
        	break;
        }
        
        if(showOtherTab)
        {
        	switch(otherPlayerViewing)
        	{
        	case CARDS:
        		int currentCardsX = 40;
            	int currentCardsY = 570;
            	int currentCardsPaddingX = 130;
            	int currentCardsPaddingExtra = 15;
            	double currentCardsScale = 0.8;
            	
            	ArrayList<Card>[] currentCards = players[otherPlayer].getCards();
            	
            	for(int i = 0; i < currentCards.length; i++)
            	{
            		for(int j = 0; j < currentCards[i].size(); j++)
            		{
            			Card card = currentCards[i].get(j);
            			card.scale(currentCardsScale, currentCardsScale);
            			card.setPosition(currentCardsX + currentCardsPaddingX * i + currentCardsPaddingExtra * j, currentCardsY);
            			card.paint(g);
            		}
            	}
        		break;
        	case PATRONS:
        		int currentPatronsX = 40;
            	int currentPatronsY = 570;
            	int currentPatronsPadding = 110;
            	double currentPatronsScale = 0.8;
            	
            	ArrayList<Patron> currentPatrons = players[otherPlayer].getPatrons();
            	
            	for(int i = 0; i < currentPatrons.size(); i++)
            	{
            			Patron patron = currentPatrons.get(i);
            			patron.scale(currentPatronsScale, currentPatronsScale);
            			patron.setPosition(currentPatronsX + currentPatronsPadding * i, currentPatronsY);
            			patron.paint(g);
            	}
            	
        		break;
        	case RESERVED:
        		int currentReservedX = 40;
            	int currentReservedY = 570;
            	int currentReservedPadding = 110;
            	double currentReservedScale = 0.8;
            	
            	ArrayList<Card> currentReserved = players[otherPlayer].getReservedCards();
            	
            	for(int i = 0; i < currentReserved.size(); i++)
            	{
            			Card card = currentReserved.get(i);
            			card.scale(currentReservedScale, currentReservedScale);
            			card.setPosition(currentReservedX + currentReservedPadding * i, currentReservedY);
            			card.paint(g);
            	}
        		break;
        	}
        }
    }

    public void nextTurn() { //turn moves 0-3 
        if(turn == players.length-1) {
            turn = 0;
        }
        else {
            turn++;
        }
        tokenClickCount = new int[6]; //reset token vairalbes so next player can click stuff
        tokenClickCounter = 0;
        canClickMoreTokens = true;
        System.out.println("next turn");
    }

    public void errorScreen(){
System.out.println("error panel pops up");
    }
    

    public void buyCard(Card c, int index) {
        boolean x = players[turn].buyCard(c);
        if(x == true) { //if player does buy card
            int[] price = c.getPrice();
            int[] gems = players[turn].getGems();
            
            gems[c.getGem()]--; //remove one gem from the color that the card was boight bc it was just added ykyk
            canBuyCard = false;
            for (int i = 0; i < 5; i++) { //add back tokens to game
                
                int add = price[i] - gems[i];
                if(add > 0) {
                this.tokens[i] += add;
                System.out.println("added " + add + " " + i);
                }
            }
         
            int level = c.getLevel(); //remove card and replace it 
            if(level ==1) {
                cards1[index] = draw1.get(0);
                draw1.remove(0);
            }
            if(level ==2) {
                cards2[index] = draw2.get(0);
                draw2.remove(0);
            }
            if(level ==3) {
                cards3[index] = draw3.get(0);
                draw3.remove(0);
            }
            nextTurn();
        }
        if(x==false) {
            errorScreen();
        }
    }

    public void buyPatron(){
        
    }

    public void reserveCard(Card c, int index){
        //players[turn].addReservedCards(c);
    }

    public void mousePressed(MouseEvent e) {
        if(e.getButton() != MouseEvent.BUTTON1) {return;}

        int x = e.getX();
    	int y = e.getY();
    	
    	if(otherTabButton.isInside(x, y))
    	{
    		showOtherTab = !showOtherTab;
    	}
    	
    	if(!showOtherTab)
    	{
    		if(otherLeftButton.isInside(x, y))
    		{
    			otherPlayer--;
    			if(otherPlayer == turn)
    			{
    				otherPlayer--;
    			}
    			if(otherPlayer < 0)
    			{
    				otherPlayer = size - 1;
    			}
    			if(otherPlayer == turn)
    			{
    				otherPlayer--;
    			}
    		} else if(otherRightButton.isInside(x, y))
    		{
    			otherPlayer++;
    			if(otherPlayer == turn)
    			{
    				otherPlayer++;
    			}
    			if(otherPlayer >= size)
    			{
    				otherPlayer = 0;
    			}
    			if(otherPlayer == turn)
    			{
    				otherPlayer++;
    			}
    		}
    	}
    	
    	if(cardTab.isInside(x,y))
    	{
    		currentPlayerViewing = CARDS;
    	}
    	if(patronTab.isInside(x,y))
    	{
    		currentPlayerViewing = PATRONS;
    	}
    	if(reservedTab.isInside(x,y))
    	{
    		currentPlayerViewing = RESERVED;
    	}
    	
    	if(showOtherTab)
    	{
    		if(otherCardTab.isInside(x,y))
        	{
        		otherPlayerViewing = CARDS;
        	}
        	if(otherPatronTab.isInside(x,y))
        	{
        		otherPlayerViewing = PATRONS;
        	}
        	if(otherReservedTab.isInside(x,y))
        	{
        		otherPlayerViewing = RESERVED;
        	}
    	}

        for (int i = 0; i < tokenButtons.length - 1; i++){ // Clicking Normal tokens
            if (tokenButtons[i].isInside(x, y)) {
                if (tokenClickCounter == 0){
                    if (tokens[i] == 0 || players[turn].getTotalTokenCount() >= 9) { errorScreen(); tokenerror = true;}
                    else {
                        tokens[i]--;
                        players[turn].addToken(i);
                        
                        tokenClickCount[i]++;
                    }
                } else if (tokenClickCounter == 1) {
                    if (tokenClickCount[i] == 1){
                        if (tokens[i] < 3) { errorScreen(); break;} //3 cuz if u draw one from 4 itll be 3yknow
                        else {

                        tokens[i]--;
                        players[turn].addToken(i);
                        tokenClickCount[i]++;
                        canClickMoreTokens = false;
                        nextTurn();
                        System.out.println("draws 2");
                        break;
                        }
                    }
                    
                    if (players[turn].getTotalTokenCount() >= 9) { errorScreen(); break;}

                    tokens[i]--;
                    players[turn].addToken(i);
                    tokenClickCount[i]++;
                    
                } else {
                    if (tokenClickCount[i] == 1 || tokens[i] == 0 || !canClickMoreTokens) { errorScreen(); break;}
                    else {
                        tokens[i]--;
                        players[turn].addToken(i);
                        tokenClickCount[i]++;
                        canClickMoreTokens = false;
                        nextTurn();
                        break;
                    }
                }
              
                tokenClickCounter++;
              
             
            }
        }

        if (tokenButtons[5].isInside(x, y)){ // Golden tokens, WIP
            tokens[5]--;
            players[turn].addToken(5);
        }

       
        for(int c = 0; c < cards1.length; c++) { //if u click lvl 1 card

            if(canBuyCard) { 
            if(cards1[c].getButton().isInside(x,y)) {
                int[] tokArr = players[turn].getTokens();
                if (tokArr[5] > 0) reserveCard(cards1[c], c);
                else buyCard(cards1[c], c);
            }
        }
        else { //if canbuycard is false u go error
            errorScreen();
        }
           
        }
        for(int c = 0; c < cards2.length; c++) { //if u click lvl 2 card

            if(canBuyCard) { 
                if(cards2[c].getButton().isInside(x,y)) {
                int[] tokArr = players[turn].getTokens();
                if (tokArr[5] > 0) reserveCard(cards2[c], c);
                else buyCard(cards2[c], c);
                }
            }
            else { //if canbuycard is false u go error
                errorScreen();
            }
        }
        for(int c = 0; c < cards3.length; c++) { //if u click lvl 2 card

            if(canBuyCard) { 
                if(cards3[c].getButton().isInside(x,y)) {
                int[] tokArr = players[turn].getTokens();
                if (tokArr[5] > 0) reserveCard(cards3[c], c);
                else buyCard(cards3[c], c);
                }
            }
            else { //if canbuycard is false u go error
                errorScreen();
            }
        
    }


        repaint();
    }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}