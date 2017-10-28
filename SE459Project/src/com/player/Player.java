/**
 * 
 */
package com.player;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author olamcdaniel
 *
 */
public class Player implements IPlayer {
	private static final Logger LOG = LogManager.getLogger(Player.class);
	
	private String playerName;
	private String gameId;
	
	@SuppressWarnings("unused")
	private Integer teamNumber;
	@SuppressWarnings("unused")
	private Integer entryPoint;
	
	private Integer updatedPlayerScore;

	private String playerId;
	
	
	/**
	 * Add basic player information and create a playerId.
	 * 
	 * @param playerName the name the player has chosen
	 * @param entryPoint the point at which the player entered their name
	 * @param teamNumber the chosen team
	 * @param gameId the generated id for the current game
	 * 
	 */
	public Player(String playerName, Integer entryPoint, Integer teamNumber, String gameId) {
		this.playerName = playerName;
		this.gameId = gameId;
		
		if (entryPoint != null && entryPoint >= 0 
				&& teamNumber != null && teamNumber >= 0) {
			
			generatePlayerId(entryPoint, teamNumber);
		}
		
	}

	@Override
	public String getPlayerName() {
		
		return playerName;
	}

	@Override
	public String getGameId() {
		
		return gameId;
	}

	@Override
	public String getPlayerId() {
		
		return playerId;
	}

	
	@Override
	public Integer getPlayerScore() {
		
		return updatedPlayerScore;
	}
	
	/**
	 * Each id can only have the values: 11, 12, 13, 14, 21, 22, 23, 24.
	 * 
	 * @param entryCnt tracks when the player entered their information
	 * @param teamCnt tracks the team the player is on
	 */
	private void generatePlayerId(Integer entryPoint, Integer teamCnt) {
		String entry = String.valueOf(entryPoint);
		String team = String.valueOf(teamCnt);
		playerId = entry + team;
		
		LOG.info("Generated playerId {} for {}: ", playerId, playerName);
	}


	@Override
	public Integer buildPlayerScore(Integer score) {
		updatedPlayerScore += score;
		
		return updatedPlayerScore;
	}

	
	@Override
	public void track() {
		// TODO Auto-generated method stub

	}

}
