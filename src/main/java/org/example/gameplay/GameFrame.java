package org.example.gameplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.ActionMap;
import javax.swing.InputMap;

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
        initControls();
        initComponents();

        System.out.println("init controls");

        startGame();

    }




    private void initControls(){
        InputMap im = this.getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = this.getRootPane().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_D,0),"right");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_A,0),"left");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_S,0),"down");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_W,0),"up");

        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    System.out.println(" right");
                } catch(Exception ex){
                    System.out.println("right catch");
                }


            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("left");
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("down");
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("up");
            }
        });
    }


    public void startGame(){
        new GameThread(ga).start();
    }
}


