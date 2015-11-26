package gui;/**
 * Created by timwissel on 26-11-15.
 */

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class gui extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("derp");
        MyButton myButton = new MyButton();

        StackPane stackPane = new StackPane();
        stackPane.getChildren().add(myButton);

        Scene scene = new Scene(stackPane, 500, 500);

        primaryStage.setScene(scene);

        primaryStage.show();
    }
}
