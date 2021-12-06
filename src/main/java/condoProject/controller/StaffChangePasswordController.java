package condoProject.controller;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.model.Building;
import condoProject.model.MailBox;
import condoProject.service.AccountDataSource;
import condoProject.service.MailDataSource;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class StaffChangePasswordController {
    private AccountList accountList;
    private MailBox mailBox;
    private Building building;
    private RoomsDataSource roomsDataSource;
    private MailDataSource mailDataSource;
    private AccountDataSource accountDataSource;
    @FXML
    Button changePasswordBtn;
    @FXML
    PasswordField password , newPassword, confirmNewPassword ;
    @FXML
    Label processStatus;

    public void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public void setMailBox(MailBox mailBox) {
        this.mailBox = mailBox;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public void setRoomsDataSource(RoomsDataSource roomsDataSource) {
        this.roomsDataSource = roomsDataSource;
    }

    public void setMailDataSource(MailDataSource mailDataSource) {
        this.mailDataSource = mailDataSource;
    }

    @FXML public void changePasswordBtnOnAction(Event event){

//
      try {
          if (password.getText().equals("") || newPassword.getText().equals("") || confirmNewPassword.getText().equals("")) {
              processStatus.setText("Please fill all data");

          }

          else if((password.getText()).equals((accountList.getCurrentAccount().getPassword())) ) {
              if (newPassword.getText().equals(confirmNewPassword.getText())) {
                  accountList.getCurrentAccount().setPassword(newPassword.getText());
                  processStatus.setText("Change password success");

                  accountDataSource.setAccountData(accountList);
              } else
                  processStatus.setText("Password and confirm isn't match!");


          }
          else{

              processStatus.setText("Invalid password!");
          }
      }
      catch (NullPointerException e){

      }


    }
    @FXML public void handleBackToAdminImageViewOnAction(Event event) throws IOException {
        ImageView backToAdminMenu = (ImageView) event.getSource();
        Stage stage = (Stage) backToAdminMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staff.fxml"));
        stage.setScene(new Scene(loader.load(), 1024, 768));
        StaffController staffController = loader.getController();
        staffController.setAccountList(accountList);
        staffController.setAccountDataSource(accountDataSource);
        staffController.setBuilding(building);
        staffController.setRoomsDataSource(roomsDataSource);
        staffController.setMailDataSource(mailDataSource);
        staffController.setMailBox(mailBox);
        stage.show();;

        stage.show();
    }

}
