package com.exam.repo;

import com.exam.enitity.exam.Question;
import com.exam.enitity.exam.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question,Long> {


    public Set<Question>  findByQuiz(Quiz quiz);
}
