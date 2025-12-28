package com.MyProject.QuizApp.Controller;

import com.MyProject.QuizApp.Model.Question;
import com.MyProject.QuizApp.Service.QuestionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("question")
public class QuestionController {
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    @Autowired
    QuestionService questionService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getQuestions")
    public ResponseEntity<List<Question>> getQuestions(){
            List<Question> questions = questionService.getQuestions();
            if (questions.isEmpty() || questions.size() == 0){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(questions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("getQuestion/{id}")
    public ResponseEntity<Question> getQuestionByID(@PathVariable Long id){
        try {
            Question que = questionService.getQuestionByID(id);
            return ResponseEntity.status(HttpStatus.OK).body(que);
        }catch (IllegalArgumentException e){
          return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addQuestion")
    public ResponseEntity<Question> addQuestion(@RequestBody Question question){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(questionService.addQuestion(question));
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{id}")
    public Question updateQuestion(@PathVariable long id, @RequestBody Question question){
        return questionService.updateQuestion(id, question);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteQuestion(@PathVariable long id){
        try {
            questionService.deleteQuestion(id);
            return new ResponseEntity<>("Deleted successfully", HttpStatus.OK);
        }catch (IllegalArgumentException e){
            if ("ID_NOT_FOUND".equals(e.getMessage())){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID not found");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request");
        }catch (RuntimeException e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
        }
    }
}
