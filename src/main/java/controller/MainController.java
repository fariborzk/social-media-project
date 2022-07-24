package controller;

import Database.JDBC;
import enums.Messages;
import views.GroupView;

import java.sql.ResultSet;

public class MainController extends Controller {
    private static MainController mainController = null;
    private JDBC jdbc = JDBC.getInstance();
    private MainController(){

    }
    private static void setMainController(MainController mainController){
        MainController.mainController = mainController;
    }
    public static MainController getInstance(){
        if (MainController.mainController == null)
            setMainController(new MainController());
        return MainController.mainController;
    }
   public Messages handleCreateGroup(String groupName, String groupID, String user_id){
       if (!checkGroupIDFormat(groupID))
           return Messages.INVALID_GROUP_ID;
       if (doesGroupIDExists(groupID))
           return Messages.GROUP_ID_EXISTS;
       try {
           jdbc.addNewGroupToSDL(groupName, groupID, user_id);
           return Messages.SUCCESS;
       }

       catch (Exception e){
           System.out.println(e);
       }
       return Messages.SQL_EXCEPTION;
    }
    private boolean doesGroupIDExists(String groupID){
        return false;
    }
    private boolean checkGroupIDFormat(String groupID){
        return groupID.matches("@.+");
    }
    public  Messages handleGroup(String group_id,String adminID){
        ResultSet resultSet = jdbc.findGroupsFromDataBase(group_id,adminID);
        try {
            while (resultSet.next()){
                if (resultSet.getString("group_id").equals(group_id))
                {
                    String group_name = resultSet.getString("group_name");
                    String id = resultSet.getString("id");
                    String created_date = resultSet.getString("created_time");
                    new GroupView(group_id, group_name, id, created_date, true, adminID).run();
                    return Messages.SUCCESS;
                }
                else
                    System.out.println(Messages.GROUP_ID_DO_NOT_EXIST);
            }
        }
        catch (Exception e){
            System.out.println(e +"\n" + e.fillInStackTrace());
        }
        return null;
    }
}
