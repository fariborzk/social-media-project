package views;

import controller.WelcomeController;
import enums.Messages;
import enums.Type;

import java.text.ParseException;

public class LoginMenu extends Menu{
    private static LoginMenu instance = null;
    private WelcomeController welcomeController = null;
    private LoginMenu()
    {
        welcomeController = WelcomeController.getInstance();
    }
    private static void setInstance(LoginMenu instance)
    {
        LoginMenu.instance = instance;
    }
    public static LoginMenu getInstance()
    {
        if (LoginMenu.instance == null)
            LoginMenu.setInstance(new LoginMenu());
        return LoginMenu.instance;
    }
    private void login() throws ParseException {
        String userName = this.getInput("user name");
        String password = this.getInput("password");
        Messages message = this.welcomeController.handleLogin(userName, password);
        if (message == Messages.SUCCESS){
            System.out.println(Messages.SUCCESS);
            new MainMenu(userName).run();
        }
        else
            System.out.println(message);
    }

    @Override
    public void run() throws ParseException {
        showOptions();
        Type type = null;
        String choice = this.getChoice();
        switch (choice){
            case "1":
            case "yes":
                forgetPass();
                break;
            case "2":
            case "no":
                login();
                break;
            default:
                System.out.println(Messages.INVALID_CHOICE);
        }
    }

    private void forgetPass() {

    }

    @Override
    protected void showOptions() {
        System.out.println("Please enter your info to login...");
        System.out.println("Do you Forget Your Pass?");
        System.out.println("1. Yes");
        System.out.println("2. No");

    }
}
