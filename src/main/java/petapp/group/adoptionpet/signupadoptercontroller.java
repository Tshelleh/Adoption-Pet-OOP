package petapp.group.adoptionpet;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;
import petapp.group.adoptionpet.petapp.accountManagement.User;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adopter;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class signupadoptercontroller implements Initializable {

    @FXML
    TextField adoptername;
    @FXML
    TextField adoptercont;
    @FXML
    TextField adopterage;
    @FXML
    TextField adopterloc;
    @FXML
    PasswordField adopterpass;

    public void signup(Event signup) throws IOException {
        try {
        Adopter newAdopter=new Adopter(GeneralFunctions.userGenerateId());
        newAdopter.Sing_up(adoptername.getText(),adopterpass.getText(),Integer.parseInt(adopterage.getText()),Integer.parseInt(adoptercont.getText()),adopterloc.getText());
        JOptionPane.showMessageDialog(null,"Successful Sing Up");
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)signup.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
        }
        catch (RuntimeException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
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
