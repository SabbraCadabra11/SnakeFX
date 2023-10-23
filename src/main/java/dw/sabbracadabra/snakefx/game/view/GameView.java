package dw.sabbracadabra.snakefx.game.view;

import dw.sabbracadabra.snakefx.game.model.TileGrid;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class GameView extends VBox {
    private final Playfield playfield;

    public GameView(TileGrid tileGrid, StatsBar statsBar) {
        Pane backgroundPane = new Pane();
        Image backgroundImage = new Image("dw/sabbracadabra/snakefx/images/snakeBackground.png");
        ImageView imageView = new ImageView(backgroundImage);
        backgroundPane.getChildren().add(imageView);

        playfield = new Playfield(tileGrid);
        StackPane stackPane = new StackPane(backgroundPane, playfield);
        getChildren().addAll(statsBar, stackPane);
        setBackground(Background.fill(Color.DARKGREY));
    }

    public Playfield getPlayfield() {
        return playfield;
    }

}
