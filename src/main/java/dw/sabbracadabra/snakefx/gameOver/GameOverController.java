package dw.sabbracadabra.snakefx.gameOver;

import dw.sabbracadabra.snakefx.game.GameController;
import dw.sabbracadabra.snakefx.game.model.GameStats;
import dw.sabbracadabra.snakefx.mainMenu.MainMenuController;
import dw.sabbracadabra.snakefx.util.Config;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GameOverController {
    private final Stage stage;
    private final GameOverView view;

    public GameOverController(Stage stage, GameStats currGameStats) {
        this.stage = stage;
        GameStats bestGameStats = loadBestGameStats();
        view = new GameOverView(currGameStats, bestGameStats);
        view.setFocusTraversable(true);

        Button mainMenuButton = view.getMainMenuButton();
        Button newGameButton = view.getNewGameButton();
        mainMenuButton.setOnAction(event -> {
            MainMenuController mainMenu = new MainMenuController(stage);
            mainMenu.run();
        });
        newGameButton.setOnAction(event -> {
            GameController game = new GameController(stage);
            game.run();
        });
    }

    public void run() {
        Scene scene = new Scene(view, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setScene(scene);
        stage.setTitle("Snake - Game Over");
    }

    private GameStats loadBestGameStats() {
        return new GameStats(0, 0, 0);
    }
}
