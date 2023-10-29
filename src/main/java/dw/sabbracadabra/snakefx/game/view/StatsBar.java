package dw.sabbracadabra.snakefx.game.view;

import dw.sabbracadabra.snakefx.util.Config;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class StatsBar extends HBox {
    private Label lengthLabel;
    private Label timeLabel;
    private Label scoreLabel;

    public StatsBar() {
        initializeLabels();
        configureLayout();
    }

    private void initializeLabels() {
        Font font = Config.DEFAULT_FONT;
        font = Font.font(font.getFamily(), FontWeight.SEMI_BOLD, 30);
        lengthLabel = new Label("Length: 0");
        timeLabel = new Label("Time: 00:00");
        scoreLabel = new Label("Score: 0");

        lengthLabel.setFont(font);
        timeLabel.setFont(font);
        scoreLabel.setFont(font);

        lengthLabel.setTextFill(Color.LIGHTGREEN);
        timeLabel.setTextFill(Color.LIGHTGREEN);
        scoreLabel.setTextFill(Color.LIGHTGREEN);

        getChildren().addAll(lengthLabel, timeLabel, scoreLabel);
    }

    private void configureLayout() {
        setMinHeight(Config.GAME_STATUS_BAR_HEIGHT);
        setMinWidth(Config.GAME_STATUS_BAR_WIDTH);
        setSpacing(70);
        setPadding(new Insets(10));
        setAlignment(Pos.CENTER);
        setBackground(Background.fill(Color.valueOf("#1a1a1a")));
    }


    public void updateAll(int snakeSize, String timer, int score) {
        lengthLabel.setText("Length: " + snakeSize);
        timeLabel.setText("Time: " + timer);
        scoreLabel.setText("Score: " + score);
    }
}

