/**
 * 
 */
package com.player;


import java.util.ArrayList;
import java.util.List;

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
	
	private PlayerDAO playerDAO;
	
	private List<Integer> answeredQuestions;
	
	
	/**
	 * Default constructor.
	 */
	public Player() {
		// TODO Auto-generated constructor stub
	}
	
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
		
		updatedPlayerScore = new Integer(0);
		if (entryPoint != null && entryPoint >= 0 
				&& teamNumber != null && teamNumber >= 0) {

			LOG.info("New Player Added. Name = {} Team = {}: ", playerName, teamNumber);
			generatePlayerId(entryPoint, teamNumber);
		}
		answeredQuestions = new ArrayList<Integer>();
		storePlayers(this);
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
	 * @param player
	 */
	private void storePlayers(Player player) {
		playerDAO = new PlayerDAO(player);
	}
	
	/**
	 * Each id can only have the values: 11, 12, 13, 14, 21, 22, 23, 24.
	 * 
	 * @param entryCnt tracks when the player entered their information
	 * @param teamCnt tracks the team the player is on
	 */
	private void generatePlayerId(Integer entryPoint, Integer teamCnt) {
		try {
			String entry = String.valueOf(entryPoint);
			String team = String.valueOf(teamCnt);
			playerId = entry + team;
		
			LOG.info("Generated playerId {} for {}: ", playerId, playerName);
		} catch (IllegalArgumentException ex) {
			LOG.error("An invalid parameter was provided.");
		}
	}


	@Override
	public Integer buildPlayerScore(Integer score) {
		
		updatedPlayerScore += score;
		
		return updatedPlayerScore;
	}

	@Override
	public void trackPlayer(IPlayer iPlayer) {
		System.out.println("Player " + iPlayer.getPlayerName() +  " tracked ");
		
	}
	
	@Override
	public List<Integer> getAnsweredQuestions() {
		return answeredQuestions;
	}


}
