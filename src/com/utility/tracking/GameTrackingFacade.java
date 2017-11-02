/**
 * 
 */
package com.utility.tracking;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
import com.player.tracker.PlayerTracker;
import com.team.tracker.TeamTracker;

/**
 * @author olamcdaniel
 *
 */
public class GameTrackingFacade implements IGameSubject {
	private static final Logger LOG = LogManager.getLogger(GameTrackingFacade.class);

	
	private TeamTracker teamTracker;
	private PlayerTracker playerTracker;

	List<Object> iGameTracker;
	List<IGameTracker> trackingList;
	

	/**
	 * 
	 */
	public GameTrackingFacade() {
		trackingList = new ArrayList<>();
		iGameTracker = new ArrayList<>();
		teamTracker = new TeamTracker();
		playerTracker = new PlayerTracker();
	}


	@Override
	public void registerGameTracker(IGameTracker track) {
		LOG.info("Registering game tracker...");
		trackingList.add(track);
	}

	
	@Override
	public void removeGameTracker(IGameTracker track) {
		LOG.info("Removing game tracker...");
		trackingList.remove(track);
		
	}
 	

	@Override
	public void notifyGameTrackers() {
		LOG.info("Notifying game trackers...");
		trackingList.stream().forEach(tracker -> {
			System.out.println(tracker);
		});
		
	}

	
	/**
	 * @param list
	 */
	public void updateTeams(List<ArrayList<IPlayer>> list) {
		teamTracker.addNewTeam(list);
	}
	
	/**
	 * @param iPlayer
	 */
	public void updatePlayers(IPlayer iPlayer) {
		playerTracker.addNewPlayer(iPlayer);
	}
	
}
