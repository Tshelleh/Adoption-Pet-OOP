package petapp.group.adoptionpet.petapp.GeneralFunctions;

import javafx.collections.ObservableList;
import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.adoptionprocessing.Pet;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Search {
   static List<Pet> filterpets=new ArrayList<>();

    public static List<Pet> searchprocess(String queryname, int queryage, String querybreed, String queryspec, ObservableList<Pet> non_filter_pets) {
        filterpets = non_filter_pets.stream()
                .filter(item -> queryname.equals("-1")||item.getPetName().toLowerCase().trim().contains(queryname.toLowerCase().trim()))
                .filter(item ->queryage==-1|| item.getAge() == queryage)
                .filter(item -> querybreed.equals("-1")||item.getBreed().toLowerCase().trim().contains(querybreed.toLowerCase().trim()))
                .filter(item ->  queryspec.equals("-1")||item.getSpecies().toLowerCase().trim().contains(queryspec.toLowerCase().trim()))
                .collect(Collectors.toList());
        return filterpets;
    }
}
