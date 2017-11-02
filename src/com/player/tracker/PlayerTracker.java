/**
 * 
 */
package com.player.tracker;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
import com.player.PlayerDAO;
import com.utility.tracking.IPlayerSubject;
import com.utility.tracking.IPlayerTracker;

/**
 * Observes the players activity.
 * 
 * @author olamcdaniel
 *
 */
public class PlayerTracker implements IPlayerSubject {

	public static final Logger LOG = LogManager.getLogger(PlayerTracker.class);

	private List<IPlayerTracker> trackers;
	private List<IPlayer> iPlayers;
	
	private IPlayer iPlayer;
	
	private PlayerDAO playerDAO;
	
	/**
	 * 
	 */
	public PlayerTracker() {
		//playerDAO = new PlayerDAO();
		iPlayers = new ArrayList<>();
		trackers = new ArrayList<>();
		iPlayers = new ArrayList<>();
		
	}
	
	@Override
	public void registerPlayerTracker(IPlayerTracker track) {
		LOG.info("Registering player...");
		
		trackers.add(track);
		
	}

	@Override
	public void removePlayerTracker(IPlayerTracker track) {
		LOG.info("Removing player...");
		
		trackers.remove(track);
	}

	@Override
	public void notifyPlayerTrackers() {
		LOG.info("Notifying player trackers...");
		
		trackers.stream().forEach(tracker -> {
			tracker.trackPlayer(iPlayer);
		});
	}
	

	/**
	 * As players are added, notify other players.
	 * 
	 * @param iPlayer
	 */
	public void addNewPlayer(IPlayer iPlayer) {
		this.iPlayer = iPlayer;
		//iPlayers = playerDAO.getBuiltPlayers();
		
		iPlayers.add(iPlayer);
		notifyPlayerTrackers();
	}
	

//	/**
//	 * Stores the players id and name for team based, single player, or one-on-one play.
//	 * 
//	 * @param playerId 
//	 * 			An alphanumerical sequence consisting of the first 3 characters 
//	 * 			of the players name and a 2 digit numerical sequence representing
//	 * 			the team and the players position in the team.
//	 * @param playerName 
//	 * 			the player name
//	 * 
//	 * @return a mapping of the current player
//	 */
//	public HashMap<String, String> preparePlayerForTracking(Integer playerId, String playerName) {
//		
//		playerTracker.put(player.getPlayerId(), player.getPlayerName());
//		return playerTracker;
//		
//	}
	
}
