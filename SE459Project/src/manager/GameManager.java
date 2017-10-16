/**
 * 
 */
package manager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import answers.Answer;
import answers.Answers;
import questions.Question;
import questions.Questions;
import utility.Constants;
import utility.FileParser;

/**
 * @author olamcdaniel
 *
 */
public class GameManager {

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

		questions = FileParser.getQuestionInfo("Questions.json", "questions");
		answers = FileParser.getAnswerInfo("Answers.json", "answers");

		welcome();
		
		playEngine(playAgain);
	}
	
	private static void welcome() {
		System.out.println("Welcome to The Guessing Game!");
		System.out.println("Depending on the difficulty of the question, " + Constants.NEW_LINE + "you may earn 1, 2, or 3 points.");
		System.out.println("Your point total will be updated throughout the game. " + Constants.NEW_LINE
				+ "If the available points reach 0, the question will be discarded"
				+ "and you'll be asked to choose another.");
		
		System.out.println("The game will start after you choose a number between 1 and 15.");
	}

	private static void playEngine(boolean play) {
		while (play) {
			System.out.println();
			System.out.println("POINT TOTAL = " + pointTotal);
			System.out.println();
			System.out.println("Please choose a number :");
			questionChoice = scanner.nextInt();
			
			choiceChecker(questionChoice);
			
			System.out.println(question.getQuestion());
			System.out.println("Please enter your answer: ");

			scanner = new Scanner(System.in);
			questionAnswer = scanner.nextLine();

			answerChecker(questionAnswer);
			
			if (!isHint && hint.toLowerCase().equals(Constants.YES)) {
				
				hint();
				System.out.println(Constants.NEW_LINE + "Please enter your answer: ");

				scanner = new Scanner(System.in);
				questionAnswer = scanner.nextLine();
				answerHintChecker(questionAnswer, weight);
			} else {
				System.out.println("Would you like to quit?");
				
				scanner = new Scanner(System.in);
				questionAnswer = scanner.nextLine();
				if (questionAnswer.toLowerCase().equals(Constants.YES)) {
					close();
				} else {
					playEngine(play);
				}
			}
		}
	}
	
	private static void choiceChecker(int answerEntered) {
		
		if (answerEntered > questions.size()) {
			System.out.println("Please choose a number between 1 and 15: ");
			
			scanner = new Scanner(System.in);
			answerEntered = scanner.nextInt();
		} 
		if (answerEntered < 0) {
			System.out.println("Please choose a number between 1 and 15: ");

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
				answer.getAnswer().toLowerCase().contains(answerEntered.toLowerCase());
		boolean isCorrectLength = 
				answer.getAnswer().replaceAll(Constants.WHITESPACE, Constants.EMPTY_CHAR).length() == 
				answerEntered.replaceAll(Constants.WHITESPACE, Constants.EMPTY_CHAR).length();
		
		if (isCorrect && isCorrectLength) {
			isHint = true;
			System.out.println();
			System.out.println("Your Answer: " + answerEntered);
			System.out.println("The correct answer is: " + answer.getAnswer());	
			pointTotal += weight;
			return;
		} 
		if (!isCorrect || !isCorrectLength) {
			isHint = false;
			System.out.println("Your answer is incorrect.  Would you like a hint?");
			System.out.println("Accepting a hint will reduce your available points for this question by 1.");
			System.out.println(Constants.NEW_LINE + "Points Remaining = " + weight);
			System.out.println("Please enter Yes or No: ");
			
			scanner = new Scanner(System.in);
			hint = scanner.nextLine();
		}
	}
	
	private static void answerHintChecker(String questionAnswer, int weight) {
		String pleaseContinue;
		weight -= 1;
		if (!answer.getAnswer().toLowerCase().contains(questionAnswer.toLowerCase())) {
			isHint = false;
			System.out.println("Your answer is incorrect.  Would you like a hint?");
		    System.out.println("Accepting a hint will reduce your available points for this question by 1.");
			System.out.println(Constants.NEW_LINE + "Points remaining = " + Constants.NEW_LINE + weight);
			System.out.println("Please enter Yes or No: ");
			
			scanner = new Scanner(System.in);
			hint = scanner.nextLine();
			if (weight == 0) {
				
				System.out.println("Your all out of hints for this question.");
				System.out.println("Would you like to continue playing? Please enter Yes or No: ");
				
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
				System.out.println(Constants.NEW_LINE + "Points remaining = " + Constants.NEW_LINE + weight);
				hint();
			} else {
				System.out.println("Would you like to continue playing? Please enter Yes or No: ");
				
				scanner = new Scanner(System.in);
				pleaseContinue = scanner.nextLine();
				
				if (pleaseContinue.matches(Constants.LETTERS) && pleaseContinue.toLowerCase().equals(Constants.YES)) {
					System.out.println(Constants.NEW_LINE + "POINT TOTAL = " + Constants.NEW_LINE + pointTotal);
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
		System.out.println("Total Points = " + pointTotal);
		System.exit(1);
	}
}
