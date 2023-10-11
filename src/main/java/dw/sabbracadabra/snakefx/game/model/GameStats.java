package dw.sabbracadabra.snakefx.game.model;

import dw.sabbracadabra.snakefx.util.Config;

public class GameStats {
    private int snakeLength;
    private int score;
    private int timer;
    private long lastAteFoodTime;
    private int bonusScoreMultiplier;
    private final static int BONUS_POINTS_DURATION = 8000; // eight seconds

    public GameStats() {
        snakeLength = Config.SNAKE_STARTING_LENGTH;
        score = 0;
        timer = 0;
        bonusScoreMultiplier = 1;
    }

    public GameStats(int snakeLength, int timer, int score) {
        this.snakeLength = snakeLength;
        this.timer = timer;
        this.score = score;
        bonusScoreMultiplier = 1;
    }

    public int getSnakeLength() {
        return snakeLength;
    }

    public String getGameClock() {
        short seconds = (short) (timer / 1000); //the game won't ever be longer than short type
        byte minutes = (byte) (seconds / 60);
        seconds %= 60;

        return String.format("%02d:%02d", minutes, seconds);
    }

    public int getScore() {
        return score;
    }

    public void handleAteFood() {
        snakeLength++;
        score += 100;
        if (areBonusPointsApplied()) {
            bonusScoreMultiplier++;
        } else {
            bonusScoreMultiplier = 1;
        }
        lastAteFoodTime = System.currentTimeMillis();
    }

    public void updateAllStats(int timeInMs) {
        updateTimer(timeInMs);
        updateScore();
    }

    private void updateTimer(int timeInMs) {
        timer += timeInMs;
    }

    private void updateScore() {
        final long currentTime = System.currentTimeMillis();
        if (areBonusPointsApplied()) {
            final int basePoints = 50;
            final double elapsedTimeFraction = (double)(currentTime - lastAteFoodTime) / BONUS_POINTS_DURATION;
            final double bonusPoints = (basePoints * (1 - elapsedTimeFraction)) * bonusScoreMultiplier;
            score += bonusPoints;
        }
    }

    private boolean areBonusPointsApplied() {
        return System.currentTimeMillis() - lastAteFoodTime <= BONUS_POINTS_DURATION;
    }
}
