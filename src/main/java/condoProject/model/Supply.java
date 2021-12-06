package condoProject.model;

import javafx.scene.image.Image;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Supply extends Mail{

    private String serviceName;
    private String trackingNumber;
    public Supply(String status, String roomNumber, String senderInfo, String size, String imageFileName, String serviceName, String trackingNumber,Date date, String receiverName) {
       super(status,roomNumber,senderInfo, size,imageFileName, date, receiverName);
       super.setType("Supply");
       this.serviceName = serviceName;
       this.trackingNumber = trackingNumber;

    }
    public Supply( String roomNumber, String senderInfo, String size, String imageFileName, String serviceName, String trackingNumber, Date date, String receiverName) {
        super(roomNumber,senderInfo, size,imageFileName, date, receiverName);
        super.setType("Supply");
        this.serviceName = serviceName;
        this.trackingNumber = trackingNumber;

    }



    public String getServiceName() {
        return serviceName;
    }


    public String getTrackingNumber() {
        return trackingNumber;
    }


    @Override
    public String getData(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  getType()+","+getStatus()+","+super.getRoomNumber()+","+getSenderInfo()+","+
                getSize()+","+getImageFileName()+","+getServiceName()+","+getTrackingNumber()
                +","+formatter.format(getDate())+","+getReceiverName();
    }




}
