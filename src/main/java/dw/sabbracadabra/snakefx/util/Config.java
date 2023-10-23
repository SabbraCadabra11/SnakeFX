package dw.sabbracadabra.snakefx.util;

import javafx.scene.text.Font;

public class Config {
    public static final int PLAYING_PANE_WIDTH = 800;
    public static final int PLAYING_PANE_HEIGHT = 800;
    public static final int GAME_STATUS_BAR_WIDTH = 800;
    public static final int GAME_STATUS_BAR_HEIGHT = 70;
    public static final int WINDOW_HEIGHT = PLAYING_PANE_HEIGHT + GAME_STATUS_BAR_HEIGHT;
    public static final int WINDOW_WIDTH = PLAYING_PANE_WIDTH;
    public static final int TILE_GRID_ROWS = 40;
    public static final int TILE_GRID_COLUMNS = 40;
    public static final double TILE_SIZE = (double) PLAYING_PANE_HEIGHT / TILE_GRID_ROWS;
    public static final int SNAKE_STARTING_LENGTH = 4;
    public static final Font DEFAULT_FONT = Font.font("MS Sans Seriff", 34);
}
