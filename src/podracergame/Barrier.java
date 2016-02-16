/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podracergame;

import images.ResourceTools;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

/**
 *
 * @author Liam
 */
public class Barrier {
    
private Image image3;
    
    public void draw(Graphics graphics){
        graphics.setColor(color);
        graphics.drawImage(image3, getX(), getY(), width, height, null);
    }
    
    
    
    
    public Barrier(int x, int y, Color color){
        image3 = ResourceTools.loadImageFromResource("podracergame/Rock.png");
        
        this.x = x;
        this.y = y;
        this.color = color;
    }
    
    private int x, y;
    private int width = 30;
    private int height = 40;
    
    private Color color;


    public void move(int x, int y){
        this.x += x; 
        this.y += y;
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }
}
