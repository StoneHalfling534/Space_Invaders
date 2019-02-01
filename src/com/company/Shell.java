/*
Dhruv Upadhyay
January 19, 2019
File: Shell.java

Class defining properties of the shell ejected from spaceship
 */

package com.company;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.function.Consumer;

//This class defines properties of the shot fired by the spaceship (The shell)

public class Shell {
    public int x, y;
    public double velocity = -8;
    private BufferedImage img;

    //Imports shell png file and turns it into an img object
    public Shell(int xVal, int yVal) {
        try {
            img = ImageIO.read(new File("shell.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        this.x = xVal;
        this.y = yVal;
        this.move();
    }


    //Paints shell onto the JFrame, method is called in Main class
    public void paint(Graphics2D g2d) {
        g2d.drawImage(this.img, this.x, this.y, null);
    }

    //Defines regular vertical motion of shell, as it goes upwards at set velocity
    public void move() {
        this.y += this.velocity;
    }

    //Checks if the shell has hit an alien, and removes the alien if that is the case
    public void check(Consumer<Alien> removeAlien, Consumer<Shell> removeShell, Alien a) {
        if ((this.x > a.x-a.w/2 && this.x < a.x + a.w/2) && (this.y > a.y-a.h/2 && this.y < a.y + a.h)) {
            removeShell.accept(this);
            removeAlien.accept(a);
        }
    }
}
