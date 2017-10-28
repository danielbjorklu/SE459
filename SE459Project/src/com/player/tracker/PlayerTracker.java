/**
 * 
 */
package com.player.tracker;

import java.util.HashMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.Player;
import com.score.IScore;

/**
 * Observes the players activity.
 * 
 * @author olamcdaniel
 *
 */
public class PlayerTracker {

	public static final Logger LOG = LogManager.getLogger(PlayerTracker.class);

	private HashMap<String, String> playerTracker;
	
	private IScore score;

	private Player player;
	
	
	PlayerTracker(Player player) {
		 this.player = player;
		
	   
	     init();
	}
	
	private void init() {
		playerTracker = new HashMap<>();
		
	}

	/**
	 * Stores the players id and name for team based, single player, or one-on-one play.
	 * 
	 * @param playerId 
	 * 			An alphanumerical sequence consisting of the first 3 characters 
	 * 			of the players name and a 2 digit numerical sequence representing
	 * 			the team and the players position in the team.
	 * @param playerName 
	 * 			the player name
	 * 
	 * @return a mapping of the current player
	 */
	public HashMap<String, String> preparePlayerForTracking(Integer playerId, String playerName) {
		
		playerTracker.put(player.getPlayerId(), player.getPlayerName());
		return playerTracker;
		
	}
	
}
