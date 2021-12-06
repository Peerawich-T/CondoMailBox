package condoProject.controller;

import condoProject.model.Building;
import condoProject.model.Room;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;


public class RoomInfoListController {
    private Building building;
    private ObservableList<Room> roomObservableList;
    private RoomsDataSource roomsDataSource;
    @FXML
    TableView roomListTable;
    @FXML
    ImageView backToStaffBtn;
    @FXML
    Button addARoomBtn;

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
            public void run() {
                setRoomListTable();

            }
        });

    }


    public void setRoomListTable() {

        roomObservableList = FXCollections.observableArrayList();
         for(Room room : building.toList()){
             roomObservableList.add(room);
         }
            Collections.sort(roomObservableList);
            roomListTable.setItems(roomObservableList);
            TableColumn guestName = new TableColumn("Guest's name");
            guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
            guestName.setPrefWidth(130.5);

            TableColumn roomNumber = new TableColumn("Room number");
            roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
            roomNumber.setPrefWidth(130.5);
            TableColumn roomType = new TableColumn("Type");
            roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
            roomType.setPrefWidth(130.5);
            TableColumn building = new TableColumn("Building");
            building.setCellValueFactory(new PropertyValueFactory<>("building"));
            building.setPrefWidth(130.5);
            TableColumn floor = new TableColumn("Floor");
            floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
            floor.setPrefWidth(130.5);

            TableColumn coGuestName = new TableColumn("CoGuest's name");
            coGuestName.setCellValueFactory(new PropertyValueFactory<>("coGuestName"));
            coGuestName.setPrefWidth(130.5);

            roomListTable.getColumns().addAll(guestName, coGuestName, roomNumber, roomType, building, floor);





    }

    @FXML
    public void handleBackToStaffBtnOnAction(Event event) throws IOException {

        ImageView backToStaffBtn = (ImageView) event.getSource();
        Stage stage = (Stage) backToStaffBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staff.fxml"));

        stage.setScene(new Scene(loader.load(), 1024, 768));
        StaffController staffController = loader.getController();
        staffController.setBuilding(building);
        staffController.setRoomsDataSource(roomsDataSource);

        stage.show();

    }

    @FXML
    public void handleAddARoomBtnOnAction(Event event) throws IOException {
        addARoomBtn = (Button) event.getSource();
        Stage stage = (Stage) addARoomBtn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/AddNewRoom.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        AddANewRoomController addANewRoomController = loader.getController();
        addANewRoomController.setBuilding(building);
        addANewRoomController.setRoomDataSource(roomsDataSource);


        stage.show();
    }

}
