package petapp.group.adoptionpet.petapp.GeneralFunctions;

import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.adoptionprocessing.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class GeneralFunctions {
    public static void WriteInFile(String filename, ArrayList<?> infoList) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            for (Object object : infoList) {
                writer.write(object.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File is not found\n" + e.getMessage());
        }
    }

    public static ArrayList<?> ReadFromFile(String filename) {
        ArrayList<Object> infoList = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String Line;
            while ((Line = reader.readLine()) != null) {
                if (Line.startsWith("Adopter: ")) {

                    String[] parts = Line.substring(9).split(";");

                    if (parts.length == 7) {
                        String[] Listid=parts[6].split(",");
                        ArrayList<Integer> numListid = new ArrayList<>();
                        for(String str:Listid){
                            numListid.add(Integer.parseInt(str));
                        }
                        infoList.add(new Adopter((Integer.parseInt(parts[0])), parts[1], parts[2], (Integer.parseInt(parts[3])), (Integer.parseInt(parts[4])), parts[5],numListid));
                    }
                    else if(parts.length==6){
                        infoList.add(new Adopter((Integer.parseInt(parts[0])), parts[1], parts[2], (Integer.parseInt(parts[3])), (Integer.parseInt(parts[4])), parts[5],new ArrayList<>()));
                    }
                } else if (Line.startsWith("Admin: ")) {
                    String[] parts = Line.substring(7).split(";");
                    if (parts.length == 4) {
                        infoList.add(new Admin(Integer.parseInt(parts[0]), parts[1], parts[2], Integer.parseInt(parts[3])));
                    }
                }
                else if (Line.startsWith("Shelter: ")) {
                    String[] parts = Line.substring(9).split(";");

                    if (parts.length == 6) {
                        String[] Listid=parts[5].split(",");
                        ArrayList<Integer> numListid = new ArrayList<>();
                        for(String str:Listid){
                            numListid.add(Integer.parseInt(str));
                        }
                        infoList.add(new Shelter((Integer.parseInt(parts[0])), parts[1], parts[2], (Integer.parseInt(parts[3])),(Integer.parseInt(parts[4])),numListid));
                    }
                    else if(parts.length==5){
                        infoList.add(new Shelter((Integer.parseInt(parts[0])), parts[1], parts[2], (Integer.parseInt(parts[3])),(Integer.parseInt(parts[4])),new ArrayList<>()));
                    }
                }
                else if (Line.startsWith("Pet: ")) {
                    String[] parts = Line.substring(5).split(";");
                    if (parts.length == 8) {
                        infoList.add(new Pet(Integer.parseInt(parts[0]), parts[1], parts[2],parts[3],Integer.parseInt(parts[4]),parts[5],parts[6],Integer.parseInt(parts[7])));
                    }
                }
                else if (Line.startsWith("Adoption: ")) {
                    String[] parts = Line.substring(10).split(";");
                    if (parts.length == 5) {
                        infoList.add(new Adoption(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),Integer.parseInt(parts[2]),parts[3], LocalDate.parse(parts[4])));
                    }
                }
                else if (Line.startsWith("notification: ")) {
                    String[] parts = Line.substring(14).split(";");
                    if (parts.length == 4) {
                        infoList.add(new notification(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]),parts[2],Boolean.parseBoolean(parts[3])));
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            WriteInFile(filename, new ArrayList<>());
        }
        return infoList;
    }


    public static int userGenerateId() {

        int Id=0;
        if(!AdoptionPetApp.users.isEmpty())
            Id = AdoptionPetApp.users.getLast().getID();
        return ++Id ;
    }
public static int shelterGenerateId() {
    int Id=0;
    if(!AdoptionPetApp.shelters.isEmpty())
        Id = AdoptionPetApp.shelters.getLast().getId();
    return ++Id ;
    }
public static int petGenerateId() {
    int Id=0;
    if(!AdoptionPetApp.pets.isEmpty())
        Id = AdoptionPetApp.pets.getLast().getID();
    return ++Id ;
}
public static int adoptionGenerateId() {
    int Id=0;
    if(!AdoptionPetApp.adoptions.isEmpty())
       Id = AdoptionPetApp.adoptions.getLast().getAdoptionId();
    return ++Id ;
    }
    public static int notificationGenerateId() {
        int Id=0;
        if(!AdoptionPetApp.notifications.isEmpty())
            Id = AdoptionPetApp.notifications.getLast().getNotifiId();
        return ++Id ;
    }
}