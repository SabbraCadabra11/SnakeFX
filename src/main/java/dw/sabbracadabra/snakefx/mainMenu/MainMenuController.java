package dw.sabbracadabra.snakefx.mainMenu;

import dw.sabbracadabra.snakefx.game.GameController;
import dw.sabbracadabra.snakefx.highscoresMenu.HighscoresMenuController;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.DatabaseUtil;
import dw.sabbracadabra.snakefx.util.HighscoresManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.SQLException;


public class MainMenuController {
    private final Stage stage;
    private final MainMenuView view;
    private final HighscoresManager hsManager;

    public MainMenuController(Stage stage) {
        this.stage = stage;
        view = new MainMenuView();
        try {
            Connection connection = DatabaseUtil.getConnection();
            hsManager = new HighscoresManager(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void run() {
        view.setFocusTraversable(true);
        Button newGameButton = view.getNewGameButton();
        Button highscoresButton = view.getHighscoresButton();
        Button exitButton = view.getExitButton();

        newGameButton.setOnAction(event -> {
            GameController controller = new GameController(stage, hsManager);
            controller.run();
        });
        highscoresButton.setOnAction(event -> {
            HighscoresMenuController controller = new HighscoresMenuController(stage, hsManager);
            controller.run();
        });
        exitButton.setOnAction(event -> stage.close());

        view.requestFocus();
        Scene scene = new Scene(view, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setScene(scene);
    }

}
