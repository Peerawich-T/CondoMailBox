package condoProject.controller;

import condoProject.model.Building;
import condoProject.model.DoubleRoom;
import condoProject.model.Room;
import condoProject.service.RoomsDataSource;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Collections;

public class AddANewCustomerController {
    private RoomsDataSource roomsDataSource;
    private Building building;
    private ObservableList<Room> emptyRoomObservableList;
    private Room selectedRoom;
    @FXML
    TableView  emptyRoomTable;
    @FXML
    Button addBtn;
    @FXML
    Label  roomNumberLabel, roomTypeLabel,  buildingLabel, floorLabel, guestNameLabel, coGuestNameLabel, status;
    @FXML
    TextField guestNameTextField, coGuestNameTextField;
    @FXML
    ImageView returnImage;

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
                setEmptyRoomTable();
                guestNameTextField.setVisible(false);
                coGuestNameTextField.setVisible(false);
                guestNameLabel.setVisible(false);
                coGuestNameLabel.setVisible(false);
                emptyRoomTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedRoom((Room)newValue);
                        showTextField((Room)newValue);
                        status.setText("");
                    }

                });


            }
        });

    }


    public void setEmptyRoomTable(){
        emptyRoomObservableList = FXCollections.observableArrayList();
        for(Room i :building.toList() ){
            if(i.checkEmptyRoom() ){
                emptyRoomObservableList.add(i);
            }
            

            else if(i.getRoomType().equals("Double")){
                if(i.checkEmptyRoom() ||((DoubleRoom)i).getCoGuestName().equals("empty")){
                    emptyRoomObservableList.add(i);
                }
            }

        }
        Collections.sort(emptyRoomObservableList);
        emptyRoomTable.setItems(emptyRoomObservableList);

        TableColumn guestName = new TableColumn("Guest's name");
        guestName.setCellValueFactory(new PropertyValueFactory<>("guestName"));
        guestName.setPrefWidth(130.5);

        TableColumn roomNumber = new TableColumn("Room number");
        roomNumber.setCellValueFactory(new PropertyValueFactory<>("roomNumber"));
        roomNumber.setPrefWidth(130.5);
        TableColumn roomType = new TableColumn("Type");
        roomType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
        roomType.setPrefWidth(130.5);
        TableColumn  building = new TableColumn("Building");
        building.setCellValueFactory(new PropertyValueFactory<>("building"));
        building.setPrefWidth(130.5);
        TableColumn floor = new TableColumn("Floor");
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        floor.setPrefWidth(130.5);

        TableColumn coGuestName = new TableColumn("CoGuest's name");
        coGuestName.setCellValueFactory(new PropertyValueFactory<>("coGuestName"));
        coGuestName.setPrefWidth(130.5);

        emptyRoomTable.getColumns().addAll(guestName,coGuestName, roomNumber, roomType,building, floor);
    }
   @FXML public void showSelectedRoom(Room room){
        selectedRoom = room;
        roomNumberLabel.setText("Room number : "+room.getRoomNumber());
        roomTypeLabel.setText("Room type : "+room.getRoomType());
        buildingLabel.setText("Building : "+room.getBuilding());
        floorLabel.setText("Floor : "+room.getFloor());



   }
   @FXML public void showTextField(Room room){
       if(room.getRoomType().equals("Double")){
        if(room.getGuestName().equals("empty") && ((DoubleRoom)room).getCoGuestName().equals("empty")){
            guestNameLabel.setVisible(true);
            guestNameTextField.setVisible(true);
            coGuestNameLabel.setVisible(true);
            coGuestNameTextField.setVisible(true);
        }
        else if(((DoubleRoom)room).getCoGuestName().equals("empty")){
               guestNameLabel.setVisible(false);
               guestNameTextField.setVisible(false);
               coGuestNameLabel.setVisible(true);
               coGuestNameTextField.setVisible(true);
           }
       }
       if(room.getRoomType().equals("Single")){
           guestNameLabel.setVisible(true);
           guestNameTextField.setVisible(true);
           coGuestNameLabel.setVisible(false);
           coGuestNameTextField.setVisible(false);

       }
   }

    @FXML public void handelAddBtnOnAction (Event event) {

            if (selectedRoom.getRoomType().equals("Single")) {

                Room currentRoom = building.searchRoom(selectedRoom.getRoomNumber());
                if (guestNameTextField.getText().equals("")) {
                    status.setText("Please Enter Guest's name.");
                } else {
                    status.setText("");
                    currentRoom.setGuestName(guestNameTextField.getText());
                    roomsDataSource.setRoomData(building);
                    status.setText("add Complete");

                    guestNameTextField.setText("");
                    if (currentRoom.checkEmptyRoom().equals(false)) {
                        emptyRoomObservableList.remove(currentRoom);

                    }

                    emptyRoomTable.refresh();
                }

            }

            else if (selectedRoom.getRoomType().equals("Double")) {
                DoubleRoom currentRoom = (DoubleRoom) building.searchRoom(selectedRoom.getRoomNumber());
                if (currentRoom.getGuestName().equals("empty") && currentRoom.getCoGuestName().equals("empty")) {
                    if (guestNameTextField.getText().equals("")) {
                        status.setText("Please enter guest's name");
                    } else {
                        currentRoom.setGuestName(guestNameTextField.getText());
                        if (coGuestNameTextField.getText().equals("")) {
                            currentRoom.setCoGuestName("empty");
                        } else {
                            currentRoom.setCoGuestName(coGuestNameTextField.getText());
                            coGuestNameTextField.setVisible(false);
                            coGuestNameLabel.setVisible(false);
                            emptyRoomObservableList.remove(currentRoom);
                        }
                        roomsDataSource.setRoomData(building);
                        status.setText("add complete");

                        guestNameLabel.setVisible(false);
                        guestNameTextField.setVisible(false);
                        emptyRoomTable.refresh();
                    }
                } else if (currentRoom.getCoGuestName().equals("empty") && !currentRoom.getGuestName().equals("empty")) {
                    if (coGuestNameTextField.getText().equals("")) {
                        status.setText("Please enter CoGuest's name");
                    } else {
                        currentRoom.setCoGuestName(coGuestNameTextField.getText());
                        roomsDataSource.setRoomData(building);
                        emptyRoomObservableList.remove(currentRoom);
                        status.setText("add complete!!");

                        emptyRoomTable.refresh();
                    }

                }

            }
            coGuestNameTextField.setText("");
            guestNameTextField.setText("");
        }


    
    @FXML public void handleReturnImageOnAction(Event event)throws IOException{
        returnImage= (ImageView) event.getSource();
        Stage stage = (Stage) returnImage.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/staff.fxml"));


        stage.setScene(new Scene(loader.load(), 1024, 768));
       StaffController staffController = loader.getController();
        staffController.setBuilding(building);
        staffController.setRoomsDataSource(roomsDataSource);
    }

   }



