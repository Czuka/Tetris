package org.example.gameplay;

import java.awt.*;

public class TetrisBlock {
    private int[][] shape;
    private Color color;
    private int xOffSet,yOffSet;




    public TetrisBlock(int[][] shape, Color color){
        this.shape = shape;
        this.color = color;



    }


    public void spawn(int gridWidth){
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
}
