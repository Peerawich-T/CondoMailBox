package condoProject.model;

import java.util.ArrayList;

public class AccountList {
    private ArrayList<Account> accountList ;
    private Account currentAccount;

    public AccountList(){
        accountList = new ArrayList<Account>();
    }

    public ArrayList<Account> toList(){
        return accountList;
    }
    public void addNewAccount(Account account){
        accountList.add(account);
    }
    public boolean checkLogin(String userName, String password){
        for(Account i : accountList){

            if(i.checkUserNameAndPassword(userName,password)){
                currentAccount =  i;

                return true ;
            }

        }
        return false;
    }
    public Account getCurrentAccount(){
        return currentAccount;
    }
    public boolean addStaff(String userName, String name, String password, String status){

        boolean checkDuplicate = false ;
        for(Account i : accountList){

            if(userName.equals(i.getUserName())){
                checkDuplicate = true;
            }
        }
        if(!checkDuplicate){


            return  true;
        }
        return  false;

    }
    public Account searchAccount(String userName){
        for (Account i:accountList) {
            if(userName.equals(i.getUserName())){
                currentAccount= i;
                return i;}
        }
        return null;
    }


}
