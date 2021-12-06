package condoProject.model;

import java.util.ArrayList;

public class Building {
    private ArrayList<Room> roomArrayList;

    public Building(){
        roomArrayList = new ArrayList<Room>();
    }



    public ArrayList<Room> getRoomList(){
        return roomArrayList;
    }
    public boolean addANewRoom(Room room){
        if(!checkDuplicate(room)){
          roomArrayList.add(room);
        return true;}
        else
            return false;

    }

    public ArrayList<Room> toList() {
        return  roomArrayList;
    }

    public Room searchRoom(String roomNumber) {
        for (Room i: roomArrayList) {
            if(i.getRoomNumber().equals(roomNumber)){
                return  i;
            }
        }
        return null;
    }
    public boolean checkDuplicate(Room room){
        for(Room i  : roomArrayList){
            if((i.getRoomNumber().equals(room.getRoomNumber())) && i.getBuilding().equals(room.getBuilding())){
                return  true;
            }
        }
        return false;
    }


}
