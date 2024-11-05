import java.util.*;
import java.awt.image.*;
import javax.swing.*;
import javax.imageio.ImageIO;

public class Card {
    public static BufferedImage[5] lvl1cards;



    private int points;
    private int gem_col;
    private int level;
    private int[] price;
    private Button button;

    public Card(int points, int gemColor, int level, int[] price){
        this.points = points;
        this.gem_col = gemColor;
        this.level = level;
        this.price = price;
    }
}
