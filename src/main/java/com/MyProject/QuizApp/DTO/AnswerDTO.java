package com.MyProject.QuizApp.DTO;

import lombok.Data;

@Data
public class AnswerDTO {
    private Long questionId;
    private String selectedOption;
}
