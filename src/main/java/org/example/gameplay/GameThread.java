package org.example.gameplay;

public class GameThread extends Thread{

    private GameArea ga;
    private int testLoop;
    private int scoreTrack;
    private int levelTrack = 1;
    private int scorePerLevel = 3;

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
            if(ga.isOutOfBounds()){
                System.out.println("game over");
                break;
            }
            ga.moveBlockToBackground();
            scoreTrack += ga.clearLines();
            System.out.println(""+scoreTrack);

            int lvl = scoreTrack / scorePerLevel+1;
            if( lvl > levelTrack ){
                levelTrack = lvl;
            }
            ga.labelUpdates(scoreTrack, levelTrack);


        }
    }
}
