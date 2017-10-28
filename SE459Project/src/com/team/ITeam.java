/**
 * 
 */
package com.team;

import java.util.HashMap;
import java.util.Optional;

import com.player.IPlayer;

/**
 * @author olamcdaniel
 *
 */
public interface ITeam extends IPlayer {

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
