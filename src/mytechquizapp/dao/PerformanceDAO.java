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
import java.sql.Statement;
import java.util.ArrayList;
import mytechquizapp.dbutil.DBConnection;
import mytechquizapp.pojo.Performance;
import mytechquizapp.pojo.StudentScore;

/**
 *
 * @author HARSHIT
 */
public class PerformanceDAO {
    
    public static ArrayList<String> getAllStudentId() throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ArrayList<String> userIdList = new ArrayList<>();
        ResultSet rs = stmt.executeQuery("SELECT DISTINCT(userid) FROM performance");
        while(rs.next()){
            userIdList.add(rs.getString("userid"));
        }
        return userIdList;
    }
    
    public static ArrayList<String> getAllExamId(String studentId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT examid FROM performance WHERE userid=?");
        ps.setString(1, studentId);
        ArrayList<String> examIdList = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            examIdList.add(rs.getString("examid"));
        }
        return examIdList;
    }
    
    public static StudentScore getScore(String studentId, String examId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT per, language FROM performance WHERE userid=? AND examid=?");
        ps.setString(1, studentId);
        ps.setString(2, examId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        StudentScore score = new StudentScore();
        score.setPer(rs.getDouble("per"));
        score.setLanguage(rs.getString("language"));
        return score;        
    }
    
    public static ArrayList<Performance> getAllData() throws SQLException{
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM performance");
        ArrayList<Performance> scoreList = new ArrayList<>();
        while(rs.next()){
            String studentId = rs.getString("userid");
            String examId = rs.getString("examId");
            int right = rs.getInt("right");
            int wrong = rs.getInt("wrong");
            int unattempted = rs.getInt("unattempted");
            double per = rs.getDouble("per");
            String language = rs.getString("language");
            Performance obj = new Performance(studentId, examId, right, wrong, unattempted, per, language);
            scoreList.add(obj);
        }
        return scoreList;
    }
}
