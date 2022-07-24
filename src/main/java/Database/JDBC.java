package Database;
import enums.Type;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class JDBC
{
   private Statement statement;
   private Connection connection;
   private static JDBC instance = null;
    private static void setInstance(JDBC jdbc)
    {
        JDBC.instance = jdbc;
    }
    public static JDBC getInstance(){
        if (JDBC.instance == null)
            setInstance(new JDBC());
        return JDBC.instance;
    }
    private JDBC() {
        try {
           connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_test1", "root", "2581537299");
            Statement statement = connection.createStatement();
            System.out.println("connection");
            this.statement = statement;
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
    public void addNewUserToMySql(String first_name, String last_name, String birthday, String email, String phone_number, String join_date, String user_password, String username, String gender
            , String forget_passQ, String  forget_passA, Type type) throws SQLException {
        //System.out.println(user_password);
        String sql = " insert into user_profile (first_name, last_name, username, birthday, email, phone_number, join_date, gender,user_password, user_type)" + " values (?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";
        String user_type;
        if (type == Type.SIMPLE) user_type = "S";
        else user_type = "B";
        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString (1, first_name);
        preparedStmt.setString (2, last_name);
        preparedStmt.setString (3, username);
        preparedStmt.setString (4, birthday);
        preparedStmt.setString (5, email);
        preparedStmt.setString (6, phone_number);
        preparedStmt.setString (7, join_date);
        preparedStmt.setString (8, gender);
        preparedStmt.setString (9, user_password);
        preparedStmt.setString (10, user_type);
        preparedStmt.execute();
    }
    public ResultSet findUserNameFromDatabase(String username){
        ResultSet resultSet = null;
        try
        {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_profile WHERE username=? ");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
        }
        catch (Exception e)
        {
            System.out.println("error while executing...");
            System.out.println(e);
        }
       return resultSet;
    }
    public ResultSet findUserPasswordFromDatabase(String username){
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT user_password FROM user_profile WHERE username=?  ;");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
        }
        catch (Exception e){
            System.out.println("error while executing...");
            System.out.println(e);
        }
        return resultSet;
    }

    public void addNewGroupToSDL(String groupName, String groupID, String admin_id) throws SQLException {
        String sql = " insert into group_profile (group_name, group_id, created_time, admin_id)" + " values (?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        String datetime = LocalDate.now().toString() + "T"+ LocalTime.now().toString();
        System.out.println(datetime);
        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        preparedStmt.setString (1, groupName);
        preparedStmt.setString (2, groupID);
        preparedStmt.setString (3, datetime);
        preparedStmt.setString (4, admin_id);
        preparedStmt.execute();
    }

    public ResultSet findAllAdminGroups(String user_id) {
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT group_name, group_id FROM group_profile WHERE  admin_id = ?;");
            preparedStatement.setString(1, user_id);
            resultSet = preparedStatement.executeQuery();
        }
        catch (Exception e){
            System.out.println("error while executing...");
            System.out.println(e);
        }
        return resultSet;
    }
   public ResultSet findGroupsFromDataBase(String group_id, String admin_id){
        ResultSet resultSet = null;
       try {
           PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM group_profile WHERE  admin_id = ? AND group_id = ?;");
           preparedStatement.setString(1, admin_id);
           preparedStatement.setString(2, group_id);
           resultSet = preparedStatement.executeQuery();
       }
       catch (Exception e){
           System.out.println("error while executing...");
           System.out.println(e);
       }
       return resultSet;
   }
    public ResultSet fillAllSimpleGroups() {
        return null;
    }
    public void addGroupMember(String group_id, String user_id) {
        String sql = " insert into group_membership (user_id, group_id, joined_date)" + " values (?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        String datetime = LocalDate.now().toString() + "T"+ LocalTime.now().toString();
        System.out.println(datetime);
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString (1, user_id);
            preparedStmt.setString (2, group_id);
            preparedStmt.setString (3, datetime);
            preparedStmt.execute();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
    public void addGroupPost(String group_id, String user_id, String address, String post_type, String forwarded_from, String replied_to){
        String sql = " insert into group_post (posted_user_id, group_id, created_datetime, media_location, post_type)" + " values (?, ?, ?, ?, ?)";
        LocalDateTime now = LocalDateTime.now();
        String datetime = LocalDate.now().toString() + "T"+ LocalTime.now().toString();
        System.out.println(datetime);
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString (1, user_id);
            preparedStmt.setString (2, group_id);
            preparedStmt.setString (3, datetime);
            preparedStmt.setString (4, address);
            preparedStmt.setString (5, post_type);
            preparedStmt.execute();
            System.out.println("here");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    public String getNumberOfTextMessage() {
        String sql = "SELECT COUNT(*) AS num " +
                "FROM group_post " +
                "WHERE post_type =?;";
        ResultSet resultSet = null;
        try {
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
            preparedStmt.setString (1, "T");
            resultSet = preparedStmt.executeQuery();
        }
       catch (Exception e){
           System.out.println("error in getNumber of textMessages");
           System.out.println(e.getStackTrace());
       }

        try {
            while (resultSet.next()){
                System.out.println(resultSet.getInt("num"));
                return String.valueOf(resultSet.getInt("num"));
            }
        }
        catch (Exception e){
            System.out.println(e);
            System.out.println(e.getStackTrace());
        }
       return "0";
            }
}