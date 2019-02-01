/*
Name: Dhruv Upadhyay
Date: 18th January 2019
File: Bomb.java

A clas that defines the bomb-objects created from the user
 */

package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.function.Consumer;


//Defines the bomb object-the bombs/bullets dropped by aliens to kill the spaceship
public class Bomb {
    public int x, y;
    public double velocity=8;
    private BufferedImage img;

    //Imports a png file and turns it into an img object.

    //Also defines the x and y coordinates of the bomb as per those of the alien it is originating from
    public Bomb(int xVal, int yVal) {
        try {
            img = ImageIO.read(new File("bomb.png"));
        } catch (IOException i) {
        }
        this.x = xVal;
        this.y = yVal;
        this.move();
    }

    //Paints the object onto the JFrame, and allows it to be seen by the user
    public void paint(Graphics2D g2d) {
        g2d.drawImage(this.img, this.x, this.y, null);
    }

    //Defines the regular vertically downwards movement of the bomb through a set velocity, and incrementing the y-value
    //by that velocity every tick
    public void move() {
        this.y+=this.velocity;
    }

    //Checks if the bomb is in the same coordinate as the spaceship.
    public void check(Consumer<Spaceship> removeSpaceship, Consumer<Bomb> removeBomb, Spaceship ss) {
            if (this.x > ss.x && this.x < ss.x+30 && this.y > ss.y && this.y < ss.y + 30) {
                removeSpaceship.accept(ss);
                removeBomb.accept(this);
            }
    }
}

