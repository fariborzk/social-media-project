import views.WelcomeMenu;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) throws ParseException {
        WelcomeMenu welcomeMenu = WelcomeMenu.getInstance();
        welcomeMenu.run();
    }
}
