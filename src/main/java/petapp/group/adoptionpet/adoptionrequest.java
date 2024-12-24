package petapp.group.adoptionpet;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
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
import petapp.group.adoptionpet.petapp.GeneralFunctions.notification;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Admin;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adopter;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adoption;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Pet;

import javax.swing.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class adoptionrequest implements Initializable {
    @FXML
    private TableView<Adoption> adoptiontable;
    @FXML
    private TableColumn<Adoption,String> namcol;
    @FXML
    private TableColumn<Adoption,Integer> agecol;
    @FXML
    private TableColumn<Adoption,String> adopternamecol;
    @FXML
    private TableColumn<Adoption,String> speciescol;
    @FXML
    private TableColumn<Adoption,Integer> contactcol;
    @FXML
    private TableColumn<Adoption,Integer> adopteragecol;
    @FXML
    private TextField petname;
    @FXML
    private TextField petage;
    @FXML
    private TextField petspec;
    @FXML
    private TextField adoptername;
    @FXML
    private TextField adoptercontact;
   @FXML
    private TextField adopterage;
    @FXML
    private Label labsheltername;
    @FXML
    private Label lablelusername;

    Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    ObservableList<Adoption> AdoptionList ;

    public void GetSelected(Event select){
        Adoption adoption =adoptiontable.getSelectionModel().getSelectedItem();
        if(adoption==null){
            return;
        }
        petname.setText(adoption.getPet().getPetName());
        petage.setText(String.valueOf(adoption.getPet().getAge()));
        adoptername.setText(adoption.getAdopter().getUserName());
        petspec.setText(adoption.getPet().getSpecies());
        adopterage.setText(String.valueOf(adoption.getAdopter().getAge()));
        adoptercontact.setText(String.valueOf(adoption.getAdopter().getContact_info()));
    }
    public void homepage(Event homepage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adminmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)homepage.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(scene);
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
    public void Accept(Event accept){
        Adoption adoption=adoptiontable.getSelectionModel().getSelectedItem();
        adoption.editinfo("accept","NO");
        clearfield();
        JOptionPane.showMessageDialog(null,"You accepted adoption");
        notification newNotification=new notification(GeneralFunctions.notificationGenerateId(),adoption.getAdopterId(),ActiveAdmin.getUserName(),false);
        AdoptionPetApp.notifications.add(newNotification);
    }
    public void Refuse(Event refuse){
        Adoption adoption=adoptiontable.getSelectionModel().getSelectedItem();
        adoption.editinfo("refuse","YES");
        clearfield();
        JOptionPane.showMessageDialog(null,"You refused adoption");
        notification newNotification=new notification(GeneralFunctions.notificationGenerateId(),adoption.getAdopterId(),ActiveAdmin.getUserName(),false);
        AdoptionPetApp.notifications.add(newNotification);

    }
    public void Reports(Event reports) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("Reports.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)reports.getSource()).getScene().getWindow();
        stage.setTitle("Reports");
        stage.setScene(scene);
    }


    public void logout (Event logout)throws IOException {
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }

    private void clearfield(){
        petname.clear();
        petage.clear();
        adoptername.clear();
        adopterage.clear();
        adoptercontact.clear();
        petspec.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdoptionList= FXCollections.observableArrayList(AdoptionPetApp.adoptions) ;
        AdoptionList.removeIf(adopt -> adopt.getStatus().equals("accept")||adopt.getStatus().equals("refuse")||adopt.getPet().getShelterid()!=ActiveAdmin.getShelterId());
//        if(!AdoptionList.isEmpty()&&(AdoptionList.getLast().getStatus().equals("accept")||AdoptionList.getLast().getStatus().equals("refuse"))) {
//           AdoptionList.remove(AdoptionList.getLast());
//        }
        adoptiontable.setItems(AdoptionList);
        namcol.setCellValueFactory(cellData->{
            Pet pet= cellData.getValue().getPet();
            return new SimpleStringProperty(pet!=null?pet.getPetName():"N/A");
        });
        agecol.setCellValueFactory(cellData->{
            Pet pet= cellData.getValue().getPet();
            return new SimpleIntegerProperty(pet!=null?pet.getAge():-1).asObject();
        });
       adopteragecol.setCellValueFactory(cellData->{
           Adopter adopter = cellData.getValue().getAdopter();
           return new SimpleIntegerProperty(adopter!=null?adopter.getAge():-1).asObject();
       });
        adopternamecol.setCellValueFactory(cellData->{
            Adopter adopter = cellData.getValue().getAdopter();
            return new SimpleStringProperty(adopter!=null?adopter.getUserName():"N/A");
        });
        speciescol.setCellValueFactory(cellData->{
            Pet pet= cellData.getValue().getPet();
            return new SimpleStringProperty(pet!=null?pet.getSpecies():"N/A");
        });
        contactcol.setCellValueFactory(cellData->{
            Adopter adopter = cellData.getValue().getAdopter();
            return new SimpleIntegerProperty(adopter!=null?adopter.getContact_info():-1).asObject();
        });
        labsheltername.setText(ActiveAdmin.getShelter().getShelterName());
        lablelusername.setText(ActiveAdmin.getUserName());
    }
}
