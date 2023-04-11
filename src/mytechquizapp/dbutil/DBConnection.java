/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytechquizapp.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author HARSHIT
 */
public class DBConnection {
    
    private static Connection conn;
    
    static{        
        try{
            Class.forName("oracle.jdbc.OracleDriver");			
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","project","java");
            JOptionPane.showMessageDialog(null, "Connected Successfully!!", "Success", JOptionPane.INFORMATION_MESSAGE);
        } catch(ClassNotFoundException | SQLException ex){
            System.out.println("Error : "+ex);
            JOptionPane.showMessageDialog(null, "Error : "+ex, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static Connection getConnection(){
        return conn;
    }
    
    public static void closeConnection(){
        if(conn!=null){
            try {
                conn.close();
                JOptionPane.showMessageDialog(null, "Connection closed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error in closing the connection!! "+ex, "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }    
}
