package com.MyProject.QuizApp.Service;

import com.MyProject.QuizApp.DTO.AnswerDTO;
import com.MyProject.QuizApp.DTO.QuizResultDTO;
import com.MyProject.QuizApp.DTO.QuizSubmitRequestDTO;
import com.MyProject.QuizApp.Model.Question;
import com.MyProject.QuizApp.Model.QuestionJPA;
import com.MyProject.QuizApp.Model.Quiz;
import com.MyProject.QuizApp.Model.QuizJPA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {

    @Autowired
    QuizJPA quizJPA;

    @Autowired
    QuestionJPA questionJPA;


    public Quiz createQuestionsForQuiz(String topic, int num) {

        if (!questionJPA.existsByTopic(topic)){
            throw new IllegalArgumentException("TOPIC_NOT_FOUND");
        }
        List<Question> question = questionJPA.getQuestionsByTopic(topic, num);
        Quiz quiz = new Quiz();
        quiz.setTopic(topic);
        quiz.setQuestion(question);

        return quizJPA.save(quiz);
    }

    public QuizResultDTO evaluateQuiz(QuizSubmitRequestDTO request) {
        Quiz quiz = quizJPA.findById(request.getQuizId())
                .orElseThrow(() -> new IllegalArgumentException("Quiz not found"));

        Map<Long, String> correctAnswerMap = new HashMap<>();
        for (Question question : quiz.getQuestion()) {
            correctAnswerMap.put(question.getId(), question.getAns());
        }

        int correctCount = 0;

        for (AnswerDTO answer : request.getAnswers()) {
            String correctAns = correctAnswerMap.get(answer.getQuestionId());

            if (correctAns != null &&
                    correctAns.equals(answer.getSelectedOption())) {
                correctCount++;
            }
        }

        QuizResultDTO result = new QuizResultDTO();
        result.setTotalQuestions(quiz.getQuestion().size());
        result.setCorrectAnswers(correctCount);
        result.setWrongAnswers(
                quiz.getQuestion().size() - correctCount
        );

        return result;
    }

    public void deleteQuiz() {
        quizJPA.deleteAll();
    }

}
