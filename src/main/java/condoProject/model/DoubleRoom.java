package condoProject.model;

public class DoubleRoom extends Room {
    private String coGuestName;
    public DoubleRoom(String roomNumber, String building,String floor){
        super("Double",roomNumber, building, floor);
        coGuestName= "empty";


    }
    public DoubleRoom(String roomNumber, String building, String floor, String guestName, String coGuestName){
        super("Double",roomNumber,building, floor,guestName);

        this.coGuestName = coGuestName;
    }

    public String getCoGuestName() {
        return coGuestName;
    }

    public void setCoGuestName(String coGuestName) {
        this.coGuestName =coGuestName;
    }
    @Override
    public Boolean checkEmptyRoom(){
        if( (super.getGuestName().equals("empty") ) && (coGuestName.equals("empty")) ){

            return true;
        }
        else
            return false;
    }
    @Override
    public Boolean checkGuest(String name){
        if((coGuestName.equals(name) && !coGuestName.equals("empty"))|| (super.getGuestName().equals(name)))
            return  true;
        else
        return false;
    }

}
