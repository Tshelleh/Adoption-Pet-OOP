package petapp.group.adoptionpet;

import javafx.application.Platform;
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

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Logincontroller implements Initializable {
@FXML
    TextField usernamelogin;
@FXML
    PasswordField passwordlogin;
public void login(Event login) throws IOException {
       AdoptionPetApp.ActiveUserid = User.Log_in(usernamelogin.getText(), passwordlogin.getText());
    if(AdoptionPetApp.ActiveUserid!=-1) {
        AdoptionPetApp.users.stream()
                .filter(user -> AdoptionPetApp.ActiveUserid==user.getID())
                .forEach(user -> AdoptionPetApp.indexActiveUser=AdoptionPetApp.users.indexOf(user));
        JOptionPane.showMessageDialog(null,"Successful Login");

        if(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).getRole().equals("Admin"))
        {
            Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
            System.out.println(ActiveAdmin.getShelter().getPets().size());
            FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adminmenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) login.getSource()).getScene().getWindow();
            stage.setTitle("Home Page");
            stage.setScene(scene);
            AdoptionPetApp.notifications.stream().filter(noti->noti.getUserId()== ActiveAdmin.getShelterId() && noti.isOpened()==false)
                    .forEach(noti -> {noti.sendadminnotif(); noti.setOpened(true);});
        }
        else{
           Adopter ActiveAdopter=(Adopter)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
            FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adoptermenu.fxml"));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) ((Node) login.getSource()).getScene().getWindow();
            stage.setTitle("Home Page");
            stage.setScene(scene);
            AdoptionPetApp.notifications.stream().filter(noti-> noti.getUserId()== ActiveAdopter.getID() && noti.isOpened()==false)
                    .forEach(noti -> {noti.sendadopternotif(); noti.setOpened(true);});
        }
    }
}
public void singup1(Event signup1) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("signup1.fxml"));
    Scene scene = new Scene(fxmlLoader.load());
    Stage stage=(Stage)((Node)signup1.getSource()).getScene().getWindow();
    stage.setTitle("Sign up");
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

    public void initialize(URL url, ResourceBundle rb){


    }

}
