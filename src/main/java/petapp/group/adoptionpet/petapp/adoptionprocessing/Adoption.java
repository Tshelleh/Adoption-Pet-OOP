package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;

import java.time.LocalDate;

public class Adoption {
private final int AdoptionId;
private Pet pet;  //has admin id
private int petId;   //get it from pet
private Adopter adopter;
private int adopterId;
private String status;
private LocalDate Adoption_Date;
private static int AdoptionCount=0;

    public Adoption(int adoptionId) {
       this(adoptionId,-1,-1,"Request sent",LocalDate.now());
       AdoptionCount++;
    }

    public Adoption(int adoptionId, int petId, int adopterId, String status, LocalDate adoption_Date) {
        AdoptionId = adoptionId;
        this.petId = petId;
        this.adopterId = adopterId;
        this.status = status;
        Adoption_Date = adoption_Date;
    }

    public LocalDate getAdoption_Date() {
        return Adoption_Date;
    }


    public String getStatus() {
        return status;
    }


    public int getAdopterId() {
        return adopterId;
    }

    public void setAdopterId(int adopterId) {
        this.adopterId = adopterId;
    }

    public int getAdoptionId() {
        return AdoptionId;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public Adopter getAdopter() {
        return adopter;
    }

    public void setAdopter(Adopter adopter) {
        this.adopter = adopter;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void Adoption_pet(Adopter adopter,Pet pet){
     setAdopter(adopter);
     setAdopterId(adopter.getID());
     setPet(pet);
     System.out.println("shelter id "+pet.getShelterid());
     setPetId(pet.getID());
     adopter.setAdoption_history(this);
     adopter.setAdoption_history_id(this.AdoptionId);
     AdoptionPetApp.adoptions.add(this);
    }
    public void editinfo(String status,String availability){
        this.status=status;
        this.getPet().setAvailability(availability);
    }

    @Override
    public String toString() {
        return "Adoption: " +
               AdoptionId +
                ";" + petId +
                ";" + adopterId +
                ";" + status +
                ";" + Adoption_Date ;
    }
}


