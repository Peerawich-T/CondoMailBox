package condoProject.controller;


import condoProject.model.*;
import condoProject.service.AccountDataSource;
import condoProject.service.MailDataSource;
import condoProject.service.RoomsDataSource;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;


import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class LoginController  {

    private AccountList accountList;
    private AccountDataSource accountDataSource;
    private RoomsDataSource roomsDataSource;
    private MailDataSource mailDataSource;
    private MailBox mailBox;
    private Building building;

    @FXML
    TextField userName;
    @FXML
    PasswordField passwordField;
    @FXML
    Button ok;
    @FXML
    ComboBox comboBox;
    @FXML
    Label status;
    @FXML
    ImageView infoImageView;
    @FXML
    public void initialize() {

        comboBox.setItems(FXCollections.observableArrayList("Admin","Staff"));

        comboBox.setValue("Admin");



        accountDataSource = new AccountDataSource("data", "AccountData.csv");
        accountList = accountDataSource.getAccountData();








    }
    @FXML
    public void handleOkOnAction(ActionEvent event) throws IOException {



        if(accountList.checkLogin(userName.getText(), passwordField.getText())) {

            if (comboBox.getValue().equals("Admin") && accountList.getCurrentAccount().getType().equals("Admin")) {


                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));


                stage.setScene(new Scene(loader.load(), 1024, 768));
                AdminController adminController = loader.getController();
                adminController.setAccountList(accountList);
                adminController.setAccountDataSource(accountDataSource);

                stage.show();


            } else {
                status.setText("Invalid username or password ");
            }
            if (comboBox.getValue().equals("Staff") && accountList.getCurrentAccount().getType().equals("Staff")) {
                ((Staff) (accountList.getCurrentAccount())).setDate(new Date());

                Button b = (Button) event.getSource();
                Stage stage = (Stage) b.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/staff.fxml"));


                stage.setScene(new Scene(loader.load(), 1024, 768));
                StaffController staffController = loader.getController();
                staffController.setAccountList(accountList);

                staffController.setBuilding(building);
                staffController.setRoomsDataSource(roomsDataSource);

                staffController.setAccountDataSource(accountDataSource);
                accountDataSource.setAccountData(accountList);
                stage.show();


            }
        }
            else {
                status.setText("Invalid username or password ");
                try {
                    if (accountList.searchAccount(userName.getText()).getType().equals("Staff")) {
                        Staff staff = (Staff) accountList.searchAccount(userName.getText());
                        if (!staff.checkStatus()) {
                            status.setText("This account is deactivated please contact admin");
                            staff.addTryToLogin();
                            accountDataSource.setAccountData(accountList);

                        }
                    }
                } catch (NullPointerException exception) {
                    status.setText("Invalid username or password ");
                }
            }
        }
        @FXML public void  handleInfoImageViewOnAction(Event event)throws IOException{
            infoImageView = (ImageView) event.getSource();
            Stage stage = (Stage) infoImageView.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/devInfo.fxml"));


            stage.setScene(new Scene(loader.load(), 800, 600));
            InfoController infoController = loader.getController();



            stage.show();
        }




    }


