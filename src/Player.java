import java.util.*;

public class Player {
	private int[] tokens;

	private ArrayList<Card>[] cards;
	private ArrayList<Card> reservedCards;
	private ArrayList<Patron> patrons;
	private int thenumber;

	public Player() {
		this.tokens = new int[6];

		this.cards = new ArrayList[5];
		for (int i = 0; i < 5; i++)
			this.cards[i] = new ArrayList<Card>();

		this.reservedCards = new ArrayList<Card>();
		this.patrons = new ArrayList<Patron>();
	}

	public int getScore() {
		int score = 0;

		for (int i = 0; i < this.cards.length; i++)
			for (int j = 0; j < this.cards[i].size(); j++)
				score += this.cards[i].get(j).getPoints();

		for (int i = 0; i < this.patrons.size(); i++)
			score += this.patrons.get(i).getPoints();

		return score;
	}

	public ArrayList<Card>[] getCards() {
		return cards;
	}

	public void addCard(Card c) { // addcard for testig
		cards[c.getGem()].add(c);

	}

	public ArrayList<Patron> getPatrons() {
		return patrons;
	}

	public ArrayList<Card> getReservedCards() {
		return reservedCards;
	}

	public int getTotalTokenCount() {
		int sum = 0;
		for (int i = 0; i < this.tokens.length; i++) // GET THE TOTAL NUMBER OF TOKENS BY ADDING ALL TOKEN COUNTS OF ALL
														// COLORS
			sum += this.tokens[i];
		return sum;
	}

	public int[] getTokens() {
		return tokens;
	}

	public int[] getGems() {
		int[] gems = new int[5];
		for (int c = 0; c < gems.length; c++) {
			gems[c] = cards[c].size();
		}
		return gems;
	}

	public int numCards() {
		int num = 0;
		for (int c = 0; c < cards.length; c++) {
			for (int i = 0; i < cards[c].size(); i++) {
				num++;
			}
		}
		return num;

	}

	public int numcardswithpoints() { // in case of double tie
		int num = 0;
		for (int c = 0; c < cards.length; c++) {
			for (int i = 0; i < cards[c].size(); i++) {
				if (cards[c].get(i).getPoints() != 0) {
					num++;
				}
			}
		}
		return num;

	}

	public int[] buyCard(Card c) {
		int[] price = c.getPrice();

		int[] usedTokens = new int[6];
		int[] buyingPower = new int[5];

		for (int i = 0; i < 5; i++) {
			buyingPower[i] = tokens[i] + cards[i].size();
		}

		int totalDiff = 0;

		for (int i = 0; i < 5; i++) {
			if (price[i] > buyingPower[i]) {
				totalDiff += price[i] - buyingPower[i];
				usedTokens[i] = buyingPower[i];
			} else {
				usedTokens[i] += price[i];
			}
		}

		if (tokens[5] >= totalDiff) {
			for (int i = 0; i < 5; i++) {
				usedTokens[i] -= cards[i].size();
				
				if(usedTokens[i] < 0)
				{
					usedTokens[i] = 0;
				}
			}
			usedTokens[5] = totalDiff;
		} else {
			return null;
		}
		System.out.print("Used tokens: [");
		for (int i = 0; i < 6; i++) {
			System.out.print(usedTokens[i] + ", ");
			tokens[i] -= usedTokens[i];
		}
		System.out.println("]");

		// add card
		cards[c.getGem()].add(c);
		return usedTokens;
	}

	public boolean buyPatron(Patron p) {
		int[] price = p.getPrice(); // price of patron
		for (int c = 0; c < price.length; c++) { // check if you can buy
			if (price[c] > getGems()[c]) {
				return false;
			}

		}
		patrons.add(p); // if you can buy add the patron to the player
		return true; // return true to the game
	}

	public boolean canbuypatron(Patron[] x) { // check if player can buy a patron so it will prompt them to buy one
		boolean canbuy;
		for (Patron a : x) {
			if (a != null) {

				canbuy = true;
				int[] price = a.getPrice();
				for (int c = 0; c < price.length; c++) { // check if you can buy
					if (price[c] > getGems()[c]) {
						canbuy = false;
					}

				}
				if (canbuy == true) {
					return true;
				}
			}
		}
		return false;

	}

	public void addToken(int colour) {
		tokens[colour]++;
	}

	public void addReservedCards(Card c) {
		this.reservedCards.add(c);
	}

	public void addnumber(int num) {
		thenumber = num;
	}

	public int getnumber() {
		return thenumber;
	}

	public void removeToken(int a) {
		tokens[a]--;
	}
}