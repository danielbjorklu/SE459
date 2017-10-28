/**
 * 
 */
package com.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;

/**
 * The team data access object will hold references to any team or teams created.
 * If the player decides to play by themselves, they are still considered a team.
 * 
 * @author olamcdaniel
 *
 */
public class TeamDAO {
	private static final Logger LOG = LogManager.getLogger(TeamDAO.class);

	HashMap<String, IPlayer> team1;
	HashMap<String, IPlayer> team2;
	
	List<Team> teams;
	
	/**
	 * Teams must be passed from the game manager.
	 * 
	 * @param team 
	 * @param team1 must contain at least one Player
	 * @param team2 can be empty or null
	 */
	public TeamDAO(HashMap<String, IPlayer> team1, Optional<HashMap<String, IPlayer>> team2) {
		//this.team = team;
		this.team1 = team1;
		this.team2 = team2.get();
	}
	
	/**
	 * @return a list of teams
	 */
	public List<Team> buildTeam() {
		teams = new ArrayList<>();
		
		if (team1 != null) { 
			teams.add((Team) team1.entrySet().stream().map(key -> key.getValue())
				.collect(Collectors.toList()));
			
			LOG.info("Team one created with {} players: ", teams.size());
		}
		if (team2 != null) {
			teams.add((Team) team2.entrySet().stream().map(key -> key.getValue())
					.collect(Collectors.toList()));
			LOG.info("Team two created with {} players: ", teams.size());
		}
		
		return teams;
	}

}
