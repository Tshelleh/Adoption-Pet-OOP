package petapp.group.adoptionpet.petapp.GeneralFunctions;

import petapp.group.adoptionpet.petapp.accountManagement.User;

import javax.swing.*;

public class notification {

  private int notifiId;
  private int sendTOId; //for admin id is shelter id
  private String username;
  private boolean isOpened;

  public notification(int notifiid,int userid,String username,boolean isopened){
      notifiId=notifiid;
      sendTOId=userid;
      isOpened=isopened;
      this.username=username;
  }
  public void sendadminnotif(){
      JOptionPane.showMessageDialog(null,"Adoption request has been sent by "+this.username+"\n See adoption request");
  }
    public void sendadopternotif(){
        JOptionPane.showMessageDialog(null,"Adoption request responded to.\n See adoption history ");
    }

    public int getNotifiId() {
        return notifiId;
    }


    public int getUserId() {
        return sendTOId;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void setOpened(boolean opened) {
        isOpened = opened;
    }

    @Override
    public String toString() {
        return "notification: " +
                notifiId +
                ";" + sendTOId +
                ";"+username +
                ";" + isOpened;
    }
}
