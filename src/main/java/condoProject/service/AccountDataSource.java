package condoProject.service;

import condoProject.model.Account;
import condoProject.model.AccountList;
import condoProject.model.Staff;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class AccountDataSource {

    private String fileDirectoryName;
    private String fileName;
    private AccountList accountList;

    public AccountDataSource(String fileDirectoryName, String fileName) {
        this.fileDirectoryName = fileDirectoryName;
        this.fileName = fileName;
        checkFileIsExisted();
    }

    private void checkFileIsExisted() {
        File file = new File(fileDirectoryName);
        if (!file.exists()) {
            file.mkdirs();
        }
        String filePath = fileDirectoryName + File.separator + fileName;
        file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Cannot create " + filePath);
            }
        }
    }

    private void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");

            if(data[0].equals("Admin")){
                Account a = new Account(data[1],data[2],data[0]);
                accountList.addNewAccount(a);

            }
            if(data[0].equals("Staff")){

                DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                try {
                    Date pickupDate = formatter.parse(data[5]);
                    Account a = new Staff(data[1], data[2], data[3], data[4],pickupDate,Integer.parseInt(data[6]));

                    accountList.addNewAccount(a);
                } catch (ParseException e) {
                    System.out.println("Can not parse this string!");
                }



            }
        }
        reader.close();
    }


    public AccountList getAccountData() {
        try {
            accountList = new AccountList();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }


        return accountList;
    }


    public void setAccountData(AccountList accountData) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Account account : accountData.toList()) {
                String line="";

                line = account.getData();



                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

}
