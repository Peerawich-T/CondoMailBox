package condoProject.model;

import java.util.ArrayList;

public class Account {
    private String userName;
    private String password;

    private String type;



    public Account(String userName, String password, String type){
        this.userName = userName;
        this.password = password;
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }
    public String getPassword(){
        return password;
    }
    public void setPassword(String password) {this.password = password;}
    public String getType(){return  type;
    }
    public boolean checkUserNameAndPassword(String userName, String password){

            if(getUserName().equals(userName) && getPassword().equals(password)){
                return true ;}
            else{
                return  false;
            }

    }

    @Override
    public String toString() {
        return "Account{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
    public String getData(){
        return getType()+","+getUserName()+","+getPassword()+","+"Admin";
    }
}
