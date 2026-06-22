package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class NegateCommand implements ButtonCommand {

    private final CalculatorModel model;

    public NegateCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.negate();
    }
}
