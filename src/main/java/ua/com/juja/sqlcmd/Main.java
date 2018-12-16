package ua.com.juja.sqlcmd;

import java.sql.*;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sqlcmd", "root", "root");

        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO user (name, password)" +
                "VALUES ('Stiven', 'Pupkin')");
        stmt.close();

        //select
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM user WHERE id > 10");
        while (rs.next()){
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("______");

        }
        rs.close();
        stmt.close();

        //table names
        stmt = connection.createStatement();
        rs = stmt.executeQuery(" SELECT table_name FROM information_schema.tables WHERE table_schema = 'sqlcmd'");
        while (rs.next()) {
            System.out.println(rs.getString("table_name"));
        }

        rs.close();
        stmt.close();

        //delete
        stmt = connection.createStatement();
        String delete = "DELETE FROM user " +
                "WHERE id > 1 AND id < 100";
        stmt.close();

        //update
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE user SET password = ? WHERE id > 22");
        int randomInt =  new Random().nextInt();
        String pass = String.valueOf(randomInt);
        ps.setString(1,pass);
        ps.executeUpdate();
        stmt.close();

        connection.close();
    }
}
