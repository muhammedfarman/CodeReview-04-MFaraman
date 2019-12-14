package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main extends Application {
    Stage window;

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("CodeReview-04 _ muhammed-Faraman");

        window = primaryStage;


        ObservableList<Products> items = FXCollections.observableArrayList(
                //create Products
                new Products("Pfeffer", "1 Stück",
                        "Schwarzer Pfeffer verleiht Ihren Speisen eine pikante Schärfe, besonders wenn er länger mitgekocht wird. ",
                        3.49, 2.79, "/Resources/pfeffer__600x600.jpg"),
                new Products("Schafmilchkäse", "200 Gramm Packung",
                        "Hier gibt es keine Beschreibung, weil unsere Handelskette kennst sich nur bedingt damit aus, wie man eine Werbebeschreibung schreibt.",
                        3.59, 1.99, "/Resources/cheese_salakis__600x600.jpg"),
                new Products("Vöslauer", "1.5 Spritziges Vöslauer Mineralwasser", "Spritziges Vöslauer Mineralwasser.",
                        0.75, 0.49, "/Resources/voslauer__600x600.jpg"),
                new Products("Zucker", "500 Gramm Paket",
                        "Natürliches Gelieren wird durch Apfelpektin unterstützt, welches im richtigen Verhältnis mit Zitronensäure und Kristallzucker abgemischt wurde.",
                        1.39, 0.89, "/Resources/zucker__600x600.jpg")
        );


//Images


//Textfield
        TextField prodName = new TextField();
        prodName.setEditable(false);

        TextField qn = new TextField();
        qn.setEditable(false);

        TextField old = new TextField();
        TextField newP = new TextField();

//Labels
        Label name = new Label("Prod. Name: ");
        Label quantity = new Label("Quantity: ");
        Label oldPrice = new Label("Old Price: ");
        Label newPrice = new Label("New Price: ");
        Label desciption = new Label("Description: ");
        Label descText = new Label();
//Buttons
        Button Update = new Button("update");
        Button Report = new Button("Report");
        Button cleara = new Button("clear");
//text area
        TextArea des = new TextArea("");
des.setEditable(false);

//layout
        ImageView img = new ImageView();
        img.setFitHeight(100);
        img.setFitWidth(100);
        ListView<Products> lst = new ListView<>();
        lst.setItems(items);

        GridPane input = new GridPane();
        input.add(name, 0, 0);
        input.add(prodName, 1, 0);
        input.add(quantity, 0, 1);
        input.add(qn, 1, 1);
        input.add(oldPrice, 0, 2);
        input.add(old, 1, 2);
        input.add(newPrice, 0, 3);
        input.add(newP, 1, 3);


        GridPane buttons = new GridPane();
        buttons.add(Update, 0, 0);
        buttons.add(Report, 1, 0);
        VBox box = new VBox(img, input, buttons);

        BorderPane show = new BorderPane();
        show.setCenter(box);
        show.setRight(lst);
        show.setBottom(des);
        Scene scene = new Scene(show, 600, 500);
        window.setScene(scene);
        primaryStage.show();


//Observation
        lst.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            String oP = Double.toString(newValue.getOldprice());
            String nP = Double.toString(newValue.getNewprice());

            prodName.setText(newValue.getPname());
            qn.setText(newValue.getQuantity());
            des.setText(newValue.getDes());
            old.setText(oP);
            newP.setText(nP);
            img.setImage(new Image(this.getClass().getResourceAsStream(newValue.getImage())));
        });
        //Clear
        cleara.setOnAction(actionEvent -> {
            System.out.println("clear clicked");
            prodName.setText("");
            qn.setText("");
            des.setText("");
            oldPrice.setText("");
            newPrice.setText("");
            img.setImage(new Image(this.getClass().getResourceAsStream("")));

        });
        //Update
        Update.setOnAction(actionEvent -> {
            System.out.println("update clicked");
            int selIdx = lst.getSelectionModel().getSelectedIndex();
            Double nP = Double.valueOf(newP.getText());
            Double oP=Double.valueOf(old.getText());
            if (selIdx != -1) {
                String oPr = old.getText();
                String nPr = newP.getText();
                lst.getItems().get(selIdx).setNewprice(nP);
                lst.getItems().get(selIdx).setOldprice(oP);
                lst.refresh();
               updateComplete();
            } else {
                noSelection();
            }

        });
        //Report
        Report.setOnAction(report -> {
            try {
                writeReport(items);
                successReport();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }


    //print report in new file - see source folder
    private void writeReport(ObservableList<Products> items) throws IOException {
        FileWriter fileWriter = new FileWriter("./src/report.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        items.forEach(data -> {
            printWriter.println(data.getPname());
            printWriter.println(data.getQuantity());
            printWriter.println(data.getDes());
            printWriter.println("Old Price " + data.getOldprice() + "€");
            printWriter.println("New Price" + data.getNewprice() + "€");
            printWriter.println("");
        });
        printWriter.close();
    }

    //if report has been successfully printed - show this message

    private void successReport() {
        Alert successReport = new Alert(Alert.AlertType.INFORMATION);
        successReport.setTitle("Success");
        successReport.setHeaderText(null);
        successReport.setContentText("\"Report\" successfully created.");
        successReport.showAndWait();
    }

    //Error select
    private void noSelection() {
        Alert noSelection = new Alert(Alert.AlertType.ERROR);
        noSelection.setTitle("Error");
        noSelection.setHeaderText(null);
        noSelection.setContentText("which price to edit?");
        noSelection.showAndWait();
    }

    //success alert
    private void updateComplete() {
        Alert updateComplete = new Alert(Alert.AlertType.INFORMATION);
        updateComplete.setTitle("Success");
        updateComplete.setHeaderText(null);
        updateComplete.setContentText("Update completed.");
        updateComplete.showAndWait();
    }





    public static void main (String[]args){
            launch(args);
        }
    }