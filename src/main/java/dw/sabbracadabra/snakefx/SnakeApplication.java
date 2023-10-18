package dw.sabbracadabra.snakefx;

import dw.sabbracadabra.snakefx.mainMenu.MainMenuController;
import dw.sabbracadabra.snakefx.util.DatabaseUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import java.sql.*;

public class SnakeApplication extends Application {
    @Override
    public void start(Stage stage) throws SQLException {
        Connection connection = DatabaseUtil.getConnection();
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