package sample;

import java.util.Objects;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) throws Exception {
    // Get the FXML
    Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("sample.fxml")));
    // Set the title to "Knockoff Quizlet"
    primaryStage.setTitle("Knockoff Quizlet");
    // Set the width and the height of the app.
    int width = 1280;
    int height = 720;

    Scene scene = new Scene(root, width, height);

    // Add our wonderful dark mode CSS.
    scene.getStylesheets().add("sample/style.css");

    // Set the scene, its max and min width & height to 1280 x 720.
    primaryStage.setScene(scene);
    primaryStage.setMaxHeight(height);
    primaryStage.setMinHeight(height);
    primaryStage.setMaxWidth(width);
    primaryStage.setMinWidth(width);
    primaryStage.show();
  }
}
