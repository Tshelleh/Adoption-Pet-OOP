package petapp.group.adoptionpet.petapp.adoptionprocessing;

import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.accountManagement.User;

import javax.swing.*;
import java.util.ArrayList;

public class Adopter extends User implements Comparable {
    private int Contact_info;
    private int Age;
    private String Location;
    private ArrayList<Adoption> Adoption_history=new ArrayList<>();
    private ArrayList<Integer> Adoption_history_id;

    public Adopter(int id){

        this(id,"default","default",-1,-1,"default",new ArrayList<>());
        User.count_user++;
    }
    public Adopter(int ID, String UserName, String Password, int contact_info,int age,String location,ArrayList<Integer> Adoption_history_id) {
        super(ID, UserName, Password,"Adopter");
        Contact_info = contact_info;
        Age=age;
        Location=location;
        this.Adoption_history_id=Adoption_history_id;
    }

    public void setContact_info(int contact_info) {
        Contact_info = contact_info;
    }


    public int getContact_info() {
        return Contact_info;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }

    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public void setAdoption_history(Adoption adoption_history) {
        Adoption_history.add(adoption_history);
    }

    public ArrayList<Integer> getAdoption_history_id() {
        return Adoption_history_id;
    }

    public void setAdoption_history_id(int adoption_history_id) {
        Adoption_history_id.add(adoption_history_id);
    }

    public ArrayList <Adoption> getAdoption_history() {
        return Adoption_history;
    }

    @Override
    public String toString() {
        String idList=";";
        if (!Adoption_history_id.isEmpty()) {
            for(int i=0;i<Adoption_history_id.size();i++){
                if(i!=Adoption_history_id.size()-1) {
                    idList += Adoption_history_id.get(i) + ",";
                }
                else {
                    idList += Adoption_history_id.get(i);
                }

            }

        }
        return "Adopter: "+getID()+";"+getUserName()+";"+getPassword()+";"+Contact_info+";"+Age+
                ";" + Location +idList;
    }


    public void Sing_up(String username,String Password,int Age,int contactinfo,String Location)throws RuntimeException{
       boolean isExist=false;
        for (User user: AdoptionPetApp.users){
            if(user.getUserName().equals(username)) {
                isExist=true;
           }
        }
        if(isExist) {
            throw new RuntimeException("User name already Exist");
        }
        else
        {
            String userName = username.toLowerCase().trim();
            setUserName(userName);
            String password = Password;
            setPassword(password);
            int age = Age;
            setAge(age);
            int contact_info = contactinfo;
            setContact_info(contact_info);
            String location = Location.toLowerCase().trim();
            setLocation(location);
            AdoptionPetApp.users.add(this);
        }
    }

        public void editAdopter(String name,String password,int age ,String location,int  ContactInfo){
            Adopter Activeadopter=(Adopter)(AdoptionPetApp.users.get(AdoptionPetApp.indexActiveUser));
                    try {
            String New_Name = name;
           setUserName(New_Name);
            String New_password = password;
            setPassword(New_password);
            int New_Age=Age;
            setAge(New_Age);
            int New_Contactinfo=Contact_info;
            setContact_info(New_Contactinfo);
            String new_Location=location;
            setLocation(new_Location);
           Activeadopter.setUserName(New_Name);
           Activeadopter.setPassword(New_password);
           Activeadopter.setLocation(new_Location);
           Activeadopter.setAge(New_Age);
          Activeadopter.setContact_info(New_Contactinfo);
            JOptionPane.showMessageDialog(null,"User information has been updated successfully");

        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null,"Editing error");
        }

    }


    public void delete_account(){
        Adoption_history.stream().filter(adopt ->adopt.getStatus().equals("Request sent"))
                        .forEach(adopt -> adopt.editinfo(adopt.getStatus(),"YES"));
        AdoptionPetApp.users.remove(AdoptionPetApp.indexActiveUser);
        AdoptionPetApp.ActiveUserid=-1;
        AdoptionPetApp.indexActiveUser=-1;
    }
    public int compareTo(Object object){
        Adopter other_adopter=(Adopter) object;
        return Integer.compare(this.Adoption_history.size(),other_adopter.Adoption_history.size());
    }


}


