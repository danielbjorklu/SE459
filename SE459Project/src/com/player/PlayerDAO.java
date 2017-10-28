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

	private Player player;

	List<Player> players;
	
	/**
	 * Players must be passed from the game manager.
	 * 
	 * @param player 
	 */
	public PlayerDAO(Player player) {
		this.player = player;
	}
	
	
	/**
	 * @return a list of all players
	 */
	public List<Player> buildPlayer() {
		players = new ArrayList<>();
		
		if (players != null) {
			players.add(player);
			LOG.info("New Player created {}: ", player.getPlayerId());
		}
		
		return players;
	}
	

}
