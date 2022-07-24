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
                showAllGroups();
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

    private void showAllGroups() {
        ResultSet simpleResultSet = jdbc.fillAllSimpleGroups();
        System.out.println("1. groups that you are admin");
        System.out.println("2. other groups");
        String choice = this.getChoice();
        switch (choice){
            case "1":
              groups(true);
                break;
            case "2":
              groups(false);
                break;
            default:
                System.out.println(Messages.INVALID_CHOICE);
                break;
        }

    }

    public void  groups(boolean admin){
        ResultSet resultSet;
        if (admin)
        resultSet = jdbc.findAllAdminGroups(user_id);
        else
            resultSet = jdbc.findAllJoinedGroups(user_id);
        try {
            while (resultSet.next()) {
                String group_name = resultSet.getString("group_name");
                String group_id = resultSet.getString("group_id");
                System.out.println("group_name : " + group_name + " group_id : " + group_id);
            }
            String choice = this.getInput("enter group_id :");
            Messages messages = mainController.handleGroup(choice, user_id, admin);
            System.out.println(messages);
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    private void createNewGroup() throws ParseException {
        String groupName = this.getInput("group name > ");
        String groupID = this.getInput("groupID (@groupID) >");
        System.out.println(mainController.handleCreateGroup(groupName, groupID, user_id));
        run();
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
