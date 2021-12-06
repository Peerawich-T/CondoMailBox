package condoProject.service;

import condoProject.model.*;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class MailDataSource {
    private String fileName;
    private String fileDirectoryName;
    private MailBox mailBox;

    public MailDataSource(String fileDirectoryName, String fileName){
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    public void checkFileIsExisted(){
        File file = new File(fileDirectoryName);
        if (!file.exists()){
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if(!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    public MailBox getMailData(){
        try{
            mailBox = new MailBox();
            readData();
        }catch (FileNotFoundException e){
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }


        return mailBox;
    }

    private void readData() throws IOException{
        String filePath  = fileDirectoryName + File.separator +fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String line = "";
        while ((line = reader.readLine()) != null){
            String[] data = line.split(",");
            if(data[0].equals("Letter")){
                Mail letter = null;
                try {
                    letter = new Mail(data[1], data[2], data[3], data[4], data[5], formatter.parse(data[6]), data[7]);
                } catch (ParseException e) {
                   System.err.print("Can't parse this String data!");
                }

                mailBox.addNewMail(letter);
            }
            if(data[0].equals("Document")){
                Mail document = null;
                try {
                    document = new Document(data[1], data[2], data[3], data[4], data[5], data[6], formatter.parse(data[7]), data[8]);
                } catch (ParseException e) {
                    System.err.print("Can't parse this String data!");
                }

                mailBox.addNewMail(document);
            }
            if(data[0].equals(("Supply"))){
                Mail supply = null;
                try {
                    supply = new Supply(data[1], data[2], data[3], data[4], data[5], data[6], data[7], formatter.parse(data[8]), data[9]);
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                mailBox.addNewMail(supply);
            }

        }
    }

    public void setMailDataSource(MailBox mailBox){
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Mail mail : mailBox.toList()) {
                String line="";

                line = mail.getData();




                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

}
