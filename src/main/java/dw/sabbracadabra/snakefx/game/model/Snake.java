package dw.sabbracadabra.snakefx.game.model;


import dw.sabbracadabra.snakefx.util.Config;

import java.util.Deque;
import java.util.LinkedList;

public class Snake implements Moveable {
    private final TileGrid tileGrid;
    private final Deque<Tile> body;
    private Tile latestTail;
    private Trajectory trajectory;

    public enum Trajectory {
        UP, RIGHT, DOWN, LEFT, NONE
    }

    public Snake(int startRow, int startColumn, TileGrid tileGrid) {
        body = new LinkedList<>();
        this.tileGrid = tileGrid;
        for (int i = 0; i < Config.SNAKE_STARTING_LENGTH; i++) {
            Tile tile = this.tileGrid.getTile(startRow, startColumn - i);
            tile.setType(TileType.SNAKE);
            body.add(tile);
        }
        Tile tail = body.peekLast();
        latestTail = this.tileGrid.getTile(tail.getRow(), tail.getColumn());
        trajectory = Trajectory.RIGHT;
    }

    public Deque<Tile> getBody() {
        return body;
    }

    public Tile getHead() {
        return body.peekFirst();
    }

    public Trajectory getTrajectory() {
        return trajectory;
    }

    public void setTrajectory(Trajectory trajectory) {
        this.trajectory = trajectory;
    }

    public void grow() {
        body.add(latestTail);
    }

    @Override
    public boolean move() {
        Tile tail = body.peekLast();
        latestTail = tileGrid.getTile(tail.getRow(), tail.getColumn());
        switch (trajectory) {
            case RIGHT -> {
                return goRight();
            }
            case DOWN -> {
                return goDown();
            }
            case LEFT -> {
                return goLeft();
            }
            case UP -> {
                return goUp();
            }
            case NONE -> {
                return false;
            }
        }
        return false;
    }

    @Override
    public boolean goLeft() {
        Tile head = body.peekFirst();
        if (head.getColumn() == 0) {
            return false;
        }
        int nextHeadRow = head.getRow();
        int nextHeadColumn = head.getColumn() - 1;
        trajectory = Trajectory.LEFT;
        return makeTailNewHead(nextHeadRow, nextHeadColumn);
    }

    @Override
    public boolean goRight() {
        Tile head = body.peekFirst();
        if (head.getColumn() == Config.TILE_GRID_COLUMNS - 1) {
            return false;
        }
        int nextHeadRow = head.getRow();
        int nextHeadColumn = head.getColumn() + 1;
        trajectory = Trajectory.RIGHT;
        return makeTailNewHead(nextHeadRow, nextHeadColumn);
    }

    @Override
    public boolean goUp() {
        Tile head = body.peekFirst();
        if (head.getRow() == 0) {
            return false;
        }
        int nextHeadRow = head.getRow() - 1;
        int nextHeadColumn = head.getColumn();
        trajectory = Trajectory.UP;
        return makeTailNewHead(nextHeadRow, nextHeadColumn);
    }

    @Override
    public boolean goDown() {
        Tile head = body.peekFirst();
        if (head.getRow() == Config.TILE_GRID_ROWS - 1) {
            return false;
        }
        int nextHeadRow = head.getRow() + 1;
        int nextHeadColumn = head.getColumn();
        trajectory = Trajectory.DOWN;
        return makeTailNewHead(nextHeadRow, nextHeadColumn);
    }

    private boolean makeTailNewHead(int nextHeadRow, int nextHeadColumn) {
        Tile newHead = tileGrid.getTile(nextHeadRow, nextHeadColumn);
        if (body.contains(newHead)) {
            System.out.println("Collided with itself");
            return false;
        }
        newHead.setType(TileType.SNAKE);
        body.addFirst(newHead);
        Tile oldTail = body.pollLast();
        oldTail.setType(TileType.EMPTY);
        return true;
    }

    public void printBodySegmentsCoordinates() {
        for (Tile tile : body) {
            System.out.println(tile.getCoordinates());
        }
    }
}










