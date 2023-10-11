module dw.sabbracadabra.snakefx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens dw.sabbracadabra.snakefx to javafx.fxml;
    exports dw.sabbracadabra.snakefx;
}