/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytechquizapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import mytechquizapp.dbutil.DBConnection;
import mytechquizapp.pojo.Users;

/**
 *
 * @author HARSHIT
 */
public class UsersDAO {
    
    public static boolean verifyCredentials(Users user) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM users WHERE userid=? AND password=? AND usertype=?");
        ps.setString(1, user.getUserID());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUserType());
        ResultSet rs = ps.executeQuery();
        return rs.next();
    }
    
    public static boolean addUser(Users user) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT 1 FROM users WHERE userid=?");
        ps.setString(1, user.getUserID());
        ResultSet rs = ps.executeQuery();
        if(rs.next())
            return false;
        else{
            ps = conn.prepareStatement("INSERT INTO users VALUES(?,?,?)");
            ps.setString(1, user.getUserID());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getUserType());
            int ans = ps.executeUpdate();
            return ans!=0;
        }
    }
    
    public static boolean updatePassword(String userId, String newPassword) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE users SET password=? WHERE userid=?");
        ps.setString(1, newPassword);
        ps.setString(2, userId);
        int ans = ps.executeUpdate();
        return ans!=0;
    }
}
