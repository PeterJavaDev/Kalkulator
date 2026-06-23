package pl.pajakcodes.calculator.model;

/**
 * The binary arithmetic operators the calculator supports.
 * Each constant knows how to apply itself, so callers never switch on a symbol.
 */
public enum Operator {
    ADD {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    SUBTRACT {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE {
        @Override
        public double apply(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("Nie można dzielić przez zero!");
            }
            return a / b;
        }
    },
    POWER {
        @Override
        public double apply(double a, double b) {
            double result = Math.pow(a, b);
            if (Double.isNaN(result) || Double.isInfinite(result)) {
                throw new ArithmeticException("Nie można obliczyć potęgi!");
            }
            return result;
        }
    };

    public abstract double apply(double a, double b);
}
