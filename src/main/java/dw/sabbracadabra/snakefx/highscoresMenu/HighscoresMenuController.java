package dw.sabbracadabra.snakefx.highscoresMenu;

import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.HighscoresManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HighscoresMenuController {
    private final Stage stage;
    private final HighscoresMenuView view;
    private final HighscoresManager hsManager;

    public HighscoresMenuController(Stage stage, HighscoresManager hsManager) {
        this.stage = stage;
        this.hsManager = hsManager;
        view = new HighscoresMenuView(hsManager.getHighscores());
    }

    public void run() {
        view.setFocusTraversable(true);
        view.requestFocus();
        Scene scene = new Scene(view, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setScene(scene);
    }
}
