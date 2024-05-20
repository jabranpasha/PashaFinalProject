import java.awt.event.MouseListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.*;
import java.util.*;
import java.awt.Font;
import java.io.*;
public class DrawPanel extends JPanel implements MouseListener {
    private Rectangle start = new Rectangle (360, 400, 200, 75);
    private BufferedImage CurrentScreen;
    private File StartScreen = new File("images/Title Card.png");
    private File GameScreen = new File("images/Untitled.png");
    public DrawPanel() {


    }
    protected void paintComponent(Graphics g, File f) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        try {
            CurrentScreen = ImageIO.read(f);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(CurrentScreen, 0, 0, null);
    }


    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) { // IF LEFT CLICK
            if (start.contains(clicked)){
                paintComponent(g, GameScreen);

            }


            }
        if (e.getButton() == 3) { // IF RIGHT CLICK CARD

        }
    }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mouseClicked(MouseEvent e) { }

}