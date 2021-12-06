package condoProject.model;

import java.util.Comparator;

public class Room implements Comparable {
    private String guestName;
    private String roomNumber;
    private String roomType;
    private String building;
    private String floor;


    public Room(String roomType, String roomNumber, String building,String floor) {
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.building = building;
        this.floor = floor;
        guestName = "empty";
    }
    public Room(String roomType, String roomNumber, String building,String floor, String guestName){
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.building = building;
        this.guestName = guestName;
        this.floor = floor;
    }

    public Boolean checkEmptyRoom(){
           if(guestName.equals("empty") ){
               return true;
           }
           else
               return false;
    }

    public Boolean checkGuest(String checkName) {
        if(checkName.equals(guestName)){
            return true;
        }
        return false;
    }
    public String getGuestName() {
        return guestName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return roomType;
    }

    public String getBuilding() {
        return building;
    }
    public String getFloor() {return floor;}


    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }


    @Override
    public int compareTo(Object o) {

        return this.roomNumber.compareTo(((Room)o).getRoomNumber());
    }
}
