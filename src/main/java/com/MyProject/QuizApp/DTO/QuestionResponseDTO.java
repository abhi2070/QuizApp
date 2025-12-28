package com.MyProject.QuizApp.DTO;

import lombok.Data;

import java.util.List;

@Data
public class QuestionResponseDTO {
    private Long questionId;
    private String actualQuestion;
    private List<String> options;
}
