package petapp.group.adoptionpet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;
import petapp.group.adoptionpet.petapp.GeneralFunctions.Search;
import petapp.group.adoptionpet.petapp.GeneralFunctions.notification;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adopter;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adoption;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Pet;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ResourceBundle;

public class adoptermenucontroller implements Initializable {
    @FXML
    private TableView<Pet> pettable;
    @FXML
    private TableColumn<Pet,String> namcol;
    @FXML
    private TableColumn<Pet,Integer> agecol;
    @FXML
    private TableColumn<Pet,String> healthcol;
    @FXML
    private TableColumn<Pet,String> breedcol;
    @FXML
    private TableColumn<Pet,String> speciescol;
    @FXML
    private TableColumn<Pet,String> availcol;
    @FXML
    private TableColumn<Pet,Integer> idcol;
    @FXML
    private TextField petname;
    @FXML
    private TextField petage;
    @FXML
    private TextField petspec;
    @FXML
    private TextField petbreed;
    @FXML
    private TextField pethealth;

    @FXML
    private TextField petsearchname;
    @FXML
    private TextField petsearchage;
    @FXML
    private TextField petsearchspec;
    @FXML
    private TextField petsearchbreed;

    @FXML
    private Label lablelusername;
    Adopter ActiveAdopter=(Adopter) (AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    ObservableList<Pet> petList ;

    public void GetSelected(Event select){
        Pet pet =pettable.getSelectionModel().getSelectedItem();
        if(pet==null){
            return;
        }
        petname.setText(pet.getPetName());
        petage.setText(String.valueOf(pet.getAge()));
        pethealth.setText(pet.getHealth());
        petspec.setText(pet.getSpecies());
        petbreed.setText(pet.getBreed());
    }


    public void logout (Event logout)throws IOException{
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }
    public void adopterprofile(Event adopterprofile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdopterProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adopterprofile.getSource()).getScene().getWindow();
        stage.setTitle("My Profile");
        stage.setScene(scene);
    }
    public void DeleteAccount(Event deleteacount) throws IOException {
        ActiveAdopter.delete_account();
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)deleteacount.getSource()).getScene().getWindow();
        stage.setTitle("Login");
        stage.setScene(scene);//
    }
    public void AdoptionHistory(Event adoptionHistory) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdoptionHistory.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adoptionHistory.getSource()).getScene().getWindow();
        stage.setTitle("Adoption History");
        stage.setScene(scene);
    }
    public void Adopt(Event adopt){
        Adoption adoption=new Adoption(GeneralFunctions.adoptionGenerateId());
        Pet selectedpet=pettable.getSelectionModel().getSelectedItem();
        adoption.Adoption_pet(ActiveAdopter,selectedpet);
        selectedpet.setAvailability("NO");
        clearfield();
        JOptionPane.showMessageDialog(null,"The request has been sent");
        notification newNotification=new notification(GeneralFunctions.notificationGenerateId(),selectedpet.getShelterid(),ActiveAdopter.getUserName(),false);
        AdoptionPetApp.notifications.add(newNotification);
    }
    public void Search(Event search) {
        ArrayList<Pet> filterpets = (ArrayList<Pet>) Search.searchprocess((!petsearchname.getText().isEmpty()?petsearchname.getText() : "-1"),
                !petsearchage.getText().isEmpty()?Integer.parseInt(petsearchage.getText()):-1,
                !petsearchbreed.getText().isEmpty()?petsearchbreed.getText() : "-1" ,
                !petsearchspec.getText().isEmpty()?petsearchspec.getText() : "-1" ,
                petList);
        ObservableList<Pet>FilterPets=FXCollections.observableArrayList(filterpets) ;
        pettable.setItems(FilterPets);
    }
    private void clearfield(){
        petname.clear();
        petage.clear();
        petbreed.clear();
        pethealth.clear();
        petspec.clear();
    }
    public void Clearsearch(Event clear){
        petsearchname.clear();
        petsearchage.clear();
        petsearchbreed.clear();
        petsearchspec.clear();
        pettable.setItems(petList);

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        petList= FXCollections.observableArrayList(AdoptionPetApp.pets) ;
        Iterator<Pet> iterator=petList.iterator();
        while (iterator.hasNext()){
            Pet pet=iterator.next();
            if(pet.getAvailability().equals("NO")){
                iterator.remove();
            }
        }
        pettable.setItems(petList);
        namcol.setCellValueFactory(new PropertyValueFactory<>("petName"));
        agecol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        healthcol.setCellValueFactory(new PropertyValueFactory<>("Health"));
        breedcol.setCellValueFactory(new PropertyValueFactory<>("Breed"));
        speciescol.setCellValueFactory(new PropertyValueFactory<>("Species"));
        availcol.setCellValueFactory(new PropertyValueFactory<>("Availability"));
        idcol.setCellValueFactory(new PropertyValueFactory<>("ID"));
        lablelusername.setText(ActiveAdopter.getUserName());
    }
}
