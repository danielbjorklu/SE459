/**
 * 
 */
package com.manager;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.answers.Answer;
import com.answers.Answers;
import com.questions.Question;
import com.questions.Questions;
import com.utility.Constants;
import com.utility.FileParser;
import com.utility.game.GameDialogue;

/**
 * @author olamcdaniel
 *
 */
public class GameManager {
	
	final static Logger LOG = LogManager.getLogger(GameManager.class);
	
	static Answer answer;
	static Question question;

	static ArrayList<Questions> questions;
	static ArrayList<Answers> answers;
	
	static int pointTotal;
	static int questionChoice;
	static int weight;
	
	static String questionAnswer;	
	static String hint;
	
	
	static Scanner scanner;
	
	static boolean isHint;
	static boolean playAgain;

	/**
	 * Temporary method to test game functionality.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		pointTotal = 0;
		playAgain = true;
		
		questions = FileParser.getQuestionInfo(Constants.QUESTION_PATH, "questions");
		LOG.info("Adding questions to the game.");
		
		answers = FileParser.getAnswerInfo(Constants.ANSWER_PATH, "answers");
		LOG.info("Adding answers to the game.");

		LOG.info("Starting game engine.");
		welcome();	
		playEngine(playAgain);
	}
	
	private static void welcome() {
		LOG.debug("Writing welcome message.");
		System.out.println(GameDialogue.WELCOME_MSG);
	}

	private static void playEngine(boolean play) {
		while (play) {
			System.out.println();
			System.out.println(GameDialogue.POINT_TOTAL + pointTotal);
			System.out.println();
			System.out.println("Please choose a number :");
			questionChoice = scanner.nextInt();
			
			choiceChecker(questionChoice);
			LOG.debug(questionChoice);
			
			System.out.println(question.getQuestion());
			System.out.println(GameDialogue.ANSWER_CHOICE);

			scanner = new Scanner(System.in);
			questionAnswer = scanner.nextLine();

			answerChecker(questionAnswer);
			
			if (!isHint && hint.toLowerCase().equals(Constants.YES)) {
				
				hint();
				System.out.println(Constants.NEW_LINE + GameDialogue.ANSWER_CHOICE);

				scanner = new Scanner(System.in);
				questionAnswer = scanner.nextLine();
				answerHintChecker(questionAnswer, weight);
			} else {
				System.out.println(GameDialogue.QUIT);
				
				scanner = new Scanner(System.in);
				questionAnswer = scanner.nextLine();
				if (questionAnswer.toLowerCase().equals(Constants.YES)) {
					LOG.debug("Player {} has chosen to quit the game.", "NEED_TO_FIX");
					close();
				} else {
					LOG.debug("Starting game over.");
					playEngine(play);
				}
			}
		}
	}
	
	private static void choiceChecker(int answerEntered) {
		
		if (answerEntered > questions.size()) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);
			
			scanner = new Scanner(System.in);
			answerEntered = scanner.nextInt();
		} 
		if (answerEntered < 0) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);

			scanner = new Scanner(System.in);
			answerEntered = scanner.nextInt();
		}
		if (answerEntered == questions.size()) {
			question = (Question) questions.get(answerEntered - 1);
			answer = (Answer) answers.get(answerEntered - 1);
			weight = answer.getAnswerWeight();
		} else {
			question = (Question) questions.get(answerEntered);
			answer = (Answer) answers.get(answerEntered);
			weight = answer.getAnswerWeight();
		}
	}
	
	private static void answerChecker(String answerEntered) {
		
		isHint = false;
		boolean isCorrect = 
				answer.getAnswer().toLowerCase().contains(answerEntered.toLowerCase())
				|| answer.getAnswer().toLowerCase().equals(answerEntered.toLowerCase());
		LOG.debug("Is the submitted answer correct: {}", isCorrect);
		
		boolean isCorrectLength = 
				answer.getAnswer().replaceAll(Constants.WHITESPACE, Constants.EMPTY_CHAR).length() == 
				answerEntered.replaceAll(Constants.WHITESPACE, Constants.EMPTY_CHAR).length();
		LOG.debug("Is the submitted answer the correct size: {}", isCorrectLength);
		
		if (isCorrect && isCorrectLength) {
			isHint = true;
			LOG.info("Correct answer entered.");
			System.out.println(Constants.NEW_LINE + GameDialogue.YOUR_ANSWER);
				
			pointTotal += weight;
			LOG.info("Awarding {} points.", weight);
			LOG.debug("Player {} has amassed {} points, exiting.", "NEED_TO_ADD", pointTotal);
			System.out.println(Constants.NEW_LINE + GameDialogue.POINT_TOTAL + pointTotal);
			return;
		} 
		if (!isCorrect || !isCorrectLength) {
			isHint = false;
			
			System.out.println(Constants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + Constants.NEW_LINE);
			System.out.println(GameDialogue.HINT_MSG);

			scanner = new Scanner(System.in);
			hint = scanner.nextLine();
		}
	}
	
	private static void answerHintChecker(String questionAnswer, int weight) {
		String pleaseContinue;
		weight -= 1;
		if (weight <= 1) {
			System.out.println(GameDialogue.HINT_WARNING);
		}
		if (!answer.getAnswer().toLowerCase().contains(questionAnswer.toLowerCase())) {
			isHint = false;
			System.out.println(Constants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + Constants.NEW_LINE);
			
			System.out.println(GameDialogue.HINT_MSG);
			
			scanner = new Scanner(System.in);
			hint = scanner.nextLine();
			
			if (weight == 0) {
				
				System.out.println(GameDialogue.HINT_PNTS_MSG);
				
				scanner = new Scanner(System.in);
				pleaseContinue = scanner.nextLine();
				if (pleaseContinue.matches(Constants.LETTERS) && pleaseContinue.toLowerCase().equals(Constants.YES)) {
					return;
				} else {
					close();
				}
			}
			if (hint.toLowerCase().equals(Constants.YES) && weight > 0) {
				weight -= 1;
				System.out.println(Constants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + Constants.NEW_LINE);
				hint();
			} else {
				System.out.println(GameDialogue.CONTINUE_PLAY);
				
				scanner = new Scanner(System.in);
				pleaseContinue = scanner.nextLine();
				
				if (pleaseContinue.matches(Constants.LETTERS) && pleaseContinue.toLowerCase().equals(Constants.YES)) {
					System.out.println(Constants.NEW_LINE + GameDialogue.POINT_TOTAL + pointTotal + Constants.NEW_LINE );
					return;
				} else { 
					close();
				}
			}
		}	
	}
	
	private static void hint() {
		System.out.println(Constants.NEW_LINE + answer.getAnswerHint());
	}
	
	private static void close() {
		scanner.close();
		playAgain = false;
		System.out.println(GameDialogue.TOTAL_POINTS + pointTotal);
		System.exit(1);
	}
}
