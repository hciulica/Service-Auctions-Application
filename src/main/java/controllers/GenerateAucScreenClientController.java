package controllers;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import exception.EmptyFieldException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.Client;

import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;


public class GenerateAucScreenClientController implements Initializable {
    @FXML
    public FontAwesomeIconView closeBtn2;
    @FXML
    public ComboBox <String> fieldBtn;
    @FXML
    public VBox pinAuc;
    @FXML
    public JFXButton submitBtn;
    @FXML
    public TextField title;
    @FXML
    public TextArea description;
    @FXML
    public Label message;


    ObservableList<String> activityField= FXCollections.
            observableArrayList ("Agricultura","Constructii", "Transport si Depozitare",
                    "Hoteluri si Restaurante", "Tranzactii Imobiliare", "Invatamant",
                    "Sanatate","Activitati Culturale", "Activitati Tehnice", "Alte");


    @FXML
    //close window
    public void handleCloseButtonAction(MouseEvent event) {
        if(event.getSource()==closeBtn2)
        {
            System.exit(0);
        }
    }

    @FXML
    //display new auction
    public void handleSubmitButtonAction(ActionEvent event)
    {
        /*Node[] nodes = new Node[1];
        for (int i = 0; i < nodes.length; i++) {
            try {

                final int j = i;
                nodes[i] = FXMLLoader.load(getClass().getClassLoader().getResource("AuctionClient.fxml"));
                pinAuc.getChildren().add(nodes[i]);*/

        try {
            if(title.getText().isEmpty()|fieldBtn.getSelectionModel().isEmpty()| description.getText().isEmpty())
            {
                throw new EmptyFieldException();
            }
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AuctionClient.fxml"));
            Pane root = null;
            root = loader.load();
            // Get the controller instance:
            AuctionClientController auction = loader.getController();
            // Set a reference for the parent vbox:
            auction.setParent(pinAuc);
            auction.setFields(title.getText(),(String) fieldBtn.getValue(), description.getText());

            //clear text Fields after submit
            title.clear();
            description.clear();

            pinAuc.getChildren().add(root);



            }
             catch (IOException e)
            {
                e.printStackTrace();
            }

            catch(EmptyFieldException e)
            {
                message.setText(e.getMessage());
            }

    }
    //display all auction that have been registered in the past
    public void display()  {
        try {
            JSONArray array=new JSONArray();
            array=Client.displayAuctions();
            Iterator<JSONObject> iterator = array.iterator();
            while (iterator.hasNext())
            {
                JSONObject obj2 = iterator.next();
                //if the status of the obj is closed it will not be displayed
                if(!obj2.containsValue("closed")) {
                    FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("AuctionClient.fxml"));
                    Pane root = null;
                    root = loader.load();
                    AuctionClientController auction = loader.getController();
                    auction.setParent(pinAuc);
                    //sets the reference of the current obj in json so we can change the status if closed
                    auction.currentAuction=obj2;
                    auction.displayPrice(obj2);
                    auction.displayAuctions((String) obj2.get("Title:"), (String) obj2.get("Activity Field:"), (String) obj2.get("Description:"));

                    pinAuc.getChildren().add(root);
                }
            }


        }
        catch (IOException e)
        {
            e.printStackTrace();
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

        display();


    }
}
