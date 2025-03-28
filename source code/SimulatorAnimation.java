package src;

import java.util.Timer;
import java.util.TimerTask;

public class SimulatorAnimation implements Runnable {
    private final SimulatorPanel graphicsPanel;
    private Thread animationThread;
    private final int FPS;
    private boolean running = true; 

    public SimulatorAnimation(SimulatorPanel panel, int fps) {
        this.graphicsPanel = panel;
        this.FPS = fps;
    }

    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS; // Time per frame in nanoseconds
        long lastFrameTime = System.nanoTime();

        while (running) {
            long currentTime = System.nanoTime();

            if (currentTime - lastFrameTime >= timePerFrame) {
                graphicsPanel.repaint(); // Generates one frame
                lastFrameTime = currentTime; // Update last frame time
            }
        }
    }

    //start animation loop
    public void start() {
        animationThread = new Thread(this); //start loop on its own thread
        animationThread.start();
    }

    //stop animation loop
    public void stop() {
        running = false;
    }

    public void setAnimation(int catAction){
        graphicsPanel.setAnimation(catAction);
    }

    public void resetAnimationTimer(int durationInSeconds){
        graphicsPanel.resetAnimationTimer(durationInSeconds);
    }

    // Method to set the thought bubble text
    public void setThought(String text, int durationInSeconds) {
        graphicsPanel.setThought(text, durationInSeconds);
    }
}