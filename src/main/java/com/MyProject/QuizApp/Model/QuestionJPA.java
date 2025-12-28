package com.MyProject.QuizApp.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionJPA extends JpaRepository<Question, Long> {
    @Query(value = "SELECT * FROM quiz_question q WHERE  q.topic=:topic ORDER BY RAND() LIMIT :num", nativeQuery = true)
    List<Question> getQuestionsByTopic(String topic, int num);

    boolean existsByTopic(String topic);
}
