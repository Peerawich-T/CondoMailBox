package condoProject.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Mail  implements  Comparable{
    private String roomNumber;
    private String receiverName;
    private String senderInfo;
 private String size;
 private String imageFileName;
 private String status;
 private String type;
 private Date date;

 public Mail(String roomNumber, String senderInfo, String size,  String imageFileName, Date date, String receiverName) {
     this.roomNumber = roomNumber;
     this.senderInfo = senderInfo;
     this.size = size;
     this.imageFileName = imageFileName;
     this.status = "Waiting owner";
     this.type = "Letter";
     this.date = date ;
     this.receiverName = receiverName;
 }
    public Mail(String status,String roomNumber, String senderInfo, String size,  String imageFileName, Date date, String receiverName){
        this.roomNumber= roomNumber;
        this.senderInfo = senderInfo;
        this.size = size;
        this.imageFileName = imageFileName;
        this.status = status;
        this.type = "Letter";
        this.date = date ;
        this.receiverName = receiverName;
    }


    public String getRoomNumber() {
        return roomNumber;
    }


    public String getReceiverName() {
        return receiverName;
    }


    public String getSenderInfo() {
        return senderInfo;
    }


    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getImageFileName() {
        return imageFileName;
    }


    public void setTaken(){
     this.status = "Received";
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }


    public String getData(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return getType()+","+getStatus()+","+getRoomNumber()+","+getSenderInfo()+","+
                getSize()+","+getImageFileName()+","+formatter.format(getDate())+","+getReceiverName();
    }

    @Override
    public String toString() {
        return "Mail{" +
                "receiverInfo='" + roomNumber + '\'' +
                ", senderInfo='" + senderInfo + '\'' +
                ", size='" + size + '\'' +
                ", imageFileName='" + imageFileName + '\'' +
                ", status='" + status + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    @Override
    public int compareTo(Object mail) {
        return this.getDate().compareTo(((Mail)mail).getDate());
    }
}
