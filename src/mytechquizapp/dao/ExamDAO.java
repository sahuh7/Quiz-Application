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
import mytechquizapp.pojo.Exam;

/**
 *
 * @author HARSHIT
 */
public class ExamDAO {
    
    public static String getExamId() throws SQLException{
        int rowCount=0;
        Connection conn = DBConnection.getConnection();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS totalrows FROM exam");
        if(rs.next())
            rowCount=rs.getInt("totalrows");
        String newId = "EX-"+(rowCount+1);
        return newId;
    }
    
    public static void addExam(Exam newExam) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO exam VALUES(?,?,?)");
        ps.setString(1, newExam.getExamId());
        ps.setString(2, newExam.getLanguage());
        ps.setInt(3, newExam.getTotalQuestions());
        ps.executeUpdate();
    }
    
    public static ArrayList<String> getExamIdBySubject(String subject) throws SQLException{
        ArrayList<String> exIdList= new ArrayList<>();
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT examid FROM exam WHERE language=?");
        ps.setString(1, subject);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            exIdList.add(rs.getString("examid"));
        }
        return exIdList;
    }
    
    public static int getQuestionCountByExamId(String examId) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT total_question FROM exam WHERE examid=?");
        ps.setString(1, examId);
        ResultSet rs = ps.executeQuery();
        rs.next();
        return rs.getInt("total_question");
    }
}
