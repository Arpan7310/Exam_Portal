package com.exam.Controller;

import com.exam.enitity.exam.Category;
import com.exam.enitity.exam.Quiz;
import com.exam.repo.QuizRepository;
import com.exam.service.CategoryService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/quiz")
public class QuizController {


    @Autowired
    private QuizService quizService;


    @Autowired

    private CategoryService categoryService;

    //add Quiz

    @PostMapping("/")

    public ResponseEntity<Quiz>  add (@RequestBody Quiz quiz) {

        return  ResponseEntity.ok(this.quizService.addQuiz(quiz));
    }

    //update Quiz

    @PutMapping("/")
    public ResponseEntity<Quiz>  update(@RequestBody Quiz quiz) {
        return ResponseEntity.ok(this.quizService.updateQuiz(quiz));
    }


    @GetMapping("/")
    public ResponseEntity<?> quizzes() {
        return ResponseEntity.ok(this.quizService.getQuizzes());
    }



    //get Single Quiz
    @GetMapping("/{qid}")
    public  ResponseEntity<Quiz> getQuiz(@PathVariable("qid") Long qid) {
        return ResponseEntity.ok(this.quizService.getQuiz(qid));
    }


    @DeleteMapping("/{qid}")
    public void deleteQuiz(@PathVariable("qid") Long qid) {
         this.quizService.deleteQuiz(qid);
    }

    @GetMapping("/category/{cid}")
    public ResponseEntity<List<Quiz>> getQuizzesofCategory(@PathVariable("cid") Long cid){
        Category category = new Category();
        category.setCid(cid);
        return ResponseEntity.ok(this.quizService.getQuizzesofCategory(category));
    }

    //active quizzes
    @GetMapping("/active/")
    public ResponseEntity<List<Quiz>> getActiveQuizzes() {
        return ResponseEntity.ok(this.quizService.getActiveQuizzes());
    }

    //active QuizzesOfCategory
    @GetMapping("/category/active/{cid}")
    public ResponseEntity<List<Quiz>> getActiveQuizzes(@PathVariable("cid") Long cid) {

        Category c= new Category();
        c.setCid(cid);
        return ResponseEntity.ok(this.quizService.getActiveQuizzesofCategory(c));
    }


}
