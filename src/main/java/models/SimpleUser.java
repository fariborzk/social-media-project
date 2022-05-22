package models;

import enums.RegisterWith;

public class SimpleUser extends User{

    private String birthDay = null;
    public SimpleUser(String username, String userID, String password, String birthDay, String mail, RegisterWith registerWith) {
        super(username, userID, password, mail, registerWith);
        this.birthDay = birthDay;
    }

}