package pl.pajakcodes.calculator.command;

import pl.pajakcodes.calculator.model.CalculatorModel;
import pl.pajakcodes.calculator.model.Operator;

public class OperatorCommand implements ButtonCommand {

    private final CalculatorModel model;
    private final Operator operator;

    public OperatorCommand(CalculatorModel model, Operator operator) {
        this.model = model;
        this.operator = operator;
    }

    @Override
    public void execute() {
        model.applyOperator(operator);
    }
}
