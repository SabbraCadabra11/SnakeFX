package dw.sabbracadabra.snakefx.mainMenu;

import dw.sabbracadabra.snakefx.util.factories.ButtonFactory;
import dw.sabbracadabra.snakefx.util.factories.LabelFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

public class MainMenuView extends VBox {
    private final Button newGameButton;
    private final Button highscoresButton;
    private final Button exitButton;
    public MainMenuView() {
        Label titleLabel = LabelFactory.getLabel("Snake", Color.LIGHTGREEN, 52, FontWeight.EXTRA_BOLD);
        newGameButton = ButtonFactory.getButton("New game", FontWeight.BOLD, 40);
        highscoresButton = ButtonFactory.getButton("Highscores", FontWeight.BOLD, 40);
        exitButton = ButtonFactory.getButton("Exit", FontWeight.BOLD, 40);

        setBackground(Background.fill(Color.BLACK));
        setAlignment(Pos.CENTER);
        setSpacing(20);

        getChildren().addAll(titleLabel, newGameButton, highscoresButton, exitButton);
        VBox.setMargin(titleLabel, new Insets(0, 0, 60, 0));
    }

    public Button getNewGameButton() {
        return newGameButton;
    }

    public Button getHighscoresButton() {
        return highscoresButton;
    }

    public Button getExitButton() {
        return exitButton;
    }
}
