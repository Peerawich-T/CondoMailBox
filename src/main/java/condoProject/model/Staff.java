package condoProject.model;



import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Staff extends Account implements Comparable{
    private String name;
    private String status ;
    private int  tryToLogin;
    private Date date;


    public Staff(String name,String userName,String password, String status){
       super(userName,password,"Staff");
        this.name =  name;
        this.date = new Date();
        this.status = status;
        tryToLogin = 0;

    }
    public Staff(String name , String userName, String password, String status,Date date, int tryToLogin){
        super(userName, password, "Staff");
        this.name = name;
        this.date = date;
        this.tryToLogin = tryToLogin;
        this.status = status;
    }
    @Override
    public boolean checkUserNameAndPassword(String userName, String password){

        if((this.getUserName().equals(userName)&&(this.getPassword().equals(password)) && (checkStatus()))){
            return true ;}
        else{
            return  false;
        }

    }



    public void addTryToLogin(){
        tryToLogin+=1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public void setDeActivate(){
        status = "Deactivate";
    }
    public boolean checkStatus(){
        if(status.equals("Activate")){
            return true;
        }
        else
            return  false;
    }

    public int getTryToLogin() {
        return tryToLogin;
    }

    @Override
    public int compareTo(Object staff){
        return this.getDate().compareTo(((Staff)staff).getDate());
    }

    @Override
    public String toString() {
        return "Staff{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", date=" + date +
                '}';
    }
    @Override
    public String getData(){
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return  getType()+ ","+getName()+","+getUserName()+"," +getPassword()+","+getStatus()
                +"," +formatter.format(getDate())+","+tryToLogin;
    }
}
