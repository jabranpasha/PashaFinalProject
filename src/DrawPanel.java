import java.awt.event.*;
import javax.swing.*;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Point;
import java.awt.image.*;
import java.util.*;
import java.awt.Font;
import java.io.*;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
public class DrawPanel extends JPanel implements MouseListener, KeyListener, ActionListener {
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
    private String PacManRightC = "Pacman rightC";
    private String PacManLeft = "Pacman left";
    private String PacManLeftC = "Pacman leftC";

    private String PacManDown ="Pacman down";
    private String PacManDownC = "Pacman downC";
    private String PacManUp = "Pacman up";
    private String PacManUpC = "Pacman upC";
    private String PacManClosedR = "Pacman ClosedR";
    private String PacManClosedL = "Pacman ClosedL";
    private String PacManClosedD = "Pacman ClosedD";
    private String PacManClosedU = "Pacman ClosedU";
    private String blank = "blank";
    private int xVelocity;
    private int yVelocity;
    private Timer timer;
    private int time;
    private boolean isMoving;
    private boolean play = false;
    private boolean space = false;
    private Clip WakaWaka;
    private boolean sound;
    private Rectangle[] borders;
    private Rectangle pHitBox;




    private int x;
    private final int xWidth;
    private int y;
    private final int yHeight;
    public DrawPanel() {
        this.addMouseListener(this);
        this.addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();
        CurrentImage = StartScreen;
        CurrentPacMan = blank;
        time = 0;
        timer = new Timer(75, this);
        timer.start();
        isMoving = false;
        x = 626;
        y = 327;
        xWidth = 75;
        yHeight = 65;
        sound = true;
        pHitBox = new Rectangle(x, y, 75, 65);
        borders = new Rectangle[3];
        Rectangle border1 = new Rectangle(110 ,90 ,1070 ,542);
        Rectangle border2 = new Rectangle(120 ,105 ,1040 ,215);
        Rectangle border3 = new Rectangle(120, 400, 1040,225);
        borders[0] = border1;
        borders[1] = border2;
        borders[2] = border3;


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
        x += xVelocity;
        y += yVelocity;
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
            if (play){
                playSound();
                play = false;
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
        int prevX = x;
        int prevY = y;
        if (pressedKeys[38]){
            CurrentPacMan = PacManUp;
            if (time % 1.5 == 0) {
                CurrentPacMan = PacManUpC;
            }
            if (time % 3 == 0) {
                CurrentPacMan = PacManClosedU;
            }
            movePacMan(0, -6);
        }
        if (pressedKeys[37]){
            CurrentPacMan = PacManLeft;
            if (time % 1.5 == 0) {
                CurrentPacMan = PacManLeftC;
            }
            if (time % 3 == 0) {
                CurrentPacMan = PacManClosedL;
            }
            movePacMan(-6, 0);
        }
        if (pressedKeys[40]){
            CurrentPacMan = PacManDown;
            if (time % 1.5 == 0) {
                CurrentPacMan = PacManDownC;
            }
            if (time % 3 == 0) {
                CurrentPacMan = PacManClosedD;
            }
            movePacMan(0, 6);
        }
        if (pressedKeys[39]) {
            CurrentPacMan = PacManRight;
            if (time % 1.5 == 0) {
                CurrentPacMan = PacManRightC;
            }
            if (time % 3 == 0) {
                CurrentPacMan = PacManClosedR;
            }
            movePacMan(6, 0);
        }
        if (pressedKeys[32]){

        }
        if (!isMoving) {
            x = prevX;
            y = prevY;
        }
        if (sound) {
            playSound();
            sound = false;
        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        pressedKeys[key] = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() instanceof Timer){
            time++;
        }
    }
    public void playSound() {
        if (CurrentImage.equals(GameScreen)){
            try {
                AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File("src/sounds/Waka Waka.wav").getAbsoluteFile());
                WakaWaka = AudioSystem.getClip();
                WakaWaka.open(audioInputStream);
                WakaWaka.loop(Clip.LOOP_CONTINUOUSLY);
                WakaWaka.start();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
    public boolean Intersects(Rectangle r) {
        double hitboxRight = x + xWidth;
        double hitboxBottom = y + yHeight;
        double rRight = r.getX() + r.getWidth();
        double rBottom = r.getY() + r.getHeight();
        boolean intersects = (x < rRight) && (hitboxRight > r.getX()) &&
                (y < rBottom) && (hitboxBottom > r.getY());

        return intersects;
    }
    public void checkBorders(){
        isMoving = true;
            if (Intersects(borders[1]) || Intersects(borders[2]) || !Intersects(borders[0])) {
                isMoving = false;
            }
    }
    private void movePacMan(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
        checkBorders();
    }
}