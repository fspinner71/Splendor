import java.util.*;

public class Player {
    private int[] tokens;
    private int[] gems;
    private ArrayList<Card>[] cards;
    private ArrayList<Card> reservedCards;
    private ArrayList<Patron> patrons;

    public Player(){
        this.tokens = new int[6];
        this.gems = new int[5];
        this.cards = new ArrayList[5];
        for (int i = 0; i < 5; i++)
            this.cards[i] = new ArrayList<Card>();
        
        this.reservedCards = new ArrayList<Card>();
        this.patrons = new ArrayList<Patron>();
    }

    public int getScore(){
        int score = 0;

        for (int i = 0; i < this.cards.length; i++)
            for (int j = 0; j < this.cards[i].size(); i++)
                score += this.cards[i].get(j).getPoints();

        for (int i = 0; i < this.patrons.size(); i++)
            score += this.patrons.get(i).getPoints();

        return score;
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
    public int[] getGems() { 
        return gems;
    }

    public boolean buyCard(Card c){
        boolean canBuy = false;
        int[] price = c.getPrice();

        for (int i = 0; i < 5; i++) { // check if you have sufficient amounts of each gem to buy this card
            if (this.gems[i] + this.tokens[i] < price[i])
                return false;
        }

        for (int i = 0; i < 5; i++) {
            int num_to_remove = price[i] - this.gems[i];
            this.tokens[i] -= num_to_remove;
        }

        this.gems[c.getGem()]++;
        cards[c.getColor()].add(c);
        
        return true;
    }
    
    public boolean buyPatron(Patron p) {  
        int[] price = p.getPrice(); //price of patron
        for(int c = 0; c < price.length; c++) { //check if you can buy
            if(price[c] > gems[c]) {
                return false;
            }

        }
        patrons.add(p); //if you can buy add the patron to the player 
        return true; //return true to the game
}
}