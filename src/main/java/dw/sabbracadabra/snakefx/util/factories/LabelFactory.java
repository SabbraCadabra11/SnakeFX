package dw.sabbracadabra.snakefx.util.factories;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class LabelFactory {
    public static Label getLabel(String caption, Font font, Color captionColor) {
        Label label = new Label(caption);
        label.setFont(font);
        label.setTextFill(captionColor);
        return label;
    }
}
