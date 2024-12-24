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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adopter;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Adoption;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Pet;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Shelter;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class adoptionhistory implements Initializable {
    @FXML
    private TableView<Adoption> adoptiontable;
    @FXML
    private TableColumn<Adoption,String> namcol;
    @FXML
    private TableColumn<Adoption,String> shelternamcol;
    @FXML
    private TableColumn<Adoption,String> locationcol;
    @FXML
    private TableColumn<Adoption,String> speciescol;
    @FXML
    private TableColumn<Adoption,String> statuscol;
    @FXML
    private TableColumn<Adoption,Integer> contactcol;
    @FXML
    private Label lablelusername;

    Adopter ActiveAdopter=(Adopter) (AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    ObservableList<Adoption> AdoptionList ;


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
    public void adopterprofile(Event adopterprofile) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdopterProfile.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adopterprofile.getSource()).getScene().getWindow();
        stage.setTitle("My Profile");
        stage.setScene(scene);
    }

    public void homepage(Event homepage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adoptermenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)homepage.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AdoptionList= FXCollections.observableArrayList(ActiveAdopter.getAdoption_history()) ;
        adoptiontable.setItems(AdoptionList);
        namcol.setCellValueFactory(cellData->{
            Pet pet= cellData.getValue().getPet();
            return new SimpleStringProperty(pet!=null?pet.getPetName():"N/A");
        });
        shelternamcol.setCellValueFactory(cellData->{
            int shelterid = cellData.getValue().getPet().getShelterid();
           Optional<Shelter> shelter = AdoptionPetApp.shelters.stream().filter(shelt -> shelt.getId()==shelterid).findFirst();
            return new SimpleStringProperty(shelter.isPresent() ?shelter.get().getShelterName():"N/A");
        });
       contactcol.setCellValueFactory(cellData->{
           int shelterid = cellData.getValue().getPet().getShelterid();
           Optional<Shelter> shelter = AdoptionPetApp.shelters.stream().filter(shelt -> shelt.getId()==shelterid).findFirst();
           return new SimpleIntegerProperty(shelter.isPresent()?shelter.get().getContact_info():-1).asObject();
       });

       locationcol.setCellValueFactory(cellData->{
           int shelterid = cellData.getValue().getPet().getShelterid();
           Optional<Shelter> shelter = AdoptionPetApp.shelters.stream().filter(shelt -> shelt.getId()==shelterid).findFirst();
           return new SimpleStringProperty(shelter.isPresent() ?shelter.get().getLocation():"N/A");
       });
        speciescol.setCellValueFactory(cellData->{
            Pet pet= cellData.getValue().getPet();
            return new SimpleStringProperty(pet!=null?pet.getSpecies():"N/A");
        });
        statuscol.setCellValueFactory(new PropertyValueFactory<>("status"));
        lablelusername.setText(ActiveAdopter.getUserName());
    }
}
