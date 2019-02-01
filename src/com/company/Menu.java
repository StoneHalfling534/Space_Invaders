package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//In this class, the main menu of the program is defined
public class Menu extends JPanel {

    //The constructor creates all of the GUI elements and programs them
    public Menu() {
        this.setLayout(new GridBagLayout());
        JLabel heading = new JLabel("Welcome to Java Space Invaders!", JLabel.LEFT);
        heading.setSize(18,20);

        //JButtons that allow users to see instructions, quit, and start the game are defined
        JButton start = new JButton("Play", null);
        JButton instructions = new JButton("How To Play", null);
        JButton quit = new JButton("Quit", null);
        quit.setLayout(null);
        start.setLayout(null);
        instructions.setLayout(null);
        heading.setLayout(null);
        heading.setLocation(10, 0);
        start.setLocation(10, 20);
        quit.setLocation(10, 30);

        //Adds each of the GUI components to the JPanel
        this.add(heading);
        this.add(start);
        this.add(quit);
        this.add(instructions);
        instructions.setLocation(10, 80);

        //Action listener allows events to occur when buttons are pressed.
        //When the 'start' button is rpessed, the game begins, and the space invaders JPanel is opened
        //as defined by the lambda
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                goToGame.run();
            }
        });

        //This actionListener dicates that when quit is pressed, the program will exit
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JOptionPane.showMessageDialog(null, "Exiting...");
                System.exit(0);
            }
        });

        //When the instructions button is pressed, the instructions JPanel is opened, which opens
        //the instructions
        instructions.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                instruct.run();
            }
        });
        this.revalidate();
        this.repaint();

    }

    //References to the defined lambdas are given below
    public Runnable goToGame = () -> {
    };

    public Runnable instruct = () -> {
    };

}
