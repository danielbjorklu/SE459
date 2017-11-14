/**
 * 
 */
package com.team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;

/**
 * The team data access object will hold references to any team or teams
 * created. If the player decides to play by themselves, they are still
 * considered a team.
 * 
 * @author olamcdaniel
 *
 */
public class TeamDAO {
    private static final Logger LOG = LogManager.getLogger(TeamDAO.class);

    HashMap<String, IPlayer> team1;
    HashMap<String, IPlayer> team2;

    List<ArrayList<IPlayer>> teams;
    
    ArrayList<IPlayer> teamOne;
    ArrayList<IPlayer> teamTwo;

    /**
     * Teams must be passed from the game manager.
     * 
     * @param team1
     *            must contain at least one Player
     * @param team2
     *            can be empty or null
     */
    public TeamDAO(HashMap<String, IPlayer> team1, Optional<HashMap<String, IPlayer>> team2) {

	this.team1 = team1;
	this.team2 = team2.get();
	
	teamOne = new ArrayList<>();
	teamTwo = new ArrayList<>();
	
	buildTeams();
    }

    /**
     * Initialize teams list
     */
    public TeamDAO() {
	teamOne = new ArrayList<>();
	teamTwo = new ArrayList<>();
    }

    /**
     * 
     */
    private void buildTeams() {
	teams = new ArrayList<>();

	teams.add(0, buildTeamOne());
	teams.add(1, buildTeamTwo());
    }

    private ArrayList<IPlayer> buildTeamOne() {
	
	if (team1 != null) {

	    team1.entrySet().stream().forEach(team -> {
		teamOne.add(team.getValue());
	    });

	    LOG.info("Team one created with {} players: ", teamOne.size());
	}
	return teamOne;
    }

    private ArrayList<IPlayer> buildTeamTwo() {
	
	if (team2 != null) {

	    team2.entrySet().stream().forEach(team -> {
		teamTwo.add(team.getValue());
	    });

	    LOG.info("Team two created with {} players: ", teamTwo.size());
	}
	return teamTwo;
    }

    /**
     * @return teams a list of both teams
     */
    public List<ArrayList<IPlayer>> getBuiltTeams() {

	return teams;
    }

}
