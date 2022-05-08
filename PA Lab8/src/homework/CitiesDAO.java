package homework;

import java.beans.PropertyEditorSupport;
import java.sql.*;
import java.util.ArrayList;

public class CitiesDAO {

    public void create(City city){
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(
                    "insert into cities (name, country, continent, latitude, longitude) " +
                            "values (?, ?, ?, ?, ?)");
            pstmt.setString(1, city.name);
            pstmt.setString(2, city.country);
            pstmt.setString(3, city.continent);
            pstmt.setDouble(4, city.latitude);
            pstmt.setDouble(5, city.longitude);
            pstmt.executeUpdate();
            con.commit();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public ArrayList<City> getAllCities(){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(
                    "select * from cities");
            rs = pstmt.executeQuery();

            ArrayList<City> cities = new ArrayList<City>();

            while(rs.next()){
                City resultCity = new City();

                resultCity.id = rs.getInt(1);
                resultCity.name = rs.getString(2);
                resultCity.country = rs.getString(3);
                resultCity.continent = rs.getString(4);
                resultCity.latitude = rs.getDouble(5);
                resultCity.longitude = rs.getDouble(6);

                cities.add(resultCity);
            }

            return cities;

        }
        catch(SQLException e){
            e.printStackTrace();
            return null;
        }
        finally{
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }
    }

    public City findByName(String name){
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = DBConnectionPool.getConnection();
            pstmt = con.prepareStatement(
                    "select * from cities where name=?");
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();

            if (rs.next()){
                City resultCity = new City();

                resultCity.id = rs.getInt(1);
                resultCity.name = rs.getString(2);
                resultCity.country = rs.getString(3);
                resultCity.continent = rs.getString(4);
                resultCity.latitude = rs.getDouble(5);
                resultCity.longitude = rs.getDouble(6);

                return resultCity;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return null;
    }

    public String findById(int id){
        Connection con = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            con = DBConnectionPool.getConnection();
            stmt = con.createStatement();
            rs = stmt.executeQuery(
                    "select name from cities where id=" + id);
            return rs.next() ? rs.getString(1) : null;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally{
            try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { stmt.close(); } catch (SQLException e) { e.printStackTrace(); }
            try { con.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return null;
    }
}
