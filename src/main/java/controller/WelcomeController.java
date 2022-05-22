package controller;

import enums.Messages;
import enums.RegisterWith;
import enums.Type;
import models.BusinessUser;
import models.SimpleUser;
import models.User;
import views.Menu;
import views.WelcomeMenu;

public class WelcomeController extends Controller {
    private static WelcomeController instance = null;
    private WelcomeController(){

    }
    private static void setInstance(WelcomeController welcomeController)
    {
        WelcomeController.instance = welcomeController;
    }
    public static WelcomeController getInstance(){
        if (WelcomeController.instance == null)
            setInstance(new WelcomeController());
        return WelcomeController.instance;
    }
    public Messages handleRegister(String userName, String userID, String password, String repeatedPassword, Type type, String birthday, String gmail, RegisterWith registerWith) {
        if (this.doesUserIDExist(userName)){
            return Messages.USERID_EXIST;
        }
        Messages message = this.validatePassword(password, repeatedPassword);
        if (message  != Messages.SUCCESS)
            return message;
        if (type == null)
            return Messages.INVALID_TYPE;
        if (type == Type.SIMPLE)
            new SimpleUser(userName, userID, password,null, gmail,registerWith );
        else if (type == Type.BUSINESS)
            new BusinessUser(userName, userID, password,null, gmail, registerWith);
        return Messages.SUCCESS;
    }
    public Messages handleLogin(String userName, String password) {
        User user = User.getUserByUserName(userName);
        if (user != null && user.getPassword().equals(password)) {
            Menu.setLoggedInUser(user);
            return Messages.SUCCESS;
        }
        return Messages.WRONG_CREDENTIALS;
    }

    private Messages validatePassword(String password, String repeatedPassword) {
        if (!password.equals(repeatedPassword))
            return Messages.MISMATCH_PASSWORD;
        if (password.length() < 5)
            return Messages.SHORT_PASSWORD;
        if (password.length() > 10)
            return Messages.LONG_PASSWORD;
        if (!isNumeric(password))
            return Messages.JUST_NUMBER;
        //if (isAlphabetic(password))
        //return Messages.JUST_ALPHA;
        return Messages.SUCCESS;
    }
    public boolean isAlphabetic (String password){
        return password.matches("[a-zA-z]");
    }
    private boolean isNumeric(String password) {
        return !password.matches("[a-zA-z]");
    }
    private boolean doesUserIDExist(String userName) {
        return User.getUserByUserID(userName) != null;
    }
}
