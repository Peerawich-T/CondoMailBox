package condoProject.service;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.InvalidPathException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;



public class ImageDataSource {


    public String getImage(String dirName, String imageName) {
        File jarDir = null;
        File codeDir = null;
        File f = new File(imageName);
        try {
            jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
            codeDir = jarDir.getParentFile();
            String path = codeDir.toString() + File.separator + dirName + File.separator + f.getName();
            File uploadFile = new File(path);
            return uploadFile.toURI().toString();

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String copyImage(String dirName, File original, String defaultImage) {
        File file = new File(dirName);
        if (!file.exists()) {
            file.mkdirs();
        }
        File jarDir = null;
        File codeDir = null;
        try {
            jarDir = new File(this.getClass().getProtectionDomain().getCodeSource()
                    .getLocation().toURI().getPath());
            codeDir = jarDir.getParentFile();
            String copyPath = codeDir.toString() + File.separator + dirName + File.separator + original.getName();

            File copy = new File(copyPath);
            try {
                File imageFile = copy;
                if(!imageFile.exists()){
                    Files.copy(original.toPath(), copy.toPath());

                    return original.getName();

                }
                else {
                    DateFormat dateFormat = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
                    if(imageFile.getName().contains(".jpg")){
                        copyPath =codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date())+".jpg";
                        copy = new File(copyPath);
                        Files.copy(original.toPath(), copy.toPath());
                        return  dateFormat.format(new Date())+".jpg";
                    }
                    else if(imageFile.getName().contains(".png")){
                        copyPath =codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date())+".png";
                        copy = new File(copyPath);
                        Files.copy(original.toPath(), copy.toPath());
                        return  dateFormat.format(new Date())+".png";
                    }
                    else if(imageFile.getName().contains(".jpeg")){
                        copyPath =codeDir.toString() + File.separator + dirName + File.separator + dateFormat.format(new Date())+".jpeg";
                        copy = new File(copyPath);
                        Files.copy(original.toPath(), copy.toPath());
                        return  dateFormat.format(new Date())+".jpeg";
                    }
                }

            } catch (IOException e) {
                System.out.println("Can't copy");
            }
            catch (NullPointerException e){
                System.out.println("Can't copy");
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        catch (NullPointerException e){
            System.out.println("Can't copy");
        }

        return defaultImage;
    }








}
