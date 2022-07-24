package models;

import Database.JDBC;

import java.io.File;
import java.io.FileWriter;

public class Posts {
    private JDBC jdbc = JDBC.getInstance();
    String location;
    String createDateTime;
    int posted_user_id;
    int received_user_id;// fill in if it is a direct message;
    int group_id;// fill in if it is a group post
    // if both of received user_id and group_id is null, then it is  a post in your page
    String post_type; // T if text , V if video, I if image;
    int reply_post_id; //fill in if it is a replied_post
    int forwarded_from_user_id;// fill in if it was forwarded
    //you can use User model fields instead of posted_user_id--> username
    //received_user_id --> username  group_id --> group_id   forwarded_from_user_id-->username
    public void writeFile(String text, String group_id, String user_id){
        String fileName = jdbc.getNumberOfTextMessage() + ".txt";
        String fileAddress = "C:\\Users\\tejan system\\Desktop\\social-media-project\\text_messages\\" + fileName;
        File myObj = new File(fileAddress);
        System.out.println("here2");
        try {
            if (myObj.createNewFile()) {
                FileWriter myWriter = new FileWriter(fileAddress);
                myWriter.write(text);
                myWriter.close();
                System.out.println("write_file");
                jdbc.addGroupPost(group_id, user_id, fileAddress,"T",null, null);
            }
        }
        catch (Exception e){
            System.out.println("an error while creating or writing file..");
            System.out.println(e.getStackTrace());
        }
    }
}
