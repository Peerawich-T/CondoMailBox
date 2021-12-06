package condoProject.service;

import javafx.stage.FileChooser;

import java.io.File;

public class BrowseFile {
    public FileChooser Browse(){

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Browse Image");

        String directoryString = System.getProperty("user.home") ;
        File directory = new File(directoryString);

        if(!directory.canRead()){
            directory = new File("c:/");
        }
       FileChooser.ExtensionFilter png = new FileChooser.ExtensionFilter("png", "*.PNG");
        FileChooser.ExtensionFilter jpg = new FileChooser.ExtensionFilter("jpg", "*.JPG");
        FileChooser.ExtensionFilter jpeg = new FileChooser.ExtensionFilter("jpeg", "*.JPEG");
        fileChooser.getExtensionFilters().addAll(png, jpg ,jpeg);
        fileChooser.setInitialDirectory(directory);



        return fileChooser;
    }
}
