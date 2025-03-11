package com.quiz.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.quiz.entity.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    // Fetch questions by category
    List<Question> findByCategory(String category);

    
    @Query(value="SELECT q FROM Question q WHERE q.category = :category ORDER BY FUNCTION('RAND') Limit :numQ")
    List<Question> findRandomQuestionsByCategory(String category, int numQ);


	



    

   
}
