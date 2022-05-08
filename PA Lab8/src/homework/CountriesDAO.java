package homework;
import java.sql.*;

public class CountriesDAO {
    private Connection con = Database.getConnection();

    public void create(String name, String code, String continent){
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into countries (name, code, continent) values (?)");
            pstmt.setString(1, name);
            pstmt.setString(2, code);
            pstmt.setString(3, continent);
            pstmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Integer findByName(String name){
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select id from countries where name='" + name + "'")) {
            return rs.next() ? rs.getInt(1) : null;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    public String findById(int id){
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(
                     "select name from countries where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
