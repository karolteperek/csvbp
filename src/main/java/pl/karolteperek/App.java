package pl.karolteperek;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class App extends Application {

    private Button button = new Button();
    private Button btnClose = new Button();
    private Button btnSelect = new Button();

    private Text text = new Text();
    private Text buildTxt = new Text();

    private String path = null;

    private FileChooser fileChooser = new FileChooser();
    private FileChooser fileSaver = new FileChooser();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("PKOBP CSV 1.2a");

        buildTxt.setText("1.2a build 09-12-2021");

        button.setText("Generuj Plik");
        btnClose.setText("Zamknij");
        btnSelect.setText("Wybierz");

        button.setDisable(true);

        fileChooser.setTitle("Wybierz plik .csv");
        fileSaver.setTitle("Wybierz miejsce zapisu");

        fileSaver.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Plik tekstowy",
                "*.txt"));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Plik CSV",
                "*.csv"));

        btnClose.setOnAction(e-> primaryStage.close());

        btnSelect.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                path = fileChooser.showOpenDialog(primaryStage).getPath();
                if (path.toLowerCase().contains(".csv")) {
                    button.setDisable(false);
                    text.setText(path);
                } else {
                    Alert.display("Bład wewnętrzny programu", "Problem kod: 01");
                }
            }
        });

        button.setOnAction(e-> new LoadData(path, fileSaver.showSaveDialog(primaryStage).getPath()));
        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(btnClose,button,btnSelect,text,buildTxt);

        Scene scene = new Scene(stackPane, 300 , 200);

        btnClose.setTranslateY(60);
        btnSelect.setTranslateY(5);
        button.setTranslateY(-50);
        buildTxt.setTranslateY(90);
        buildTxt.setTranslateX(90);
        text.setTranslateY(-25);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

    }
}

