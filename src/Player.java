import java.util.*;

public class Player {
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

    public ArrayList<Patron> getPatrons()
    {
    	return patrons;
    }
    
    public ArrayList<Card> getReservedCards()
    {
    	return reservedCards;
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
        int[] gems = new int[5];
        for(int c= 0; c < gems.length; c++) {
            gems[c]=cards[c].size();
        }
        return gems;
    }

    public boolean buyCard(Card c){
        boolean canBuy = false;
        int[] price = c.getPrice();

        for (int i = 0; i < 5; i++) { // check if you have sufficient amounts of each gem to buy this card
            if (this.getGems()[i] + this.tokens[i] < price[i])
                return false;
        }

        for (int i = 0; i < 5; i++) { //remove tokens
            int num_to_remove = price[i] - this.getGems()[i];
            if(num_to_remove > 0) {
            this.tokens[i] -= num_to_remove;
            }
        }

      //add card
        cards[c.getGem()].add(c);
        return true;
    }
    
    public boolean buyPatron(Patron p) {  
        int[] price = p.getPrice(); //price of patron
        for(int c = 0; c < price.length; c++) { //check if you can buy
            if(price[c] > getGems()[c]) {
                return false;
            }

        }
        patrons.add(p); //if you can buy add the patron to the player 
        return true; //return true to the game
}

public void addToken(int colour){
    tokens[colour]++;
}
}