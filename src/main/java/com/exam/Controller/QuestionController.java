package com.exam.Controller;

import com.exam.enitity.exam.Question;
import com.exam.enitity.exam.Quiz;
import com.exam.service.QuestionService;
import com.exam.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;


    @Autowired
    private QuizService quizService;


    // add question


    @PostMapping("/")

    public ResponseEntity<Question> add(@RequestBody Question question) {
        return ResponseEntity.ok(this.questionService.addQuestion(question));
    }


    // update Question

    @PutMapping("/")
    public ResponseEntity<Question> update(@RequestBody Question question){
        return ResponseEntity.ok(this.questionService.updateQuestion(question));
    }



    //getAllQuestions
    @GetMapping("/")

    public ResponseEntity<?> getAllQuestions(){
       return ResponseEntity.ok(this.questionService.getQuestions());
    }


    // getQuestionsofQuiz

    @GetMapping("/quiz/{qid}")
    public ResponseEntity<?> getQuestionsofQuiz(@PathVariable("qid") Long qid) {
        Quiz quiz= this.quizService.getQuiz(qid);
        Set<Question> questions= quiz.getQuestions();
        List<Question> list=new ArrayList<>(questions);
        if(list.size()>Integer.parseInt(quiz.getNumberOfQuestions())) {
            list=list.subList(0,Integer.parseInt(quiz.getNumberOfQuestions()+1));
        }

        list.forEach(el->el.setAnswer(""));

        Collections.shuffle(list);
        return ResponseEntity.ok(list);

    }


    //Get Single questions of Quiz


    @GetMapping("/{quesId}")
    public ResponseEntity<?> getSingleQuestionofQuiz(@PathVariable("quesId") Long quesId){
        return ResponseEntity.ok(this.questionService.getQuestion(quesId));
    }

    @DeleteMapping("/{quesId}")
    public void delete(@PathVariable("quesId") Long quesId){
        this.questionService.deleteQuestion(quesId);
    }


    //eval question

    @PostMapping("/eval-quiz")
     public  ResponseEntity<?> evalQuiz(@RequestBody List<Question> questions) {
        System.out.println(questions);
        double marksGot=0;
        int correctAnswers=0;
        int attempted=0;
         for (Question q:questions){
                    //single question
                    Question question = this.questionService.get(q.getQuesId());

                    if(question.getAnswer().equals(q.getGivenAnswer())) {
                        //correct
                        correctAnswers++;
                        double marksSingle=Double.parseDouble(questions.get(0).getQuiz().getMaxMarks())/questions.size();
                        marksGot+=marksSingle;
                    }
                     if(q.getGivenAnswer() !=null ){
                        attempted++;
                    }

         }
              Map<String,Object> map=Map.of("marksGot",marksGot,"correctAnswers",correctAnswers,"attempted",attempted);

        return  ResponseEntity.ok(map);

    }

}
