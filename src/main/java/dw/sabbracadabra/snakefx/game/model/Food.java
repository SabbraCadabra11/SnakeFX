package dw.sabbracadabra.snakefx.game.model;

import dw.sabbracadabra.snakefx.util.Config;

import java.util.Random;

public class Food implements Spawnable {
    private final TileGrid tileGrid;
    private Tile foodTile;

    public Food(TileGrid tileGrid) {
        this.tileGrid = tileGrid;
        spawn();
    }

    public Tile getTile() {
        return foodTile;
    }

    public String getCoordinates() {
        return foodTile.getCoordinates();
    }

    @Override
    public void spawn() {
        Random rnd = new Random();
        while (true) {
            int x = rnd.nextInt(Config.TILE_GRID_COLUMNS -1);
            int y = rnd.nextInt(Config.TILE_GRID_ROWS -1);
            Tile randomTile = tileGrid.getTile(x, y);
            if (randomTile.getType() == TileType.EMPTY) {
                foodTile = randomTile;
                foodTile.setType(TileType.FOOD);
                break;
            }
        }
    }
}
