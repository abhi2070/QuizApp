package com.MyProject.QuizApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class QuizSubmitRequestDTO {
    private Long quizId;
    private List<AnswerDTO> answers;
}
