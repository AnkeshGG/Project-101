import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class Calculator extends JFrame {
    private JTextField display;
    private JPanel buttonPanel;
    private double firstNumber = 0;
    private String operator = "";
    private boolean isOperatorClicked = false;
    private boolean isEqualClicked = false;
    private ArrayList<String> history = new ArrayList<>();
    private JPanel historyPanel;
    private JScrollPane historyScrollPane;
    private DecimalFormat df = new DecimalFormat("#.##########");

    // Color scheme
    private Color backgroundColor = new Color(33, 52, 72);
    private Color displayBackgroundColor = new Color(33, 45, 59);
    private Color displayTextColor = new Color(236, 239, 202);
    private Color operatorButtonColor = new Color(70, 70, 70);
    private Color operatorButtonTextColor = new Color(84, 119, 146);
    private Color specialButtonColor = new Color(70, 70, 70);
    private Color specialButtonTextColor = new Color(84, 119, 146);
    private Color numberButtonColor = new Color(70, 70, 70);
    private Color numberButtonTextColor = new Color(84, 119, 146);
    private Color historyPanelColor = new Color(12, 29, 44);
    private Color historyTextColor = new Color(245, 231, 169);

    public Calculator() {
        // Set up the frame
        setTitle("CalcMate");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(backgroundColor);

        // Create display
        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 36));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(displayBackgroundColor);
        display.setForeground(displayTextColor);
        display.setMargin(new Insets(10, 10, 10, 10));
        display.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // Create button panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4, 8, 8));
        buttonPanel.setBackground(backgroundColor);
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Create history panel
        historyPanel = new JPanel();
        historyPanel.setLayout(new BoxLayout(historyPanel, BoxLayout.Y_AXIS));
        historyPanel.setBackground(historyPanelColor);

        historyScrollPane = new JScrollPane(historyPanel);
        historyScrollPane.setPreferredSize(new Dimension(400, 150));
        historyScrollPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Add components to the frame
        add(display, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(historyScrollPane, BorderLayout.SOUTH);

        // Create buttons
        createButtons();

        // Final setup
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void createButtons() {
        // First row - special functions
        addSpecialButton("C", e -> clear());
        addSpecialButton("±", e -> toggleSign());
        addSpecialButton("%", e -> percentage());
        addOperatorButton("÷", e -> handleOperator("÷"));

        // Second row
        addNumberButton("7", e -> appendNumber("7"));
        addNumberButton("8", e -> appendNumber("8"));
        addNumberButton("9", e -> appendNumber("9"));
        addOperatorButton("×", e -> handleOperator("×"));

        // Third row
        addNumberButton("4", e -> appendNumber("4"));
        addNumberButton("5", e -> appendNumber("5"));
        addNumberButton("6", e -> appendNumber("6"));
        addOperatorButton("-", e -> handleOperator("-"));

        // Fourth row
        addNumberButton("1", e -> appendNumber("1"));
        addNumberButton("2", e -> appendNumber("2"));
        addNumberButton("3", e -> appendNumber("3"));
        addOperatorButton("+", e -> handleOperator("+"));

        // Fifth row
        addNumberButton("0", e -> appendNumber("0"));
        addNumberButton(".", e -> appendDecimal());
        addSpecialButton("←", e -> backspace());
        addOperatorButton("=", e -> calculateResult());
    }

    private void addNumberButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setForeground(numberButtonTextColor);
        button.setBackground(numberButtonColor);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(backgroundColor, 1, true));
        button.addActionListener(listener);
        buttonPanel.add(button);
    }

    private void addOperatorButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 24));
        button.setForeground(operatorButtonTextColor);
        button.setBackground(operatorButtonColor);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(backgroundColor, 1, true));
        button.addActionListener(listener);
        buttonPanel.add(button);
    }

    private void addSpecialButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 24));
        button.setForeground(specialButtonTextColor);
        button.setBackground(specialButtonColor);
        button.setFocusPainted(false);
        button.setBorder(new LineBorder(backgroundColor, 1, true));
        button.addActionListener(listener);
        buttonPanel.add(button);
    }

    private void appendNumber(String number) {
        if (isOperatorClicked || display.getText().equals("0") || isEqualClicked) {
            display.setText(number);
            isOperatorClicked = false;
            isEqualClicked = false;
        } else {
            display.setText(display.getText() + number);
        }
    }

    private void appendDecimal() {
        if (isOperatorClicked) {
            display.setText("0.");
            isOperatorClicked = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }

    private void handleOperator(String op) {
        if (!operator.isEmpty() && !isOperatorClicked) {
            calculateResult();
        }

        firstNumber = Double.parseDouble(display.getText());
        operator = op;
        isOperatorClicked = true;
        isEqualClicked = false;
    }

    private void calculateResult() {
        if (operator.isEmpty() || isEqualClicked) {
            return;
        }

        double secondNumber = Double.parseDouble(display.getText());
        double result = 0;

        String calculation = df.format(firstNumber) + " " + operator + " " + df.format(secondNumber);

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "×":
                result = firstNumber * secondNumber;
                break;
            case "÷":
                if (secondNumber == 0) {
                    display.setText("Error");
                    addToHistory(calculation + " = Error");
                    operator = "";
                    isEqualClicked = true;
                    return;
                }
                result = firstNumber / secondNumber;
                break;
        }

        display.setText(df.format(result));
        addToHistory(calculation + " = " + df.format(result));
        operator = "";
        isEqualClicked = true;
    }

    private void clear() {
        display.setText("0");
        firstNumber = 0;
        operator = "";
        isOperatorClicked = false;
        isEqualClicked = false;
    }

    private void toggleSign() {
        double currentValue = Double.parseDouble(display.getText());
        display.setText(df.format(-currentValue));
    }

    private void percentage() {
        double currentValue = Double.parseDouble(display.getText());
        display.setText(df.format(currentValue / 100));
    }

    private void backspace() {
        String currentText = display.getText();
        if (currentText.length() > 1) {
            display.setText(currentText.substring(0, currentText.length() - 1));
        } else {
            display.setText("0");
        }
    }

    private void addToHistory(String text) {
        history.add(text);
        JLabel historyLabel = new JLabel(text);
        historyLabel.setForeground(historyTextColor);
        historyLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        historyLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        historyPanel.add(historyLabel);
        historyPanel.revalidate();
        historyScrollPane.getVerticalScrollBar().setValue(
                historyScrollPane.getVerticalScrollBar().getMaximum());
    }

    public static void main(String[] args) {
        try {
            // Set look and feel to system default
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new Calculator());
    }
}
