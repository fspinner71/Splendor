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
    public int[] getGems() { //return array of gems thingies
        int[] gems = new int[5];
        for(int c = 0; c < cards.length; c++) {
            gems[c] = cards[c].size();
        }
        return gems;
    }

    public boolean buyCard(Card c){
        boolean canBuy = false;
        int[] price = c.getPrice();
        int[] gems = getGems();

        for (int i = 0; i < 5; i++) { // check if you have sufficient amounts of each gem to buy this card
            if (gems[i] + this.tokens[i] < price[i])
                return false;
        }

        for (int i = 0; i < 5; i++) {
            int num_to_remove = price[i] - gems[i];
            this.tokens[i] -= num_to_remove;
        }

        return true;
    }
    
    public ArrayList<Integer> buyPatron(Patron[] p) {  //so basically its gonna take in the array of the patrons on board
        // and then its gonna check if it can buy one and return an arrayList of the ones it can buy using their indexes in the array

        ArrayList<Integer> returned = new ArrayList<>(); //arraylist of indexes that u return

        for(int c = 0; c < p.length; c++) { //outer loop that loops through every patron
            
            if(p[c] != null) { //checks to see patron isnt null
                Patron current = p[c]; //current patron u are looking at 
                Boolean canbuy = true;

                int[] price = current.getPrice(); //gets price of patron
                int[] gems = getGems(); //gems of player 
                
                for (int i = 0; i < 5; i++) { //checks to see if u can buy it
                    if(price[i] > gems[i]) {
                        canbuy = false;
                        break;
                    }
                }
                if (canbuy == true) {
                    returned.add(c); 
                }
            }
        }

        return returned; 
    }
}