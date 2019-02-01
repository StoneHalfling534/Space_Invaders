package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//This class defines the page where the documents are located
public class HowToPlay extends JPanel {
    public HowToPlay() {
        //All of the various directive-variable refer to JLabels that have different instructions in
        //logical order
        JLabel heading = new JLabel("Instructions", JLabel.CENTER);
        JLabel directive1 = new JLabel("In this game, you control a spaceship and aim to defeat all aliens", JLabel.LEFT);
        JLabel directive2 = new JLabel("The aliens will be descending downwards and dropping bombs");
        JLabel directive3 = new JLabel("If you get hit by a bomb, or if the aliens reach bottom, you die!");
        JLabel directive4 = new JLabel("Here are the rules: ");
        JLabel directive5 = new JLabel("1: Control spaceship with left and right arrows");
        JLabel directive6 = new JLabel("2: Shoot at Aliens by pressing Spacebar");
        JLabel directive7 = new JLabel("3: Pause the game by pressing the 'home' button");
        JLabel directive8 = new JLabel("Good Luck, Humanity depends on you!");
        JButton comeBack = new JButton("Return to Menu", null);
        comeBack.setLayout(null);
        heading.setLayout(null);
        this.add(heading);
        this.add(directive1);
        this.add(directive2);
        this.add(directive3);
        this.add(directive4);
        this.add(directive5);
        this.add(directive6);
        this.add(directive7);
        this.add(directive8);
        this.add(comeBack);
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goToMenu.run();
            }
        });
    }
    public Runnable goToMenu = () -> {};
}
