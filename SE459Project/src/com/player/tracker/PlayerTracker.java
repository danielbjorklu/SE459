/**
 * 
 */
package com.player.tracker;

import com.player.Player;
import com.utility.tracking.GameTracker;

import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;

/**
 * @author olamcdaniel
 *
 */
public class PlayerTracker extends GameTracker implements Player {

	public static final Logger LOG = LogManager.getLogger(PlayerTracker.class);

	private String playerName;
	private String playerId;
	private String gameId;
	
	private HashMap<String, String> playerTracker;
	
	
	PlayerTracker(String playerName, String gameId) {
	     this.gameId = gameId;
	     this.playerName = playerName;
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
		
		playerTracker.put(getPlayerId(), getPlayerName());
		return playerTracker;
		
	}
	
	@Override
	public void track() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getPlayerId() {
		
		return playerId;
	}

	@Override
	public String getPlayerName() {
		
		return playerName;
	}
	
	/**
	 * Each id can only have the values: 11, 12, 13, 14, 21, 22, 23, 24.
	 * 
	 * @param entryCnt tracks when the player entered their information
	 * @param teamCnt tracks the team the player is on
	 */
	public void generatePlayerId(Integer entryCnt, Integer teamCnt) {
		String entry = String.valueOf(entryCnt);
		String team = String.valueOf(teamCnt);
		playerId = entry + team;
	}

	@Override
	public String getGameId() {
		
		return gameId;
	}
}
