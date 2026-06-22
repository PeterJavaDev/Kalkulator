package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class DigitCommand implements ButtonCommand {

    private final CalculatorModel model;
    private final String digit;

    public DigitCommand(CalculatorModel model, String digit) {
        this.model = model;
        this.digit = digit;
    }

    @Override
    public void execute() {
        model.inputDigit(digit);
    }
}
