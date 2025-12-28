package com.MyProject.QuizApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class QuizResponseDTO {
    private Long quizId;
    private List<QuestionResponseDTO> questions;
}
