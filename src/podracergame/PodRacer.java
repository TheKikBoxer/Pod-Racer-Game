/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podracergame;

import environment.Actor;
import environment.Direction;
import environment.Velocity;
import images.ResourceTools;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;

/**
 *
 * @author Liam
 */

public class PodRacer extends Actor {

    static Object getLocation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

private Image image;
private boolean visible;
private int velocity;
private int dx;
private int dy;
private int x;
private int y;
private PodRacer pod;



    public void draw(Graphics graphics){
        graphics.drawImage(image, x, y, width, height, null);
    }

    
    {
        image = ResourceTools.loadImageFromResource("podracergame/Podracer1.png");

        width = 100;
        height = 100;
        visible = true;
        x = 250;
        y = 400;
        
//        velocity = 3;
    }
    
    public PodRacer(Point position, Velocity velocity) {
        super(position, velocity);
    }
    
    @Override
    public Rectangle getObjectBoundary() {
        return new Rectangle(x, y, width, height);
    }
    
    
    
    public void move() {
        x += getDx();
        x += getDx();
        y += getDy();
}

    public int getX(){
        return x;
    }
    
    public int getY(){
        return y;
    }
    
    public Image getimage() {
    return image;
}
    
    
    
    public void KeyPressed(KeyEvent e){
        int key = e.getKeyCode();
        
        if (key == KeyEvent.VK_LEFT) {
            setDx(-30);
            setDy(0);
        }
        
        if (key == KeyEvent.VK_RIGHT) {
            setDx(30);
            setDy(0);
        }
        
//        public void move() {
//            x += dx;
//        }
    }
//    private int getHealth() {
//        return health;
//    }
//    
//    public void setHealth(int health) {
//        this.health = health;
//    }
//    
//    public void addHealth(int health) {
//        this.health += health;
//      }    
    
    public boolean isAlive(){
        return (health > 0);
    }
    
    
    
  
    
    private int health;
    
//    for move method add if (isAlive())

//    void setDirection(Direction direction) {
//        if (direction = Direction.LEFT) {
//            
//        }
//    }

    /**
     * @return the dx
     */
    public int getDx() {
        return dx;
    }

    /**
     * @param dx the dx to set
     */
    public void setDx(int dx) {
        this.dx = dx;
    }

    /**
     * @return the dy
     */
    public int getDy() {
        return dy;
    }

    /**
     * @param dy the dy to set
     */
    public void setDy(int dy) {
        this.dy = dy;
    }

}
