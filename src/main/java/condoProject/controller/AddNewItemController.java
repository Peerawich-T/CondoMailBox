package condoProject.controller;

import condoProject.model.*;
import condoProject.service.BrowseFile;
import condoProject.service.ImageDataSource;
import condoProject.service.MailDataSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;


public class AddNewItemController {
    private AccountList accountList;
    private MailBox mailBox;
    private MailDataSource mailDataSource;
    private Building building;
    private String imageFilePath, imageFileName;
    private File imageFile;
    private ImageDataSource imageDataSource;

    @FXML
    Label importantLevelLabel, serviceNameLabel, trackingNumberLabel, status, alertImage;
    @FXML
    Button addBtn, browseImage;
    @FXML
    TextField receiverName, senderInfo, importantLevel, serviceName, trackingNumber;
    @FXML
    ComboBox mailType, size, buildingComboBox, floorComboBox, roomNumberComboBox;
    @FXML
    ImageView mailImageView, returnImageView;


    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    public void setMailDataSource(MailDataSource mailDataSource) {
        this.mailDataSource = mailDataSource;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                imageFile = new File("classes"+File.separator+ "icon-512x512.png");
                imageFileName = "icon-512x512.png";
                mailType.setItems(FXCollections.observableArrayList("Letter", "Document", "Supply"));
                floorComboBox.setItems(FXCollections.observableArrayList("1", "2", "3", "4", "5", "6", "7", "8", "9"));
                buildingComboBox.setItems(FXCollections.observableArrayList("A", "B", "C", "D"));
                roomNumberComboBox.setItems(FXCollections.observableArrayList("01", "02", "03", "04", "05", "06", "07", "08", "09", "10"));
                mailType.setValue("Letter");
                floorComboBox.setValue("1");
                roomNumberComboBox.setValue("01");
                buildingComboBox.setValue("A");
                size.setItems(FXCollections.observableArrayList("C6", "Dl", "C5", "C4", "C3"));
                size.setValue("C6");
                importantLevelLabel.setVisible(false);
                importantLevel.setVisible(false);
                serviceName.setVisible(false);
                serviceNameLabel.setVisible(false);
                trackingNumber.setVisible(false);
                trackingNumberLabel.setVisible(false);


                mailType.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showInputOption((String) mailType.getValue());
                        status.setText("");

                    }

                });


            }
        });

    }


    @FXML
    public void handleAddBtnOnAction(Event event)throws IOException  {
        imageDataSource = new ImageDataSource();




            if (mailType.getValue().equals("Letter")) {
                if (receiverName.getText().equals("") || senderInfo.getText().equals("")   ) {
                status.setText("Please fill all data!!");


                } else {
                    Mail letter =    new Mail((String) (buildingComboBox.getValue()) + (String) (floorComboBox.getValue()) + (String) (roomNumberComboBox.getValue()), senderInfo.getText(), (String) size.getValue(), imageDataSource.copyImage("classes", imageFile, imageFileName), new Date(), receiverName.getText());



                        if (building.searchRoom(letter.getRoomNumber()) == null) {
                            status.setText("Not find this room number.");
                        }
                        else if (!building.searchRoom(letter.getRoomNumber()).checkGuest(receiverName.getText())) {
                            status.setText("Invalid name.");
                        }
                        else if (((building.searchRoom(letter.getRoomNumber())).checkEmptyRoom())) {
                            status.setText("This room is Empty");
                        }
                        else {
                        mailBox.addNewMail(letter);
                        mailDataSource.setMailDataSource(mailBox);
                        addBtn = (Button) event.getSource();
                        Stage stage = (Stage) addBtn.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mailbox.fxml"));


                        stage.setScene(new Scene(loader.load(), 1024, 768));


                        MailboxListController mailboxListController = loader.getController();
                        mailboxListController.setMailBox(mailBox);
                        mailboxListController.setMailDataSource(mailDataSource);
                        mailboxListController.setBuilding(building);
                        stage.show();


                    }

                }
            }
            else if (mailType.getValue().equals("Document")) {


                if (receiverName.getText().equals("") || senderInfo.getText().equals("") || importantLevel.getText().equals("") ) {
                    status.setText("Please fill all data!!");
                } else {
                    Mail document =  new Document((String) (buildingComboBox.getValue()) + (String) (floorComboBox.getValue()) + (String) (roomNumberComboBox.getValue()), senderInfo.getText(), (String) size.getValue(), imageDataSource.copyImage("classes", imageFile, imageFileName), importantLevel.getText(), new Date(), receiverName.getText());



                        if (building.searchRoom(document.getRoomNumber())==null) {
                            status.setText("Not find this room number.");
                        }
                        else if (!building.searchRoom(document.getRoomNumber()).checkGuest(receiverName.getText())) {
                            status.setText("Invalid name.");
                        }
                        else if (((building.searchRoom(document.getRoomNumber())).checkEmptyRoom())) {
                            status.setText("This room is Empty");
                        }
                         else {
                        mailBox.addNewMail(document);
                        mailDataSource.setMailDataSource(mailBox);
                        status.setText("add complete");
                        addBtn = (Button) event.getSource();
                        Stage stage = (Stage) addBtn.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mailbox.fxml"));


                        stage.setScene(new Scene(loader.load(), 1024, 768));


                        MailboxListController mailboxListController = loader.getController();
                        mailboxListController.setMailBox(mailBox);
                        mailboxListController.setMailDataSource(mailDataSource);
                        mailboxListController.setBuilding(building);
                        stage.show();
                    }
                }
            }
            else if (mailType.getValue().equals("Supply")) {

                if (receiverName.getText().equals("") || senderInfo.getText().equals("") ||  trackingNumber.getText().equals("")|| serviceName.getText().equals("") ) {
                    status.setText("Please fill all data!!");
                }
                else{
                    Mail supply  = new Supply((String) (buildingComboBox.getValue()) + (String) (floorComboBox.getValue()) + (String) (roomNumberComboBox.getValue()), senderInfo.getText(), (String) size.getValue(), imageDataSource.copyImage("classes", imageFile, imageFileName), serviceName.getText(), trackingNumber.getText(), new Date(), receiverName.getText());






                    if (building.searchRoom(supply.getRoomNumber()) == null) {
                            status.setText("Not find this room number.");
                        }
                        else if (!building.searchRoom(supply.getRoomNumber()).checkGuest(receiverName.getText())) {
                            status.setText("Invalid name.");
                        }
                        else if(((building.searchRoom(supply.getRoomNumber())).checkEmptyRoom())){
                            status.setText("This room is Empty");
                        }
                     else {
                        mailBox.addNewMail(supply);
                        mailDataSource.setMailDataSource(mailBox);
                        status.setText("add complete");
                        addBtn = (Button)event.getSource();
                        Stage stage = (Stage) addBtn.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mailbox.fxml"));


                        stage.setScene(new Scene(loader.load(), 1024, 768));


                        MailboxListController mailboxListController= loader.getController();
                        mailboxListController.setMailBox(mailBox);
                        mailboxListController.setMailDataSource(mailDataSource);
                        mailboxListController.setBuilding(building);
                        stage.show();
                    }
                }
                }

        }





    @FXML
    public void showInputOption(String mailType) {
        if (mailType.equals("Letter")) {
            size.setItems(FXCollections.observableArrayList("C6", "Dl", "C5", "C4", "C3"));
            size.setValue("C6");
            importantLevelLabel.setVisible(false);
            importantLevel.setVisible(false);
            serviceName.setVisible(false);
            serviceNameLabel.setVisible(false);
            trackingNumberLabel.setVisible(false);
            trackingNumber.setVisible(false);
        }
        if (mailType.equals("Document")) {
            size.setItems(FXCollections.observableArrayList("A3", "A4"));
            size.setValue("A4");
            importantLevelLabel.setVisible(true);
            importantLevel.setVisible(true);
            serviceName.setVisible(false);
            serviceNameLabel.setVisible(false);
            trackingNumberLabel.setVisible(false);
            trackingNumber.setVisible(false);
        }
        if (mailType.equals("Supply")) {
            size.setItems(FXCollections.observableArrayList("A", "B", "C", "D", "E", "F"));
            size.setValue("A");
            importantLevelLabel.setVisible(false);
            importantLevel.setVisible(false);
            serviceName.setVisible(true);
            serviceNameLabel.setVisible(true);
            trackingNumberLabel.setVisible(true);
            trackingNumber.setVisible(true);
        }

    }

    @FXML
    public void handleBrowseImageBtnOnAction(ActionEvent event) {

        BrowseFile browseFile = new BrowseFile();

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        FileChooser fileChooser;

        fileChooser = browseFile.Browse();

        File file = fileChooser.showOpenDialog(stage);

        try {
            imageFile = file;
            Image image = new Image(file.toURI().toString());

            imageFileName = file.getName();


            mailImageView.setImage(image);
            mailImageView.setFitHeight(170);
            mailImageView.setFitWidth(170);
        }
        catch (NullPointerException exception){

        }


    }
    @FXML public void handleReturnImageOnAction(Event event)throws IOException{
        returnImageView= (ImageView) event.getSource();
        Stage stage = (Stage) returnImageView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mailbox.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        MailboxListController mailboxListController = loader.getController();

        mailboxListController.setAccountList(accountList);
        mailboxListController.setMailBox(mailBox);
        mailboxListController.setMailDataSource(mailDataSource);
        mailboxListController.setBuilding(building);

        stage.show();

    }
}
