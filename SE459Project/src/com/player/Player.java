/**
 * 
 */
package com.player;

import com.utility.tracking.Tracker;

/**
 * @author olamcdaniel
 *
 */
public interface Player extends Tracker {
	
	
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
	
	

}
