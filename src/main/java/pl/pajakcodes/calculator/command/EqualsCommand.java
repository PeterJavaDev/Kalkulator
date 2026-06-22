package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class EqualsCommand implements ButtonCommand {

    private final CalculatorModel model;

    public EqualsCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.calculateResult();
    }
}
