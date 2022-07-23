package views;

import Database.JDBC;
import controller.MainController;
import enums.Messages;
import enums.Type;

import java.sql.ResultSet;
import java.text.ParseException;

public class MainMenu extends Menu{

    String userName;
    String user_id;
    ResultSet resultSet;
    private MainController mainController = MainController.getInstance();
    private JDBC jdbc = JDBC.getInstance();
    public MainMenu(String userName){
        try {
           ResultSet resultSet  = jdbc.findUserNameFromDatabase(userName);
           this.resultSet = resultSet;
            while (resultSet.next()){
             user_id = resultSet.getString("id");
             this.userName = resultSet.getString("username");
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    @Override
    public void run() throws ParseException {
        showOptions();
        String choice = this.getChoice().toLowerCase();
        switch (choice){
            case "1":
            case "timeline":
                break;
            case "2":
            case "groups":
                break;
            case "3":
            case "new Group":
                createNewGroup();
                break;
            case "4":
            case "direct message":
                break;
            case "5":
            case "exit":
                break;
            default:
                Object Message;
                System.out.println(Messages.INVALID_CHOICE);
        }
    }

    private void createNewGroup() {
        String groupName = this.getInput("group name > ");
        String groupID = this.getInput("groupID (@groupID) >");

        System.out.println(mainController.handleCreateGroup(groupName, groupID, user_id));

    }

    @Override
    protected void showOptions() {
        System.out.println(userName + " " + user_id);
        System.out.println(" you login as " + userName);
        System.out.println("chose one of options : ");
        System.out.println("1. Timeline");
        System.out.println("2. Groups");
        System.out.println("3. new Group");
        System.out.println("4. direct message");
        System.out.println("5. exit");
    }
}
