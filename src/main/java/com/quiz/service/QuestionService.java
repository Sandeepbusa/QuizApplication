package com.quiz.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.entity.Question;
import com.quiz.repository.QuestionRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuestionService {

	private final QuestionRepository questionRepository;

	// Fetch All the Questions
	public ResponseEntity<List<Question>> getAllQuestions() {
		try {
			return new ResponseEntity<>(questionRepository.findAll(), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList(), HttpStatus.BAD_GATEWAY);
	}

	// Fetch Question based on Category
	public ResponseEntity<List<Question>> getQuestionsByCategory(String category) {
		try {
			return new ResponseEntity<>(questionRepository.findByCategory(category), HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
	}

	// Add the Questions
	public ResponseEntity<String> addQuestion(Question question) {
		try {
			return new ResponseEntity<>("success", HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("Failed",HttpStatus.BAD_REQUEST);
	}

	// Delete the Questions
	public void deleteById(Integer id) {
		try {
		questionRepository.deleteById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	// Update the Questions
	public ResponseEntity<Question> updateQuestion(Integer id, Question updatedQuestion) {
		try {
			Question existingQuestion = questionRepository.findById(id).orElseThrow();
			existingQuestion.setQuestionTitle(updatedQuestion.getQuestionTitle());
			existingQuestion.setOption1(updatedQuestion.getOption1());
			existingQuestion.setOption2(updatedQuestion.getOption2());
			existingQuestion.setOption3(updatedQuestion.getOption3());
			existingQuestion.setOption4(updatedQuestion.getOption4());
			existingQuestion.setCategory(updatedQuestion.getCategory());
			existingQuestion.setDifficultyLevel(updatedQuestion.getDifficultyLevel());

			// Save the updated question back to the database
			return new ResponseEntity<>(HttpStatus.CREATED);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
