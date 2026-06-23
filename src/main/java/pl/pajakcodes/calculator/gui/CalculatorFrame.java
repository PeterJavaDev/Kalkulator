package pl.pajakcodes.calculator.gui;

import pl.pajakcodes.calculator.command.BackspaceCommand;
import pl.pajakcodes.calculator.command.ClearCommand;
import pl.pajakcodes.calculator.command.DecimalCommand;
import pl.pajakcodes.calculator.command.DigitCommand;
import pl.pajakcodes.calculator.command.EqualsCommand;
import pl.pajakcodes.calculator.command.NegateCommand;
import pl.pajakcodes.calculator.command.OperatorCommand;
import pl.pajakcodes.calculator.command.PercentCommand;
import pl.pajakcodes.calculator.command.SquareRootCommand;
import pl.pajakcodes.calculator.model.CalculatorModel;
import pl.pajakcodes.calculator.model.Operator;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

public class CalculatorFrame extends JFrame {

    private final CalculatorModel model;
    private final JTextField display = new JTextField("0");

    public CalculatorFrame(CalculatorModel model) {
        this.model = model;

        setTitle("Kalkulator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        configureDisplay();
        setLayout(new BorderLayout(0, 5));
        add(buildDisplayPanel(), BorderLayout.NORTH);
        add(buildButtonPanel(), BorderLayout.CENTER);

        model.setDisplayListener(display::setText);
        model.setErrorListener(message ->
                JOptionPane.showMessageDialog(this, message, "Błąd", JOptionPane.ERROR_MESSAGE));

        pack();
        setLocationRelativeTo(null);
    }

    private void configureDisplay() {
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setPreferredSize(new Dimension(300, 60));
        display.setBackground(Color.WHITE);
        display.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
    }

    private JPanel buildDisplayPanel() {
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
        topPanel.add(display, BorderLayout.CENTER);
        return topPanel;
    }

    private JPanel buildButtonPanel() {
        JPanel buttonPanel = new JPanel(new BorderLayout(0, 5));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.add(buildGrid(scientificRow(), 1, 2), BorderLayout.NORTH);
        buttonPanel.add(buildGrid(keypad(), 5, 4), BorderLayout.CENTER);
        return buttonPanel;
    }

    private JPanel buildGrid(List<ButtonSpec> specs, int rows, int cols) {
        JPanel panel = new JPanel(new GridLayout(rows, cols, 5, 5));
        for (ButtonSpec spec : specs) {
            panel.add(createButton(spec));
        }
        return panel;
    }

    private List<ButtonSpec> scientificRow() {
        return List.of(
                new ButtonSpec("√", ButtonStyle.FUNCTION, new SquareRootCommand(model)),
                new ButtonSpec("xʸ", ButtonStyle.OPERATOR, new OperatorCommand(model, Operator.POWER))
        );
    }

    private List<ButtonSpec> keypad() {
        return List.of(
                new ButtonSpec("C", ButtonStyle.FUNCTION, new ClearCommand(model)),
                new ButtonSpec("+/-", ButtonStyle.FUNCTION, new NegateCommand(model)),
                new ButtonSpec("%", ButtonStyle.FUNCTION, new PercentCommand(model)),
                new ButtonSpec("/", ButtonStyle.OPERATOR, new OperatorCommand(model, Operator.DIVIDE)),

                new ButtonSpec("7", ButtonStyle.DIGIT, new DigitCommand(model, "7")),
                new ButtonSpec("8", ButtonStyle.DIGIT, new DigitCommand(model, "8")),
                new ButtonSpec("9", ButtonStyle.DIGIT, new DigitCommand(model, "9")),
                new ButtonSpec("*", ButtonStyle.OPERATOR, new OperatorCommand(model, Operator.MULTIPLY)),

                new ButtonSpec("4", ButtonStyle.DIGIT, new DigitCommand(model, "4")),
                new ButtonSpec("5", ButtonStyle.DIGIT, new DigitCommand(model, "5")),
                new ButtonSpec("6", ButtonStyle.DIGIT, new DigitCommand(model, "6")),
                new ButtonSpec("-", ButtonStyle.OPERATOR, new OperatorCommand(model, Operator.SUBTRACT)),

                new ButtonSpec("1", ButtonStyle.DIGIT, new DigitCommand(model, "1")),
                new ButtonSpec("2", ButtonStyle.DIGIT, new DigitCommand(model, "2")),
                new ButtonSpec("3", ButtonStyle.DIGIT, new DigitCommand(model, "3")),
                new ButtonSpec("+", ButtonStyle.OPERATOR, new OperatorCommand(model, Operator.ADD)),

                new ButtonSpec("0", ButtonStyle.DIGIT, new DigitCommand(model, "0")),
                new ButtonSpec(".", ButtonStyle.DIGIT, new DecimalCommand(model)),
                new ButtonSpec("←", ButtonStyle.FUNCTION, new BackspaceCommand(model)),
                new ButtonSpec("=", ButtonStyle.EQUALS, new EqualsCommand(model))
        );
    }

    private JButton createButton(ButtonSpec spec) {
        JButton btn = new JButton(spec.label());
        btn.setFont(new Font("Arial", Font.BOLD, 20));
        btn.setPreferredSize(new Dimension(65, 55));
        btn.setFocusPainted(false);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        btn.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        btn.setBackground(spec.style().background());
        btn.setForeground(Color.WHITE);
        btn.addActionListener(e -> spec.command().execute());
        return btn;
    }
}
