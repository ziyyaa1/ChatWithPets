package src;
import opennlp.tools.doccat.*;
import java.io.*;

public class CommandDetectionBot {
    private static final String MODEL_PATH = "intent-model.bin"; //trained model
    private DocumentCategorizerME categorizer;

    // Constructor to load the trained model
    public CommandDetectionBot() {
        try {
            InputStream modelStream = new FileInputStream(MODEL_PATH);
            DoccatModel model = new DoccatModel(modelStream);
            categorizer = new DocumentCategorizerME(model);
            modelStream.close();
        } catch (IOException e) {
            System.err.println("❌ Failed to load model: " + e.getMessage());
        }
    }

    // Method to detect command intent
    public String detectCommand(String input) {
        String[] words = input.toLowerCase().split(" ");
        double[] outcomes = categorizer.categorize(words);
        return categorizer.getBestCategory(outcomes);
    }

    // Main method for user interaction
    // public static void main(String[] args) {
    //     CommandDetectionBot bot = new CommandDetectionBot();
    //     Scanner scanner = new Scanner(System.in);

    //     System.out.println("💬 Type a command (or 'exit' to quit):");
    //     while (true) {
    //         System.out.print("> ");
    //         String userInput = scanner.nextLine();
    //         if (userInput.equalsIgnoreCase("exit")) break;

    //         // Detect intent
    //         String category = bot.detectCommand(userInput);
    //         System.out.println("Detected Intent: " + category);

    //         // Take action based on detected intent
    //         if (category.equals("sleep_command")) {
    //             System.out.println("😴 Action: Sleep-related detected!");
    //         } else {
    //             System.out.println("🔧 Action: General command detected.");
    //         }
    //     }
    //     scanner.close();
    // }
}