package controller;

import Database.JDBC;
import enums.Messages;

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

}
