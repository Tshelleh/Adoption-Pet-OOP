package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.GeneralFunctions.*;
import petapp.group.adoptionpet.petapp.accountManagement.User;

import javax.swing.*;
import java.util.Scanner;

public class Admin extends User {
    private Shelter shelter;
    private int shelterId;
    public Admin(int id) {
        this(id,"default","default",-1);
        User.count_user++;
    }

    public Admin(int ID, String UserName, String Password,int shelterId) {
        super(ID, UserName, Password,"Admin");
        this.shelterId=shelterId;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelter(Shelter shelter) {
        this.shelter = shelter;
    }

    public Shelter getShelter() {
        return shelter;
    }

    @Override
    public String toString() {
        return "Admin: " +getID()+";"+getUserName()+";"+getPassword()+
                ";" + shelterId;
    }

    public void Sing_up(String username,String Password,String sheltername,String locationshelter,int contactinfo) throws  RuntimeException{
        boolean isExist=false;
        for (User user: AdoptionPetApp.users){
            if(user.getUserName().equals(username)) {
                isExist=true;
            }
        }
        if(isExist) {
            throw new RuntimeException("User name already Exist");
        }
        else {
            String userName = username.toLowerCase().trim();
            setUserName(userName);
            String password = Password;
            setPassword(password);
            add_shelter(sheltername, locationshelter, contactinfo);
            AdoptionPetApp.users.add(this);
        }
    }


    public void add_shelter(String sheltername,String locationshelter,int contactinfo){
        this.shelter=new Shelter(GeneralFunctions.shelterGenerateId());
        this.shelterId= shelter.getId();
        String shelterName=sheltername.toLowerCase().trim();
        String Location=locationshelter.trim().toLowerCase();
        int contact_info=contactinfo;
        shelter.setShelterName(shelterName);
        shelter.setLocation(Location);
        shelter.setContact_info(contact_info);
        shelter.setAdminId(this.getID());
        AdoptionPetApp.shelters.add(shelter);
    }
    public void Editadmin(String name,String password){
        try {
            String New_Name = name;
           setUserName(New_Name);
            String New_password = password;
            setPassword(New_password);
            AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).setUserName(New_Name);
            AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser).setPassword(New_password);

            JOptionPane.showMessageDialog(null,"User information has been updated successfully");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Editing error");
        }
    }

    public void delete_account(){
        this.shelter.DeleteShelter(this.shelterId);
        AdoptionPetApp.users.remove(AdoptionPetApp.indexActiveUser);
        AdoptionPetApp.ActiveUserid=-1;
        AdoptionPetApp.indexActiveUser=-1;
    }

}

