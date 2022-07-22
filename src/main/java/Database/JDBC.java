package Database;

import controller.WelcomeController;
import enums.Type;

import javax.xml.crypto.Data;
import java.sql.*;

public class JDBC {
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
        String sql = " insert into user_profile (first_name, last_name, username, birthday, email, phone_number, join_date, gender, user_type)"
                + " values (?, ?, ?, ?, ?, ? , ?, ?, ?, ?)";
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
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM user_profile WHERE username=? ");
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
        }
        catch (Exception e){
            System.out.println("error while executing...");
            System.out.println(e);
        }

       return resultSet;
    }
}
