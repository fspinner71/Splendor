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
    private ArrayList<Card> draw1;
    private ArrayList<Card> draw2;
    private ArrayList<Card> draw3;

    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.BLACK);
        g.drawString("HELLO", 600, 300);
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
