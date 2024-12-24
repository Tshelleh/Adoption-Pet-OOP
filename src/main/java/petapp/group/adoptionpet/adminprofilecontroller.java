package petapp.group.adoptionpet;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adminprofilecontroller implements Initializable {
    Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    @FXML
    private Label lablelusername;
    @FXML
            private TextField username;
    @FXML
            private TextField contact;
    @FXML
            private TextField sheltername;
    @FXML
            private TextField location;
    @FXML
            private TextField password;

    public void DeleteAccount(Event deleteacount) throws IOException {
        ActiveAdmin.delete_account();
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)deleteacount.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
    }

    public void logout (Event logout)throws IOException {
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }

    public void homepage(Event homepage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adminmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)homepage.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }
    public void Edit(Event edit){
username.setEditable(true);
password.setEditable(true);
 sheltername.setEditable(true);
 location.setEditable(true);
 contact.setEditable(true);
    }
    public void saveEdit(Event save){
        ActiveAdmin.Editadmin(username.getText(),password.getText());
        ActiveAdmin.getShelter().EditShelter(sheltername.getText(),location.getText(),Integer.parseInt(contact.getText()));
    }
    public void adoptionrequest(Event adoptionrequest) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdoptionRequest.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adoptionrequest.getSource()).getScene().getWindow();
        stage.setTitle("Adoption Request");
        stage.setScene(scene);
    }
    public void Reports(Event reports) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("Reports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)reports.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        stage.setScene(scene);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lablelusername.setText(ActiveAdmin.getUserName());
     username.setText(ActiveAdmin.getUserName());
    password.setText(ActiveAdmin.getPassword());
    sheltername.setText(ActiveAdmin.getShelter().getShelterName());
    location.setText(ActiveAdmin.getShelter().getLocation());
    contact.setText(String.valueOf(ActiveAdmin.getShelter().getContact_info()));

   }
}
