package views;

import com.mysql.cj.protocol.Message;
import controller.GroupController;
import enums.Messages;
import models.Posts;

import java.sql.ResultSet;
import java.text.ParseException;
import java.util.Scanner;


public class GroupView extends Menu{
    String id;
    String group_id;
    String group_name;
    String created_time;
    GroupController groupController = GroupController.getInstance();
    boolean admin;
    String user_id;

    public GroupView(String group_id, String group_name, String id, String created_date, boolean b,String user_id) {
        super();
        this.group_id = group_id;
        this.id = id;
        this.group_name = group_name;
        this.created_time = created_date;
        this.user_id = user_id;
        admin = true;
    }

    @Override
    public void run() throws ParseException {
        showOptions();
        String choice = this.getChoice();
        switch (choice){
            case "1":
                addNewMember();
                break;
            case "3":
                sendMessage();
            default:
                break;
        }
    }

    private void sendMessage() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("write your message : finish with exit");
        String text = "";
        String input = scanner.nextLine();
        while (!input.equals("exit")){
            text += input;
            input = scanner.nextLine();
        }
        new Posts().writeFile(text, id, user_id);
    }

    private void addNewMember() {
        System.out.println("you are adding a new member...");
        String username = this.getInput("user name");
        System.out.println(username);
        Messages message = groupController.handleAddNewMember(username, id);
        System.out.println(message);
    }

    @Override
    protected void showOptions() {
        System.out.println("you are in group with group name " + group_name + " and group id " +group_id);
        if (admin){
            System.out.println("you are the admin");
            System.out.println("1. add new member");
            System.out.println("2. remove a member");
            System.out.println("3. send a new message");
            System.out.println("4. see group messages");
            System.out.println("5. group info");
        }

    }
}
