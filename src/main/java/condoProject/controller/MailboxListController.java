package condoProject.controller;

import condoProject.model.*;
import condoProject.service.ImageDataSource;
import condoProject.service.MailDataSource;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;


public class MailboxListController {
    private AccountList accountList;
    private MailBox mailBox;
    private Building building;
    private MailDataSource mailDataSource;
    private ImageDataSource imageDataSource;
    private ObservableList<Mail> mailObservableList;
    private Image currentItemImage;
    private Mail selectedMail;
    private RoomsDataSource roomsDataSource;

    @FXML
    Label status;
    @FXML
    TableView mailListTable;
    @FXML
    Button addItemBtn, setReceivedBtn;
    @FXML
    ImageView mailImage, returnImageView;

    public void setAccountList(AccountList accountList) {this.accountList = accountList;
    }
    public void setMailBox(MailBox mailBox){this.mailBox = mailBox;}
    public void setMailDataSource(MailDataSource mailDataSource) {
        this.mailDataSource = mailDataSource;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setRoomsDataSource(RoomsDataSource roomsDataSource) {
        this.roomsDataSource = roomsDataSource;
    }

    @FXML
    public void initialize() {

        Platform.runLater(new Runnable() {
            @Override
            public void run() { imageDataSource = new ImageDataSource();
                mailDataSource = new MailDataSource("data", "MailData.csv");
                mailBox = mailDataSource.getMailData();
                setMailListTable();
                selectedMail = null;
                mailListTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        currentItemImage = new Image(imageDataSource.getImage("classes", ((Mail)newValue).getImageFileName() ) );
                        mailImage.setImage(currentItemImage);
                        selectedMail = (Mail)newValue;
                    }

                });


            }
        });
    }



    @FXML
    public void setMailListTable() {
        mailObservableList = FXCollections.observableArrayList();
        for (Mail i:mailBox.toList()) {
            if(i.getStatus().equals("Waiting owner"))
              mailObservableList.add(i);
        }
        Collections.sort(mailObservableList, Collections.reverseOrder());
        mailListTable.setItems(mailObservableList);
        TableColumn type = new TableColumn("Type");
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        type.setPrefWidth(100);

        TableColumn status = new TableColumn("Status");
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        status.setPrefWidth(100);

        TableColumn receiverName = new TableColumn("Receiver's name.");
        receiverName.setCellValueFactory(new PropertyValueFactory<>("receiverName"));
        receiverName.setPrefWidth(150);

        TableColumn roomNumber = new TableColumn("Room number");
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumber.setPrefWidth(150);


        TableColumn senderInfo = new TableColumn("Sender Info");
        senderInfo.setCellValueFactory(new PropertyValueFactory<>("senderInfo"));
        senderInfo.setPrefWidth(150);

        TableColumn size = new TableColumn("Size");
        size.setCellValueFactory(new PropertyValueFactory<>("size"));
        size.setPrefWidth(100);

        TableColumn importantLevel = new TableColumn("Important level");
        importantLevel.setCellValueFactory(new PropertyValueFactory<>("importantLevel"));
        importantLevel.setPrefWidth(100);

        TableColumn serviceName = new TableColumn("Service's name");
        serviceName.setCellValueFactory(new PropertyValueFactory<>("serviceName"));
        serviceName.setPrefWidth(100);

        TableColumn trackingNumber = new TableColumn("Tracking Number");
        trackingNumber.setCellValueFactory(new PropertyValueFactory<>("trackingNumber"));
        trackingNumber.setPrefWidth(100);

        TableColumn date = new TableColumn("Arrived at");
        date.setCellValueFactory(new PropertyValueFactory<>("date"));
        date.setPrefWidth(250);

        mailListTable.getColumns().addAll(date, type, status, receiverName, roomNumber, senderInfo, size,importantLevel, serviceName, trackingNumber);



    }
    @FXML public void handleAddItemBtnOnAction(Event event) throws IOException {


         addItemBtn = (Button)event.getSource();
        Stage stage = (Stage) addItemBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddItem.fxml"));


            stage.setScene(new Scene(loader.load(), 1024, 768));


        AddNewItemController addNewItemController = loader.getController();
        addNewItemController.setMailBox(mailBox);
        addNewItemController.setMailDataSource(mailDataSource);
        addNewItemController.setBuilding(building);
        addNewItemController.setMailDataSource(mailDataSource);
        stage.show();

    }
    @FXML public void handleSetReceivedMail(Event event){
        try {
            selectedMail.setTaken();
            mailDataSource.setMailDataSource(mailBox);
            mailObservableList.remove(selectedMail);
            mailListTable.refresh();
        }
        catch (NullPointerException exception){
            status.setText("Please select mail item!!");
        }


    }
    @FXML public void handleReturnImageOnAction(Event event)throws IOException{
        returnImageView= (ImageView) event.getSource();
        Stage stage = (Stage) returnImageView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staff.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        StaffController staffController = loader.getController();
        staffController.setAccountList(accountList);
        staffController.setBuilding(building);
        staffController.setRoomsDataSource(roomsDataSource);
        staffController.setMailDataSource(mailDataSource);
        staffController.setMailBox(mailBox);

        stage.show();

    }






}
