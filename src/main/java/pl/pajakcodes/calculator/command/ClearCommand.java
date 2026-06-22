package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class ClearCommand implements ButtonCommand {

    private final CalculatorModel model;

    public ClearCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.clear();
    }
}
