/**
 * 
 */
package com.manager;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.answers.Answer;
import com.answers.IAnswers;
import com.player.Player;
import com.player.tracker.PlayerTracker;
import com.questions.Question;
import com.questions.Questions;
import com.team.Team;
import com.utility.FileParser;
import com.utility.game.GameConstants;
import com.utility.game.GameDialogue;

/**
 * @author olamcdaniel
 *
 */
public class GameManager {
	
	
	private static final Logger LOG = LogManager.getLogger(GameManager.class);

	static Answer answer;
	static Question question;
	
	static PlayerTracker playTracker;
	
	static Map<Integer, Questions> questions;
	static Map<Integer, IAnswers> answers;
	
	static int pointTotal;
	static int questionChoice;
	static int teamChoice;
	static int weight;
	static int idx;
	
	static String questionAnswer;	
	static String enteredName;
	static String hint;
	static String playerName;
	static String gameId;
	
	static Scanner scanner;
	
	static boolean isHint;
	static boolean playAgain;
	static boolean isGameOver;
	
	static Player player;
	static Team team;

	static Integer players;
	static Integer teams;
	static Integer totalPlayers;
	static Integer teamName;
	
	/**
	 * Temporary method to test game functionality.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		scanner = new Scanner(System.in);
		pointTotal = 0;
		playAgain = true;
		
		questions = FileParser.getQuestionInfo(GameConstants.QUESTION_PATH, "questions");
		LOG.info("Adding questions to the game.");
		
		answers = FileParser.getAnswerInfo(GameConstants.ANSWER_PATH, "answers");
		LOG.info("Adding answers to the game.");

		welcome();	
		
		playEngine(playAgain);
	}
	
	

	private static void welcome() {
		
		System.out.println(GameDialogue.WELCOME_MSG);
		teamBuilder();
		
		
	}
	
	private static void teamBuilder() {
		List<Integer> list = null;
		teams = new Integer(0);
		players = new Integer(0);
		enteredName = new String();
		teamName = new Integer(0);
		
		setGameType(intScannerIn(teams));
		
		if (getGameType() == 1) {
			System.out.println(GameDialogue.SINGLE_PLAYER_GAME);
			System.out.println(GameDialogue.GAME_RULES);
			playEngine(true);
		} else {
			System.out.println(GameDialogue.TEAM_PLAYER_GAME);
			setTotalPlayers(intScannerIn(players));
			if (getTotalPlayers() > 1) {
				list = new ArrayList<>();
				for (int n = 1; n <= getTotalPlayers(); n++) {	
			
					System.out.println("Player " + n + " please enter your name: ");
					setPlayerName(stringScannerIn(enteredName));
					
					System.out.println(getPlayerName().toUpperCase() + " please choose your team: ");
					setTeam(intScannerIn(teamName));
					
					player = new Player(getPlayerName(), n, getGameType(), getPlayerName() + getTeam() + n);
					
					team = new Team(player, getTeam());
					System.out.println(team.getTeamOne().size());
					System.out.println(team.getTeamTwo().get().size());
					
				}
			}	
		}
	}

	private static Integer getTeam() {
		
		return teamName;
	}



	private static void setTeam(int teamNm) {
		if (teamNm == 0) {
			System.out.println("There are only two available teams: Team1 or Team2.");
			System.out.println("Please enter 1 for Team1 and 2 for Team2: ");
			setGameType(intScannerIn(new Integer(0)));
		} else if (teamNm > 2) {
			System.out.println("There are only two available teams: Team1 or Team2.");
			System.out.println("Please enter 1 for Team1 and 2 for Team2: ");
			setGameType(intScannerIn(new Integer(0)));
		} else  {
			teamName = teamNm;
		}
	}



	private static void setPlayerName(String player) {
		if (StringUtils.isBlank(player)) {
			System.out.println("The players name cannot be blank: ");
			System.out.println("Please enter a name: ");
			setPlayerName(stringScannerIn(player));
		} else {
			playerName = player;
		}	
	}



	private static void setTotalPlayers(int players) {
		if (players == 0) {
			System.out.println("Please enter an amount greater than 0: ");
			setTotalPlayers(intScannerIn(new Integer(0)));
		} else if (players > 8) {
			System.out.println("The maximum number of players is 8.");
			System.out.println("Please enter a number from 1 to 8: ");
			setTotalPlayers(intScannerIn(new Integer(0)));
		} else {
			totalPlayers = players;
		}
	}



	private static void setGameType(int team) {
		if (team == 0) {
			System.out.println("There are only two available game types, single player or team based.");
			System.out.println("Please enter 1 for single player and 2 for team based: ");
			setGameType(intScannerIn(new Integer(0)));
		} else if (team > 2) {
			System.out.println("There are only two available game types, single player or team based.");
			System.out.println("Please enter 1 for single player and 2 for team based: ");
			setGameType(intScannerIn(new Integer(0)));
		} else {
			teamChoice = team;
		}

	}



	/**
	 * 
	 * @param totalPlayers
	 * @return
	 */
	private static Integer getTotalPlayers() {
		
		return totalPlayers;
	}

	/**
	 * 
	 * @param gameType
	 * @return
	 */
	private static int getGameType() {
		
		return teamChoice;
	}

	/**
	 * 
	 * @param playerName
	 * @return
	 */
	private static String getPlayerName() {
		
		return playerName;
	}

	/**
	 * 
	 * @param play
	 */
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
			LOG.error("IPlayer {} entered an incorrect value. Message: {}", "NEED_TO_IMPLEMENT", ime.getMessage());
			System.out.println("Please only enter numbers.");
			
		}
		return in;
	}
	
	private static void getQuestion(int answerEntered) {
		
		if (answerEntered > questions.size()) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);			
			getQuestion(intScannerIn(questionChoice));

		} 
		if (answerEntered <= 0) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);
			getQuestion(intScannerIn(questionChoice));

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

			LOG.info("Correct answer entered.");
			System.out.println(GameConstants.NEW_LINE + GameDialogue.YOUR_ANSWER);
				
			pointTotal += weight;
			LOG.info("Awarding {} points.", weight);
			LOG.debug("IPlayer {} has amassed {} points, exiting.", "NEED_TO_ADD", pointTotal);
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
