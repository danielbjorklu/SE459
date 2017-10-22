/**
 * 
 */
package com.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import com.answers.Answer;
import com.answers.Answers;
import com.questions.Question;
import com.questions.Questions;
import com.utility.FileParser;
import com.utility.game.GameConstants;
import com.utility.game.GameDialogue;

/**
 * @author olamcdaniel
 *
 */
public class GameManager {
	
	final static Logger LOG = LogManager.getLogger(GameManager.class);
	
	static Answer answer;
	static Question question;
	
	static Map<Integer, Questions> questions;
	static Map<Integer, Answers> answers;
	
	static int pointTotal;
	static int questionChoice;
	static int weight;
	static int idx;
	
	static String questionAnswer;	
	static String hint;
	
	static Scanner scanner;
	
	static boolean isHint;
	static boolean playAgain;
	//static boolean isEntryValid;
	static boolean isGameOver;
	
	/**
	 * Temporary method to test game functionality.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		pointTotal = 0;
		playAgain = true;
		//isEntryValid = true;
		
		questions = FileParser.getQuestionInfo(GameConstants.QUESTION_PATH, "questions");
		LOG.info("Adding questions to the game.");
		
		answers = FileParser.getAnswerInfo(GameConstants.ANSWER_PATH, "answers");
		LOG.info("Adding answers to the game.");

		welcome();	
		playEngine(playAgain);
	}
	
	private static void welcome() {
		LOG.debug("Writing welcome message.");
		System.out.println(GameDialogue.WELCOME_MSG);
	}

	private static void playEngine(boolean play) {
		
		LOG.info("Starting game engine.");
		while (play) {
			
			showPointTotal();
			
			System.out.println(GameDialogue.NUMBER_CHOICE);

			getQuestion(intScannerIn(questionChoice));
			 
			checkAnswer(stringScannerIn(questionAnswer));
			
			if (!isHint && hint.toLowerCase().equals(GameConstants.YES)) {
				
				getHint();
				System.out.println(
						GameConstants.NEW_LINE + 
						GameDialogue.ANSWER_CHOICE +
						GameConstants.NEW_LINE
						);

				checkHintAnswer(stringScannerIn(questionAnswer), weight);
			} else {
				System.out.println(GameDialogue.QUIT);
				continuePlaying(stringScannerIn(questionAnswer));
				if (isGameOver) {
					exitGame();
				} else {
					playEngine(play);
				}
			}
		}
	}

	/**
	 * 
	 */
	private static void continuePlaying(String qAnswer) {
		if (qAnswer.toLowerCase().equals(GameConstants.YES)) {
			LOG.debug("Player {} has chosen to quit the game.", "NEED_TO_FIX");
			isGameOver = true;
		} else if (qAnswer.toLowerCase().equals(GameConstants.NO)){
			LOG.debug("Starting game over.");
			System.out.println(GameConstants.NEW_LINE + "Setting up next round..." + GameConstants.NEW_LINE);
			isGameOver = false;
		} else {
			System.out.println(questionAnswer + " isn't an option.  Please try again");
			continuePlaying(stringScannerIn(questionAnswer));
		}
	}

	/**
	 * 
	 */
	private static void showPointTotal() {
		if (pointTotal == 1) {
			System.out.println(
					GameConstants.NEW_LINE 
					+ GameDialogue.POINT_TOTAL 
					+ pointTotal 
					+ GameDialogue.POINT
					+ GameConstants.NEW_LINE
					);
		} else {
			System.out.println(
					GameConstants.NEW_LINE 
					+ GameDialogue.POINT_TOTAL 
					+ pointTotal 
					+ GameDialogue.POINTS
					+ GameConstants.NEW_LINE
					);
		}
	}

	/**
	 * 
	 */
	private static String stringScannerIn(String in) {
		try {
			scanner = new Scanner(System.in);
			in = scanner.nextLine();
		} catch (InputMismatchException ime) {
			LOG.error("Player {} entered an incorrect value. Message: {}", "NEED_TO_IMPLEMENT", ime.getMessage());
			System.out.println("Please only enter letters.");
		}
		return in;
	}

	/**
	 * 
	 */
	private static int intScannerIn(int in) {
		try {
			scanner = new Scanner(System.in);
			in = scanner.nextInt();
		} catch (InputMismatchException ime) {
			LOG.error("Player {} entered an incorrect value. Message: {}", "NEED_TO_IMPLEMENT", ime.getMessage());
			System.out.println("Please only enter numbers.");
			
		}
		return in;
	}
	
	private static void getQuestion(int answerEntered) {
		
		if (answerEntered > questions.size()) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);			
			getQuestion(intScannerIn(questionChoice));

