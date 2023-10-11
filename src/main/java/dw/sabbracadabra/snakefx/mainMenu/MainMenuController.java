package dw.sabbracadabra.snakefx.mainMenu;

import dw.sabbracadabra.snakefx.game.GameController;
import dw.sabbracadabra.snakefx.util.Config;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;


public class MainMenuController {
    private final Stage stage;
    private final MainMenuView view;

    public MainMenuController(Stage stage) {
        this.stage = stage;
        view = new MainMenuView();
    }

    public void run() {
        view.setFocusTraversable(true);
        Button newGameButton = view.getNewGameButton();
        Button highscoresButton = view.getHighscoresButton();
        Button exitButton = view.getExitButton();

        newGameButton.setOnAction(event -> {
            GameController controller = new GameController(stage);
            controller.run();
        });
        highscoresButton.setOnAction(event -> handleHighscoresButton());
        exitButton.setOnAction(event -> stage.close());

        view.requestFocus();
        Scene scene = new Scene(view, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setScene(scene);
    }

    private void handleHighscoresButton() {
        //TODO
    }
}
