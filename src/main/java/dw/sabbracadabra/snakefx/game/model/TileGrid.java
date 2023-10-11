package dw.sabbracadabra.snakefx.game.model;

import dw.sabbracadabra.snakefx.util.Config;

import java.util.Random;

public class TileGrid {
    private final Tile[][] grid;

    public TileGrid() {
        grid = new Tile[Config.TILE_GRID_ROWS][Config.TILE_GRID_COLUMNS];
        for (int row = 0; row < Config.TILE_GRID_ROWS; row++) {
            for (int column = 0; column < Config.TILE_GRID_COLUMNS; column++) {
                grid[row][column] = new Tile(row, column, TileType.EMPTY);
            }
        }
    }

    public Tile getTile(int row, int column) {
        return grid[row][column];
    }

    public Tile getRandomEmptyTile() {
        Random rnd = new Random();
        Tile randomTile;
        do {
            int x = rnd.nextInt(Config.TILE_GRID_COLUMNS - 1);
            int y = rnd.nextInt(Config.TILE_GRID_ROWS - 1);
            randomTile = getTile(x, y);
        } while (randomTile.getType() != TileType.EMPTY);
        return randomTile;
    }

    public void printFieldStatus() {
        for (int row = 0; row < Config.TILE_GRID_ROWS; row++) {
            for (int column = 0; column < Config.TILE_GRID_COLUMNS; column++) {
                System.out.print(grid[row][column].getType().ordinal());
            }
            System.out.println();
        }
        System.out.println();
    }

    public void printFieldStatus(int startRow, int endRow, int startColumn, int endColumn) {
        for (int row = startRow; row <= endRow; row++) {
            for (int column = startColumn; column <= endColumn; column++) {
                System.out.print(grid[row][column].getType().ordinal());
            }
            System.out.println();
        }
        System.out.println();
    }
}
