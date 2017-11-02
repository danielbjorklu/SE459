/**
 * 
 */
package com.team;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
import com.player.Player;

/**
 * @author olamcdaniel
 *
 */
public class Team implements ITeam {
	private static final Logger LOG = LogManager.getLogger(Team.class);
	
	private Player player;
	private Integer teamId;
	
	private HashMap<String, IPlayer> team1;
	private Optional<HashMap<String, IPlayer>> team2;
	
	private TeamDAO teamDAO;


	public Team() {}
	/**
	 * @param player 
	 * 
	 */
	public Team(Player player, Integer teamId) {
		this.player = player;
		this.teamId = teamId;
		
		buildTeam(player, teamId);
	}

	
	private void buildTeam(Player player, Integer teamId) {
		team1 = new HashMap<>();
		team2 = Optional.ofNullable(new HashMap<>());
		if (teamId == 1) {
			LOG.info("Creating team {}: ", teamId);
			team1.put(player.getGameId(), player);
		}
		
		if (teamId == 2) {
			LOG.info("Creating team {}: ", teamId);
			team2.get().put(player.getGameId(), player);
		}
		storeTeams(team1, team2);
	}



	/**
	 * Invokes the TeamDAO and builds in memory storage.
	 * 
	 * @param team1
	 * @param team2
	 */
	private void storeTeams(HashMap<String, IPlayer> team1, Optional<HashMap<String, IPlayer>> team2) {
		teamDAO = new TeamDAO(team1, team2);
	}
	
	
	/**
	 * @return the team data access object
	 */
	public TeamDAO getTeamBuilder() {
		return teamDAO;
	}
	

	@Override
	public void trackTeam(List<IPlayer> iTeam) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Integer getTeamId() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public HashMap<String, IPlayer> getTeamOne() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Optional<HashMap<String, IPlayer>> getTeamTwo() {
		// TODO Auto-generated method stub
		return null;
	}

}
