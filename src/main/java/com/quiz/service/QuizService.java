package com.quiz.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.quiz.entity.Question;
import com.quiz.entity.QuestionWrapper;
import com.quiz.entity.Quiz;
import com.quiz.entity.Response;
import com.quiz.repository.QuestionRepository;
import com.quiz.repository.QuizRepostory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class QuizService {

	private final QuizRepostory quizRepostory;
	private final QuestionRepository questionRepository;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		try {
			List<Question> questions = questionRepository.findRandomQuestionsByCategory(category, numQ);
			Quiz quiz = new Quiz();
			quiz.setTitle(title);
			quiz.setQuestions(questions);
			quizRepostory.save(quiz);
			return new ResponseEntity<>("Quiz created successfully", HttpStatus.OK);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>("Quiz not created", HttpStatus.BAD_REQUEST);
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = quizRepostory.findById(id); // Fixing spelling of quizRepository
		List<Question> questionFromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionForUser = new ArrayList<>();
		for (Question q : questionFromDB) { // Fixing the for loop syntax
			QuestionWrapper qw = new QuestionWrapper(q.getId(), q.getQuestionTitle(), q.getOption1(), q.getOption2(),
					q.getOption3(), q.getOption4()); // Initializing QuestionWrapper properly
			questionForUser.add(qw);
		}

		return new ResponseEntity<>(questionForUser, HttpStatus.OK); // Keeping your return statement structure
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz =  quizRepostory.findById(id).get();
		List<Question> questions = quiz.getQuestions();
		int right = 0;
		int i = 0; // count the response and fetch the right answers
		for(Response response : responses) {
			if(response.getResponse().equals(questions.get(i).getRightAnswer()))
				right++;
			i++;
		}
		return new ResponseEntity<>(right, HttpStatus.OK);
	}

}
