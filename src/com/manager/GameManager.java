/**
 * 
 */
package com.manager;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.answers.AnswerManager;
import com.answers.IAnswers;
import com.player.Player;
import com.player.tracker.PlayerTracker;
import com.questions.IQuestions;
import com.questions.QuestionManager;
import com.team.Team;
import com.utility.game.GameConstants;
import com.utility.game.GameDialogue;
import com.utility.game.GameUtils;
import com.utility.tracking.GameTrackingFacade;
import com.utility.tracking.IGameTracker;

/**
 * @author olamcdaniel
 *
 */
public class GameManager {
	
	
	private static final Logger LOG = LogManager.getLogger(GameManager.class);

	static IAnswers answer;
	static IQuestions question;
	
	static PlayerTracker playTracker;
	
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
	
	static boolean isHint;
	static boolean playAgain;
	static boolean isGameOver;
	
	static Player player;
	static Team team;

	static Integer players;
	static Integer teams;
	static Integer totalPlayers;
	static Integer teamName;
	
	static GameUtils gameUtils;

	static AnswerManager answerManager;

	static QuestionManager questionManager = new QuestionManager();
	static List<IQuestions> questions = questionManager.getQuestions();
	static List<IAnswers> answers = questionManager.getAnswers();
	
	static GameTrackingFacade gtf = new GameTrackingFacade();
	static IGameTracker teamTracker = new Team();
	static IGameTracker playerTracker = new Player();

	private static boolean isHintAnswerCorrect;

	private static String pleaseContinue;
	
	/**
	 * Temporary method to test game functionality.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		gameUtils = new GameUtils();
		pointTotal = 0;
		enteredName = new String();
		teams = new Integer(0);
		players = new Integer(0);
		teamName = new Integer(0);
		playAgain = true;
		
		gtf.registerGameTracker(playerTracker);
		gtf.registerGameTracker(teamTracker);
		
		welcome();	
		
		playEngine(playAgain);
	}

	private static void welcome() {
		
		System.out.println(GameDialogue.WELCOME_MSG);
		teamBuilder();
	}
	
	private static void teamBuilder() {
		
		setGameType(gameUtils.intScannerIn(teams));
		
		if (getGameType() == 1) {
			LOG.info("...Starting single player game...");
			
			singlePlayerMode();
			
			playEngine(true);
		} else {
			LOG.info("...Starting team game...");
			
			teamMode();	
		}
	}

	private static void setGameType(int team) {
		if (team <= 0 || team > 2) {
			System.out.println("There are only two available game types, single player or team based.");
			System.out.println("Please enter 1 for single player and 2 for team based: ");
			
			setGameType(gameUtils.intScannerIn(new Integer(0)));
		} else {
			LOG.info(" Game type 1 for single player 2 for team {}: ", team);
			teamChoice = team;
		}

	}

	/**
	 * Handles all single player mode and builds the player and team.
	 * If the player chooses to play by themselves then they are automatically
	 * assigned to team one.
	 */
	private static void singlePlayerMode() {
		System.out.println(GameDialogue.SINGLE_PLAYER_GAME);
		System.out.println(GameConstants.NEW_LINE + "Please enter your name to begin: ");
		
		setPlayerName(gameUtils.stringScannerIn(enteredName));
		
		setTeam(GameConstants.ONE);
		String gameId = getPlayerName() + getTeam() + GameConstants.ONE;
		
		player = new Player(getPlayerName(), 
				GameConstants.ONE, 
				getGameType(),
				gameId);
		team = new Team(player, getTeam());
		System.out.println(GameDialogue.GAME_RULES);
	}
	
