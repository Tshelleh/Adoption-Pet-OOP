package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;

import javax.swing.*;
import java.util.ArrayList;

public class Shelter {
    private final int id;
    private String ShelterName;
    private String Location;
    private int contact_info;
    private ArrayList <Integer> petId;
    private ArrayList<Pet> pets = new ArrayList<>();
    private int adminId;
    private static int CountShelter=0;

    public Shelter(int id) {
        this(id,"default","default",-1,-1,new ArrayList<>());
        CountShelter++;
    }

    public Shelter(int id, String shelterName, String location, int contact_info,int adminId, ArrayList<Integer> petId) {
        this.id = id;
        ShelterName = shelterName;
        Location = location;
        this.contact_info = contact_info;
        this.petId = petId;
        this.adminId=adminId;
    }

    public int getId() {
        return id;
    }

    public String getShelterName() {
        return ShelterName;
    }

    public void setShelterName(String shelterName) {
        ShelterName = shelterName;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public int getContact_info() {
        return contact_info;
    }

    public void setContact_info(int contact_info) {
        this.contact_info = contact_info;
    }

    public ArrayList<Integer> getPetId() {
        return petId;
    }


    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public void setPets(ArrayList<Pet> pets) {
        this.pets = pets;
    }

    public ArrayList<Pet> getPets() {
        return pets;
    }

    public Pet add_Pet(String PetName, int PetAge,String Breed, String Health, String Species,boolean Availability) {
        Pet NewPet = new Pet(GeneralFunctions.petGenerateId());
        String petName =PetName;
        int petAge =PetAge;
        String availability ;
        if(Availability)
            availability="YES";
        else
            availability="NO";
        String breed=Breed ;
        String health=Health ;
        String species=Species ;

        NewPet.setPetName(petName);
        NewPet.setAge(petAge);
        NewPet.setAvailability(availability);
        NewPet.setBreed(breed);
        NewPet.setHealth(health);
        NewPet.setSpecies(species);
        NewPet.setShelterid(id);

        petId.add(NewPet.getID());
        pets.add(NewPet);
        AdoptionPetApp.pets.add(NewPet);
        getPets().forEach(pet->System.out.println(pet.getPetName()));

        return NewPet;


    }
    public void EditShelter(String name,String location,int contact){

        try {
            String New_Name = name;
            setShelterName(New_Name);
            String New_location = location;
            setLocation(New_location);
            int New_info = contact;
            setContact_info(New_info);
           Admin Activeadmin=(Admin) AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser);
           Activeadmin.getShelter().setShelterName(New_Name);
            Activeadmin.getShelter().setLocation(New_location);
            Activeadmin.getShelter().setContact_info(New_info);
            JOptionPane.showMessageDialog(null,"Shelter information has been updated successfully");
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Editing error");
        }
        }

        public void DeleteShelter(int shelterId) {
                       for(int i=0;i<AdoptionPetApp.pets.size();i++){
                    if(!pets.isEmpty()) {
                        AdoptionPetApp.pets.remove(pets.get(i));
                    }
                }

            for (int i = 0; i < AdoptionPetApp.shelters.size(); i++) {
                if (AdoptionPetApp.shelters.get(i).getId() == shelterId){
                    AdoptionPetApp.shelters.remove(i);
                    break;
                }
            }
        }
    @Override
    public String toString() {
        String idList="";
        if (!petId.isEmpty()) {
            for(int i=0;i<petId.size();i++){
                if(i!=petId.size()-1) {
                    idList += petId.get(i) + ",";
                }
                else {
                    idList += petId.get(i);
                }

            }

        }
        return "Shelter: " +
                 id +";"+
                ShelterName + ";" +
                Location + ";" +
                 + contact_info +";"+adminId+";"+idList;
    }

    public void displayinfo(){
        System.out.println("Shelter name: " + getShelterName());
        System.out.println("Location: " + getLocation());
        System.out.println("Contact information: " + getContact_info());
        System.out.println("Pets: " + getPets());
    }
}


