module com.me.foodies {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.me.foodies to javafx.fxml;
    exports com.me.foodies;
}