/**
 * 
 */
package com.answers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.manager.GameManager;
import com.player.IPlayer;
import com.questions.HintManager;
import com.utility.game.GameConstants;
import com.utility.game.GameDialogue;
import com.utility.game.GameUtils;

/**
 * @author olamcdaniel
 *
 */
public class AnswerManager {
	private static final Logger LOG = LogManager.getLogger(AnswerManager.class);
	
	private IAnswers iAnswer;
	private IPlayer iPlayer;
	
	private HintManager hintManager;
	private GameUtils gameUtils;
	
	private String answerEntered;
	private String enteredAnswer;
	private boolean isCorrectAnswer;

	/**
	 * @param iAnswer
	 * @param iPlayer
	 */
	public AnswerManager(IAnswers iAnswer, IPlayer iPlayer) {
		this.iAnswer = iAnswer;
		this.iPlayer = iPlayer;
		
		gameUtils = new GameUtils();
		
	}
	
	/**
	 * @param answerEntered
	 */
	public void checkAnswer(String answerEntered) {

		hintManager = new HintManager(iPlayer, iAnswer);
		
		if (isAnswerCorrect(answerEntered)) {
			isCorrectAnswer = true;
			LOG.info("Correct answer entered.");
			System.out.println(GameConstants.NEW_LINE + GameDialogue.CORRECT_ANSWER);
			iPlayer.buildPlayerScore(iAnswer.getAnswerWeight());
			
			LOG.info("Awarding {} points.", iAnswer.getAnswerWeight());
			// show point total score.getPlayerScore()
			
			LOG.debug("{} has amassed {} points, exiting.", 
					iPlayer.getPlayerName().toUpperCase(),
					iPlayer.getPlayerScore());
			// remove question from the set of questions for this player
			// each player/team will need to have a question bucket for right and wrong questions
			// can use the question index as the key
		} else {
			isCorrectAnswer = false;
			
			System.out.println(GameDialogue.INCORRECT_HINT_ANSWER);		
			
			if (!hintManager.isHintAnswerCorrect()) {
				hintManager.getHint(gameUtils.stringScannerIn(answerEntered));
			}
		}
	}
	
	/**
	 * @return true if the entered answer is correct, false otherwise
	 */
	public boolean isAnswer() {
		return isCorrectAnswer;
	}

	private boolean isAnswerCorrect(String answerEntered) {
		
		boolean isCorrect = 
		iAnswer.getAnswer().toLowerCase().contains(answerEntered.toLowerCase())
		|| iAnswer.getAnswer().toLowerCase().equals(answerEntered.toLowerCase());
		LOG.debug("Is the submitted answer correct: {}", isCorrect);

		boolean isCorrectLength = 
		iAnswer.getAnswer().toLowerCase().replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length() == 
		answerEntered.toLowerCase().replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length();
		LOG.debug("Is the submitted answer the correct size: {}", isCorrectLength);
		
		String sAnswer = iAnswer.getAnswer().replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR);
		String eAnswer = answerEntered.replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR);
		int storedAnswer = iAnswer.getAnswer().replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length();
		int enterdAnswer = answerEntered.replaceAll(GameConstants.WHITESPACE, GameConstants.EMPTY_CHAR).length();
		LOG.debug("Stored Answer length: {}, actual: {}", storedAnswer, sAnswer);
		LOG.debug("Entered Answer lenth: {}, actual: {}", enterdAnswer, eAnswer);
		
		return isCorrect && isCorrectLength;
	}
}
