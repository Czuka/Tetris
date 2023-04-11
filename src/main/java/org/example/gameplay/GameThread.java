package org.example.gameplay;

public class GameThread extends Thread{

    private GameArea ga;
    private int testLoop;
    private int scoreTrack;
    private int levelTrack = 1;
    private int scorePerLevel = 3;
    private int pause =+ 600;
    private float speedModificator = 1.0f;

    public GameThread(GameArea ga) {
        this.ga = ga;
    }

    @Override
    public void run(){


        while(true){

            ga.spawnBlock();

            while(ga.moveBlockDown() == true){
                try {
                    //System.out.println("run whileWhileTry "+testLoop++);
                    Thread.sleep((int)(pause/speedModificator));
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

            int lvl = scoreTrack / scorePerLevel+1;
            if( lvl > levelTrack ){
                levelTrack = lvl;
                scorePerLevel += 1;
                speedModificator += 0.2f;
            }
            ga.labelUpdates(scoreTrack, levelTrack);


        }
    }
}
