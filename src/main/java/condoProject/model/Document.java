package condoProject.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Document extends Mail{

private String importantLevel;

public Document(String status, String roomNumber, String senderInfo, String size,String imageFileName,  String importantLevel,Date date, String receiverName) {
       super(status,roomNumber, senderInfo, size, imageFileName,date, receiverName);
        super.setType("Document");
        this.importantLevel = importantLevel;
    }
    public Document( String roomNumber, String senderInfo, String size,String imageFileName,  String importantLevel, Date date, String receiverName) {
        super(roomNumber, senderInfo, size, imageFileName, date, receiverName);
        super.setType("Document");
        this.importantLevel = importantLevel;


    }
    public String getImportantLevel() {
        return importantLevel;
    }



    @Override
    public String getData(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
       return  getType()+","+getStatus()+","+getRoomNumber()+","+getSenderInfo()+","+
                getSize()+","+getImageFileName()+","+getImportantLevel() + "," +
                formatter.format(getDate())+","+getReceiverName();
    }






}
