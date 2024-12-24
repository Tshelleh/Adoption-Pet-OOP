package petapp.group.adoptionpet;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.GeneralFunctions.Search;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Pet;

import javax.swing.*;
import javax.swing.text.TabableView;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class adminmenucontroller implements Initializable {
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
            private Label labsheltername;
   @FXML
            private Label lablelusername;
    Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    ObservableList<Pet>petList ;

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

    public void AddPet(Event addpet)  {
        Pet pet= ActiveAdmin.getShelter().add_Pet(petname.getText(),Integer.parseInt(petage.getText()),petbreed.getText(),pethealth.getText(),petspec.getText(),true);
        JOptionPane.showMessageDialog(null,"Pet added successfully");
        petList= pettable.getItems();
       clearfield();
        petList.add(pet);
        pettable.setItems(petList);
        pettable.refresh();
    }
    public void removepet(Event remove){
        Pet selectedpet=pettable.getSelectionModel().getSelectedItem();
        int selectedid=selectedpet.getID();
        selectedpet.Remove_Pet(selectedid);
        int selectedindex=pettable.getSelectionModel().getSelectedIndex();
        pettable.getItems().remove(selectedindex);
        clearfield();
    }

    public void adminprofile(Event adminprofile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdminProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adminprofile.getSource()).getScene().getWindow();
        stage.setTitle("My Profile");
        stage.setScene(scene);
    }
    public void DeleteAccount(Event deleteacount) throws IOException {
        ActiveAdmin.delete_account();
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)deleteacount.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
    }
    public void Search(Event search) {
        ArrayList<Pet> filterpets = (ArrayList<Pet>) Search.searchprocess(!petsearchname.getText().isEmpty()?(petsearchname.getText()):"-1",
                !petsearchage.getText().isEmpty()?Integer.parseInt(petsearchage.getText()):-1,
                !petsearchbreed.getText().isEmpty()?petsearchbreed.getText():"-1",
                !petsearchspec.getText().isEmpty()?petsearchspec.getText():"-1",
                petList);
       ObservableList<Pet>FilterPets=FXCollections.observableArrayList(filterpets) ;
        pettable.setItems(FilterPets);
    }

    public void logout (Event logout)throws IOException {
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }

    public void editpet(Event edit){
        Pet selectedpet=pettable.getSelectionModel().getSelectedItem();
        int selectedid=selectedpet.getID();
       selectedpet.UpdatePet(selectedid,petname.getText(),petspec.getText(),petbreed.getText(),Integer.parseInt(petage.getText()),pethealth.getText());
        pettable.refresh();
      clearfield();
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
           petList=FXCollections.observableArrayList(ActiveAdmin.getShelter().getPets()) ;
           petList.removeIf(p -> p.getAvailability().equals("NO"));
        if(!petList.isEmpty()&&petList.getLast().getAvailability().equals("NO")) {
            petList.remove(petList.getLast());
        }
           pettable.setItems(petList);
            namcol.setCellValueFactory(new PropertyValueFactory<>("petName"));
            agecol.setCellValueFactory(new PropertyValueFactory<>("Age"));
            healthcol.setCellValueFactory(new PropertyValueFactory<>("Health"));
            breedcol.setCellValueFactory(new PropertyValueFactory<>("Breed"));
            speciescol.setCellValueFactory(new PropertyValueFactory<>("Species"));
            availcol.setCellValueFactory(new PropertyValueFactory<>("Availability"));
            idcol.setCellValueFactory(new PropertyValueFactory<>("ID"));
            labsheltername.setText(ActiveAdmin.getShelter().getShelterName());
            lablelusername.setText(ActiveAdmin.getUserName());


    }
}
