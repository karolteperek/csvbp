package pl.karolteperek;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.control.Button;


public class Alert {

    public static void display(String title, String message) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Błąd programu");
        stage.setMinWidth(300);
        stage.setResizable(false);

        Label label = new Label();
        label.setText(message);

        Button button = new Button("Zamknij");
        button.setOnAction(e-> stage.close());


        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, button);
        vBox.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        stage.setTitle(title);

        stage.showAndWait();

    }
}
