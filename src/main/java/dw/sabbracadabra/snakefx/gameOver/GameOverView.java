package dw.sabbracadabra.snakefx.gameOver;

import dw.sabbracadabra.snakefx.util.factories.ButtonFactory;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.factories.LabelFactory;
import dw.sabbracadabra.snakefx.game.model.GameStats;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class GameOverView extends VBox {
    private static Font font = Config.DEFAULT_FONT;
    private static Button mainMenuButton;
    private static Button newGameButton;
    public GameOverView(GameStats thisGameStats, GameStats bestGameStats) {
        font = Font.font(font.getFamily(), FontWeight.BOLD, 40);

        Label titleLabel = LabelFactory.getLabel("Game over", font, Color.LIGHTGREEN);
        StatsColumn thisGameColumn = new StatsColumn("This game",
                                                    thisGameStats.getScore(),
                                                    thisGameStats.getSnakeLength(),
                                                    thisGameStats.getGameClock());
        StatsColumn bestGameColumn = new StatsColumn("Best game",
                                                    bestGameStats.getScore(),
                                                    bestGameStats.getSnakeLength(),
                                                    bestGameStats.getGameClock());
        HBox statsPane = new HBox(thisGameColumn, bestGameColumn);
        statsPane.setAlignment(Pos.CENTER);
        ButtonsPane buttonsPane = new ButtonsPane();

        getChildren().addAll(titleLabel, statsPane, buttonsPane);
        setAlignment(Pos.CENTER);
        setBackground(Background.fill(Color.valueOf("#000000")));
    }

    private static class StatsColumn extends VBox {
        StatsColumn(String columnTitle, int score, int snakeLen, String duration) {
            Font titleFont = Font.font(font.getFamily(), FontWeight.BOLD ,34);
            Font statsFont = Font.font(font.getFamily(), 30);
            Label title = LabelFactory.getLabel(columnTitle, titleFont, Color.LIGHTGREEN);
            Label scoreLabel = LabelFactory.getLabel("Score: " + score, statsFont, Color.LIGHTGREEN);
            Label snakeLenLabel = LabelFactory.getLabel("Snake length: " + snakeLen, statsFont, Color.LIGHTGREEN);
            Label clockLabel = LabelFactory.getLabel("Game duration: " + duration, statsFont, Color.LIGHTGREEN);
            getChildren().addAll(title, scoreLabel, snakeLenLabel, clockLabel);
            setAlignment(Pos.CENTER_LEFT);
            setPadding(new Insets(30));
        }
    }

    private static class ButtonsPane extends VBox {
        ButtonsPane() {
            mainMenuButton = ButtonFactory.getButton("Back to main menu", FontWeight.BOLD, 30);
            newGameButton = ButtonFactory.getButton("New game", FontWeight.BOLD, 30);
            setAlignment(Pos.CENTER);
            getChildren().addAll(newGameButton, mainMenuButton);
        }
    }

    public Button getMainMenuButton() {
        return mainMenuButton;
    }

    public Button getNewGameButton() {
        return newGameButton;
    }
}
