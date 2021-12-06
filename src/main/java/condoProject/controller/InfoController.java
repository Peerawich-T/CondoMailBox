package condoProject.controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;


public class InfoController {
    @FXML
    ImageView returnImageView;
    @FXML public void handleReturnImageViewOnAction(Event event) throws IOException {
        returnImageView = (ImageView) event.getSource();
        Stage stage = (Stage) returnImageView.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/login.fxml"));


        stage.setScene(new Scene(loader.load(), 800, 600));
        LoginController loginController = loader.getController();


        stage.show();
    }
}
