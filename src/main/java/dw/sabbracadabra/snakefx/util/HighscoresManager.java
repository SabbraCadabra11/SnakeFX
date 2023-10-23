package dw.sabbracadabra.snakefx.util;

import dw.sabbracadabra.snakefx.game.model.GameStats;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HighscoresManager {
    private List<GameStats> highscores;
    private Connection connection;

    public HighscoresManager(Connection connection) throws SQLException {
        this.connection = connection;
        highscores = loadHighscores(connection);
    }

    private List<GameStats> loadHighscores(Connection connection) throws SQLException {
        List<GameStats> highscoresList = new ArrayList<>();
        String query = "SELECT * FROM highscores ORDER BY score DESC";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                highscoresList.add(new GameStats(rs.getInt("snakeLen"),
                        rs.getInt("timer"),
                        rs.getInt("score")));
            }
        }
        return highscoresList;
    }

    public List<GameStats> getHighscores() {
        return highscores;
    }

    public List<GameStats> getTopHighscores(int count) {
        if (count >= highscores.size()) {
            return highscores;
        }
        return highscores.subList(0, count - 1);
    }

    public GameStats getBestHighscore() {
        return highscores.get(0);
    }

    public void saveHighscore(GameStats stats) {
        highscores.add(stats);
        Collections.sort(highscores);
        String insertQuery = "INSERT INTO highscores (score, timer, snakeLen) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
            statement.setInt(1, stats.getScore());
            statement.setInt(2, stats.getTimer());
            statement.setInt(3, stats.getSnakeLength());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}







