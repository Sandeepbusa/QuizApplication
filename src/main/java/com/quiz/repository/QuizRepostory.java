package com.quiz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quiz.entity.Quiz;

@Repository
public interface QuizRepostory extends JpaRepository<Quiz, Integer>{

	
}
