import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.*;

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
    public SplendorGame(int size)
    {
        this.size = size; //how many players are playign the game

        
        makeTokens();
        makeCards();
        makePatrons();
        makePlayers();
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
 

        // shuffle patron deck and add the number needed to the array(varies with player size)
    }

    public void makeCards() {

        //take in csv file and make the cards and add it to the arrays and arraylist piles
    }
    public void makePlayers() {
        players = new Player[size];

    }

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawString("HELLO", 600, 300);
    }

    public void createCards() {


    }

    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
