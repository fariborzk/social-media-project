package views;

import controller.WelcomeController;
import enums.Messages;
import enums.RegisterWith;
import enums.Type;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegisterMenu extends Menu {
    private static RegisterMenu instance = null;
    private WelcomeController welcomeController = null;
    private RegisterMenu()
    {
        welcomeController = WelcomeController.getInstance();
    }
    private static void setInstance(RegisterMenu instance)
    {
        RegisterMenu.instance = instance;
    }
    public static RegisterMenu getInstance()
    {
        if (RegisterMenu.instance == null)
            RegisterMenu.setInstance(new RegisterMenu());
        return RegisterMenu.instance;
    }
    @Override
    public void run() throws ParseException {
        showOptions();
        Type type = null;
        String choice = this.getChoice();
        switch (choice){
            case "1":
            case "simple":
                type = Type.SIMPLE;
                register(type);
                break;
            case "2":
            case "business":
                type = Type.BUSINESS;
                register(type);
                break;
            case "3":
            case "exit":
                break;
            default:
                Object Message;
                System.out.println(Messages.INVALID_CHOICE);
        }
    }
    private void register(Type type) throws ParseException {
        RegisterWith registerWith;
        registerOptions();
        String choice = this.getChoice().toLowerCase();
        switch (choice){
            case "phonenumber":
            case "1":
                registerWith = RegisterWith.PHONE_NUMBER;
                break;
            case "email":
            case "2":
                registerWith = RegisterWith.EMAIL;
                break;
            default:
                System.out.println(Messages.INVALID_CHOICE);
                return;
        }
        String phoneNumber = null;
        String email = null;
        if (registerWith == RegisterWith.PHONE_NUMBER)
        {
            phoneNumber = this.getInput("enter your phone number");
            registerWithPhoneNumber();
            String choiceOp = this.getChoice();
            if (choiceOp.equals("yes") || choiceOp.equals("1"))
            {
                email =  this.getInput("enter your email");
            }
        }
        else{
            email = this.getInput("enter your  mail");
            registerWithEmail();
            String choiceOp = this.getChoice();
            if (choiceOp.equals("yes") || choiceOp.equals("1"))
            {
                phoneNumber =  this.getInput("enter your phone number");
            }
        }
        System.out.println("Please Enter Your Info To Register...");
        String first_name = this.getInput("first name");
        String last_name = this.getInput("last name");
        String birthday = this.getInput("birthday (YYYY-dd-mm)");
        String userName = this.getInput("User Name");
        String password = this.getInput("Password");
        String gender = this.getInput("gender(F/M)");
        String repeatedPassword = this.getInput("Please Repeat Your password");
        Messages message = this.welcomeController.handleRegister(first_name,last_name, userName, birthday, email, phoneNumber,password,repeatedPassword,gender,type);
        if (message == Messages.SUCCESS){
            System.out.println("you registered successfully");
        }
        else{
            System.out.println(message);
        }
        WelcomeMenu.getInstance().run();

    }
    private void registerOptions(){
        System.out.println("Do you want to register with Email  or phone number?");
        System.out.println("1. phone number");
        System.out.println("2. email");

    }
    private void registerWithEmail() {
        System.out.println("Do you want to add your Phone number? *optional");
        System.out.println("1. yes");
        System.out.println("2. no");

    }

    private void registerWithPhoneNumber() {
        System.out.println("Do you want to add your email? *optional");
        System.out.println("1. yes");
        System.out.println("2. no");
    }

    @Override
    protected void showOptions() {
        System.out.println("Enter UserType (BUSINESS/SIMPLE)");
        System.out.println("1. simple");
        System.out.println("2. business");
        System.out.println("3. exit");
    }
}
