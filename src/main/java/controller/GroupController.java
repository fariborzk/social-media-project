package controller;

import Database.JDBC;
import com.mysql.cj.protocol.Message;
import enums.Messages;

import java.sql.ResultSet;

public class GroupController {
    private static GroupController instance;
    private JDBC jdbc = JDBC.getInstance();
    private static void setInstance(GroupController groupController){
        GroupController.instance = groupController;
    }
    private GroupController(){

    }
    public static GroupController getInstance(){
        if (instance == null)
            setInstance(new GroupController());
        return instance;
    }

    public Messages handleAddNewMember(String username, String group_id) {
        String user_id = null;
        System.out.println("handle " + username + " " + group_id);
        user_id = this.doesUserNameExist(username);
        if (user_id == null)
            return Messages.NO_USER_EXIST;
        jdbc.addGroupMember(group_id, user_id);
        return Messages.SUCCESS;
    }
    private String doesUserNameExist(String username){
        //System.out.println("in does " + username);
        ResultSet resultSet = jdbc.findUserNameFromDatabase(username);
       String id = null;
        try {
            while (resultSet.next()){
                //System.out.println(resultSet.getString("username"));
                if (resultSet.getString("username").equals(username)){
                    //System.out.println(resultSet.getString("username"));
                    return resultSet.getString("id");
                }

            }

        }
        catch (Exception e){
            System.out.println(e);
        }
      return  id;
    }
}
