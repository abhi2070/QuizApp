package com.MyProject.QuizApp.Controller;

import com.MyProject.QuizApp.DTO.QuizMapper;
import com.MyProject.QuizApp.DTO.QuizResponseDTO;
import com.MyProject.QuizApp.DTO.QuizResultDTO;
import com.MyProject.QuizApp.DTO.QuizSubmitRequestDTO;
import com.MyProject.QuizApp.Model.Quiz;
import com.MyProject.QuizApp.Service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/createQuiz")
    public ResponseEntity<QuizResponseDTO> createQuestionsForQuiz(@RequestParam String topic, @RequestParam int num){
        try {
            Quiz quiz= quizService.createQuestionsForQuiz(topic, num);
            return ResponseEntity.status(HttpStatus.CREATED).body(QuizMapper.toQuizResponseDTO(quiz));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/submit")
    public ResponseEntity<QuizResultDTO> submitQuiz(@RequestBody QuizSubmitRequestDTO request){
        try{
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(quizService.evaluateQuiz(request));
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/deleteQuiz")
    public void deleteQuiz(){
        quizService.deleteQuiz();
    }
}
