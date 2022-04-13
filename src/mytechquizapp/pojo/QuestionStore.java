/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytechquizapp.pojo;

import java.util.ArrayList;

/**
 *
 * @author HARSHIT
 */
public class QuestionStore {
    private ArrayList<Question> questionList;
    
    public QuestionStore(){
        questionList = new ArrayList<>();
    }
    
    public void addQuestion(Question q){
        questionList.add(q);
    }
    
    public Question getQuestion(int index){
        return questionList.get(index);
    }
    
    public void removeQuestion(int index){
        questionList.remove(index);
    }
    
    public void setQuestionAt(int pos, Question q){
        questionList.add(pos, q);
    }
    
    public ArrayList<Question> getAllQuestions(){
        return questionList;
    }
    
    public int getCount(){
        return questionList.size();
    }
}
