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
import java.util.ArrayList;
import mytechquizapp.dbutil.DBConnection;
import mytechquizapp.pojo.Question;
import mytechquizapp.pojo.QuestionStore;

/**
 *
 * @author HARSHIT
 */
public class QuestionDAO {
    
    public static void addQuestions(QuestionStore qs) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO questions VALUES(?,?,?,?,?,?,?,?,?)");       
        ArrayList<Question> questionList = qs.getAllQuestions();
        for(Question obj : questionList){
            ps.setString(1, obj.getExamId());
            ps.setInt(2, obj.getQno());
            ps.setString(3, obj.getQuestion());
            ps.setString(4, obj.getAnswer1());
            ps.setString(5, obj.getAnswer2());
            ps.setString(6, obj.getAnswer3());
            ps.setString(7, obj.getAnswer4());
            ps.setString(8, obj.getCorrectAnswer());
            ps.setString(9, obj.getLanguage());
            ps.executeUpdate();
        }
    }
    
    public static ArrayList<Question> getQuestionsByExamId(String examId) throws SQLException{
        Connection conn= DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT * FROM questions WHERE examid=? ORDER BY qno");
        ps.setString(1, examId);
        ResultSet rs = ps.executeQuery();
        ArrayList<Question> questionList = new ArrayList<>();
        while(rs.next()){
            Question obj = new Question();
            obj.setExamId(rs.getString("examid"));
            obj.setQno(rs.getInt("qno"));
            obj.setQuestion(rs.getString("question"));
            obj.setAnswer1(rs.getString("answer1"));
            obj.setAnswer2(rs.getString("answer2"));
            obj.setAnswer3(rs.getString("answer3"));
            obj.setAnswer4(rs.getString("answer4"));
            obj.setCorrectAnswer(rs.getString("correct_answer"));
            obj.setLanguage(rs.getString("language"));
            
            questionList.add(obj);
        }
        return questionList;
    }
    
    public static void updateQuestions(QuestionStore qs) throws SQLException{
        Connection conn = DBConnection.getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE questions SET question=?, answer1=?, answer2=?, answer3=?, answer4=?, correct_answer=? WHERE examid=? AND qno=?");
        ArrayList<Question> questionList = qs.getAllQuestions();
        for(Question obj : questionList){
            ps.setString(7, obj.getExamId());
            ps.setInt(8, obj.getQno());
            ps.setString(1, obj.getQuestion());
            ps.setString(2, obj.getAnswer1());
            ps.setString(3, obj.getAnswer2());
            ps.setString(4, obj.getAnswer3());
            ps.setString(5, obj.getAnswer4());
            ps.setString(6, obj.getCorrectAnswer());
            ps.executeUpdate();
        }       
    }
}