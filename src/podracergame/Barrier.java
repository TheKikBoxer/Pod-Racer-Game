/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package podracergame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

/**
 *
 * @author Liam
 */
public class Barrier {
    
    public void draw(Graphics graphics){
        graphics.setColor(color);
        
                         
    }
    
    
    
    
    public Barrier(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
        
    }
    
    private int x, y;
    private Color color;
    
    
//    
//    public Rectangle getHitBox() {
//        return new ();
//    }
    
    
}
