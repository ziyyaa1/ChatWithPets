package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.Font;
import java.io.IOException;
import java.io.InputStream;
import java.util.Timer;
import java.util.TimerTask;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import static src.Constants.CatConstants.*;

//graphical panel (modify for graphical display)
public class SimulatorPanel extends JPanel{

    private int imageCenterX = 0;
    private int imageCenterY = 0;

    private int frames = 0; //calculate frames
    private long lastFpsCheck = 0;
    private BufferedImage catSpriteSheet, catSingleSprite;
    private BufferedImage[][] catAnimations; //array for sprite pieces
    private int animationTick, animationIndex, animationSpeed = 18;
    private Timer animationTimer;
    private String thoughtText = ""; // Thought text
    private Timer thoughtTimer;
    private int catAction = IDLE_1;
    
    public SimulatorPanel(){

        importImage();
        loadAnimation();
        setPanelSize();
    }

    //override the JPanel paintComponent()
    //used for drawing on the panel
    public void paintComponent(Graphics graphics){
        super.paintComponent(graphics);// clears prev frame;
        drawBackground(); //paint the background
        
        imageCenterX = getWidth()/4;
        imageCenterY = getHeight()/4;

        if (catSpriteSheet != null) {

            updateAnimationTick();

            //AUTOMATICALLY switch between IDLE_1 and IDLE_2 since they both bake up the entire idle animation
            if(catAction == IDLE_1 && animationIndex >= GetAnimationSize(catAction)-1){
                catAction = IDLE_2;
            }
            else if(catAction == IDLE_2 && animationIndex >= GetAnimationSize(catAction)-1){
                catAction = IDLE_1;
            }

            //draw current sprites on the pannel;
            catSingleSprite = catAnimations[catAction][animationIndex];
            graphics.drawImage(catSingleSprite, imageCenterX, imageCenterY, 256, 256, null);
        }

        // Draw thought bubble in the top-right
        if (!thoughtText.isEmpty()) {
            graphics.setColor(Color.WHITE);
            graphics.setFont(new Font("Arial", Font.BOLD, 20));
            graphics.drawString(thoughtText, 10, getHeight()/5); // Position in top-right
        }

        //trackFPS();

    }

    //set dimentions (size) of the panel
    private void setPanelSize(){
        Dimension size = new Dimension(400, 600);
        setPreferredSize(size);
        setMinimumSize(size);
        setMaximumSize(size);
    }

    //load all animation
    private void loadAnimation(){
        catAnimations = new BufferedImage[15][15];

        for(int col = 0; col < catAnimations.length; col++){
            for(int row = 0; row < catAnimations[col].length; row++){
                catAnimations[col][row] = catSpriteSheet.getSubimage(row*32, col*32, 32, 32); //sprite piece
            }
        }
    }

    //for updating current animation index in the loop
    private void updateAnimationTick(){
        animationTick ++;
        if(animationTick >= GetBestAnimationSpeed(catAction)){
            animationTick = 0;
            animationIndex++;

            if(animationIndex >= GetAnimationSize(catAction)){
                animationIndex = 0;
            }
        }
    }

    //set the current animation on loop
    public void setAnimation(int catAction){

        if(IsAnimationAvailable(catAction)){
            this.catAction = catAction;
        }
    }

    // Method to set the thought bubble text
    public void setThought(String text, int durationInSeconds) {
        this.thoughtText = text;
        repaint(); // Redraw to show text

        // Clear thought bubble after a duration
        if (thoughtTimer != null) {
            thoughtTimer.cancel();
        }

        thoughtTimer = new Timer();
        thoughtTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                thoughtText = "";
                repaint(); // Redraw to remove text
            }
        }, durationInSeconds * 1000);
    }

    //import and load sprites images
    private void importImage(){
        InputStream imageInputStream = getClass().getResourceAsStream("/res/AllCats.png");

        if (imageInputStream == null) {
            System.err.println("Error: Image file 'AllCats.png' not found.");
            return;
        }
        
        //read the image from input stream
        try {
            catSpriteSheet = ImageIO.read(imageInputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            try {
                imageInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // Reset the animation after a duration
    public void resetAnimationTimer(int durationInSeconds) {
        if (animationTimer != null) {
            animationTimer.cancel(); // Cancel existing timer if already running
        }
        
        animationTimer = new Timer();
        animationTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                catAction = IDLE_1; // Reset to default animation
                repaint();
            }
        }, durationInSeconds * 1000); // Convert seconds to milliseconds
    }

    void drawBackground(){
        setBackground(new Color(0xADD8E6));
    }

    //get fps count on console
    private void trackFPS(){
        frames++;
        if(System.currentTimeMillis() - lastFpsCheck >= 1000){
            lastFpsCheck = System.currentTimeMillis();
            System.out.println("FPS: " + frames);
            frames = 0;
        }
    }
}
