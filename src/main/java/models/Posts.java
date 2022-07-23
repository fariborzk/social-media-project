package models;

public class Posts {
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
}
