package org.example.gameplay;

public class GameThread extends Thread{

    private GameArea ga;
    private int testLoop;

    public GameThread(GameArea ga) {
        this.ga = ga;
    }

    @Override
    public void run(){


        while(true){
            System.out.println("run while "+testLoop++);

            ga.spawnBlock();

            while(ga.moveBlockDown() == true){
                try {
                    //System.out.println("run whileWhileTry "+testLoop++);
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }



        }
    }


}
