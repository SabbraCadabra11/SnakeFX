package dw.sabbracadabra.snakefx.highscoresMenu;

import dw.sabbracadabra.snakefx.game.model.GameStats;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.factories.ButtonFactory;
import dw.sabbracadabra.snakefx.util.factories.LabelFactory;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.FontWeight;

import java.util.List;

public class HighscoresMenuView extends VBox {
    private Button nextPageBtn;
    private Button prevPageBtn;
    private final Button backToMenuBtn;
    private final GridPane scoresTable;
    private final Label tablePageLabel;


    public HighscoresMenuView(List<GameStats> highscores) {
        Label titleLabel = LabelFactory.getLabel("Highscores", Color.LIGHTGREEN, 40, FontWeight.BOLD);

        scoresTable = generateInitialScoresTable(highscores);
        tablePageLabel = LabelFactory.getLabel("", Color.LIGHTGREEN, 18, FontWeight.NORMAL);
        tablePageLabel.setPadding(new Insets(15));
        updateTablePageLabel(1, (int) Math.ceil((double) highscores.size() / 10));

        HBox tableButtons = loadTableButtons();
        backToMenuBtn = ButtonFactory.getButton("Back to main menu", FontWeight.BOLD, 24);

        Pane titleAndTableMargin = new Pane();
        titleAndTableMargin.setMinHeight(Config.WINDOW_HEIGHT * 0.05);

        setBackground(Background.fill(Color.valueOf("#000000")));
        setAlignment(Pos.CENTER);
        getChildren().addAll(titleLabel, titleAndTableMargin, scoresTable, tablePageLabel, tableButtons, backToMenuBtn);
    }

    void updateTablePageLabel(int currPage, int allPages) {
        tablePageLabel.setText(currPage + "/" + allPages);
    }

    private GridPane generateInitialScoresTable(List<GameStats> highscores) {
        if (highscores.isEmpty()) {
            return new GridPane();
        }

        GridPane table = new GridPane();
        table.setAlignment(Pos.CENTER);
        table.setHgap(5);
        table.setVgap(5);

        Label scoreHeader = LabelFactory.getLabel("Score", Color.LIGHTGREEN, 24, FontWeight.NORMAL);
        Label lengthHeader = LabelFactory.getLabel(
                "Snake Length", Color.LIGHTGREEN, 24, FontWeight.NORMAL);
        Label timeHeader = LabelFactory.getLabel("Time", Color.LIGHTGREEN, 24, FontWeight.NORMAL);

        table.add(scoreHeader, 1, 0);
        table.add(lengthHeader, 2, 0);
        table.add(timeHeader, 3, 0);
        table.getChildren().forEach(label ->
            GridPane.setMargin(label, new Insets(5, 20, 5, 20)));

        for (int i = 1; i <= 10; i++) {
            if (i > highscores.size()) {
                break;
            }
            String score = String.valueOf(highscores.get(i - 1).getScore());
            String length = String.valueOf(highscores.get(i - 1).getSnakeLength());
            String time = highscores.get(i - 1).getGameClock();
            Label ordinal = LabelFactory.getLabel(String.valueOf(i), Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label scoreLabel = LabelFactory.getLabel(score, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label lengthLabel = LabelFactory.getLabel(length, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label timeLabel = LabelFactory.getLabel(time, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            table.add(ordinal, 0, i);
            table.add(scoreLabel, 1, i);
            table.add(lengthLabel, 2, i);
            table.add(timeLabel, 3, i);
        }
        table.getChildren().forEach(label -> {
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
        });
        table.setAlignment(Pos.CENTER);
        return table;
    }

    private HBox loadTableButtons() {
        nextPageBtn = ButtonFactory.getButton("Next page", FontWeight.BOLD, 20);
        prevPageBtn = ButtonFactory.getButton("Prev page", FontWeight.BOLD, 20);
        HBox tableButtons = new HBox(prevPageBtn, nextPageBtn);
        tableButtons.setAlignment(Pos.CENTER);
        tableButtons.getChildren().forEach(button -> HBox.setMargin(button, new Insets(10)));
        return tableButtons;
    }

    public Button getNextPageBtn() {
        return nextPageBtn;
    }

    public Button getPrevPageBtn() {
        return prevPageBtn;
    }

    public Button getBackToMenuBtn() {
        return backToMenuBtn;
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
            [Prev page]              [Next page]
                    [Back to main menu]
 */







