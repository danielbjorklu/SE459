/**
 * 
 */
package com.team;

import java.util.HashMap;
import java.util.Optional;

import com.player.IPlayer;
import com.player.Player;

/**
 * @author olamcdaniel
 *
 */
public class Team implements ITeam {

	private Player player;
	private Integer teamId;
	
	private HashMap<String, IPlayer> team1;
	private Optional<HashMap<String, IPlayer>> team2;
	
	private TeamDAO teamDAO;


	private Team() {}
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
			team1.put(player.getGameId(), player);
		}
		
		if (teamId == 2) {
			team2.get().put(player.getGameId(), player);
		}
		storeTeams(team1, team2);
	}


	@Override
	public String getPlayerName() {
		
		return player.getPlayerName();
	}

	
	@Override
	public String getGameId() {
		
		return player.getGameId();
	}

	
	@Override
	public String getPlayerId() {
		
		return player.getPlayerId();
	}

	
	@Override
	public Integer getPlayerScore() {
		
		return player.getPlayerScore();
	}
	
	@Override
	public Integer getTeamId() {
		
		return teamId;
	}

	
	@Override
	public HashMap<String, IPlayer> getTeamOne() {
		
		return team1;
	}

	
	@Override
	public Optional<HashMap<String, IPlayer>> getTeamTwo() {
		
		return team2;
	}


	@Override
	public Integer buildPlayerScore(Integer score) {
		
		return player.buildPlayerScore(score);
	}
	
	/**
	 * Invokes the TeamDAO and builds in memory storage.
	 * 
	 * @param team1
	 * @param team2
	 */
	public void storeTeams(HashMap<String, IPlayer> team1, Optional<HashMap<String, IPlayer>> team2) {
		teamDAO = new TeamDAO(team1, team2);
	}
	
	
	public Team getTeam() {
		return this;
	}
	@Override
	public void track() {
		// TODO Auto-generated method stub

	}

}
