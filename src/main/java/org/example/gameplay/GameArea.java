package org.example.gameplay;

import javax.swing.*;
import java.awt.*;

public class GameArea extends JPanel  {

    private final static int ROWS = 20;
    private final static int COLUMNS = 10;
    private final static int CELL_SIZE = 30;

    private TetrisBlock block;

    private Color[][] backgroundBlocs;

    public GameArea(){
        this.setBounds(0,0,300,600);
        backgroundBlocs = new Color[ROWS][COLUMNS];

    }

    public void spawnBlock()
    {
        block = new TetrisBlock(new int[][]{{1,0},{1,0},{1,1}}, Color.BLUE );
        block.spawn(COLUMNS);
    }

    public boolean moveBlockDown(){
        if (checkBottom() == false){
            moveBlockToBackground();
            clearLines();
            return false;
        }
        block.moveDown();
        repaint();

        return true;
    }

    public void moveBlockLeft(){
        if(!checkLeft()) return;
        block.moveLeft();

        repaint();
    }
    public void moveBlockRight(){
        if (!checkRight()) return;
        block.moveRight();
        repaint();
    }
    public void dropBlock(){
        while(checkBottom()){
            block.moveDown();
        }
        repaint();

    }
    public void rotateBlock(){
        block.rotate();
        repaint();
    }



    private boolean checkBottom(){
        if(block.getBootomEdge() == ROWS)    return false;

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int col= 0; col<w; col++){
            for(int row  = h-1; row>=0; row--){
                if (shape[row][col] != 0) {
                    int x = col +block.getXOffSet();
                    int y = row +block.getYOffSet() +1;
                    if(y<0) break;
                    if (backgroundBlocs[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }

    private boolean checkLeft(){
        if(block.getLeftEdge() <= 0) return false;

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row= 0; row<h; row++){
            for(int col = 0; col<w; col++){
                if (shape[row][col] != 0) {
                    int x = col +block.getXOffSet() -1;
                    int y = row +block.getYOffSet();
                    if(y<0) break;
                    if (backgroundBlocs[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }


    private boolean checkRight(){
        if(block.getRightEdge() == COLUMNS ) return false;

        int[][] shape = block.getShape();
        int w = block.getWidth();
        int h = block.getHeight();

        for (int row= 0; row<h; row++){
            for(int col = w-1 ; col >= 0; col--){
                if (shape[row][col] != 0) {
                    int x = col +block.getXOffSet() +1;
                    int y = row +block.getYOffSet();
                    if(y<0) break;
                    if (backgroundBlocs[y][x] != null) return false;
                    break;
                }
            }
        }
        return true;
    }

    public void clearLines() {
        boolean lineIsFilled;

        for (int r = ROWS-1; r >= 0; r--){

            lineIsFilled  = true;

            for(int c = 0; c < COLUMNS; c++){
                if (backgroundBlocs[r][c] == null){
                    lineIsFilled =  false;
                    break;
                }
            }
            if(lineIsFilled){
                clearLine(r);
                slideDown(r);
                clearLine(0);
                repaint();
                r++;
            }

        }
    }
    private void clearLine(int r){
        for(int i = 0; i < COLUMNS; i++){
            backgroundBlocs[r][i] = null;
        }
    }

    private void slideDown(int r){
        for(int row = r; row > 0; row--){
            for(int col = 0; col < COLUMNS; col++){
                backgroundBlocs[row][col] = backgroundBlocs[row-1][col];
            }
        }
    }



    private void moveBlockToBackground(){
        int[][]shape = block.getShape();
        int h = block.getHeight();
        int w = block.getWidth();

        int xPos = block.getXOffSet();
        int yPos = block.getYOffSet();
        Color color = block.getColor();

        for(int r= 0; r<h; r++){
            for (int c = 0; c < w; c++){
                if (shape[r][c] == 1){
                    backgroundBlocs[r + yPos][ c+ xPos] = color;
                }
            }
        }
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

                    drawGridSquare(g, c, col+x,row+y);

                }
            }
        }
    }
    /*
                        g.setColor(c);
                    g.fillRect((col+x) * CELL_SIZE+2 , (row+y)*CELL_SIZE+2, CELL_SIZE-4, CELL_SIZE-4);
     */

    private void drawBackground(Graphics g){
        Color color;

        for (int row = 0; row< ROWS; row++)
        {
            for (int col = 0; col < COLUMNS; col++)
            {
                color = backgroundBlocs[row][col];

                if(color !=null){

                    drawGridSquare(g,color,col, row);

                }
            }
        }
    }

    private void drawGridSquare(Graphics g, Color color, int column, int rows) {
        g.setColor(color);
        g.fillRect((column) * CELL_SIZE+2 , (rows)*CELL_SIZE+2, CELL_SIZE-4, CELL_SIZE-4);
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

        drawBackground(g);
        drawBlock(g);

    }
}
