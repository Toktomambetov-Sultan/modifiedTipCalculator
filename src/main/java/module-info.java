module java_fx.test1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens java_fx.test1 to javafx.fxml;
    exports java_fx.test1;
}