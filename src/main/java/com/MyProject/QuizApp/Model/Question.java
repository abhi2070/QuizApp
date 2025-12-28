package com.MyProject.QuizApp.Model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "quiz_question")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String option1;
    private String option2;
    private String option3;
    private String option4;
    private String ans;
    private String topic;
    @Column(name = "actual_question")
    private String actualQuestion;

}
