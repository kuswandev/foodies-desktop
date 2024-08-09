package com.me.foodies;

import com.me.foodies.utils.Navigator;
import com.me.foodies.views.screens.LoginScreen;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage stage) {
        stage.setTitle("Foodies");
        stage.setWidth(1000);
        stage.setHeight(800);
        stage.show();

        Navigator.init(stage);
        Navigator.navigate(new LoginScreen());
    }

    public static void main(String[] args) {
        launch();
    }
}
