import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class SplendorEnd extends JPanel {
    private static BufferedImage background;
    private static BufferedImage elGato;
    private static BufferedImage player;
    private static BufferedImage wins;
    private static BufferedImage period;
    private SplendorFrame parentFrame;
    private int winner;
    private Player[] players;
    public static BufferedImage[] numbers;
    static {
        try {
            background = ImageIO.read(SplendorEnd.class.getResource("/Images/background.png"));
            
            numbers = new BufferedImage[10];
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

            elGato = ImageIO.read(Card.class.getResource("/Images/ElGato.png"));
            wins = ImageIO.read(Card.class.getResource("/Images/Wins.png"));
            player = ImageIO.read(Card.class.getResource("/Images/Player.png"));
            period = ImageIO.read(Card.class.getResource("/Images/Period.png"));
            System.out.println("end panel loaded");
        } catch (Exception e) {
            System.out.println("splenodr end images failed to load");
            e.printStackTrace();
        }
        
    }
    public SplendorEnd (Player[] x) //takes in winner and players
    {     
    	players = x;
    	getWinner(x);
    	winner = 0;
    }
    public void getWinner(Player[] a) {
        int winner = 0;
        for(int c = 0; c < a.length-1; c++) {
            if(a[c+1].getScore() > a[c].getScore()) {
                winner = c+1;
            }
            if(a[c+1].getScore() == a[c].getScore()) {
                if(a[c+1].numCards() < a[c].numCards()) {
                    winner = c+1;
                }
                if(a[c+1].numCards()  == a[c].numCards()) {
                    if(a[c+1].numcardswithpoints() < a[c].numcardswithpoints()) {
                        winner = c+1;
                    }
                }
            }
        }
        this.winner = winner;

    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(elGato, 600, 200, elGato.getWidth()*5, elGato.getHeight(), null);
        //do the score

        

    }

    


}
