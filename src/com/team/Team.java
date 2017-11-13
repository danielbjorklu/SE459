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

    /**
     * 
     */
    public Team() {
	team1 = new HashMap<>();
	team2 = Optional.ofNullable(new HashMap<>());
	
	teamDAO = new TeamDAO();
    }

    /**
     * @param player
     * @param teamId
     * 
     */
    public void setPlayer(Player player, Integer teamId) {
	this.player = player;
	this.teamId = teamId;
	buildTeam(player, teamId);
    }

    private void buildTeam(Player player, Integer teamId) {

	if (teamId == 1) {
	    LOG.info("Adding {} to team {}: ", player.getPlayerName(), teamId);
	    team1.put(player.getGameId(), player);
	}

	if (teamId == 2) {
	    LOG.info("Adding {} to team {}: ", player.getPlayerName(), teamId);
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

	return 0;
    }

    @Override
    public HashMap<String, IPlayer> getTeamOne() {

	return team1;
    }

    @Override
    public Optional<HashMap<String, IPlayer>> getTeamTwo() {

	return team2;
    }

    /**
     * List teams
     */
    public void listTeams() {

	if (teamDAO.getBuiltTeams().size() > 1) {
	    System.out.println();
	    System.out.println("Team One: ");
	    teamDAO.getBuiltTeams().get(0).stream().forEach(player -> {
		System.out.println(player.getPlayerName() );
	    });
	    System.out.println();
	    System.out.println("Team Two: ");
	    teamDAO.getBuiltTeams().get(1).stream().forEach(player -> {
		System.out.println(player.getPlayerName() );
	    });
	}
    }

}
