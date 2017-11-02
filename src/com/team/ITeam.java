/**
 * 
 */
package com.team;

import java.util.HashMap;
import java.util.Optional;

import com.player.IPlayer;
import com.utility.tracking.ITeamTracker;

/**
 * @author olamcdaniel
 *
 */
public interface ITeam extends ITeamTracker {

	/**
	 * 
	 * @return the team id
	 */
	Integer getTeamId();
	
	/**
	 * @return a mapping of team one
	 */
	HashMap<String, IPlayer> getTeamOne();
	
	/**
	 * @return a mapping of team two
	 */
	Optional<HashMap<String, IPlayer>> getTeamTwo();
	
}
