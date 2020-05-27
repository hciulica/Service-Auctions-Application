package controllers;
import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class GenerateAucScreenClientController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    public ComboBox <String> fieldBtn;
    @FXML
    public VBox pinAuc=null;
    @FXML
    public JFXButton submitBtn;

    ObservableList<String> activityField= FXCollections.
            observableArrayList ("Agricultura","Constructii", "Transport si Depozitare",
                    "Hoteluri si Restaurante", "Tranzactii Imobiliare", "Invatamant",
                    "Sanatate","Activitati Culturale", "Activitati Tehnice", "Alte");


    @FXML
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }
    }

    @FXML
    public void handleSubmitButtonAction(ActionEvent event)
    {
        Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getClassLoader().getResource("AuctionClient.fxml"));
                pinAuc.getChildren().add(nodes[i]);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fieldBtn.setItems(activityField);

        //Change prompt Text Color
        fieldBtn.setEditable(false);
        fieldBtn.setButtonCell(new ListCell<String>()
        {

            @Override
            protected void updateItem(String item, boolean empty)
            {
                super.updateItem(item, empty);
                if(empty || item==null){
                    // styled like -fx-prompt-text-fill:
                    setStyle("-fx-text-fill: derive(-fx-control-inner-background,-30%)");
                } else {
                    setStyle("-fx-text-fill: -fx-text-inner-color");
                    setText(item.toString());
                }
            }

        });






    }
}
