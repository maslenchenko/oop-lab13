package task2;

import netscape.javascript.JSObject;
import org.json.JSONObject;

import java.awt.*;
import java.sql.*;

public class Database{

    private static Connection connection = null;
    private static Database db = null;

    public Database() {
        connect();
    }

    private static void connect(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\User\\Desktop\\2-year-ucu\\oop\\lab-13\\websitesdb.db");
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");
    }

    public static Database getConnection(){
        if (db == null) {
            db = new Database();
            return db;
        }
        return db;
    }

    public void add(String domain, JSONObject jsonObject){
        try {
            String sql = "INSERT INTO websites(domain, parsed) VALUES (?, ?);";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, domain);
            pstmt.setString(2, jsonObject.toString());
            pstmt.executeUpdate();
            System.out.println("website added to websitesdb successfully");
            pstmt.close();
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public String get(String domain) {
        try {
            String sql = "SELECT parsed FROM websites WHERE domain = ?;";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, domain);
            ResultSet rs = pstmt.executeQuery();
            try {
                String result = rs.getString("parsed");
                rs.close();
                pstmt.close();
                return result;
            }
            catch (SQLException e) {
                rs.close();
                pstmt.close();
                return "not found";
            }
        }
        catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return "1";
    }
}