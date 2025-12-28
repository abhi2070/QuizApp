package com.MyProject.QuizApp.Service;

import com.MyProject.QuizApp.Model.Question;
import com.MyProject.QuizApp.Model.QuestionJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionJPA questionJPA;

    public List<Question> getQuestions(){
        return questionJPA.findAll();
    }

    public Question getQuestionByID(Long id) {
        return questionJPA.findById(id).orElseThrow(() -> new IllegalArgumentException("ID_NOT_FOUND"));
    }

    public Question addQuestion( Question question) {
            return questionJPA.save(question);
    }

    public Question updateQuestion(long id, Question newQuestion) {
        Question existingQuestion = questionJPA.findById(id).orElseThrow(() -> new RuntimeException("Question not found with id " + id));
        existingQuestion.setCategory(newQuestion.getCategory());
        existingQuestion.setOption1(newQuestion.getOption1());
        existingQuestion.setOption2(newQuestion.getOption2());
        existingQuestion.setOption3(newQuestion.getOption3());
        existingQuestion.setOption4(newQuestion.getOption4());
        existingQuestion.setAns(newQuestion.getAns());
        existingQuestion.setTopic(newQuestion.getTopic());
        existingQuestion.setActualQuestion(newQuestion.getActualQuestion());

        return questionJPA.save(existingQuestion);
    }

    public void deleteQuestion(long id) {
        if (!questionJPA.existsById(id)){
            throw new IllegalArgumentException("ID_NOT_FOUND");
        }
        try {
            questionJPA.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("DELETE_FAILED");
        }
    }
}
