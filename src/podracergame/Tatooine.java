/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podracergame;

import audio.AudioPlayer;
import environment.Environment;
import environment.Velocity;
import grid.Grid;
import images.ResourceTools;
import java.awt.Color;
import static java.awt.Color.BLACK;
import static java.awt.Color.BLUE;
import static java.awt.Color.ORANGE;
import static java.awt.Color.black;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Menu;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

/**
 *
 * @author Liam
 */
class Tatooine extends Environment implements MouseMotionListener {

    private Grid grid;
    private PodRacer pod;
    private ArrayList<Barrier> myBarriers;
    Image backgroundImage1, backgroundImage2, podRacerImage, barrierImage;
    private Menu menu;
    private GameState state = GameState.MENU;
    private AudioManager soundManager;

    public Tatooine() {

//        grid = new Grid(100, 70, 10, 10, new Point(20, 20), new Color(100, 0, 100));
        backgroundImage1 = ResourceTools.loadImageFromResource("podracergame/sand.jpg");
        backgroundImage2 = ResourceTools.loadImageFromResource("podracergame/sand.jpg");
        podRacerImage = ResourceTools.loadImageFromFile("Podracer1.png");
        barrierImage = ResourceTools.loadImageFromResource("podracergame/Rock.png");

//      topImageY = this.getHeight() - this.image1.getHeight(null);

        pod = new PodRacer(new Point(400, 400), new Velocity(0, 0));
        this.getActors().add(pod);
        state = GameState.MENU;
        this.addMouseMotionListener(this);//  addMouseListener(this);
        
        soundManager = AudioManager.getSoundManager();
        
        myBarriers = new ArrayList<>();
        myBarriers.add(new Barrier(100, 200, Color.BLACK));
        myBarriers.add(new Barrier(150, 250, Color.BLACK));
        myBarriers.add(new Barrier(50, 50, Color.BLACK));
        myBarriers.add(new Barrier(70, 290, Color.BLACK));
    }

    @Override
    public void initializeEnvironment() {
    }

    int moveDelay = 0;
    int moveDelayLimit = 5;

    @Override
    public void timerTaskHandler() {
//        System.out.println("Welcome to the Pod Race" + counter++);
        if (state == GameState.GAME) {
            if (pod != null) {
                if (moveDelay >= moveDelayLimit) {
                    moveDelay = 0;
                    pod.move();
                } else {
                    moveDelay++;
                }
                checkIntersections();
            }
            moveimages();
        }

    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pod.setDx(-6);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pod.setDx(6);
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            AudioPlayer.play("/podracer/Coin_pickup.wav");
        }

    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pod.setDx(0);
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pod.setDx(0);
        }
    }
    

   
    private final static int PLAY_BUTTON_X = 50;
    private final static int PLAY_BUTTON_Y = 300;
    private final static int PLAY_BUTTON_WIDTH = 35;
    private final static int PLAY_BUTTON_HEIGHT = 50;
    private static Color buttonColor = Color.WHITE;

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        if (state == GameState.MENU) {
            if (new Rectangle(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT).contains(e.getPoint())) {

            } else {
            }
            state = GameState.GAME;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (state == GameState.MENU) {
            if (new Rectangle(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT).contains(e.getPoint())) {
                buttonColor = Color.RED;
            } else {
                buttonColor = Color.WHITE;
            }
        }
    }

    @Override
    public void paintEnvironment(Graphics graphics) {

        if (state == GameState.MENU) {

            graphics.drawImage(backgroundImage1, 0, topImageY, this);
            graphics.drawImage(backgroundImage2, 0, topImageY - backgroundImage2.getHeight(this), this);

            Font fnt0 = new Font("arial", Font.BOLD, 50);
            graphics.setFont(fnt0);
            graphics.setColor(Color.white);

            Font fnt1 = new Font("arial", Font.BOLD, 70);
            graphics.setFont(fnt1);
            graphics.drawString("Start Game", 100, 350);
            graphics.setColor(buttonColor);
            graphics.fill3DRect(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT, true);
            
            

        } else if (state == GameState.GAME) {
            if ((backgroundImage1 != null) && (backgroundImage2 != null)) {
                graphics.drawImage(backgroundImage1, 0, topImageY, this);
                graphics.drawImage(backgroundImage2, 0, topImageY - backgroundImage2.getHeight(this), this);

            }

            if (myBarriers != null) {
                for (int i = 0; i < myBarriers.size(); i++) {
                    myBarriers.get(i).draw(graphics);
                }
                //graphics.drawImage(barrierImage, 100, 100, this);
            }

            if (pod != null) {
//                graphics.drawImage(pod.getImage(), pod.getX(), pod.getY(), this);
                pod.draw(graphics);
            }
        }

    }


    public int getSystemCoordX(int x, int y) {
        return x;
//        return grid.getCellSystemCoordinate(x, y).x;
    }

    public int getSystemCoordY(int x, int y) {
        return y;
//        return grid.getCellSystemCoordinate(x, y).y;

    }

    public int getCellWidth() {
        return 20;
//        return grid.getCellWidth();
    }

    public int getCellHeight() {
        return 20;
//        return grid.getCellHeight();
    }

    private void checkIntersections() {
//check if the PodRacer is inside a barrier 
//        if (myBarriers.contains(PodRacer.getLocation())) {
//            System.out.println("HIT");
//        }
    }

    int topImageY = 0;

    private void moveimages() {
        for (int i = 0; i < 6; i++) {
            topImageY++;
            
            for (Barrier barrier : myBarriers) {
                barrier.move(0, 1);
                if (barrier.getY() > this.getHeight()) {
                    barrier.setY((int) (Math.random() * -100));
                    barrier.setX((int) (Math.random() * 250));
                }
            }
            
        }

        if (backgroundImage1 != null) {
            if (topImageY > this.getHeight()) {
                topImageY = this.getHeight() - backgroundImage1.getHeight(null);
            }
        }
    
    }
    

}
