package dw.sabbracadabra.snakefx.game.model;

import java.util.Objects;

public class Tile implements Collidable{
    private int row;
    private int column;
    private boolean isOccupied;
    private TileType type;

    public Tile(int row, int column, TileType tileType) {
        this.row = row;
        this.column = column;
        isOccupied = false;
        type = tileType;
    }
    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int y) {
        this.column = y;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public TileType getType() {
        return type;
    }

    public void setType(TileType type) {
        this.type = type;
        setOccupied(this.type != TileType.EMPTY);
    }

    public String getCoordinates() {
        return "%d,%d".formatted(row, column);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tile tile = (Tile) o;
        return row == tile.row && column == tile.column && type == tile.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, column, type);
    }

    @Override
    public boolean isTouched() {
        return false;
    }
}
