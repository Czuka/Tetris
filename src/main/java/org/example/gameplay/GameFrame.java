package org.example.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.KeyStroke;


public class GameFrame extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public int screenWidth = (int) screenSize.getWidth();
    public int screenHeight = (int) screenSize.getHeight();
    private GameArea ga;
    JFrame frame ;

    public void initComponents(){
        frame = new JFrame("Tetris");

        frame.setBounds(screenWidth/2-200,screenHeight/2-300,450, 637);
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        frame.setVisible(true);


        ga = new GameArea();
        frame.add(ga);


    }

    public GameFrame() {

        initComponents();
        initControls();
        System.out.println("init controls");

        startGame();

    }




    private void initControls(){
        InputMap im = frame.getRootPane().getInputMap();
        ActionMap am = frame.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke("RIGHT"),"rightKEY");
        im.put(KeyStroke.getKeyStroke("LEFT"),"leftKEY");
        im.put(KeyStroke.getKeyStroke("DOWN"),"downKEY");
        im.put(KeyStroke.getKeyStroke("UP"),"upKEY");

        am.put("rightKEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockRight();
            }
        });
        am.put("leftKEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.moveBlockLeft();
            }
        });
        am.put("downKEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.dropBlock();
            }
        });
        am.put("upKEY", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.rotateBlock();
            }
        });
    }


    public void startGame(){
        new GameThread(ga).start();
    }
}


