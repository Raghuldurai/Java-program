import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class morse extends JFrame {
    private JTextArea inputTextArea;
    private JTextArea outputTextArea;

    private static final String[] morseAlphabet = {
            ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-", ".-..", "--", "-.",
            "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", "-----",
            ".----", "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.", ".-.-.-", "--..--",
            "..--..", "-.-.--", ".-.-.."
    };

    private static final String[] characters = {
            "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
            "U", "V", "W", "X", "Y", "Z", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", ".", ",", "?", "!", "/"
    };

    public morse() {
        super("Morse Code Translator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        inputTextArea = new JTextArea();
        outputTextArea = new JTextArea();

        JButton toMorseButton = new JButton("To Morse");
        JButton toTextButton = new JButton("To Text");

        toMorseButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String text = inputTextArea.getText().toUpperCase();
                String morse = textToMorse(text);
                outputTextArea.setText(morse);
            }
        });

        toTextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String morse = inputTextArea.getText();
                String text = morseToText(morse);
                outputTextArea.setText(text);
            }
        });

        JPanel inputPanel = new JPanel(new BorderLayout());
        inputPanel.add(new JLabel("Input Text/Morse"), BorderLayout.NORTH);
        inputPanel.add(new JScrollPane(inputTextArea), BorderLayout.CENTER);

        JPanel outputPanel = new JPanel(new BorderLayout());
        outputPanel.add(new JLabel("Output"), BorderLayout.NORTH);
        outputPanel.add(new JScrollPane(outputTextArea), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(toMorseButton);
        buttonPanel.add(toTextButton);

        add(inputPanel);
        add(outputPanel);
        add(buttonPanel);

        setVisible(true);
    }

    private String textToMorse(String text) {
        StringBuilder morseCode = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == ' ') {
                morseCode.append("   ");
            } else {
                for (int j = 0; j < characters.length; j++) {
                    if (characters[j].charAt(0) == c) {
                        morseCode.append(morseAlphabet[j]).append(" ");
                        break;
                    }
                }
            }
        }
        return morseCode.toString();
    }

    private String morseToText(String morse) {
        StringBuilder text = new StringBuilder();
        String[] words = morse.split("   ");
        for (String word : words) {
            String[] letters = word.split(" ");
            for (String letter : letters) {
                for (int j = 0; j < morseAlphabet.length; j++) {
                    if (morseAlphabet[j].equals(letter)) {
                        text.append(characters[j]);
                        break;
                    }
                }
            }
            text.append(" ");
        }
        return text.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new morse();
            }
        });
    }
}
