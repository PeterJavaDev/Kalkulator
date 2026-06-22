package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;

public class BackspaceCommand implements ButtonCommand {

    private final CalculatorModel model;

    public BackspaceCommand(CalculatorModel model) {
        this.model = model;
    }

    @Override
    public void execute() {
        model.backspace();
    }
}
