package pl.pajakcodes.calculator;

import pl.pajakcodes.calculator.model.CalculatorModel;
import pl.pajakcodes.calculator.gui.CalculatorFrame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class Calculator {

    static void main() {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new CalculatorFrame(new CalculatorModel()).setVisible(true);
        });
    }
}
