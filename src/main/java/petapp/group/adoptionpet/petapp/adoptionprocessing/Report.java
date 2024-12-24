package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.accountManagement.User;

import java.util.ArrayList;
import java.util.Collections;

public class Report {
    private Admin ActiveAdmin = (Admin) (AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
    private int ReceivedRequests = getAdoptionRequestStats();
    private int ProcessingRequests;
    private int AcceptableRequests = adoption_per_shelter();
    private double AcceptableRate = getadoptionrate_pershelter();
    private int NumofPetsavailable = available_pets_Per_shelter();

    public int adoption_per_shelter() {

        int adoption_count = 0;
        for (Pet pet : ActiveAdmin.getShelter().getPets()) {
            for (Adoption adoption : AdoptionPetApp.adoptions) {
                if (adoption.getStatus().equals("accept") && adoption.getPetId() == pet.getID()) {
                    adoption_count++;
                }
            }
        }
        return adoption_count;
    }

    public int available_pets_Per_shelter() {
        int available_count = 0;

        for (Pet pet : ActiveAdmin.getShelter().getPets()) {
            if (pet.getAvailability().equals("YES")) {
                available_count++;

            }
        }

        return available_count;
    }

    public double getadoptionrate_pershelter() {
        double adoption_rate;
        try {
            System.out.println(adoption_per_shelter()+ " ps="+ActiveAdmin.getShelter().getPets().size());
            adoption_rate = (double) (((double)adoption_per_shelter()) / (double)ActiveAdmin.getShelter().getPets().size()) * 100.0;
            System.out.println(adoption_rate+" s");
        }
        catch (ArithmeticException e){
            adoption_rate = 0.0 ;
        }

        return adoption_rate;
    }

    public int getAdoptionRequestStats() {
        int countReceived = 0;
        int countProcessor = 0;
        for (Pet pet : ActiveAdmin.getShelter().getPets()) {
            for (Adoption adoption : AdoptionPetApp.adoptions) {
                if (adoption.getPetId() == pet.getID()) {
                    if (!adoption.getStatus().equals("Request sent")) {
                        countProcessor++;
                        countReceived++;
                    } else {
                        countReceived++;
                    }
                }
            }
        }
        ProcessingRequests = countProcessor;
        return countReceived;
    }

    public ArrayList<Adopter> most_active_adopters() {
        ArrayList<Adopter> ActiveAdopters = new ArrayList<>();
        for (User user:AdoptionPetApp.users){
            if(user instanceof Adopter){
               ActiveAdopters.add((Adopter) user);
            }

        }
            Collections.sort(ActiveAdopters);

        return ActiveAdopters;
    }

    public int getReceivedRequests() {
        return ReceivedRequests;
    }



    public int getProcessingRequests() {
        return ProcessingRequests;
    }


    public int getAcceptableRequests() {
        return AcceptableRequests;
    }



    public double getAcceptableRate() {
        return AcceptableRate;
    }


    public int getNumofPetsavailable() {
        return NumofPetsavailable;
    }

}



