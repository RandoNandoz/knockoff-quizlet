package sample;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
        primaryStage.setTitle("Hello World");
        int width = 1280;
        int height = 720;
        primaryStage.setScene(new Scene(root, width, height));
        primaryStage.setMaxHeight(height);
        primaryStage.setMinHeight(height);
        primaryStage.setMaxWidth(width);
        primaryStage.setMinWidth(width);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
