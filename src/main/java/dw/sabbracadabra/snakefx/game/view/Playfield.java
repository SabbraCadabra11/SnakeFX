package dw.sabbracadabra.snakefx.game.view;

import dw.sabbracadabra.snakefx.game.model.Tile;
import dw.sabbracadabra.snakefx.game.model.TileGrid;
import dw.sabbracadabra.snakefx.game.model.TileType;
import dw.sabbracadabra.snakefx.util.Config;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

public class Playfield extends Pane {
    private final TileGrid tileGrid;

    public Playfield(TileGrid tileGrid) {
        this.tileGrid = tileGrid;
        setHeight(Config.PLAYING_PANE_HEIGHT);
        setWidth(Config.PLAYING_PANE_WIDTH);
    }

    public void updateView() {
        //this renders the frames by first clearing the tiles off the screen
        //followed by redrawing them in the updated arrangement
        getChildren().clear();
        double squareSize = Config.TILE_SIZE;
        for (int row = 0; row < Config.TILE_GRID_ROWS; row++) {
            for (int column = 0; column < Config.TILE_GRID_COLUMNS; column++) {
                Rectangle square = new Rectangle(squareSize, squareSize);
                square.setX(column * squareSize);
                square.setY(row * squareSize);
                Tile tile = tileGrid.getTile(row, column);
                square.setFill(getColorForTileType(tile.getType()));
                getChildren().add(square);
            }
        }
    }

    private Paint getColorForTileType(TileType tile) {
        switch (tile) {
            case EMPTY -> {
                return Color.BLACK;
            }
            case SNAKE -> {
                return Color.GREEN;
            }
            case FOOD -> {
                return Color.RED;
            }
            default -> {
                return Color.WHITE;
            }
        }
    }
}
