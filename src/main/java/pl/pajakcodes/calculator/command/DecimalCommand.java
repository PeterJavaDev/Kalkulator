package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class DecimalCommand implements ButtonCommand {

    private final CalculatorModel model;

    public DecimalCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.inputDecimal();
    }
}
