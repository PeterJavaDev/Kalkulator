package pl.pajakcodes.calculator.gui;

import pl.pajakcodes.calculator.command.ButtonCommand;

public record ButtonSpec(String label, ButtonStyle style, ButtonCommand command) {
}
