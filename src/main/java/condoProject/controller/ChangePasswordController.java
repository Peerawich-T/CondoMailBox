package condoProject.controller;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.service.AccountDataSource;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class ChangePasswordController {
    private AccountDataSource accountDataSource;
    private AccountList accountList;
    @FXML
    PasswordField password, newPassword, confirmNewPassword;
    @FXML
    Button changePasswordBtn;
    @FXML
    Label processStatus;
    @FXML
    ImageView backToAdminMenu;

    public void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }

    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public AccountDataSource getAccountDataSource() {
        return accountDataSource;
    }

    public AccountList getAccountList() {
        return accountList;
    }

    @FXML public void handleChangePasswordBtn(ActionEvent event){
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

            processStatus.setText("Invalid password");
        }

    }
     @FXML public void handleBackToAdminImageViewOnAction(Event event) throws IOException {
        ImageView backToAdminMenu = (ImageView) event.getSource();
        Stage stage = (Stage) backToAdminMenu.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/admin.fxml"));

        stage.setScene(new Scene(loader.load(), 1024, 768));
        AdminController adminController = loader.getController();
        adminController.setAccountList(accountList);

        stage.show();
    }

}
