/**
 * 
 */
package com.team.tracker;

import java.util.HashMap;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
import com.player.Player;
import com.score.IScore;
import com.team.Team;

/**
 * @author olamcdaniel
 *
 */
public class TeamTracker extends Team {
	public TeamTracker(Player player, Integer teamId) {
		super(player, teamId);
		// TODO Auto-generated constructor stub
	}


	private static final Logger LOG = LogManager.getLogger(TeamTracker.class);

	private IPlayer player;
	private IScore score;
	private Integer teamId;
	
	private HashMap<String, IPlayer> team1;
	private Optional<HashMap<String, IPlayer>> team2;
	
//	/**
//	 * @param player
//	 */
//	public TeamTracker() {
//		//this.player = player;
//		LOG.info("Creating team with player {}:", player);
//		
//		LOG.info("Initializing ITeam ITracker.");
//		init();
//	}
	
	
	/**
	 * Start tracking teams.
	 */
	private void init() {
		team1 = new HashMap<>();
		//team2 = new HashMap<>();
	}

	/**
	 * @param id
	 * @param player
	 */
	public void createTeamOne(String id, IPlayer player) {
		team1.put(id, player);
	}
	
	/**
	 * @param id
	 * @param player
	 */
	public void createTeamTwo(String id, IPlayer player) {
		team2.get().put(id, player);
	}
	
	@Override
	public String getPlayerName() {
		
		if (StringUtils.isNotBlank(player.getPlayerName())) {
			LOG.info("Getting {}:", player.getPlayerName());
			return player.getPlayerName();
		} else {
			LOG.error("Error getting player name.  The name is empty.");
			return "No player exists";	
		}
	}

	@Override
	public String getGameId() {
		
		if (StringUtils.isNotBlank(player.getGameId())) {
			LOG.info("Getting {}:", player.getGameId());
			return player.getGameId();
		} else {
			LOG.error("Error getting player name.  The player name is {}: ", player.getGameId());
			return "Game Id not set";
		}	
	}

	@Override
	public String getPlayerId() {
		
		if (StringUtils.isNotBlank(player.getPlayerId())) {
			LOG.info("Getting {}:", player.getPlayerId());
			return player.getPlayerId();
		} else {
			LOG.error("Error getting player name.  The name is {}: ", player.getPlayerId());
			return "IPlayer Id not set";
		}
	}

	@Override
	public Integer getTeamId() {
		
		if (teamId != null) {
			LOG.info("Getting {}:", teamId);
			return teamId;
		} else {
			LOG.error("Error getting team id.  The team id is {}: ", teamId);
			return 0;
		}
	}

	@Override
	public Integer getPlayerScore() {
		
		if (score.getPlayerScore() != null) {
			LOG.info("Getting {}:", score.getPlayerScore().toString());
			return score.getPlayerScore();
		} else {
			LOG.error("Error getting player score.  The player score is {}: ", score.getPlayerScore().toString());
			return score.noPlayerScore();
		}		
	}
	
	/**
	 * @return the teams score, otherwise zero
	 */
	public Integer getTeamScore() {

		if (score.getTeamScore() != null) {
			LOG.info("Getting {}:", score.getTeamScore().toString());
			return score.getTeamScore();
		} else {
			LOG.error("Error getting team score.  The team score is {}: ", score.getTeamScore().toString());
			return score.noTeamScore();
		}		
	}
	
	
	
	@Override
	public void track() {
		// TODO Auto-generated method stub

	}

}
