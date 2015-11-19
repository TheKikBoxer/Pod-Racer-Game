/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pod.racer.game;

import environment.Environment;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author Liam
 */
class Tatooine extends Environment {

    public Tatooine() {
    }

    @Override
    public void initializeEnvironment() {
    }

    int counter;
    
    @Override
    public void timerTaskHandler() {
//        System.out.println("Welcome to the Pod Race" + counter++);
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
//        System.out.println("KEY PRESS " + e.getKeyChar());
//        System.out.println("KEY PRESS " + e.getKeyCode());

        if(e.getKeyCode() == KeyEvent.VK_UP) {
            System.out.println("UP");
        }
        
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
    }
    
}
