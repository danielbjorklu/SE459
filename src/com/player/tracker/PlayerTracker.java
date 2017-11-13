/**
 * 
 */
package com.player.tracker;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
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

    /**
     * 
     */
    public PlayerTracker() {
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
	    iPlayers.stream().forEach(player -> {
		LOG.info("Player {} notified", player.getPlayerId());
		tracker.trackPlayer(player);
	    });
	});
    }

    /**
     * As players are added, notify other players.
     * 
     * @param iPlayer
     */
    public void addNewPlayer(IPlayer iPlayer) {
	
	iPlayers.add(iPlayer);
	notifyPlayerTrackers();
    }

}
