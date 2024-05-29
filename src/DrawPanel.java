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
    private Rectangle controls = new Rectangle (100,100,100,100);
    private BufferedImage CurrentScreen;
    private String StartScreen = "Title Card";
    private String GameScreen = "Background";
    private String CurrentImage;
    public DrawPanel() {
        this.addMouseListener(this);
        CurrentImage = StartScreen;


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            CurrentScreen = ImageIO.read(new File("images/" + CurrentImage + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        g.drawImage(CurrentScreen, 0, 0, null);
        g.drawRect(690, 530, 425, 140);
    }


    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) { // IF LEFT CLICK
            if (start.contains(clicked)){
                CurrentImage = GameScreen;

            }
            if (controls.contains(clicked)){
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