			//isEntryValid = false;
		} 
		if (answerEntered <= 0) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);
			getQuestion(intScannerIn(questionChoice));

			//isEntryValid = false;
		} else {
			System.out.println("Setting up question..." + GameConstants.NEW_LINE);
			question = (Question) questions.get(answerEntered);
			answer = (Answer) answers.get(answerEntered);
			weight = answer.getAnswerWeight();
			
			System.out.println(question.getQuestion());
			System.out.println(GameDialogue.ANSWER_CHOICE);

		}
	}
	
	private static void checkAnswer(String answerEntered) {
		
		isHint = false;
		boolean isCorrect = 
				answer.getAnswer().toLowerCase().contains(answerEntered.toLowerCase())
				|| answer.getAnswer().toLowerCase().equals(answerEntered.toLowerCase());
		LOG.debug("Is the submitted answer correct: {}", isCorrect);
		
		boolean isCorrectLength = 
				answer.getAnswer().replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length() == 
				answerEntered.replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length();
		LOG.debug("Is the submitted answer the correct size: {}", isCorrectLength);
		
		if (isCorrect && isCorrectLength) {
			isHint = true;
			//isEntryValid = true;
			LOG.info("Correct answer entered.");
			System.out.println(GameConstants.NEW_LINE + GameDialogue.YOUR_ANSWER);
				
			pointTotal += weight;
			LOG.info("Awarding {} points.", weight);
			LOG.debug("Player {} has amassed {} points, exiting.", "NEED_TO_ADD", pointTotal);
			showPointTotal();
			// need facility to remove the question from gameplay
			return;
		} 
		if (!isCorrect || !isCorrectLength) {
			isHint = false;
			
			System.out.println(GameConstants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + GameConstants.NEW_LINE);
			System.out.println(GameDialogue.HINT_MSG);

			scanner = new Scanner(System.in);
			hint = scanner.nextLine();
		}
	}
	
	private static void getHint() {
		System.out.println(GameConstants.NEW_LINE + answer.getAnswerHint());
	}
	
	private static void checkHintAnswer(String questionAnswer, int weight) {
		String pleaseContinue = null;
		
		if (weight == 1) {
			System.out.println(GameDialogue.HINT_WARNING);
		}
		
		
		if (!answer.getAnswer().toLowerCase().contains(questionAnswer.toLowerCase())) {
			isHint = false;
			System.out.println(GameConstants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + GameConstants.NEW_LINE);
			
			System.out.println(GameDialogue.HINT_MSG);
			
			if (stringScannerIn(hint).toLowerCase().equals(GameConstants.YES) && weight > 0) {
				weight -= 1;
				System.out.println(GameConstants.NEW_LINE + GameDialogue.POINTS_REMAINING + weight + GameConstants.NEW_LINE);
				if (weight == 0) {
						
					System.out.println(GameDialogue.HINT_PNTS_MSG);
					continuePlaying(stringScannerIn(pleaseContinue));
					
					if (isGameOver) {
						exitGame();
					} else {
						playEngine(true);
					}
				} else {
					String hintAnswer = null;
					if (weight == 1) {
						System.out.println(GameDialogue.HINT_WARNING);
					}
					getHint();
					checkHintAnswer(stringScannerIn(hintAnswer), weight);
				}
				
			} else {
				continuePlaying(stringScannerIn(pleaseContinue));
				if (isGameOver) {
					exitGame();
				} else {
					playEngine(true);
				}
			}
		} else {
			pointTotal += weight;
			System.out.println(GameDialogue.YOUR_ANSWER + GameConstants.NEW_LINE);
			if (weight == 1) {
				System.out.println("You recieved " + weight + " point out of a possible " + answer.getAnswerWeight() + " available points.");
			} else {
				System.out.println("You recieved " + weight + " points out of a possible " + answer.getAnswerWeight() + " available points.");
			}
			
			System.out.println(GameDialogue.CONTINUE_PLAY);
			
			continuePlaying(stringScannerIn(pleaseContinue));
			if (isGameOver) {
				exitGame();
			} else {
				playEngine(true);
			}
		}	
	}
	
	private static void exitGame() {
		scanner.close();
		playAgain = false;
		
		if (pointTotal == 1) {
			System.out.println(GameDialogue.FINAL_POINT_TOTAL + pointTotal + GameDialogue.POINT);
		} else {
			System.out.println(GameDialogue.FINAL_POINT_TOTAL + pointTotal + GameDialogue.POINTS);
		}
		
		System.exit(1);
	}
}
