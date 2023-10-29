package dw.sabbracadabra.snakefx.highscoresMenu;

import dw.sabbracadabra.snakefx.game.model.GameStats;
import dw.sabbracadabra.snakefx.mainMenu.MainMenuController;
import dw.sabbracadabra.snakefx.util.Config;
import dw.sabbracadabra.snakefx.util.HighscoresManager;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.List;
import java.util.Optional;

public class HighscoresMenuController {
    private final Stage stage;
    private final HighscoresMenuView view;
    private final HighscoresManager hsManager;
    private final Optional<Button> prevPageBtn;
    private final Optional<Button> nextPageBtn;
    private final Button backToMainMenuBtn;
    private List<GameStats> pageStats;
    private int currPageIndex;
    private final int allPages;

    public HighscoresMenuController(Stage stage, HighscoresManager hsManager) {
        this.stage = stage;
        this.hsManager = hsManager;
        int subListLastIndex = Math.min(hsManager.getHighscores().size(), 10);
        pageStats = hsManager.getHighscores().subList(0, subListLastIndex);
        view = new HighscoresMenuView(pageStats);

        prevPageBtn = view.getPrevPageBtn();
        nextPageBtn = view.getNextPageBtn();
        backToMainMenuBtn = view.getBackToMenuBtn();

        currPageIndex = 1;
        allPages = (int) Math.ceil((double) hsManager.getHighscores().size() / 10);
    }

    public void run() {
        prevPageBtn.ifPresent(button -> button.setOnMouseClicked(event -> handlePrevPageButton()));
        nextPageBtn.ifPresent(button -> button.setOnMouseClicked(event -> handleNextPageButton()));
        backToMainMenuBtn.setOnMouseClicked(event -> {
            MainMenuController mainMenu = new MainMenuController(stage);
            mainMenu.run();
        });
        view.updateTablePageLabel(currPageIndex, allPages);
        view.setFocusTraversable(true);
        view.requestFocus();
        Scene scene = new Scene(view, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);
        stage.setScene(scene);
    }

    private void handlePrevPageButton() {
        if (currPageIndex > 1) {
            currPageIndex--;
            int subListFirstIndex = (currPageIndex - 1) * 10;
            int subListLastIndex = 10 + (currPageIndex - 1) * 10;
            pageStats = hsManager.getHighscores().subList(subListFirstIndex, subListLastIndex);
            view.updateScoresTable(pageStats, currPageIndex);
            view.updateTablePageLabel(currPageIndex, allPages);
        }
    }

    private void handleNextPageButton() {
        int pagesCount = (int) Math.ceil((double) hsManager.getHighscores().size() / 10);
        if (currPageIndex < pagesCount) {
            currPageIndex++;
            int subListFirstIndex = (currPageIndex - 1) * 10;
            int subListLastIndex = Math.min((10 + (currPageIndex - 1) * 10), hsManager.getHighscores().size()) ;
            pageStats = hsManager.getHighscores().subList(subListFirstIndex, subListLastIndex);
            view.updateScoresTable(pageStats, currPageIndex);
            view.updateTablePageLabel(currPageIndex, allPages);
        }
    }
}
