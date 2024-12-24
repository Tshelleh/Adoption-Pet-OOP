package petapp.group.adoptionpet;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;
import petapp.group.adoptionpet.petapp.accountManagement.User;
import petapp.group.adoptionpet.petapp.adoptionprocessing.*;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class signup1controller implements Initializable {



    public void signupadmin(Event signup) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("signupadmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)signup.getSource()).getScene().getWindow();
        stage.setTitle("Sign up");
        stage.setScene(scene);
    }
    public void signupadopter(Event signup) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("signupadopter.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)signup.getSource()).getScene().getWindow();
        stage.setTitle("Sign up");
        stage.setScene(scene);
    }

    public void login(Event login) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)login.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
    }

    public void Exit(Event exit){
        System.out.println("write in file");
        GeneralFunctions.WriteInFile("User.txt", AdoptionPetApp.users);
        GeneralFunctions.WriteInFile("Pet.txt", AdoptionPetApp.pets);
        GeneralFunctions.WriteInFile("Shelter.txt", AdoptionPetApp.shelters);
        GeneralFunctions.WriteInFile("Adoption.txt", AdoptionPetApp.adoptions);
        GeneralFunctions.WriteInFile("notification.txt", AdoptionPetApp.notifications);
        Platform.exit();
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

}
