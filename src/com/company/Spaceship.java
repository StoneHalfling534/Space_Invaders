package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.Key;
import java.util.function.Consumer;

//This class defines the spaceship/protagonist of the game
public class Spaceship{
    //The fields such as position and velocity are intialized
    double velocity=0;
    int x=250;
    int y=450;
    int w,h;

    private BufferedImage img = null;

    //Within the constructor, the image is imported, and converted into an Image object, later to be cast to a BufferedImage
    public Spaceship() {
        try {
            img = ImageIO.read(new File("spaceShip.png"));
            w = img.getWidth();
            h = img.getHeight();
        } catch (IOException e){
        }
    }

    //The keyPressed interface is implemented here, and different actions are done based on the keys pressed/released

    //If left/right arrows are pressed, the spaceships moves left or right by changing its velocity. If spacebar is pressed,
    //a new shell instance is created
    public void keyPressed(Consumer<Shell> addShell, KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
            velocity = 10;
        }
        else if (e.getKeyCode()==KeyEvent.VK_LEFT) {
            velocity = -10;
        }
        else if (e.getKeyCode()==KeyEvent.VK_SPACE) {
            addShell.accept(new Shell(this.x, this.y));
        }
        else if (e.getKeyCode()==KeyEvent.VK_HOME) {
            goToMenu.run();
        }
        else{
            velocity=0;
        }
    }

    //Releasing any key always sets velocity to 0: no movement
    public void keyReleased(KeyEvent e) {
        velocity=0;
    }

    //The paint method draws the image onto a canvas through the Graphics2D object manipulation
    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, x,y, null);
    }

    //The move method defines the movement of the spaceship, as its x-coordinate is always increased by its velocity,
    //which is defined in the keyPressed/released methods
    //This move method is called in the move method in the main class
    public void move(){
        if ((x+velocity>=420)||(x+velocity<=10)) {
        }
        else {
            this.x+=velocity;
        }
    }
    public Runnable goToMenu = () ->{};
}



