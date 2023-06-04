import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame {
    private JTextField displayField;
    private StringBuilder input;

    public CalculatorGUI() {
        super("계산기");

        input = new StringBuilder();

        displayField = new JTextField();
        displayField.setEditable(false);

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        String[] buttonLabels = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
        }

        setLayout(new BorderLayout());
        add(displayField, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String command = event.getActionCommand();

            if (command.equals("=")) {
                try {
                    double result = evaluateExpression(input.toString());
                    displayField.setText(Double.toString(result));
                    input.setLength(0);
                } catch (NumberFormatException | ArithmeticException e) {
                    displayField.setText("Error");
                }
            } else {
                input.append(command);
                displayField.setText(input.toString());
            }
        }

        private double evaluateExpression(String expression) {
            return Double.parseDouble(expression);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CalculatorGUI();
            }
        });
    }
}

