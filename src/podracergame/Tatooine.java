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
    Image image1, image2, image;
    private Menu menu;
    private GameState state = GameState.MENU;

    public Tatooine() {

//        grid = new Grid(100, 70, 10, 10, new Point(20, 20), new Color(100, 0, 100));
        image1 = ResourceTools.loadImageFromResource("podracergame/sand.jpg");
        image2 = ResourceTools.loadImageFromResource("podracergame/sand.jpg");
        image = ResourceTools.loadImageFromFile("Podracer1.png");

//      topImageY = this.getHeight() - this.image1.getHeight(null);

        pod = new PodRacer(new Point(400, 400), new Velocity(0, 0));
        this.getActors().add(pod);
        state = GameState.MENU;
        this.addMouseMotionListener(this);//  addMouseListener(this);

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
//        System.out.println("KEY PRESS " + e.getKeyChar());
//        System.out.println("KEY PRESS " + e.getKeyCode());
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
//                System.out.println("WOOOOOOOOOT");
                //play a sound
                // start game
            } else {
//                play a yucky sound
            }
//            if (this.playButton.contains(e.getPoint())) {
            state = GameState.GAME;
//            }

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

//    private Rectangle playButton = new Rectangle(Tatooine.WIDTH / 2 + 370, 300, 100, 50);
    @Override
    public void paintEnvironment(Graphics graphics) {

        if (state == GameState.MENU) {
            //draw a bunch of menu stuff

            graphics.drawImage(image1, 0, topImageY, this);
            graphics.drawImage(image2, 0, topImageY - image2.getHeight(this), this);

            Font fnt0 = new Font("arial", Font.BOLD, 50);
            graphics.setFont(fnt0);
            graphics.setColor(Color.white);

            Font fnt1 = new Font("arial", Font.BOLD, 70);
            graphics.setFont(fnt1);
//            graphics.drawString("Start Game", playButton.x / 2, playButton.y / 2 + 185);
            graphics.drawString("Start Game", 100, 350);
            graphics.setColor(buttonColor);
            graphics.fill3DRect(PLAY_BUTTON_X, PLAY_BUTTON_Y, PLAY_BUTTON_WIDTH, PLAY_BUTTON_HEIGHT, true);
            
            graphics.drawString("Pod Racers", 120, 400);

        } else if (state == GameState.GAME) {
            //draw a bunch of game stuff
            if ((image1 != null) && (image2 != null)) {
                graphics.drawImage(image1, 0, topImageY, this);
                graphics.drawImage(image2, 0, topImageY - image2.getHeight(this), this);

            }

            if (grid != null) {
                grid.paintComponent(graphics);
            }

            if (myBarriers != null) {
                for (int i = 0; i < myBarriers.size(); i++) {
                    myBarriers.get(i).draw(graphics);
                }
            }

            if (pod != null) {
//                graphics.drawImage(pod.getImage(), pod.getX(), pod.getY(), this);
                pod.draw(graphics);
            }
        }
//
//        if (State == STATE.GAME) {
////          Put stuff here
//        } else if (State == STATE.MENU) {
//            menu.render(graphics);
//        }
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
       
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        topImageY++;
        if (image1 != null) {
            if (topImageY > this.getHeight()) {
                topImageY = this.getHeight() - image1.getHeight(null);
            }
        }
    
    }
    

}
