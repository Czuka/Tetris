package org.example.gameplay;

import java.awt.*;
import java.util.Random;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int xOffSet,yOffSet;
    private int [][][] shapes;
    private int currentRotation;

    private Color[] colorArray = { Color.GREEN,Color.BLUE,Color.YELLOW,Color.RED,Color.MAGENTA,Color.WHITE};


    public TetrisBlock(int[][] shape){
        this.shape = shape;

        initShapes();

    }

    private void initShapes(){
        shapes = new int[4][][];

        for(int i=0; i<4; i++ ){
            int r  = shape[0].length;
            int c = shape.length;

            shapes[i] = new int[r][c];

            for(int y=0; y<r; y++ ){
                for(int x=0; x<c; x++ ){
                    shapes[i][y][x] = shape[c-x-1][y];
                }
            }
            shape = shapes[i];
        }
    }

    public void spawn(int gridWidth){
        Random random = new Random();

        color = colorArray[random.nextInt(colorArray.length)];

        currentRotation = random.nextInt(shape.length);
        shape =shapes[currentRotation];

        yOffSet = 0 - getHeight();
        xOffSet = gridWidth/2 - getWidth()/2;
    }


    public int[][] getShape() {
        return shape;
    }
    public Color getColor() {
        return color;
    }
    public int getHeight(){return shape.length;}
    public int getWidth(){return shape[0].length;}

    public void moveDown(){ yOffSet++; }
    public void moveRight(){ xOffSet++; }
    public void moveLeft(){ xOffSet--; }

    public int getXOffSet() {
        return xOffSet;
    }
    public int getYOffSet() {
        return yOffSet;
    }
    public int getBootomEdge(){
        return yOffSet + getHeight(); }
    public int getLeftEdge(){
        return xOffSet; }
    public int getRightEdge(){
        return xOffSet + getWidth(); }



    public void rotate(){
        currentRotation++;
        if (currentRotation > 3)  {  currentRotation =0; }

        shape = shapes[currentRotation];
    }






}
