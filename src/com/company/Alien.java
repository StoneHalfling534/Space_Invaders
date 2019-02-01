/*
Dhruv Upadhyay
Alien.java
Januyary 19th, 2019

Description: Defines aliens in space invaders
 */

package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;


//The specifications for the aliens are defined in this class

public class Alien {

    //The fields such as velocity, and coordinates are defined and initialized below
    public double velocity = 20;
    public int x, y;
    public int w, h;
    private BufferedImage img = null;

    //The constructor sets the default positions of the alien and also imports a PNG file to be painted upon the JFrame.
    public Alien(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            this.img = ImageIO.read(new File("aliens.png"));
            this.w = img.getWidth();
            this.h = img.getHeight();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //The paint method casts the object upon the JFrame
    public void paint(Graphics2D g2d) {
        g2d.drawImage(img, x, y, null);
    }

    public long timeElapsed = System.currentTimeMillis();

    //This moves the aliens at the zigzag motion as they descend downwards, and also programs if the aliens have descended
    //to the bottom of the program, to kill the spaceship if that is the case.

    //Moves them at regular time intervals
    public void move(Consumer<Spaceship> removeSpaceship, Spaceship ss, List<Alien> aliens) {
        for (int i=0; i<5; i++) {
            if (System.currentTimeMillis()>timeElapsed+800) {
                if (this.x >= 420) {
                    for (Alien alien : aliens) alien.y+=10;
                    for (Alien alien : aliens) alien.velocity=-2;
                    timeElapsed = System.currentTimeMillis();
                    if (System.currentTimeMillis()>timeElapsed+800) {
                        for (Alien alien : aliens) alien.x += velocity;
                    }
                } else if (this.x <= 20) {
                    for (Alien alien : aliens) alien.y+=10;
                    for (Alien alien : aliens) alien.velocity=2;
                    timeElapsed = System.currentTimeMillis();
                    if (System.currentTimeMillis()>timeElapsed+800) {
                        for (Alien alien : aliens) alien.x += velocity;
                    }
                }
                else {
                    for (Alien alien : aliens) alien.x+=velocity;
                    timeElapsed = System.currentTimeMillis();
                }
            }
        }
        if (this.y==380) {
            removeSpaceship.accept(ss);
            System.out.println("Test");
        }
    }
}
