import java.sql.*;

public class JDBC {
   private Statement statement;

    public JDBC() throws SQLException {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/social_media_test1", "root", "2581537299");
            Statement statement = connection.createStatement();
            this.statement = statement;
        }
        catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }

}
