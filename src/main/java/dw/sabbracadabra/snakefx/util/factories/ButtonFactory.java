package dw.sabbracadabra.snakefx.util.factories;

import dw.sabbracadabra.snakefx.util.Config;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ButtonFactory {
    public static Button getButton(String caption, FontWeight fontWeight, int fontSize) {
        Font font = Config.DEFAULT_FONT;
        font = Font.font(font.getFamily(), fontWeight, fontSize);

        Button button = new Button(caption);
        button.setFont(font);
        button.setTextFill(Color.LIGHTGREEN);
        button.setBorder(Border.stroke(Color.LIGHTGREEN));
        button.setBackground(Background.fill(Color.BLACK));
        button.setFocusTraversable(true);

        return button;
    }
}
