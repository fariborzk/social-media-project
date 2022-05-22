package models;

import enums.RegisterWith;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class User {
    private static ArrayList<User> allUsers = new ArrayList<>();
    private static int id = 0;
    private final String creat_At;
    private String mail = null;
    private String username;
    private String password;
    private String userID;
    private RegisterWith registerWith;
    public  User(String username,String userID, String password, String mail, RegisterWith registerWith){
        this.userID = userID;
        this.password = password;
        this.username = username;
        this.registerWith = registerWith;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/mm/dd || HH:mm:ss ");
        if (mail != null)
            this.mail = mail;
        LocalDateTime now = LocalDateTime.now();
        this.creat_At = now.format(formatter);
        allUsers.add(this);
    }
    public static models.User getUserByUserName(String username){
        for (models.User user : allUsers)
            if (user.username.equals(username))
                return user;
        return null;
    }
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        models.User.id = id;
    }

    public static User getUserByUserID(String userID) {
        for (models.User user : allUsers)
            if (user.userID.equals(userID))
                return user;
        return null;
    }

    public String getCreat_At() {
        return creat_At;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public static ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public static void setAllUsers(ArrayList<User> allUsers) {
        User.allUsers = allUsers;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setUserId(String userId) {
        this.userID = userId;
    }

    public RegisterWith getRegisterWith() {
        return registerWith;
    }

    public void setRegisterWith(RegisterWith registerWith) {
        this.registerWith = registerWith;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
}



