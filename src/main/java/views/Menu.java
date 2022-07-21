package views;

import models.User;

import java.text.ParseException;
import java.util.Scanner;

public abstract class Menu {
    private static final Scanner scanner = new Scanner(System.in);
    private static User LoggedInUser = null;
    public static User getLoggedInUser() {
        return LoggedInUser;
    }
    protected static Scanner getScanner() {
        return scanner;
    }
    public static void setLoggedInUser(User user) { LoggedInUser = user; }
    public String getInput(String Input) {
        System.out.println(Input);
        return Menu.getScanner().nextLine().trim();
    }
    public String getChoice() {
        return Menu.getScanner().nextLine().trim();
    }
    public  abstract void run() throws ParseException;
    protected abstract void showOptions();
}
