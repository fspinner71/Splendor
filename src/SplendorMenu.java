import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
public class SplendorMenu extends JPanel implements MouseListener {
    BufferedImage background;

    public SplendorMenu () {

        try {
            background = ImageIO.read(SplendorMenu.class.getResource("/image/Background.png"));
            }
            catch (Exception E) {
                System.out.println("menu images failed to load");
                E.printStackTrace();
            }

            

    }
    public void paint(Graphics g)
    {
        super.paint(g);
        g.drawImage(background, 0, 0, getWidth(), getHeight(), null);
        
      
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
}
