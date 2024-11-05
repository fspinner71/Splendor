import java.util.*;

public class Player {
    private int score;
    private int[] tokens;
    private ArrayList<Card>[] cards;
    private ArrayList<Card> reservedCards;
    private ArrayList<Patron> patrons;

    public Player(){
        this.tokens = new int[6];
        
        this.cards = new ArrayList[5];
        for (int i = 0; i < 5; i++)
            this.cards[i] = new ArrayList<Card>();
        
        this.reservedCards = new ArrayList<Card>();
        this.patrons = new ArrayList<Patron>();
    }

    public int getScore(){
        return this.score;
    } 

    public ArrayList<Card>[] getCards(){
        return cards;
    }

    public int getTotalTokenCount(){
        int sum = 0;
        for (int i = 0; i < this.tokens.length; i++) // GET THE TOTAL NUMBER OF TOKENS BY ADDING ALL TOKEN COUNTS OF ALL COLORS
            sum += this.tokens[i];
        return sum;
    }

    public int[] getTokens(){
        return tokens;
    }
    public int[] getGems() { //return array of gems thingies
        int[] gems = new int[5];
        for(int c = 0; c < cards.length; c++) {
            gems[c] = cards[c].size();
        }
        return gems;
    }

    
}