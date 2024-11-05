import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class SplendorMenu extends JPanel implements MouseListener {
    public void paint(Graphics g)
    {
        super.paint(g);
        g.setColor(Color.black);
        g.drawString("HELLO", 600, 300);
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
