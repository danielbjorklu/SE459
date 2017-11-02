/**
 * 
 */
package com.player;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The player data access object will hold references to all players created.
 * 
 * @author olamcdaniel
 *
 */
public class PlayerDAO {
	private static final Logger LOG = LogManager.getLogger(PlayerDAO.class);

	private IPlayer player;

	List<IPlayer> players;
	
	/**
	 * Players must be passed from the game manager.
	 * 
	 * @param player 
	 */
	public PlayerDAO(IPlayer player) {
		this.player = player;
		
		buildPlayer();
	}
	

	public PlayerDAO() {
		// TODO Auto-generated constructor stub
	}


	private void buildPlayer() {
		players = new ArrayList<>();
		
		if (players != null) {
			players.add(player);
			LOG.info("New Player created {}: ", player.getPlayerId());
		}
		
	}
	
	/**
	 * @return a list of all players
	 */
	public List<IPlayer> getBuiltPlayers() {
		
		LOG.info("Total players created {}: ", players.size());
		return players;
	}

}
