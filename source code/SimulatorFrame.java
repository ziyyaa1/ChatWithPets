package src;

import javax.swing.JFrame;

//graphical frame (modify for window setup)
public class SimulatorFrame extends JFrame{
    
    public SimulatorFrame(SimulatorPanel graphicsPanel, SimulatorUI uiPanel){

        //show window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //stop program when window closed
        add(graphicsPanel); //add panel graphics in this frame/window/screen (add component)
        setResizable(false); //let user to manually resize it
        pack(); //fit the size of its components instead of having its own size
        setVisible(true);
    }
}