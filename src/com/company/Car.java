package com.company;

import javax.imageio.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

public class Car {
    private BufferedImage img = null;
    private int w, h;
    private int x, y;

    public Car(int x, int y) {
        try {
            this.img = ImageIO.read(new File("res\\station_wagon.png"));
            w = img.getWidth();
            h = img.getHeight();
        } catch (IOException e){
            System.out.println("res\\GoodManMcGee");
        }
        this.x = x;
        this.y = y;
    }

    public void paint(Graphics2D g2d){
        g2d.drawImage(img, x,  y, null);
    }

}