package petapp.group.adoptionpet.petapp.accountManagement;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import petapp.group.adoptionpet.AdoptionPetApp;
import petapp.group.adoptionpet.petapp.GeneralFunctions.GeneralFunctions;

import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public abstract class User {
    private final int ID;
    private String UserName;
    private String Password;
    private String Role;
    public static int count_user=0;


    public User(int ID,String UserName,String Password,String Role){
        this.ID=ID;
        this.UserName=UserName;
        this.Password=Password;
        this.Role=Role;
    }

    public String getRole() {
        return Role;
    }


    public String getUserName() {
        return UserName;
    }

    public String getPassword() {
        return Password;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getID() {
        return ID;
    }

    public static int Log_in(String username, String password){//id user
        int CurrantUserid;
        String EntryUserName=username.toLowerCase().trim() ;
        String EntryPassword=password;

        Optional<User> optionalUser = AdoptionPetApp.users.stream()
                .filter(user -> (user.UserName.equals(EntryUserName)) && user.Password.equals(EntryPassword))
                .findFirst();
        if (optionalUser.isPresent()){
            CurrantUserid = optionalUser.get().getID();
            return CurrantUserid;
        }
        else {
            JOptionPane.showMessageDialog(null,"wrong User name or Password");
            return -1;
        }
    }

    public abstract void delete_account();

    public void logout(Event logout) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdoptionPetApp.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        Stage stage=(Stage)((Node)logout.getSource()).getScene().getWindow();
        stage.setTitle("login");
        stage.setScene(scene);
        AdoptionPetApp.indexActiveUser=-1;
        AdoptionPetApp.ActiveUserid=-1;
    }

}
