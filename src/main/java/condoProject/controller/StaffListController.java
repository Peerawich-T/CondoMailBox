package condoProject.controller;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.model.Room;
import condoProject.model.Staff;
import condoProject.service.AccountDataSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

public class StaffListController {

    private AccountList accountList;
    private AccountDataSource accountDataSource;
    private ObservableList<Account> staffObservableList;
    private Staff selectedStaff;
    @FXML
    TableView staffListTable;
    @FXML
    ImageView backToAdminMenu;
    @FXML
    Button  setStatusBtn;
    @FXML
    Label status;

    @FXML
    public void initialize() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                setStaffListTable();
                staffListTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        selectedStaff =  (Staff)newValue;

                    }

                });


            }

        });

    }


    public void setAccountList(AccountList accountList){this.accountList =accountList;}
    public void setAccountDataSource(AccountDataSource accountDataSource){this.accountDataSource = accountDataSource;}


    public void setStaffListTable() {

        staffObservableList = FXCollections.observableArrayList();

        for (Account a : accountList.toList())
            {
                if(a.getType().equals("Staff"))
                staffObservableList.add(a);
            }

            Collections.sort(staffObservableList, Collections.reverseOrder());
            staffListTable.setItems(staffObservableList);
            TableColumn name = new TableColumn("Name");
            name.setCellValueFactory(new PropertyValueFactory<>("name"));
            name.setSortable(false);
            name.setPrefWidth(200);
            TableColumn userName = new TableColumn("Username");
            userName.setCellValueFactory(new PropertyValueFactory<>("userName"));
            userName.setSortable(false);
            userName.setPrefWidth(200);
            TableColumn status = new TableColumn("Status");
            status.setCellValueFactory(new PropertyValueFactory<>("status"));
            status.setSortable(false);
            status.setPrefWidth(100);
            TableColumn date = new TableColumn("Last login date");
            date.setCellValueFactory(new PropertyValueFactory<>("date"));
            date.setPrefWidth(250);
            TableColumn tryToLogin = new TableColumn("Try to login count");

            tryToLogin.setCellValueFactory(new PropertyValueFactory<>("tryToLogin"));
            tryToLogin.setPrefWidth(150);

            staffListTable.getColumns().addAll(date, name, userName, status, tryToLogin);






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
    @FXML public void handleSetStatusBtnOnActon(Event event){
       try {
           if (selectedStaff.getStatus().equals("Activate")) {

               selectedStaff.setDeActivate();
               accountDataSource.setAccountData(accountList);
               staffListTable.refresh();
           } else if (selectedStaff.getStatus().equals("Deactivate")) {
               selectedStaff.setStatus("Activate");
               accountDataSource.setAccountData(accountList);
               staffListTable.refresh();
           }
       }
       catch (NullPointerException exception){
            status.setText("Please select staff !!");
       }






    }

}

