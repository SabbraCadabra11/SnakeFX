package dw.sabbracadabra.snakefx.highscoresMenu;

import dw.sabbracadabra.snakefx.game.model.GameStats;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.factories.ButtonFactory;
import dw.sabbracadabra.snakefx.util.factories.LabelFactory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.List;

public class HighscoresMenuView extends VBox {
    private Button nextPageBtn;
    private Button prevPageBtn;
    private Button backToMenuBtn;

    public HighscoresMenuView(List<GameStats> highscores) {
        Font font = Config.DEFAULT_FONT;
        font = Font.font(font.getFamily(), FontWeight.BOLD, 40);
        Label titleLabel = LabelFactory.getLabel("Highscores", font, Color.LIGHTGREEN);

        GridPane scoresTable = generateInitialScoresTable(highscores);
        HBox tableButtons = loadTableButtons();

        backToMenuBtn = ButtonFactory.getButton("Back to main menu", FontWeight.BOLD, 36);

        setBackground(Background.fill(Color.valueOf("#000000")));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, scoresTable, tableButtons);

    }

    private GridPane generateInitialScoresTable(List<GameStats> highscores) {
        if (highscores.isEmpty()) {
            return new GridPane();
        }

        GridPane table = new GridPane();
        table.setAlignment(Pos.CENTER);
        table.setHgap(5);
        table.setVgap(5);

        Font font = Config.DEFAULT_FONT;
        font = Font.font(font.getFamily(), FontWeight.LIGHT, 20);
        Label scoreHeader = LabelFactory.getLabel("Score", font, Color.LIGHTGREEN);
        Label lengthHeader = LabelFactory.getLabel("Snake Length", font, Color.LIGHTGREEN);
        Label timeHeader = LabelFactory.getLabel("Time", font, Color.LIGHTGREEN);

        table.add(scoreHeader, 1, 0);
        table.add(lengthHeader, 2, 0);
        table.add(timeHeader, 3, 0);

        for (int i = 1; i <= 10; i++) {
            if (i > highscores.size()) {
                break;
            }
            String score = String.valueOf(highscores.get(i - 1).getScore());
            String length = String.valueOf(highscores.get(i - 1).getSnakeLength());
            String time = highscores.get(i - 1).getGameClock();
            Label ordinal = LabelFactory.getLabel(String.valueOf(i), font, Color.LIGHTGREEN);
            Label scoreLabel = LabelFactory.getLabel(score, font, Color.LIGHTGREEN);
            Label lengthLabel = LabelFactory.getLabel(length, font, Color.LIGHTGREEN);
            Label timeLabel = LabelFactory.getLabel(time, font, Color.LIGHTGREEN);
            table.add(ordinal, 0, i);
            table.add(scoreLabel, 1, i);
            table.add(lengthLabel, 2, i);
            table.add(timeLabel, 3, i);
        }
        table.setAlignment(Pos.CENTER);
        return table;
    }

    private HBox loadTableButtons() {
        nextPageBtn = ButtonFactory.getButton("Next page", FontWeight.BOLD, 20);
        prevPageBtn = ButtonFactory.getButton("Previous page", FontWeight.BOLD, 20);
        HBox tableButtons = new HBox(nextPageBtn, prevPageBtn);
        tableButtons.setAlignment(Pos.CENTER);
        tableButtons.setPadding(new Insets(10));

        return tableButtons;
    }
}


/*
                        HIGHSCORES

            Score       Snake Length        Time
    1       1234456         45              15:45
    2       1123443         41              14:30
    3       1112345         36              14:04
    4       1111234         32              13:34
                        ...........
                        ...........
    10      8765             7              06:21

                            1/5
            [Next page]              [Prev page]
                    [Back to main menu]
 */






