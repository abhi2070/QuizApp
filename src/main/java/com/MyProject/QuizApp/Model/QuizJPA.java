package com.MyProject.QuizApp.Model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizJPA extends JpaRepository<Quiz, Long> {
}
