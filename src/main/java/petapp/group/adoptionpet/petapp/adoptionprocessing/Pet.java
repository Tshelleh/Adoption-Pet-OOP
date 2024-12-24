package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;

import javax.swing.*;
import java.util.ArrayList;

public class Pet {
    private static int petCount = 0;
    private final int ID;
    private String petName;
    private String Species;
    private String Breed;
    private int Age;
    private String Health;
    private String Availability;
    private int Shelterid;




        public Pet(int PetId) {
        this(PetId,"default","default",
                "default",-1,"default","default",-1);
        petCount++;
    }

    public Pet(int PetId, String petName, String species, String breed, int age,
               String health,String availability,int shelterid) {
        this.ID = PetId;
        this.petName = petName;
        Species = species;
        Breed = breed;
        Age = age;
        Health = health;
        Availability=availability;
        Shelterid=shelterid;

    }


    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        Species = species;
    }

    public String getBreed() {
        return Breed;
    }

    public void setBreed(String breed) {
        Breed = breed;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getHealth() {
        return Health;
    }

    public void setHealth(String health) {
        Health = health;
    }


    public int getID() {
        return ID;
    }

    public String getAvailability() {
        return Availability;
    }
    public void setAvailability(String availability) {
        Availability = availability;
    }
    public int getShelterid() {
        return Shelterid;
    }


    public void setShelterid(int shelterid) {
        Shelterid = shelterid;
    }


    public void Remove_Pet (int petID) {
        Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
        for(int i=0;i< ActiveAdmin.getShelter().getPetId().size();i++)
        {
            if (ActiveAdmin.getShelter().getPetId().get(i) == petID)
            {
                ActiveAdmin.getShelter().getPetId().remove(i);
                ActiveAdmin.getShelter().getPets().remove(i);
            }
        }
        for(int i=0;i< AdoptionPetApp.pets.size();i++)
        {
        if(AdoptionPetApp.pets.get(i).getID()==petID)
           {
            AdoptionPetApp.pets.get(i).setAvailability("NO");
           }
        }

   }


  public void UpdatePet(int ID,String newPetName, String newSpecies, String newBreed, int newAge,
                              String newHealth) {
      Admin ActiveAdmin=(Admin)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
      for(int i=0;i<ActiveAdmin.getShelter().getPets().size();i++) {
          if (ActiveAdmin.getShelter().getPetId().get(i) == ID) {
              if (newAge >= 0) {
                  ActiveAdmin.getShelter().getPets().get(i).setAge(newAge);
              }
              if (newPetName != null && !newPetName.isEmpty()) {

                  ActiveAdmin.getShelter().getPets().get(i).setPetName(newPetName);
              }
              if (newSpecies != null && !newSpecies.isEmpty()) {
                  ActiveAdmin.getShelter().getPets().get(i).setSpecies(newSpecies);
              }
              if (newBreed != null && !newBreed.isEmpty()) {
                  ActiveAdmin.getShelter().getPets().get(i).setBreed(newBreed);
              }

              if (newHealth != null && !newHealth.isEmpty()) {
                  ActiveAdmin.getShelter().getPets().get(i).setHealth(newHealth);
              }


          }



      }
        }

    @Override
    public String toString() {
        return
                "Pet: "+ID +';'+ petName + ';' + Species + ';' + Breed + ';' + Age +';' + Health + ';' + Availability + ';'
                + Shelterid ;

    }

}


