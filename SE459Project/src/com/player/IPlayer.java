/**
 * 
 */
package com.player;

import com.utility.tracking.ITracker;

/**
 * @author olamcdaniel
 *
 */
public interface IPlayer extends ITracker {
	
	
	/**
	 * The name the player wishes to use. Can be composed of any combination
	 * of letters, numbers, or special characters not exceeding 15.
	 * 
	 * @return the players name
	 */
	String getPlayerName(); 
	
	/**
	 * A concatination of the teamId and the player's position in the team list.
	 * 
	 * @return the game id
	 */
	String getGameId();
	
	/**
	 * A concatination of the gameId and the playerName.
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
	

}
