package com.MyProject.QuizApp.DTO;

import com.MyProject.QuizApp.Model.Question;
import com.MyProject.QuizApp.Model.Quiz;

import java.util.List;
import java.util.stream.Collectors;

public class QuizMapper {

    public static QuizResponseDTO toQuizResponseDTO(Quiz quiz) {

        QuizResponseDTO dto = new QuizResponseDTO();
        dto.setQuizId(quiz.getId());

        List<QuestionResponseDTO> questionDTOs = quiz.getQuestion()
                .stream()
                .map(QuizMapper::toQuestionResponseDTO)
                .collect(Collectors.toList());

        dto.setQuestions(questionDTOs);
        return dto;
    }

    private static QuestionResponseDTO toQuestionResponseDTO(Question question) {

        QuestionResponseDTO dto = new QuestionResponseDTO();
        dto.setQuestionId(question.getId());
        dto.setActualQuestion(question.getActualQuestion());

        dto.setOptions(List.of(
                question.getOption1(),
                question.getOption2(),
                question.getOption3(),
                question.getOption4()
        ));

        return dto;
    }
}
