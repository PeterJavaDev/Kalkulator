package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class SquareRootCommand implements ButtonCommand {

    private final CalculatorModel model;

    public SquareRootCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.squareRoot();
    }
}
