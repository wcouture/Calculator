package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    /*
        Loads the SFML file and creates the JavaFX stage.
        @param - default JavaFX stage
     */
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("calcSheet.fxml"));
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(root, 250, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
