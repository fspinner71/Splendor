import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;

import javax.swing.*;
import java.io.*;
import java.net.URL;
import java.util.*;
import java.awt.*;
import java.awt.image.*;
import java.util.*;
import javax.imageio.ImageIO;
public class SplendorGame extends JPanel implements MouseListener{
    public final int BLACK = 0;
    public final int WHITE = 1;
    public final int GREEN = 2;
    public final int RED = 3;
    public final int BLUE = 4;
    public final int YELLOW = 5;

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
    public int[] tokens;
    public int size;
    public BufferedImage background;
    public SplendorGame(int size)
    {
        this.size = size; //how many players are playign the game

        try {
            this.background = ImageIO.read(SplendorGame.class.getResource("/Images/Background.png"));
        } catch (Exception e) {
            System.out.println("mogus");
            return;
        }


        makeTokens();
        makeLevel1();
        makeLevel2();
        makePatrons();
        makePlayers();
        System.out.println(Arrays.toString(patrons)); //TEST PRINTLINE
        System.out.println(Arrays.toString(cards1)); //TEST PRINTLINE
        System.out.println(Arrays.toString(cards2)); //TEST PRINTLINE
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
        cards1 = new Card[5]; //create stuff
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
            
            //add cards to stuff 
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
        cards2 = new Card[5]; //create stuff
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

    public void makePlayers() {
        players = new Player[size];

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
    }


    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
