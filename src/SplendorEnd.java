import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.util.*;
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
        this.winner = winner+1;
        repaint();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        g.drawImage(player, 325, 30, player.getWidth()/7, player.getHeight()/7, null);
        
            g.drawImage(numbers[winner], 625, 30, numbers[winner].getWidth()/7,  numbers[winner].getHeight()/7, null);

            g.drawImage(wins, 725, 30, wins.getWidth()/7,  wins.getHeight()/7, null);

            g.drawImage(elGato, 570, 130, (int)(elGato.getWidth()/1.2), (int)(elGato.getHeight()/1.2), null);


            g.drawImage(numbers[1], 425, 425, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(numbers[2], 425, 480, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(numbers[3], 425, 535, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(numbers[4], 425, 590, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(period, 455, 425, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(period, 455, 480, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(period, 455, 535, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            g.drawImage(period, 455, 590, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);

            g.drawImage(player, 500, 425,player.getWidth()/10, player.getHeight()/10, null);
            g.drawImage(player, 500, 480, player.getWidth()/10, player.getHeight()/10, null);
            g.drawImage(player, 500, 535, player.getWidth()/10, player.getHeight()/10, null);
            g.drawImage(player, 500, 590, player.getWidth()/10, player.getHeight()/10, null);

        //do the score
        players[0].addnumber(1);
        players[1].addnumber(2);
        players[2].addnumber(3);
        players[3].addnumber(4);

        for(int c = 0; c  < players.length-1; c++) { //orders players in array
            if(players[c].getScore() > players[c+1].getScore()) {
                Player temp = players[c];
                players[c] = players[c+1];
                players[c+1] = temp;
                c=0;
            }
            if(players[c].getScore() == players[c+1].getScore()) {
                if(players[c].numCards() < players[c+1].numCards()) {
                    Player temp = players[c];
                players[c] = players[c+1];
                players[c+1] = temp;
                c=0;
                }

            }
            
        }
       
        for(int c = 0; c < players.length; c++) { //is gonna draw the list of scoreboard like "1. Player 1 -"" depending on player size
        g.drawImage(numbers[players[c].getnumber()], 700, 590-55*c, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            
            if(players[c].getScore() >= 10) {
                int lastdigit = players[c].getScore();
                int firstdigit = lastdigit/10;
                lastdigit  %=10;

                g.drawImage(numbers[firstdigit], 750, 590-55*c, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
                g.drawImage(numbers[lastdigit], 775, 590-55*c, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            }
            else {
                g.drawImage(numbers[players[c].getScore()], 750, 590-55*c, numbers[1].getWidth()/10, numbers[1].getHeight()/10, null);
            }
        }
        

    }

    


}
