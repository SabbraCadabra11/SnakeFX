package dw.sabbracadabra.snakefx.game;

import dw.sabbracadabra.snakefx.game.model.TileGrid;
import dw.sabbracadabra.snakefx.gameOver.GameOverController;
import dw.sabbracadabra.snakefx.game.model.Food;
import dw.sabbracadabra.snakefx.game.model.GameStats;
import dw.sabbracadabra.snakefx.game.model.Snake;
import dw.sabbracadabra.snakefx.game.view.GameView;
import dw.sabbracadabra.snakefx.game.view.Playfield;
import dw.sabbracadabra.snakefx.game.view.StatsBar;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.HighscoresManager;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class GameController {
    private final Stage stage;
    private final TileGrid tileGrid;
    private final GameStats stats;
    private final GameView gameView;
    private final Playfield playfield;
    private final StatsBar statsBar;
    private final Snake snake;
    private final Food food;
    private final HighscoresManager hsManager;

    public GameController(Stage stage, HighscoresManager hsManager) {
        this.stage = stage;
        stats = new GameStats();
        tileGrid = new TileGrid();
        statsBar = new StatsBar();
        gameView = new GameView(tileGrid, statsBar);
        playfield = gameView.getPlayfield();
        snake = new Snake(tileGrid);
        food = new Food(tileGrid);
        this.hsManager = hsManager;
    }

    public void run() {
        playfield.setFocusTraversable(true);
        playfield.setOnKeyPressed(keyEvent -> {
            switch (keyEvent.getCode()) {
                case W, UP -> snake.getTrajectoryBuffer().add(Snake.Trajectory.UP);
                case D, RIGHT -> snake.getTrajectoryBuffer().add(Snake.Trajectory.RIGHT);
                case S, DOWN -> snake.getTrajectoryBuffer().add(Snake.Trajectory.DOWN);
                case A, LEFT -> snake.getTrajectoryBuffer().add(Snake.Trajectory.LEFT);
            }
        });
        createGameLoop();
        playfield.requestFocus();
        Scene scene = new Scene(gameView, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setTitle("Snake");
        stage.setScene(scene);
    }

    private void createGameLoop() {
        Timeline timeline = new Timeline();
        int timeDeltaInMs = 200;
        KeyFrame keyFrame = new KeyFrame(Duration.millis(timeDeltaInMs), event -> {
            if (!snake.move()) {
                timeline.stop(); // Stop the timeline when the game is over
                handleGameOver();
            }
            snake.getTrajectoryBuffer().clear();
            checkEatingFood();
            playfield.updateView();
            stats.updateAllStats(timeDeltaInMs);
            statsBar.updateAll(stats.getSnakeLength(), stats.getGameClock(), stats.getScore());
        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void handleGameOver() {
        hsManager.saveHighscore(stats);
        hsManager.getHighscores().forEach(System.out::println);
        GameOverController gameOverController = new GameOverController(stage, stats, hsManager);
        gameOverController.run();
    }

    private void checkEatingFood() {
        String snakeHeadCoordinates = snake.getHead().getCoordinates();
        String foodCoordinates = food.getCoordinates();
        if (snakeHeadCoordinates.equals(foodCoordinates)) {
            snake.grow();
            stats.handleAteFood();
            food.spawn();
        }
    }
}













