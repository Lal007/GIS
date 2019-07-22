import java.sql.*;
import java.util.ArrayList;

public class DBService {

    private static Connection connection;
    private static Statement stmt;

    public void connect(){

        connection = null;

        String url = "jdbc:sqlite:src\\main\\resources\\DBRepo.db";

        try {
            connection = DriverManager.getConnection(url);

            stmt = connection.createStatement();

            System.out.println("Connected to DB");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection(){
        if (connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public ArrayList<String> getAccount(String user) {
        ArrayList<String> result = new ArrayList<String>();
        String sql = String.format("SELECT * FROM accaunts where user = '%s';", user);

        try {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()){
                for (int i = 1; i < 6; i++) {
                    result.add(rs.getString(i));
                }
                return result;
            }else return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public int changeField(String user, String newSurname) {
        String sql = String.format("UPDATE accaunts set owner_surname = '%s' where user = '%s';", newSurname, user);

        int result = 0;
        try {
            result = stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;

    }
}
