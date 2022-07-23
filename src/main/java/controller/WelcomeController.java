package controller;
import Database.*;

import enums.Messages;
import enums.RegisterWith;
import enums.Type;
import models.BusinessUser;
import models.SimpleUser;
import models.User;
import views.Menu;
import views.WelcomeMenu;

import javax.print.DocFlavor;
import javax.xml.crypto.Data;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

public class WelcomeController extends Controller {
    private static WelcomeController instance = null;
    private JDBC jdbc = JDBC.getInstance();
    private WelcomeController() {
    }
    private static void setInstance(WelcomeController welcomeController)
    {
        WelcomeController.instance = welcomeController;
    }
    public static WelcomeController getInstance() {
        if (WelcomeController.instance == null)
            setInstance(new WelcomeController());
        return WelcomeController.instance;
    }
    public Messages handleRegister(String first_name, String last_name, String userName, String birthday, String email, String phoneNumber,
                                   String password, String repeatedPassword, String gender, Type type)  {
        if (first_name.isEmpty())
            return Messages.FIRST_NAME_CANT_BE_EMPTY;
        if (last_name.isEmpty())
            return Messages.LAST_NAME_CANT_BE_EMPTY;
        if (userName.isEmpty())
            return Messages.USER_NAME_CANT_BE_EMPTY;
        if (birthday.isEmpty())
            return Messages.BIRTHDAY_CANT_BE_EMPTY;
        if (password.isEmpty())
            return Messages.PASSWORD_CANT_BE_EMPTY;
        if (repeatedPassword.isEmpty())
            return Messages.REPEAT_PASSWORD;
        if (gender.isEmpty())
            return Messages.GENDER_CANT_BE_EMPTY;
        if (this.doesUserNameExist(userName)){
            return Messages.USERID_EXIST;
        }
        if (phoneNumber != null)
        if (!isNumeric(phoneNumber))
            return Messages.INVALID_PHONE_NUMBER;
        Messages message = this.validatePassword(password, repeatedPassword);
        if (message  != Messages.SUCCESS)
            return message;
        if (!gender.equals("F") && !gender.equals("M"))
            return Messages.INVALID_GENDER;
        if (type == null)
            return Messages.INVALID_TYPE;
        try {
            LocalDate now = LocalDate.now();
            JDBC.getInstance().addNewUserToMySql(first_name, last_name,birthday, email, phoneNumber,
                    now.toString(), password,userName,gender, null,null, type);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return Messages.SUCCESS;
    }
    public Messages handleLogin(String userName, String password) {
        if (!doesUserNameExist(userName))
            return Messages.NO_USER_EXIST;
        try {
            ResultSet resultSet = JDBC.getInstance().findUserPasswordFromDatabase(userName);
            while (resultSet.next()){
                if(resultSet.getString("user_password").equals(password)){
                    return Messages.SUCCESS;
                }

                else return Messages.MISMATCH_PASSWORD;
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
        return Messages.WRONG_CREDENTIALS;
    }

    private Messages validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword))
            return Messages.MISMATCH_PASSWORD;
        if (password.length() < 5)
            return Messages.SHORT_PASSWORD;
        if (isNumeric(password))
            return Messages.JUST_NUMBER;
        if (isAlphabetic(password))
       return Messages.JUST_ALPHA;
        return Messages.SUCCESS;
    }
    public boolean isAlphabetic (String password){
        return password.matches("[a-zA-z]+");
    }
    private boolean isNumeric(String str) {
        return str.matches("[0-9]+");
    }
    private boolean doesUserNameExist(String userName) {
        ResultSet resultSet = JDBC.getInstance().findUserNameFromDatabase(userName);
        try {
            if (resultSet.next()) return true;
            else return false;
        }
        catch (Exception e){
            System.out.println(e);
            return false;
        }

    }
}
