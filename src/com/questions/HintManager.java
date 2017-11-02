/**
 * 
 */
package com.questions;

import com.answers.AnswerManager;
import com.answers.IAnswers;
import com.manager.GameManager;
import com.player.IPlayer;
import com.utility.game.GameConstants;
import com.utility.game.GameDialogue;
import com.utility.game.GameUtils;

/**
 * @author olamcdaniel
 *
 */
public class HintManager  {

	private IAnswers iAnswer;
	private IPlayer iPlayer;
	
	private AnswerManager answerManager;
	private GameUtils gameUtils;
	
	private String enteredAnswer;
	private boolean isHintAnswerCorrect;
	private int weight;


	/**
	 * Instantiate HintManager.
	 * @param iPlayer
	 * @param iAnswers
	 *
	 */
	public HintManager(IPlayer iPlayer, IAnswers iAnswers) {
		this.iPlayer = iPlayer;
		this.iAnswer = iAnswers;
		
		answerManager = new AnswerManager(iAnswers, iPlayer);
		gameUtils = new GameUtils();
		weight = iAnswer.getAnswerWeight();

	}
	
	/**
	 * @param hint 
	 * 
	 */
	public void getHint(String hint) {
			
		if (hint.toLowerCase().equals(GameConstants.YES)) {
			
			System.out.println(
					GameConstants.NEW_LINE +
					GameDialogue.POINTS_REMAINING + 
					iAnswer.getAnswerWeight() + 
					GameConstants.NEW_LINE
					);
			
			checkWeight(weight);
			
			System.out.println(
					"HINT: " + 
			GameConstants.NEW_LINE +
			iAnswer.getAnswerHint()
			);
			
			// should print out question here
			System.out.println(GameConstants.NEW_LINE + "Please enter your answer: ");
			processHint(gameUtils.stringScannerIn(enteredAnswer), weight);
		} else {
			gameUtils.keepPlaying(
					gameUtils.stringScannerIn(enteredAnswer), iPlayer);
		}
	}
	
	/**
	 * @param questionAnswer 
	 * @param weight 
	 * 
	 */
	public void processHint(String questionAnswer, int wt) {
		weight = wt;
		weight -= 1;
		
		answerManager.checkAnswer(questionAnswer);
		
		if (answerManager.isAnswer()) {
			isHintAnswerCorrect = true;

			System.out.println(GameDialogue.CORRECT_ANSWER);
			iPlayer.buildPlayerScore(weight);
	
		} else { 
			// print out message
			isHintAnswerCorrect = false;
			return;

		}
	}

	/**
	 * If weight equals one, then the player only gets one shot at answering the question.
	 * If weight equals zero, exit the hint engine
	 * 
	 * @param weight the point value of the question
	 */
	private void checkWeight(int weight) {
		if (weight == 1) {
			System.out.println(GameDialogue.HINT_WARNING + GameConstants.NEW_LINE);
		}  else if (weight == 0) {
			System.out.println(GameDialogue.NO_HINTS_LEFT);
			isHintAnswerCorrect = false;
			GameManager.continuePlay();
		}
	}
	
	/**
	 * @return true if the hint answer is correct, false otherwise
	 */
	public boolean isHintAnswerCorrect() {
		return isHintAnswerCorrect;
	}

	/**
	 * @return the current point value of the question
	 */
	public int getWeight() {
		
		return weight;
	}
	
	
}
