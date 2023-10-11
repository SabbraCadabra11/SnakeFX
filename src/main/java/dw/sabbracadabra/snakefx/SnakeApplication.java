package dw.sabbracadabra.snakefx;

import dw.sabbracadabra.snakefx.mainMenu.MainMenuController;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;

public class SnakeApplication extends Application {
    @Override
    public void start(Stage stage) {
        Connection connection = null;
        MainMenuController mainMenuController = new MainMenuController(stage);
        mainMenuController.run();
        stage.setResizable(false);
        stage.setTitle("Snake");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}