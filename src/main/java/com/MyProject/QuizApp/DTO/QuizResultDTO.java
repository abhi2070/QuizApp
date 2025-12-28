package com.MyProject.QuizApp.DTO;

import lombok.Data;

@Data
public class QuizResultDTO {
    private int totalQuestions;
    private int correctAnswers;
    private int wrongAnswers;
}
