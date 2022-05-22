package models;

import enums.RegisterWith;

public class BusinessUser extends User {

    private String lunchDay = null;
    public BusinessUser(String username, String userID, String password, String lunchDay, String mail, RegisterWith registerWith) {

        super(username, userID, password, mail, registerWith);
        this.lunchDay = lunchDay;
    }
}