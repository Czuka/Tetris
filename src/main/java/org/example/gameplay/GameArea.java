package org.example.gameplay;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel  {

    private final static int ROWS = 20;
    private final static int COLUMNS = 10;
    private final static int CELL_SIZE = 30;

    private TetrisBlock block;

    public GameArea(){
        this.setBounds(0,0,300,600);
        //backgroundBlocs = new Color[ROWS][COLUMNS];

        spawnBlock();
    }

    public void spawnBlock()
    {
        block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.BLUE );
        block.spawn(COLUMNS);
    }

    public boolean moveBlockDown(){
        if (checkBottom() == false) return false;
        block.moveDown();
        repaint();

        return true;
    }

    private boolean checkBottom(){
        if(block.getBootomEdge() == ROWS){
            System.out.println("boolean");
            return false;
        }
        return true;
    }



    private void drawBlock(Graphics g){

        int h = block.getHeight();
        int w = block.getWidth();
        Color c = Color.blue;
        int [][] shape = block.getShape();

        for (int row = 0; row< h; row++)
        {
            for (int col = 0; col < w; col++)
            {
                if (shape[row][col] ==1 ){
                    int x = block.getXOffSet();
                    int y = block.getYOffSet();

//                    int x = (block.getXOffSet() + col)*CELL_SIZE;
//                    System.out.println("x=" +x+ " col= "+col+ "  block.getxOffSet()= "+block.getXOffSet());
//                    int y = (block.getYOffSet() + row)*CELL_SIZE;
//                    System.out.println("y=" +y+ " row= "+row+ "  block.getyOffSet()= "+block.getYOffSet());


                    g.setColor(c);
                    g.fillRect((col+x) * CELL_SIZE+2 , (row+y)*CELL_SIZE+2, CELL_SIZE-4, CELL_SIZE-4);
                }
            }
        }
    }


    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);

        //drawBackground(g);

        g.setColor(Color.BLACK);
        g.fillRect(0,0,300,600);
        g.setColor(new Color(5, 100, 35));
        g.fillRect(300,0,150,600);

        g.setColor(new Color(0,51,51));
        for (int j=0; j<ROWS; j++) {
            for (int i = 0; i < COLUMNS; i++) {
                g.drawRect(i * CELL_SIZE , j*CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
        g.fillRect(300,0,10,600);
        drawBlock(g);

    }
}
