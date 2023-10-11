package dw.sabbracadabra.snakefx.game.view;

import dw.sabbracadabra.snakefx.game.model.TileGrid;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView extends VBox {
    private final Playfield playfield;

    public GameView(TileGrid tileGrid, StatsBar statsBar) {
        playfield = new Playfield(tileGrid);
        getChildren().addAll(statsBar, playfield);
        setBackground(Background.fill(Color.DARKGREY));
    }

    public Playfield getPlayfield() {
        return playfield;
    }

}
