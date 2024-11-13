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
        } catch (Exception e) {
            System.out.println("splenodr end images failed to load");
            e.printStackTrace();
        }
        
    }
    public SplendorEnd (int winner, Player[] x) //takes in winner and players
    {     
    	players = x;
    	this.winner = winner;
    	
    }

    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(elGato, -600, 200, elGato.getWidth(), elGato.getHeight(), null);

        for(int c = 0; c  < players.length-1; c++) { //orders players in array
            if(players[c].getScore() > players[c+1].getScore()) {
                Player temp = players[c];
                players[c] = players[c+1];
                players[c+1] = temp;
                c=0;
            }
        }

        for(int c = 0; c < players.length; c++) { //is gonna draw the list of scoreboard like "1. Player 1 -"" depending on player size
            g.drawImage(numbers[c+1], 400, 500 + 100*c, 50, 100, null);
            
            
        }

    }

    


}
