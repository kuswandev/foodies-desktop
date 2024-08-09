package com.me.foodies.utils;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class Navigator {
    private static Stage stage;
    private static final Stack<Scene> history = new Stack<>();

    public static void init(Stage primaryStage) {
        stage = primaryStage;
    }

    public static void navigate(Parent screen) {
        history.push(stage.getScene());
        stage.setScene(new Scene(screen));
    }

    public static void replace(Parent screen) {
        stage.setScene(new Scene(screen));
    }

    public static void back() {
        if (!history.isEmpty()) {
            stage.setScene(history.pop());
        }
    }
}
