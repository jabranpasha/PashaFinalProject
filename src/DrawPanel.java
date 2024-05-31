import java.awt.event.KeyEvent;
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
public class DrawPanel extends JPanel implements MouseListener, KeyListener {
    private boolean[] pressedKeys;
    private Button start = new Button (690 ,530 ,425 ,140, true);
    private Button controls = new Button (190 ,530 ,425 ,140, true);
    private Button back = new Button( 745 ,610 ,500 ,100,false);
    private BufferedImage CurrentScreen;
    private BufferedImage PacMan;
    private String CurrentPacMan;
    private String CurrentImage;
    private String StartScreen = "Title Card";
    private String ControlScreen = "Controls";
    private String GameScreen = "Background";
    private String PacManRight = "Pacman right";
    private String PacManLeft = "Pacman left";
    private String PacManDown ="Pacman down";
    private String PacManUp = "Pacman up";
    private String blank = "blank";
    private int x;
    private int y;
    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        CurrentImage = StartScreen;
        CurrentPacMan = blank;
        x = 626;
        y = 300;


    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        try {
            CurrentScreen = ImageIO.read(new File("images/" + CurrentImage + ".png"));
            PacMan = ImageIO.read(new File("images/" + CurrentPacMan + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        pressedKeys = new boolean[128];
        g.drawImage(CurrentScreen, 0, 0, null);
        g.drawImage(PacMan, x, y,null);
    }


    public void mousePressed(MouseEvent e) {

        Point clicked = e.getPoint();

        if (e.getButton() == 1) { // IF LEFT CLICK
            if (start.contains(clicked) && start.isClickable()) {
                CurrentImage = GameScreen;
                CurrentPacMan = PacManRight;
                start.setClickable(false);
                controls.setClickable(false);

            }
            if (controls.contains(clicked) && controls.isClickable()) {
                CurrentImage = ControlScreen;
                start.setClickable(false);
                controls.setClickable(false);
                back.setClickable(true);
            }
            if (back.contains(clicked) && back.isClickable()) {
                CurrentImage = StartScreen;
                start.setClickable(true);
                controls.setClickable(true);
                back.setClickable(false);
            }
        }
        if (e.getButton() == 3) { // IF RIGHT CLICK CARD

        }
    }
    public void mouseReleased(MouseEvent e) { }
    public void mouseEntered(MouseEvent e) { }
    public void mouseExited(MouseEvent e) { }
    public void mouseClicked(MouseEvent e) { }


    @Override
    public void keyTyped(KeyEvent e) {}
    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = true;
        if (pressedKeys[38]){
            CurrentPacMan = PacManUp;
            y-=20;

        }
        if (pressedKeys[37]){
            CurrentPacMan = PacManLeft;
            x-=20;
        }
        if (pressedKeys[40]){
            CurrentPacMan = PacManDown;
            y+=20;
        }
        if (pressedKeys[39]){
            CurrentPacMan = PacManRight;
            x+=20;
        }


    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }
}