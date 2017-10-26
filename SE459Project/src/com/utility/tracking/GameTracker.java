/**
 * 
 */
package com.utility.tracking;

import com.player.Player;

/**
 * @author olamcdaniel
 *
 */
public abstract class GameTracker implements Tracker {


	private Integer score;
	private Player player;
	
	/**
	 * Constructor for tracking players.
	 */
	public GameTracker(Player player) {
		this.player = player;
	}
	
	public GameTracker() {
		
	}
	
	@Override
	public abstract void track();

}
