import java.awt.event.MouseListener;
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

    public DrawPanel() {


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int x = 0;
        int y = 0;
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/Title Card.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(image, 0, 0, null);
    }


    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) { // IF LEFT CLICK


            }
        if (e.getButton() == 3) { // IF RIGHT CLICK CARD

        }
    }

    public void mouseReleased(MouseEvent e) { }

    public void mouseEntered(MouseEvent e) { }

    public void mouseExited(MouseEvent e) { }

    public void mouseClicked(MouseEvent e) { }

}