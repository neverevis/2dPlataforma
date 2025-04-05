package game;

public class GameLoop implements Runnable{

    private GamePanel gp;
    final long frameTime;

    public GameLoop(GamePanel gp){
        this.gp = gp;
        frameTime = 1_000_000_000/(long)gp.FPS;
    }

    @Override
    public void run() {
        long nextTime = System.nanoTime() + frameTime;
        long sleepTime;

        int counter = 0;
        long lastCheck = System.nanoTime();

        double deltaTime;
        double currentTime;
        double lastTime = System.nanoTime();

        while(true){
            currentTime = System.nanoTime();
            deltaTime = (currentTime - lastTime) / 1_000_000_000.0;
            lastTime = currentTime;

            update(deltaTime);
            gp.repaint();

            try{
                sleepTime = (nextTime - System.nanoTime())/1_000_000;

                if(sleepTime < 0)
                    sleepTime = 0;

                Thread.sleep(sleepTime);

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            nextTime += frameTime;

            if(System.nanoTime() - lastCheck >= 1_000_000_000) {
                System.out.println(counter);
                gp.getFrames(counter);
                counter = 0;
                lastCheck = System.nanoTime();
            }
            counter++;
        }
    }

    public void update(double deltaTime) {
        gp.player.update(deltaTime);
        if(gp.imgX > -1*gp.screenWidth)
            gp.imgX -= gp.tileSize*deltaTime*5;
        else
            gp.imgX = 0;
    }
}
