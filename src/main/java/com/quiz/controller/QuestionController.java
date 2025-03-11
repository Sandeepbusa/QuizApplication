package com.quiz.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.entity.Question;
import com.quiz.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {

	private final QuestionService questionService;
	
	@GetMapping("/allQuestions")
	public ResponseEntity<List<Question>> getAllQuestion(){
		return questionService.getAllQuestions();
		
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionsByCategory(@PathVariable String category){
		return questionService.getQuestionsByCategory(category);
	}
	
	@PostMapping("/addQuestion")
	public ResponseEntity<String> addQuestion(@RequestBody Question question) {
		return questionService.addQuestion(question);
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteById(@PathVariable Integer id) {
		 questionService.deleteById(id);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseEntity<Question>> updateResource(@PathVariable Integer id, @RequestBody Question updatedQuestion) {
	    ResponseEntity<Question> updated = questionService.updateQuestion(id, updatedQuestion);
	    return ResponseEntity.ok(updated);
	}


	
}
