/**
 * 
 */
package com.utility.tracking;

import java.util.List;

import com.player.IPlayer;

/**
 * @author olamcdaniel
 *
 */
public interface ITeamTracker extends IGameTracker {

	/**
	 * Notify teams when a tracked event occurs.
	 * 
	 * @param iTeam the team to track
	 */
	void trackTeam(List<IPlayer> iTeam);
}
