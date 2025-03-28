package src;
import opennlp.tools.doccat.*;
import opennlp.tools.util.*;
import java.io.*;

public class TrainIntentModel {
    // public static void main(String[] args) {
    //     try {
    //         // Step 1: Load training data from the file
    //         // "intent-training.txt" should contain labeled examples in this format:
    //         // sleep_command    Go to sleep
    //         // general_command  Play music
    //         InputStreamFactory inputStreamFactory = new MarkableFileInputStreamFactory(new File("intent-training.txt"));

    //         // Convert each line of the training file into a DocumentSample object
    //         ObjectStream<String> lineStream = new PlainTextByLineStream(inputStreamFactory, "UTF-8");
    //         ObjectStream<DocumentSample> sampleStream = new DocumentSampleStream(lineStream);

    //         // Step 2: Configure model training parameters
    //         TrainingParameters params = new TrainingParameters();
    //         params.put(TrainingParameters.ITERATIONS_PARAM, "100");
    //         params.put(TrainingParameters.CUTOFF_PARAM, "1");

    //         // Step 3: Train the intent detection model
    //         DoccatFactory factory = new DoccatFactory();
    //         DoccatModel model = DocumentCategorizerME.train("en", sampleStream, params, factory);

    //         // Step 4: Save the trained model to a binary file
    //         File modelFile = new File("intent-model.bin");
    //         FileOutputStream modelOut = new FileOutputStream(modelFile);
    //         model.serialize(modelOut);
    //         modelOut.close();

    //         // Step 5: Print success message
    //         System.out.println("✅ Model training complete! Saved as 'intent-model.bin'.");

    //     } catch (IOException e) {
    //         // Handle any file reading/writing errors
    //         e.printStackTrace();
    //     }
    // }
}