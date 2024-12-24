package petapp.group.adoptionpet;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adopter;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adopterprofilecontroller implements Initializable {

    Adopter Activeadopter=(Adopter)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    @FXML
    private Label lablelusername;
    @FXML
    private TextField username;
    @FXML
    private TextField contact;
     @FXML
    private TextField age;
    @FXML
    private TextField location;
    @FXML
    private TextField password;
    Adopter ActiveAdopter=(Adopter) (AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));

    public void DeleteAccount(Event deleteacount) throws IOException {
        ActiveAdopter.delete_account();
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)deleteacount.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);//
    }

    public void logout (Event logout)throws IOException {
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }

    public void AdoptionHistory(Event adoptionHistory) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdoptionHistory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adoptionHistory.getSource()).getScene().getWindow();
        stage.setTitle("Adoption History");
        stage.setScene(scene);
    }

    public void homepage(Event homepage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adoptermenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)homepage.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }
    public void Edit(Event edit){
        username.setEditable(true);
        password.setEditable(true);
        location.setEditable(true);
        contact.setEditable(true);
        age.setEditable(true);
    }
    public void saveEdit(Event save){
        Activeadopter.editAdopter(username.getText(),password.getText(),Integer.parseInt(age.getText()),location.getText(),Integer.parseInt(contact.getText()));

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        lablelusername.setText(Activeadopter.getUserName());
        username.setText(Activeadopter.getUserName());
        password.setText(Activeadopter.getPassword());
        location.setText(Activeadopter.getLocation());
        contact.setText(String.valueOf(Activeadopter.getContact_info()));
        age.setText(String.valueOf(Activeadopter.getAge()));

    }
}


