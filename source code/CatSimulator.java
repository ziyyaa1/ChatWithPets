package src;
import java.util.Scanner;

public class CatSimulator{

    private static SimulatorFrame frame;
    private static SimulatorPanel graphicsPanel;
    private static SimulatorUI graphicsPanelUI;
    private static SimulatorAnimation animation;
    private static final int SET_FPS = 60;

    public static void main(String[] args) {

        handleGraphics();
        handleCommands(animation);

    }

    private static void handleCommands(SimulatorAnimation petAnimation){
        CommandDetectionBot bot = new CommandDetectionBot();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your cat's name: ");
        //String catName = scanner.nextLine();
        String catName = "Bella";
        Cat myCat = new Cat(catName, petAnimation);

        System.out.println("💬 Type a command (or 'exit' to quit):");
        while (true) {
            System.out.print("> ");
            //String userInput = scanner.nextLine();
            String userInput = graphicsPanelUI.getInputString();
            if (userInput.equalsIgnoreCase("exit")) break;

            // Detect intent
            String category = bot.detectCommand(userInput);

            myCat.talkToPet(category); //process the commands
        }
        scanner.close();
    }

    private static void handleGraphics(){

        //setup frame and panel
        graphicsPanel = new SimulatorPanel();

        // Create the UI panel for user input
        graphicsPanelUI = new SimulatorUI(graphicsPanel);
        graphicsPanelUI.showUI();

        //put the graphicsPanel into the frame
        //frame is the screen window
        //graphicsPanel is where the picture is drawn
        //so we are basically putting the picture on the frame lol
        frame = new SimulatorFrame(graphicsPanel, graphicsPanelUI);

        //setup animation
        animation = new SimulatorAnimation(graphicsPanel, SET_FPS);
        animation.start(); //start animation loop
    }

}