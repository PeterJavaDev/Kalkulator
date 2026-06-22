package pl.pajakcodes.calculator.model;

public class CalculatorModel {

    private static final int MAX_LENGTH = 15;

    public interface DisplayListener {
        void onDisplayChanged(String display);
    }

    public interface ErrorListener {
        void onError(String message);
    }

    private String display = "0";
    private double firstOperand = 0;
    private Operator operator = null;
    private boolean operatorPressed = false;

    private DisplayListener displayListener = d -> {};
    private ErrorListener errorListener = m -> {};

    public void setDisplayListener(DisplayListener listener) {
        this.displayListener = listener;
        listener.onDisplayChanged(display);
    }

    public void setErrorListener(ErrorListener listener) {
        this.errorListener = listener;
    }

    public String getDisplay() {
        return display;
    }

    public void inputDigit(String digit) {
        if (operatorPressed || display.equals("0")) {
            display = digit;
            operatorPressed = false;
        } else if (display.length() < MAX_LENGTH) {
            display += digit;
        }
        fireDisplayChanged();
    }

    public void inputDecimal() {
        if (operatorPressed) {
            display = "0.";
            operatorPressed = false;
        } else if (!display.contains(".")) {
            display += ".";
        }
        fireDisplayChanged();
    }

    public void applyOperator(Operator op) {
        firstOperand = Double.parseDouble(display);
        operator = op;
        operatorPressed = true;
    }

    public void calculateResult() {
        if (operator == null) {
            return;
        }
        double secondOperand = Double.parseDouble(display);
        try {
            display = format(operator.apply(firstOperand, secondOperand));
        } catch (ArithmeticException ex) {
            errorListener.onError(ex.getMessage());
            display = "0";
        }
        operator = null;
        operatorPressed = true;
        fireDisplayChanged();
    }

    public void clear() {
        display = "0";
        firstOperand = 0;
        operator = null;
        operatorPressed = false;
        fireDisplayChanged();
    }

    public void backspace() {
        if (display.length() > 1) {
            display = display.substring(0, display.length() - 1);
        } else {
            display = "0";
        }
        fireDisplayChanged();
    }

    public void negate() {
        display = format(-Double.parseDouble(display));
        fireDisplayChanged();
    }

    public void percent() {
        display = format(Double.parseDouble(display) / 100);
        fireDisplayChanged();
    }

    private void fireDisplayChanged() {
        displayListener.onDisplayChanged(display);
    }

    private static String format(double result) {
        if (result == Math.floor(result) && !Double.isInfinite(result)) {
            return String.valueOf((long) result);
        }
        return String.valueOf(result);
    }
}
