package condoProject.controller;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.model.Staff;
import condoProject.service.AccountDataSource;
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
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AddNewStaffController {

    private AccountList accountList;
    private AccountDataSource accountDataSource;


    @FXML
    Label userNameStatus, passwordStatus, confirmPasswordStatus, nameStatus, alertImage;
    @FXML
    Button addBtn;
    @FXML
    TextField userName, name;
    @FXML
    PasswordField password, confirmPassword;


    public void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;

    }

@FXML
    public void handleAddBtnOnAction(Event event) {

        if (userName.getText().equals("") || password.getText().equals("") || confirmPassword.getText().equals("") || name.getText().equals("")  ) {


            if (userName.getText().equals("")) {
                userNameStatus.setText("*");

            }
            else
                userNameStatus.setText("");
            if (password.getText().equals("")) {
                passwordStatus.setText("*");
            }
            else
                passwordStatus.setText("");
            if (confirmPassword.getText().equals("")) {
                confirmPasswordStatus.setText("*");

            }
            else
                confirmPasswordStatus.setText("");
            if (name.getText().equals("")) {
                nameStatus.setText("*");

            }

            else
                nameStatus.setText("");
        }

        else {

            if (password.getText().equals(confirmPassword.getText())) {
                confirmPasswordStatus.setText("");
                if (accountList.addStaff(userName.getText(), name.getText(), password.getText(), "Activate")) {
                    accountList.addNewAccount(new Staff(name.getText(), userName.getText(), password.getText(), "Activate"));

                    accountDataSource.setAccountData(accountList);
                    confirmPasswordStatus.setText("add complete!");
                } else
                    confirmPasswordStatus.setText("username is already used!");
            }
            else {
                confirmPasswordStatus.setText("isn't match");
                passwordStatus.setText("");

            }
        }
    }




@FXML
    public void handleBackToAdminImageViewOnAction(Event event) throws IOException {
        ImageView backToAdminMenu = (ImageView) event.getSource();
        Stage stage = (Stage) backToAdminMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));

        stage.setScene(new Scene(loader.load(), 1024, 768));
        AdminController adminController = loader.getController();
        adminController.setAccountList(accountList);
        adminController.setAccountDataSource(accountDataSource);

        stage.show();
    }



}
