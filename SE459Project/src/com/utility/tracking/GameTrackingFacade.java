/**
 * 
 */
package com.utility.tracking;

import com.player.IPlayer;
import com.score.IScore;
import com.team.ITeam;
import com.time.Time;

/**
 * @author olamcdaniel
 *
 */
public abstract class GameTrackingFacade implements ITracker {

	protected IScore score;
	protected IPlayer player;
	protected ITeam team;
	protected Time time;
	
	/**
	 * Constructor for tracking players.
	 * 
	 * @param player
	 */
	public GameTrackingFacade(IPlayer player, ITeam team, IScore score, Time time) {
		this.player = player;
		this.team = team;
		this.score = score;
		this.time = time;
		
		init();
	}

	private void init() {
		player.track();
		team.track();
		score.track();
		time.track();
	}
	
	
}
