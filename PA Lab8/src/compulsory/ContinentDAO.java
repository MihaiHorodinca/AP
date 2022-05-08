package compulsory;
import java.sql.*;

public class ContinentDAO {
    private Connection con = Database.getConnection();

    public void create(String name){
        try {
            PreparedStatement pstmt = con.prepareStatement(
                    "insert into continents (name) values (?)");
            pstmt.setString(1, name);
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
                     "select id from continents where name='" + name + "'")) {
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
                     "select name from continents where id=" + id)) {
            return rs.next() ? rs.getString(1) : null;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
