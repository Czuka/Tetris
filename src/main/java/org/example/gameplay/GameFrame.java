package org.example.gameplay;

import javax.swing.*;
import java.awt.*;

public class GameFrame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = (int) screenSize.getWidth();
    public int screenHeight = (int) screenSize.getHeight();
    private GameArea ga;

    public void initComponents(){
        JFrame frame = new JFrame("Tetris");

        frame.setBounds(screenWidth/2-200,screenHeight/2-300,450, 637);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);
        ga = new GameArea();
        frame.add(ga);
    }

    public GameFrame() {
        initComponents();
        startGame();
    }

    public void startGame(){
        new GameThread(ga).start();
    }

}


