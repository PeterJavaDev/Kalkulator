package pl.pajakcodes.calculator.gui;

import java.awt.Color;

public enum ButtonStyle {
    DIGIT(new Color(60, 60, 60)),
    OPERATOR(new Color(220, 160, 60)),
    FUNCTION(new Color(160, 160, 160)),
    EQUALS(new Color(70, 130, 180));

    private final Color background;

    ButtonStyle(Color background) {
        this.background = background;
    }

    public Color background() {
        return background;
    }
}
