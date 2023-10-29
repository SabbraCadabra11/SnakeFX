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
import java.util.Optional;

public class HighscoresMenuView extends VBox {
    private Button nextPageBtn;
    private Button prevPageBtn;
    private final Button backToMenuBtn;
    private final GridPane scoresTable;
    private final Label tablePageLabel;


    public HighscoresMenuView(List<GameStats> pageScores) {
        Label titleLabel = LabelFactory.getLabel("Highscores", Color.LIGHTGREEN, 40, FontWeight.BOLD);
        backToMenuBtn = ButtonFactory.getButton("Back to main menu", FontWeight.BOLD, 24);

        if (!pageScores.isEmpty()) {
            scoresTable = generateScoresTable();
            updateScoresTable(pageScores, 1);
            tablePageLabel = LabelFactory.getLabel(
                    "/0", Color.LIGHTGREEN, 18, FontWeight.NORMAL);
            tablePageLabel.setPadding(new Insets(15));
            updateTablePageLabel(1, 1);

            HBox tableButtons = loadTableButtons();
            getChildren().addAll(titleLabel, scoresTable, tablePageLabel, tableButtons, backToMenuBtn);
        } else {
            scoresTable = null;
            tablePageLabel = null;
            Label noScoresLabel = LabelFactory.getLabel("No scores to show.\nPlay at least one game first.",
                    Color.LIGHTGREEN,20, FontWeight.NORMAL);
            getChildren().addAll(titleLabel, noScoresLabel, backToMenuBtn);
        }

        setBackground(Background.fill(Color.BLACK));
        setAlignment(Pos.CENTER);
        double topTitleMargin = Config.WINDOW_HEIGHT * 0.05;
        double bottomTitleMargin = Config.WINDOW_HEIGHT * 0.1;
        double bottomMargin = Config.WINDOW_HEIGHT * 0.1;
        VBox.setMargin(titleLabel, new Insets(topTitleMargin, 20, bottomTitleMargin, 20));
        VBox.setMargin(backToMenuBtn, new Insets(10, 0, bottomMargin, 0));
    }

    private HBox loadTableButtons() {
        nextPageBtn = ButtonFactory.getButton("Next page", FontWeight.BOLD, 20);
        prevPageBtn = ButtonFactory.getButton("Prev page", FontWeight.BOLD, 20);
        HBox tableButtons = new HBox(prevPageBtn, nextPageBtn);
        tableButtons.setAlignment(Pos.CENTER);
        tableButtons.getChildren().forEach(button -> HBox.setMargin(button, new Insets(10)));
        return tableButtons;
    }

    private GridPane generateScoresTable() {
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

        return table;
    }

    void updateTablePageLabel(int currPage, int allPages) {
        tablePageLabel.setText(currPage + "/" + allPages);
    }

    void updateScoresTable(List<GameStats> newPageScores, int pageNumber) {
        if (scoresTable.getRowCount() > 1) {
            scoresTable.getChildren().removeIf(label -> GridPane.getRowIndex(label) > 0);
        }
        for (int i = 0; i < newPageScores.size(); i++) {
            String score = String.valueOf(newPageScores.get(i).getScore());
            String length = String.valueOf(newPageScores.get(i).getSnakeLength());
            String time = newPageScores.get(i).getGameClock();
            int ordinal = (i + 1) + (pageNumber - 1) * 10;
            Label ordinalLabel = LabelFactory.getLabel(String.valueOf(ordinal),
                                Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label scoreLabel = LabelFactory.getLabel(score, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label lengthLabel = LabelFactory.getLabel(length, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            Label timeLabel = LabelFactory.getLabel(time, Color.LIGHTGREEN, 24, FontWeight.LIGHT);
            scoresTable.add(ordinalLabel, 0, i + 1);
            scoresTable.add(scoreLabel, 1, i + 1);
            scoresTable.add(lengthLabel, 2, i + 1);
            scoresTable.add(timeLabel, 3, i + 1);
        }
        scoresTable.getChildren().forEach(label -> {
            GridPane.setHalignment(label, HPos.CENTER);
            GridPane.setValignment(label, VPos.CENTER);
        });
        scoresTable.setAlignment(Pos.CENTER);
    }

    public Optional<Button> getNextPageBtn() {
        return Optional.ofNullable(nextPageBtn);
    }

    public Optional<Button> getPrevPageBtn() {
        return Optional.ofNullable(prevPageBtn);
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







