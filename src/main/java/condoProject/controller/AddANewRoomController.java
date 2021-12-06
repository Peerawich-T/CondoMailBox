package condoProject.controller;

import condoProject.model.Building;
import condoProject.model.DoubleRoom;
import condoProject.model.Room;
import condoProject.service.AccountDataSource;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


import java.io.IOException;


public class AddANewRoomController {
    private RoomsDataSource roomsDataSource;
    private Building building;
    @FXML
    ImageView returnImage;
    @FXML
    Button addBtn;

    @FXML
    ComboBox typeComboBox, floorComboBox, roomNumberComboBox ,buildingComboBox;
    @FXML
    Label status;
    @FXML
    public void initialize() {



        typeComboBox.setItems(FXCollections.observableArrayList("Single", "Double"));
        typeComboBox.setValue("Single");

       floorComboBox.setItems(FXCollections.observableArrayList("1","2", "3", "4","5", "6", "7", "8", "9"));
        floorComboBox.setValue("1");
       roomNumberComboBox.setItems(FXCollections.observableArrayList("01","02", "03", "04","05", "06", "07", "08", "09", "10"));
       roomNumberComboBox.setValue("01");
       buildingComboBox.setItems(FXCollections.observableArrayList("A", "B", "C", "D"));
       buildingComboBox.setValue("A");

    }

 public void setBuilding(Building building){
     this.building = building;
 }
 public void setRoomDataSource(RoomsDataSource roomDataSource){
     this.roomsDataSource = roomDataSource;
 }

 @FXML public void handleAddBtnOnAction(Event event)throws IOException {

            if (typeComboBox.getValue().equals("Single")) {
                if (buildingComboBox.getValue().equals(null) || floorComboBox.getValue().equals(null) || roomNumberComboBox.getValue().equals(null)) {
                    status.setText("Please enter all of data!!!");
                } else {
                    Room room = new Room("Single", (String) buildingComboBox.getValue() + (String) floorComboBox.getValue() + (String) roomNumberComboBox.getValue(), (String) buildingComboBox.getValue(), (String) floorComboBox.getValue());
                    if (building.addANewRoom(room)) {
                        status.setText("Add Complete");
                        roomsDataSource.setRoomData(building);
                        addBtn = (Button) event.getSource();
                        Stage stage = (Stage) addBtn.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomInformationList.fxml"));


                        stage.setScene(new Scene(loader.load(), 1024, 768));
                        RoomInfoListController roomInfoListController = loader.getController();
                        roomInfoListController.setBuilding(building);
                        roomInfoListController.setRoomsDataSource(roomsDataSource);
                    } else
                        status.setText("This Building already has this room's number.");
                }
            } else if (typeComboBox.getValue().equals("Double")) {
                if (buildingComboBox.getValue().equals(null)|| floorComboBox.getValue() == null || roomNumberComboBox.getValue() == null) {
                    status.setText("Please enter all of data!!!");
                } else {
                    Room room = new DoubleRoom((String) buildingComboBox.getValue()+ (String) floorComboBox.getValue() + (String) roomNumberComboBox.getValue(), (String) buildingComboBox.getValue(), (String) floorComboBox.getValue());
                    if (building.addANewRoom(room)) {
                        status.setText("Add Complete");
                        roomsDataSource.setRoomData(building);
                        addBtn = (Button) event.getSource();
                        Stage stage = (Stage) addBtn.getScene().getWindow();
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomInformationList.fxml"));


                        stage.setScene(new Scene(loader.load(), 1024, 768));
                        RoomInfoListController roomInfoListController = loader.getController();
                        roomInfoListController.setBuilding(building);
                        roomInfoListController.setRoomsDataSource(roomsDataSource);
                    } else
                        status.setText("This Building already has this room's number.");
                }
            }
        }






    


    @FXML public void handleReturnImageOnAction(Event event)throws IOException{
        returnImage= (ImageView) event.getSource();
        Stage stage = (Stage) returnImage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/RoomInformationList.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
        RoomInfoListController roomInfoListController = loader.getController();
        roomInfoListController.setBuilding(building);
        roomInfoListController.setRoomsDataSource(roomsDataSource);
    }

}
