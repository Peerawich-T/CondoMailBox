package condoProject.controller;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.service.AccountDataSource;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import javax.swing.text.html.ImageView;
import java.io.IOException;


public class AdminController {

    private AccountList accountList;
    private AccountDataSource accountDataSource;
    @FXML  Button staffListBtn, logOutBtn, changePassword;



    public void setAccountList(AccountList accountList) {
        this.accountList = accountList;
    }

    public void setAccountDataSource(AccountDataSource accountDataSource) {
        this.accountDataSource = accountDataSource;
    }


    @FXML
    public void handleStaffListBtnOnAction(ActionEvent event)throws IOException {

        Button b = (Button) event.getSource();
        Stage stage = (Stage) b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/StaffList.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        StaffListController staffListController = loader.getController();

        staffListController.setAccountList(accountList);
        staffListController.setAccountDataSource(accountDataSource);

        stage.show();

    }
    @FXML
    public void handleAddAStaffBtnOnAction(ActionEvent event)throws IOException{

        Button b  = (Button) event.getSource();
        Stage stage = (Stage)b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AdNewStaff.fxml"));

        stage.setScene(new Scene(loader.load(), 1024,768));
        AddNewStaffController addNewStaffController = loader.getController();
        addNewStaffController.setAccountList(accountList);
        addNewStaffController.setAccountDataSource(accountDataSource);

        stage.show();


    }
    @FXML
    public void handleLogOutBtnOnAction(ActionEvent event)throws IOException{
        Button b  = (Button) event.getSource();
        Stage stage = (Stage)b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));

        stage.setScene(new Scene(loader.load(), 800,600));

        stage.show();

    }
    @FXML
    public void handleChangePasswordBtnOnAction(ActionEvent event)throws IOException{
        Button b = (Button) event.getSource();
        Stage stage = (Stage)b.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ChangePassword.fxml"));
        stage.setScene(new Scene(loader.load(), 1024,768));
        ChangePasswordController changePasswordController = loader.getController();
        changePasswordController.setAccountDataSource(accountDataSource);
        changePasswordController.setAccountList(accountList);
        stage.show();


    }


}
