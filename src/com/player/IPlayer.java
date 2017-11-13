/**
 * 
 */
package com.player;

import java.util.List;

import com.utility.tracking.IPlayerTracker;

/**
 * @author olamcdaniel
 *
 */
public interface IPlayer extends IPlayerTracker {
	
	
	/**
	 * The name the player wishes to use. Can be composed of any combination
	 * of letters, numbers, or special characters not exceeding 15.
	 * 
	 * @return the players name
	 */
	String getPlayerName(); 
	
	/**
	 * A concatenation of the teamId and the player's position in the team list.
	 * 
	 * @return the game id
	 */
	String getGameId();
	
	/**
	 * A concatenation of the gameId and the playerName.
	 * 
	 * @return the players id
	 */
	String getPlayerId();

	/**
	 * 
	 * @return the final score
	 */
	Integer getPlayerScore();
	
	
	/**
	 * @param score the awarded points for a question
	 * @return the current score
	 */
	Integer buildPlayerScore(Integer score);
	
	
	/**
	 * A List of question ids the player has answered
	 * 
	 * @return list of question ids answered
	 */
	List<Integer> getAnsweredQuestions();

}
