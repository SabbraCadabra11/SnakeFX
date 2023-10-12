package dw.sabbracadabra.snakefx.game.model;


import dw.sabbracadabra.snakefx.util.Config;

import java.util.*;

public class Snake implements Moveable {
    private final TileGrid tileGrid;
    private final Deque<Tile> body;
    private final Queue<Trajectory> trajectoryBuffer;
    private Tile latestTail;
    private Trajectory trajectory;

    public enum Trajectory {
        UP, RIGHT, DOWN, LEFT, NONE
    }

    public Snake(int startRow, int startColumn, TileGrid tileGrid) {
        body = new LinkedList<>();
        trajectoryBuffer = new LinkedList<>();
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

    public Tile getHead() {
        return body.peekFirst();
    }

    private void setTrajectory() {
        if (trajectoryBuffer.isEmpty()) {
            return;
        }
        Trajectory restricted = getRestrictedTrajectory();
        while (!trajectoryBuffer.isEmpty()) {
            Trajectory next = trajectoryBuffer.poll();
            if (next != restricted) {
                switch (trajectory) {
                    case UP, DOWN -> {
                        if (next == Trajectory.LEFT || next == Trajectory.RIGHT) {
                            trajectory = next;
                        }
                    }
                    case LEFT, RIGHT -> {
                        if (next == Trajectory.UP || next == Trajectory.DOWN) {
                            trajectory = next;
                        }
                    }
                }
            }
        }
    }

    private Trajectory getRestrictedTrajectory() {
        switch (trajectory) {
            case UP -> {
                return Trajectory.DOWN;
            }
            case DOWN -> {
                return Trajectory.UP;
            }
            case LEFT -> {
                return Trajectory.RIGHT;
            }
            case RIGHT -> {
                return Trajectory.LEFT;
            }
            default -> {
                return Trajectory.NONE;
            }
        }
    }

    public Queue<Trajectory> getTrajectoryBuffer() {
        return trajectoryBuffer;
    }

    public void grow() {
        body.add(latestTail);
    }

    @Override
    public boolean move() {
        setTrajectory();
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










