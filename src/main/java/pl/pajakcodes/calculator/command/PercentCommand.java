package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class PercentCommand implements ButtonCommand {

    private final CalculatorModel model;

    public PercentCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.percent();
    }
}
