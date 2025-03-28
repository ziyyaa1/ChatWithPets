package src;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AttributeSet;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.awt.BorderLayout;

public class SimulatorUI {

    private SimulatorPanel graphicsPanel;
    private JPanel userInputPanel;
    private String userInputString = ""; // Store user input
    private final Object inputLock = new Object(); // Lock for waiting thread

    public SimulatorUI(SimulatorPanel graphicsPanel){
        this.graphicsPanel = graphicsPanel;

        graphicsPanel.setLayout(new BorderLayout()); // Layout for proper alignment
        handleUserInputBar();
    }

    public void showUI(){
        // Add the text field to the panel
        graphicsPanel.add(userInputPanel, BorderLayout.SOUTH); //add component to the graphics panel
    }

    //handles the user input bar
    private void handleUserInputBar(){

        // Create the user input field
        JTextField userInputBar = new JTextField();
        setTextLimit(userInputBar, 50); // Set text limit to 50 characters

        // Create the "Enter" button
        JButton enterButton = new JButton("Enter");

        // Handle button click event
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeAndNotifyInput(userInputBar);
            }
        });

        // Handle pressing "Enter" in the text field
        userInputBar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                storeAndNotifyInput(userInputBar);
            }
        });

        // Create a panel to hold the text field and button
        userInputPanel = new JPanel(new BorderLayout());
        userInputPanel.add(userInputBar, BorderLayout.CENTER);
        userInputPanel.add(enterButton, BorderLayout.EAST);
    }

    //forgot how this works but it works XD
    private void setTextLimit(JTextField textField, int limit) {
        AbstractDocument doc = (AbstractDocument) textField.getDocument();
        doc.setDocumentFilter(new DocumentFilter() {
            @Override
            public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
                if (fb.getDocument().getLength() + string.length() <= limit) {
                    super.insertString(fb, offset, string, attr);
                }
            }

            @Override
            public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                if (fb.getDocument().getLength() - length + text.length() <= limit) {
                    super.replace(fb, offset, length, text, attrs);
                }
            }
        });
    }

    // Blocking method to wait for user input
    public String getInputString() {
        synchronized (inputLock) {
            try {
                while (userInputString.isEmpty()) { // Wait until input is available
                    inputLock.wait();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            String input = userInputString;
            userInputString = ""; // Reset input after retrieval
            return input;
        }
    }

    // Store input and notify waiting thread
    private void storeAndNotifyInput(JTextField userInputBar) {
        synchronized (inputLock) {
            userInputString = userInputBar.getText(); // Store the input
            userInputBar.setText(""); // Clear input field
            inputLock.notify(); // Wake up waiting thread
        }
    }
}
