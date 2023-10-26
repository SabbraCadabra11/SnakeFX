package dw.sabbracadabra.snakefx.util.factories;

import dw.sabbracadabra.snakefx.util.Config;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class LabelFactory {
    public static Label getLabel(String caption, Color captionColor, int fontSize, FontWeight fontWeight) {
        Font font = Config.DEFAULT_FONT;
        font = Font.font(font.getFamily(), fontWeight, fontSize);
        Label label = new Label(caption);
        label.setFont(font);
        label.setTextFill(captionColor);
        return label;
    }
}
