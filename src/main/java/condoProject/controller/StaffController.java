package condoProject.controller;

import condoProject.model.*;
import condoProject.service.AccountDataSource;
import condoProject.service.MailDataSource;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffController {
    private AccountList accountList;
    private AccountDataSource accountDataSource;
    private MailBox mailBox;
    private Building building;
    private RoomsDataSource roomsDataSource;
    private MailDataSource mailDataSource;
    @FXML
    ImageView mailboxImage;
    @FXML
    Button roomInfoListBtn, addANewCustomer, changePassword;
    @FXML
    ImageView logOutImage;

    public void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setRoomsDataSource(RoomsDataSource roomsDataSource) {
        this.roomsDataSource = roomsDataSource;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }
    public void setMailDataSource(MailDataSource mailDataSource) {
        this.mailDataSource = mailDataSource;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox ;
    }
    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                roomsDataSource = new RoomsDataSource("data","RoomsData.csv");
                building = roomsDataSource.getRoomData();

            }
        });

    }
    @FXML
    public void handleRoomInfoListBtnOnAction(Event event) throws IOException {
        roomInfoListBtn = (Button) event.getSource();
        Stage stage = (Stage) roomInfoListBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomInformationList.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        RoomInfoListController roomInfoListController = loader.getController();
        roomInfoListController.setBuilding(building);
        roomInfoListController.setRoomsDataSource(roomsDataSource);

        stage.show();
    }


    @FXML
    public void handleMailboxImageOnAction(Event event) throws IOException {
        ImageView mailboxImage = (ImageView) event.getSource();
        Stage stage = (Stage) mailboxImage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Mailbox.fxml"));

        stage.setScene(new Scene(loader.load(), 1024, 768));
        MailboxListController mailboxListController = loader.getController();

        mailboxListController.setAccountList(accountList);

        mailboxListController.setBuilding(building);
        mailboxListController.setRoomsDataSource(roomsDataSource);
        stage.show();
    }

    @FXML
    public void handleLogOutImageOnAction(Event event) throws IOException {
        logOutImage = (ImageView) event.getSource();
        Stage stage = (Stage) logOutImage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));


        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController loginController = loader.getController();


        stage.show();
    }
    @FXML public void handleAddANewCustomerButtonOnAction(Event event)throws IOException{
        addANewCustomer = (Button) event.getSource();
        Stage stage = (Stage) addANewCustomer.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewCustomer.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        AddANewCustomerController addANewCustomerController = loader.getController();
        addANewCustomerController.setBuilding(building);
        addANewCustomerController.setRoomsDataSource(roomsDataSource);

        stage.show();
    }
    @FXML public void handleChangePasswordOnAction(Event event)throws  IOException{
        changePassword = (Button) event.getSource();
        Stage stage = (Stage) changePassword.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffChangePassword.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        StaffChangePasswordController staffChangePasswordController = loader.getController();
        staffChangePasswordController.setAccountList(accountList);
        staffChangePasswordController.setBuilding(building);
        staffChangePasswordController.setMailBox(mailBox);
        staffChangePasswordController.setRoomsDataSource(roomsDataSource);
        staffChangePasswordController.setMailDataSource(mailDataSource);
        staffChangePasswordController.setAccountDataSource(accountDataSource);
    }



}


