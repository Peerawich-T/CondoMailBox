package condoProject.model;

import java.util.ArrayList;

public class MailBox {
    private ArrayList<Mail> mailArrayList= new ArrayList<>();







    public ArrayList<Mail> toList() {
        return mailArrayList;
    }
    public void addNewMail(Mail mail){
        mailArrayList.add(mail);

    }

}
