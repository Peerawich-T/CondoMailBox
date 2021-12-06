package condoProject.service;

import condoProject.model.*;

import java.io.*;

public class RoomsDataSource {
    private String fileDirectoryName;
    private String fileName;
    private Building building;

    public RoomsDataSource(String fileDirectoryName, String fileName){
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

    public void readData() throws IOException {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileReader fileReader = new FileReader(file);
        BufferedReader reader = new BufferedReader(fileReader);
        String line = "";
        while ((line = reader.readLine()) != null) {
            String data[] = line.split(",");

            if(data[0].equals("Single")){

                Room a = new Room(data[0], data[1], data[2], data[3], data[4]);
                building.addANewRoom(a);
            }
            if(data[0].equals("Double")){

                Room a = new DoubleRoom(data[1], data[2], data[3], data[4], data[5]);
                building.addANewRoom(a);
            }
        }

        reader.close();
     }


    public Building getRoomData() {
        try {
            building = new Building();
            readData();
        } catch (FileNotFoundException e) {
            System.err.println(this.fileName + " not found");
        } catch (IOException e) {
            System.err.println("IOException from reading " + this.fileName);
        }


        return building;
    }





    public void setRoomData(Building building) {
        String filePath = fileDirectoryName + File.separator + fileName;
        File file = new File(filePath);
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter(file);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (Room room :building.getRoomList()) {
                String line="";
                if(room.getRoomType().equals("Single")){
                    line += room.getRoomType()+","+room.getRoomNumber()+","+room.getBuilding()+","+room.getFloor()+","+room.getGuestName();

                }
                if(room.getRoomType().equals("Double")){
                  line += room.getRoomType()+","+room.getRoomNumber()+","+room.getBuilding()+","+room.getFloor()+","+room.getGuestName()+","+((DoubleRoom)room).getCoGuestName();
                }


                writer.append(line);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.err.println("Cannot write " + filePath);
        }
    }

}
