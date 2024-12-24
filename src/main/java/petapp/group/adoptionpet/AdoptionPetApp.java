package petapp.group.adoptionpet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;
import petapp.group.adoptionpet.petapp.GeneralFunctions.notification;
import petapp.group.adoptionpet.petapp.accountManagement.User;
import petapp.group.adoptionpet.petapp.adoptionprocessing.*;

import java.io.IOException;
import java.util.ArrayList;

public class AdoptionPetApp extends Application{
   public static ArrayList<User> users = (ArrayList<User>) GeneralFunctions.ReadFromFile("User.txt") ;
   public static ArrayList<Pet> pets = (ArrayList<Pet>) GeneralFunctions.ReadFromFile("Pet.txt") ;
    public static ArrayList<Shelter> shelters = (ArrayList<Shelter>) GeneralFunctions.ReadFromFile("Shelter.txt") ;
    public static ArrayList<Adoption> adoptions = (ArrayList<Adoption>) GeneralFunctions.ReadFromFile("Adoption.txt") ;
    public static ArrayList<notification> notifications = (ArrayList<notification>) GeneralFunctions.ReadFromFile("notification.txt") ;
    public static int ActiveUserid=-1;
    public static int indexActiveUser=-1;

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        System.out.println(pets.size());
            for (int j = 0; j <AdoptionPetApp.adoptions.size(); j++) {
                for (int k = 0; k < AdoptionPetApp.pets.size(); k++) {
                if (AdoptionPetApp.pets.get(k).getID() ==AdoptionPetApp.adoptions.get(j).getPetId()) {
                    AdoptionPetApp.adoptions.get(j).setPet(AdoptionPetApp.pets.get(k));
                    break;
                }
            }
        }
        for (int k = 0; k < AdoptionPetApp.users.size(); k++) {
            if (AdoptionPetApp.users.get(k) instanceof Adopter) {
                Adopter ActiveAdopter = (Adopter) (AdoptionPetApp.users.get(k));
                for (int i = 0; i < ActiveAdopter.getAdoption_history_id().size(); i++) {
                    for (int j = 0; j < AdoptionPetApp.adoptions.size(); j++) {
                        if (AdoptionPetApp.adoptions.get(j).getAdoptionId() == ActiveAdopter.getAdoption_history_id().get(i)) {

                            ActiveAdopter.getAdoption_history().add(AdoptionPetApp.adoptions.get(j));
                            AdoptionPetApp.adoptions.get(j).setAdopter(ActiveAdopter);
                            break;
                        }
                    }
                }
                for (int j = 0; j < AdoptionPetApp.adoptions.size(); j++) {
                    if (AdoptionPetApp.users.get(k).getID() == AdoptionPetApp.adoptions.get(j).getAdopterId()) {

                        AdoptionPetApp.adoptions.get(j).setAdopter((Adopter) AdoptionPetApp.users.get(k));

                    }
                }

            }
            else if(AdoptionPetApp.users.get(k) instanceof Admin){
                Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(k));
                AdoptionPetApp.shelters.stream().filter(shelter -> shelter.getId()==ActiveAdmin.getShelterId())
                        .forEach(shelter -> ActiveAdmin.setShelter(shelter));

                for (int j = 0; j < ActiveAdmin.getShelter().getPetId().size(); j++) {
                    for (int i = 0; i < AdoptionPetApp.pets.size(); i++) {
                        if (AdoptionPetApp.pets.get(i).getID() ==ActiveAdmin.getShelter().getPetId().get(j)) {

                            ActiveAdmin.getShelter().getPets().add(AdoptionPetApp.pets.get(i));
                            break;
//

                        }
                    }
                }

            }
        }
        launch();
    }


}