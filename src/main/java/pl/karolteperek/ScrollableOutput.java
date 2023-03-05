package pl.karolteperek;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;
import java.util.List;

public class ScrollableOutput {
    public static void display(String title, List<String[]> list) {

        TableColumn operationNO = new TableColumn("Numer operacji") ; // Numer operacji [0]
        operationNO.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("operationNO")
        );
        TableColumn operation = new TableColumn("Data operacji"); // Data operacji [1]
        operation.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("operation")
        );
        TableColumn currency = new TableColumn("Data waluty"); // Data waluty [2]
        currency.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("currency")
        );
        TableColumn transaction = new TableColumn("Typ transakcji"); // Typ transakcji [3]
        transaction.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("transaction")
        );
        TableColumn amount = new TableColumn("Kwota"); // Kwota [4]
        amount.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,Double>("amount")
        );
        TableColumn description = new TableColumn("Opis"); // Opis [5]
        description.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("description")
        );
        TableColumn address = new TableColumn( "Adres"); // Adres [6]
        address.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("address")
        );
        TableColumn address_second = new TableColumn( "Adres2"); // Adres [7]
        address_second.setCellValueFactory(
                new PropertyValueFactory<ArrayToProperty,String>("address_second")
        );


        javafx.scene.control.TableView tableView = new javafx.scene.control.TableView();
        tableView.getColumns().addAll(operationNO,operation,currency,transaction,amount,description,address,address_second);

        Text sum = new Text(null);
        Text all = new Text("Łącznie:");

        Button button1 = new Button("Usuń");

        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);

        stage.setTitle("Błąd programu");
        stage.setMinWidth(1024);
        stage.setMinHeight(768);
        stage.setResizable(true);
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();

        stage.setMaxWidth(screenBounds.getWidth());
        stage.setMaxHeight(screenBounds.getHeight());

        Button button = new Button("Zamknij");
        button.setOnAction(e-> stage.close());

        VBox vBox = new VBox();
        Text text = new Text();

        Double sum1 = 0.00d;

        ObservableList<ArrayToProperty> data = FXCollections.observableArrayList();

        try {
            for (int i=0; i<list.size();i++) {
                data.addAll(new ArrayToProperty(list.get(i)));
                String[] sum2 = list.get(i);
                sum1 += Double.parseDouble(sum2[4]);
                sum.setText(sum1.toString());

            }
        } catch (Exception e) {
            Alert.display("Błąd wewnętrzny", e.toString());
        }


        button1.setOnAction( e-> {
            ObservableList<ArrayToProperty> selectedRows = tableView.getSelectionModel().getSelectedItems();
            ArrayList<ArrayToProperty> rows = new ArrayList<>(selectedRows);
            Double newsum = Double.parseDouble(sum.getText()) - Double.parseDouble(rows.get(0).getAmount());
            sum.setText(newsum.toString());
            rows.forEach(row -> tableView.getItems().remove(row));
        });


        tableView.setTranslateY(5);
        vBox.getChildren().addAll(tableView,button1, sum, all,button);
        vBox.setAlignment(Pos.CENTER);
        VBox.setVgrow(tableView, Priority.ALWAYS);



        Scene scene = new Scene(vBox);
        stage.setScene(scene);

        sum.setTranslateX(-stage.getMaxHeight()/2.3);
        sum.setTranslateY(tableView.getMaxHeight()+50);
        button1.setTranslateY(tableView.getMaxHeight()+10);
        button.setTranslateY(tableView.getMaxHeight()-10);
        sum.setStyle("-fx-font: 28 arial;");
        all.setTranslateX(sum.getTranslateX());
        all.setTranslateY(sum.getTranslateY()-60);
        all.setStyle("-fx-font: 24 arial;");
        stage.setTitle(title);
        tableView.setItems(data);
        stage.showAndWait();

    }
}

