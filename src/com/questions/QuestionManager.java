/**
 * 
 */
package com.questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.answers.IAnswers;
import com.utility.FileParser;
import com.utility.game.GameConstants;

/**
 * @author olamcdaniel
 *
 */
public class QuestionManager {
	Logger LOG = LogManager.getLogger(QuestionManager.class);
	
	private Integer randomNumber;
	private Integer answerNumber;
	private Integer correctQuestion;
	boolean isAvailable;
	
	private Random rand;
	
	private List<Integer> correctQuestions;
	private List<Integer> index;
	
	private IQuestions question;
	private IAnswers answer;

	private List<IQuestions> questions;
	private List<IAnswers> answers;
	
	
	/**
	 * 
	 */
	public QuestionManager() {
		rand = new Random();
		correctQuestions = new ArrayList<>();
		index = new ArrayList<>();
		isAvailable = true;
		
		LOG.info("Adding answers to the game.");
		answers = FileParser.getAnswerInfo(GameConstants.ANSWER_PATH, "answers");
		LOG.info("Adding questions to the game.");
		questions = FileParser.getQuestionInfo(GameConstants.QUESTION_PATH, "questions");
		
	}
	
	/**
	 * @return a list of parsed questions
	 */
	public List<IQuestions> getQuestions() {
		
		return questions;
	}
	
	
	/**
	 * @return a list of parsed answers
	 */
	public List<IAnswers> getAnswers() {
		
		return answers;
	}
	
	private Integer generateRandomNumber() {
		
		randomNumber = rand.nextInt(questions.size() - 1);
		index.stream().forEach(q -> {
			
			for (Integer i : correctQuestions) {
				if (i == randomNumber) {
					questions.remove(i);
				}
			}
	
		});
		
		answerNumber = randomNumber;
		
		return randomNumber;
	}
	
	/**
	 * If question is correct eliminate it from the queue of questions
	 * to be asked.
	 * 
	 * @param correctQuestion
	 */
	public void setCorrectQuestion(Integer correctQuestion) {
		this.correctQuestion = correctQuestion;
		correctQuestions.add(correctQuestion);
	}
	
	
	/**
	 * @return the size of the question queue
	 */
	public Integer questionQueueSize() {
		return questions.size();
	}
	
	/**
	 * @return answer
	 */
	public IAnswers getRandAnswer() {
		answer = answers.get(answerNumber);
		return answer;
		
	}
	
	/**
	 * @return a random question
	 */
	public IQuestions getRandQuestion() {
		
		question = questions.get(generateRandomNumber());
		
		return question;	
	}
	
	/**
	 * @return the answer hint for the current question
	 */
	public String getHint() {
		
		return getRandAnswer().getAnswerHint();
	}
}

