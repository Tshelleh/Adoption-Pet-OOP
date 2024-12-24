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
import petapp.group.adoptionpet.petapp.adoptionprocessing.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ReportController implements Initializable {
    Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    Report report=new Report();
    ObservableList<Adopter> ActiveAdopterList;
    ObservableList<Report> AdoptionReport;
    @FXML
    private  TableView<Adopter> activeAdopter;
    @FXML
    private TableColumn<Adopter,String> adopternamecol;
    @FXML
    private TableColumn<Adopter,Integer> contactcol;
    @FXML
    private TableColumn<Adopter,Integer> adopteragecol;
    @FXML
    private TableColumn<Adopter,String> adopterlocationcol;
    @FXML
    private TableColumn<Adopter,Integer> adopterHistoryAdopLengthcol;

  @FXML
    private  TableView<Report> adoptionreport;
    @FXML
    private TableColumn<Report,Integer> ReceivedRequests;
    @FXML
    private TableColumn<Report,Integer>  ProcessingRequests;
    @FXML
    private TableColumn<Report,Integer> AcceptableRequests;
    @FXML
    private TableColumn<Report,Double> AcceptableRate;
    @FXML
    private TableColumn<Report,Integer> NumofPetsavailable;

    @FXML
    private Label lablelusername;

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
    public void logout (Event logout)throws IOException {
        AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).logout(logout);
    }

    public void adoptionrequest(Event adoptionrequest) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("AdoptionRequest.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)adoptionrequest.getSource()).getScene().getWindow();
        stage.setTitle("Adoption Request");
        stage.setScene(scene);
    }

    public void homepage(Event homepage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("adminmenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)homepage.getSource()).getScene().getWindow();
        stage.setTitle("Home Page");
        stage.setScene(scene);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ActiveAdopterList= FXCollections.observableArrayList(report.most_active_adopters()) ;
        AdoptionReport=FXCollections.observableArrayList();
        AdoptionReport.add(report);
        adoptionreport.setItems(AdoptionReport);
        activeAdopter.setItems(ActiveAdopterList);

        adopternamecol.setCellValueFactory(cellData->{
            String userName = cellData.getValue().getUserName();
            return new SimpleStringProperty(userName!=null?userName:"N/A");
        });
        adopteragecol.setCellValueFactory(new PropertyValueFactory<>("Age"));
        adopterlocationcol.setCellValueFactory(new PropertyValueFactory<>("Location"));
        contactcol.setCellValueFactory(new PropertyValueFactory<>("Contact_info"));
        adopterHistoryAdopLengthcol.setCellValueFactory(cellData->{
          Integer adopHistoryLength = cellData.getValue().getAdoption_history().size();
            return new SimpleIntegerProperty(adopHistoryLength).asObject();
        });

        ReceivedRequests.setCellValueFactory(new PropertyValueFactory<>("ReceivedRequests"));
        ProcessingRequests.setCellValueFactory(new PropertyValueFactory<>("ProcessingRequests"));
        AcceptableRequests.setCellValueFactory(new PropertyValueFactory<>("AcceptableRequests"));
        AcceptableRate.setCellValueFactory(new PropertyValueFactory<>("AcceptableRate"));
        NumofPetsavailable.setCellValueFactory(new PropertyValueFactory<>("NumofPetsavailable"));

        lablelusername.setText(ActiveAdmin.getUserName());
    }
}