	/**
	 * Handles all player and team creation for the game.
	 */
	private static void teamMode() {
		System.out.println(GameDialogue.TEAM_PLAYER_GAME);
		setTotalPlayers(gameUtils.intScannerIn(players));
		if (getTotalPlayers() > 1) {
			
			for (int n = 1; n <= getTotalPlayers(); n++) {	
		
				LOG.debug("Total Players iterator {}:", n);
				System.out.println("Player " + n + " please enter your name: ");
				setPlayerName(gameUtils.stringScannerIn(enteredName));
				
				System.out.println(getPlayerName().toUpperCase() + " please choose your team: ");
				setTeam(gameUtils.intScannerIn(teamName));
				
				player = new Player(getPlayerName(), n, getGameType(), getPlayerName() + getTeam() + n);
				
				gtf.updatePlayers(player);
				
				team = new Team(player, getTeam());					
			}
			
			gtf.updateTeams(team.getTeamBuilder().getBuiltTeams());
		}
	}

	private static Integer getTeam() {
		
		return teamName;
	}

	private static void setTeam(int teamNm) {
		if (teamNm <= 0 || teamNm > 2) {
			System.out.println("There are only two available teams: Team1 or Team2.");
			System.out.println("Please enter 1 for Team1 and 2 for Team2: ");
			setGameType(gameUtils.intScannerIn(new Integer(0)));
		} else  {
			teamName = teamNm;
		}
	}

	private static void setPlayerName(String player) {
		if (StringUtils.isBlank(player)) {
			System.out.println("The players name cannot be blank: ");
			System.out.println("Please enter a name: ");
			setPlayerName(gameUtils.stringScannerIn(player));
		} else {
			playerName = player;
		}	
	}

	private static void setTotalPlayers(int players) {
		if (players <= 0) {
			System.out.println("Please enter an amount greater than 0: ");
			setTotalPlayers(gameUtils.intScannerIn(new Integer(0)));
		} else if (players > 8) {
			System.out.println("The maximum number of players is 8.");
			System.out.println("Please enter a number from 1 to 8: ");
			setTotalPlayers(gameUtils.intScannerIn(new Integer(0)));
		} else {
			totalPlayers = players;
		}
	}

	private static Integer getTotalPlayers() {
		
		return totalPlayers;
	}

	private static int getGameType() {
		
		return teamChoice;
	}

	private static String getPlayerName() {
		
		return playerName;
	}

	private static void playEngine(boolean play) {
		
		LOG.info("Starting game engine.");
		while (play) {
			
			showPointTotal();
			
			System.out.println(GameDialogue.CHOSEN_NUMBER);

			//should use the question manager here
			getQuestion(gameUtils.intScannerIn(questionChoice));
			//randomQuestion.getRandQuestion().getQuestion();
			
			answerManager = new AnswerManager(answer, player);
			answerManager.checkAnswer(gameUtils.stringScannerIn(questionAnswer));

		}
	}

	/**
	 * 
	 */
	public static void continuePlay() {
		System.out.println(GameDialogue.CONTINUE_PLAY);

		playEngine(!gameUtils.keepPlaying(
				gameUtils.stringScannerIn(pleaseContinue), player));
	}

	private static void showPointTotal() {
		// TODO point total shouldn't be an instance variable, it should come from a Score object
		if (pointTotal == 1) {
			System.out.println(
					GameConstants.NEW_LINE 
					+ GameDialogue.CURRENT_POINT_TOTAL 
					+ pointTotal 
					+ GameDialogue.POINT
					+ GameConstants.NEW_LINE
					);
		} else {
			System.out.println(
					GameConstants.NEW_LINE 
					+ GameDialogue.CURRENT_POINT_TOTAL 
					+ pointTotal 
					+ GameDialogue.POINTS
					+ GameConstants.NEW_LINE
					);
		}
	}

	private static void getQuestion(int answerEntered) {
		if (answerEntered <= 0 || answerEntered > questions.size()) {
			System.out.println(GameDialogue.WRONG_NUMBER_SIZE);
			getQuestion(gameUtils.intScannerIn(questionChoice));

		} else {
			System.out.println("Setting up question..." + GameConstants.NEW_LINE);
			question = questions.get(answerEntered);
			answer = answers.get(answerEntered);
			weight = answer.getAnswerWeight();
			
			System.out.println(question.getQuestion());
			System.out.println(GameDialogue.ENTERED_ANSWER);
		}
	}
	

}
