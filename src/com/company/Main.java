/**
 * File: Main.java
 * Project: ICS3U1 ISU: Space Invaders Game
 * Date: 19th, January. 2019
 *
 * Description: A project that clones the atari classic: Space Invaders in JAVA.
 *
 */

package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

public class Main extends JPanel implements KeyListener {

    //Creates a list of all of the graphical entities: aliens, shells, bombs, and spaceships within the games.
    private java.util.List<Alien> aliens = new CopyOnWriteArrayList<>();
    private java.util.List<Shell> shells = new CopyOnWriteArrayList<>();
    private java.util.List<Bomb> bombs = new CopyOnWriteArrayList<>();
    private java.util.List<Spaceship> spaceships = new CopyOnWriteArrayList<>();

    //Initializes the keyListener for keyboard control, and resets all of the variables if the game is being restarted.
    public Main() {
        addKeyListener(this);
        setFocusable(true);
        reset();
    }

    //Paint method paints and casts all of the graphical elements onto the JFrame to be seen and manipulated by the users
    public void paint(Graphics g) {
        super.paint(g);
        try {
            BufferedImage backgroundImage = ImageIO.read(new File("background.png"));
        } catch (IOException i) {
        }
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        //The below for loops iterate over all graphical elements in the lists, and paints
        //them one-by-one onto the JFrame.
        for (Spaceship ss : spaceships) ss.paint(g2d);
        for (Alien al : aliens) al.paint(g2d);
        for (Shell shell : shells) shell.paint(g2d);
        for (Bomb bomb : bombs) bomb.paint(g2d);
    }

    //The lastDrop variable is used to compare current time with a set time to implement certain actions at a regular interval
    public long lastDrop = System.currentTimeMillis();
    public int test = 1;

    //This methods resets the game if the game has ended, to initiate a new game.

    //This is done by removing all graphical elements from their respective lists, and
    //re-initializing them to their original value.
    public void reset() {
        aliens.clear();
        shells.clear();
        bombs.clear();
        spaceships.clear();
        for (int i = 10; i < 300; i += 40) this.aliens.add(new Alien(i, 0));
        for (int i = 10; i < 300; i += 40) this.aliens.add(new Alien(i, 30));
        for (int i = 10; i < 300; i += 40) this.aliens.add(new Alien(i, 60));
        for (int i = 10; i < 300; i += 40) this.aliens.add(new Alien(i, 90));
        this.spaceships.add(new Spaceship());
    }

    //The move method is a fundamental method in the game, as it is responsible for moving all of the graphical elements
    //in the game. This is done by calling the respecting move methods from each of the classes they belong to

    //This is done through for loops which iterate through the lists holding each of the graphical elements within each other
    //and calling the move method on each one.
    public void move() {
        for (Alien alien : aliens) for (Shell shell : shells) shell.check(aliens::remove, shells::remove, alien);
        for (Spaceship ss : spaceships) for (Bomb bomb : bombs) bomb.check(spaceships::remove, bombs::remove, ss);
        for (Alien alien : aliens) for (Spaceship ss : spaceships) alien.move(spaceships::remove, ss, aliens);
        for (Shell shell : shells) shell.move();
        for (Bomb bomb : bombs) bomb.move();

        for (Spaceship ss : spaceships) ss.move();

        //This conditional checks if the user has killed all of the aliens, and takes subsequent steps.
        if ((aliens.isEmpty()) && test == 1) {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int option = JOptionPane.showConfirmDialog(this, "You Have Defeated the Aliens and beat the game. Return to main menu? If you press no, the game will close", "Congratulations", dialogButton);
            if (option == JOptionPane.YES_OPTION) {
                this.goToMenu.run();
                reset();
            } else if (option == JOptionPane.NO_OPTION) {
                System.exit(0);
            }
            test = 0;
        }

        //This conditional checks if the spaceship has been hit, and calls the dialog box that tells the user that they have lost
        else if (spaceships.isEmpty()&&test==1) {
            JOptionPane.showMessageDialog(null, "You have been shot by an alien, Game Over. Redirecting to the main menu... ");
            reset();
            this.goToMenu.run();
        }

        //The random numbers usage is used to drop bombs at random from the aliens in the list, every second (1000 milliseconds)
        int randomInt = (int) (10 * Math.random());
        int listLength = aliens.size();
        int randomIndex = (int) ((listLength - 1) * Math.random());
        if (listLength == 0) {
        } else {
            int randomPos = aliens.get(randomIndex).x;
            int randomYPos = aliens.get(randomIndex).y;
            if (randomInt == 5 && System.currentTimeMillis() > lastDrop + 1000) {
                lastDrop = System.currentTimeMillis();
                bombs.add(new Bomb(randomPos, randomYPos));
            }
        }
    }

    //This lambda function is used to open the menu when desired
    public Runnable goToMenu = () -> {};

    //In the main method, each of the Main game-panel, menu-panel, and instruction-panel are created
    //Furthermore, the repaint() method is called, and the tick-method is defined also
    public static void main(String[] args) {
        Main panel = new Main();
        Menu menu = new Menu();
        HowToPlay instruction  = new HowToPlay();
        panel.goToMenu.run();
        JFrame frame = new JFrame();
        frame.setMinimumSize(new Dimension(500, 600));
        frame.setPreferredSize(frame.getMinimumSize());
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //The lambdas which allow seamless transition to the menus and instruction pane are also defined here
        panel.goToMenu = instruction.goToMenu = () -> {
            frame.setContentPane(menu);
            menu.grabFocus();
            frame.invalidate();
            frame.validate();
            frame.repaint();
        };

        menu.goToGame = () -> {
            frame.setContentPane(panel);
            panel.grabFocus();
            frame.invalidate();
            frame.validate();
            frame.repaint();
        };

        menu.instruct = () -> {
            frame.setContentPane(instruction);
            panel.grabFocus();
            frame.invalidate();
            frame.validate();
            frame.repaint();
        };

        frame.setContentPane(menu);
        panel.grabFocus();
        frame.invalidate();
        frame.validate();
        frame.repaint();

        //This is the tick loop, that always run throughout the life of the program
        //This runs to repaint the program continuously, giving the illusion of movement and animation. This occurs every 10 milliseconds
        while (true) {
            if (frame.getContentPane() == panel) {
                panel.move();
                panel.repaint();
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ignored) {
            }
        }
    }

    //The implementation of the Keylistener interface is also prorgammed and done here, which allows keyboard manipulation
    //of the text
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Spaceship ss : spaceships) ss.keyPressed(this.shells::add, e);
        if (e.getKeyCode() == KeyEvent.VK_HOME) this.goToMenu.run();
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for (Spaceship ss : spaceships) ss.keyReleased(e);
    }
}
