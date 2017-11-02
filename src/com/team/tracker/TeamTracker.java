/**
 * 
 */
package com.team.tracker;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;
import com.team.TeamDAO;
import com.utility.tracking.ITeamSubject;
import com.utility.tracking.ITeamTracker;

/**
 * Observes the teams activities.
 * 
 * @author olamcdaniel
 *
 */
public class TeamTracker implements ITeamSubject {

	private static final Logger LOG = LogManager.getLogger(TeamTracker.class);

	private List<ITeamTracker> trackers;
	private List<ArrayList<IPlayer>> iTeams;
	
	private List<IPlayer> iTeam;
	
	private TeamDAO teamDAO;

	private List<IPlayer> iTeamOne;
	private List<IPlayer> iTeamTwo;
	
	/**
	 * Default constructor initiates tracking list and teams list.
	 */
	public TeamTracker() {
		teamDAO = new TeamDAO();
		trackers = new ArrayList<>();
		iTeam = new ArrayList<>();
		iTeams = teamDAO.getBuiltTeams();
	}
	
	@Override
	public void registerTeamTracker(ITeamTracker track) {
		LOG.info("Registering team...");
		
		trackers.add(track);
		
	}

	@Override
	public void removeTeamTracker(ITeamTracker track) {
		LOG.info("Removing team...");
		
		trackers.remove(track);
	}

	@Override
	public void notifyTeamTrackers() {
		LOG.info("Notifying team trackers...");
		
		trackers.stream().forEach(tracker -> {
			tracker.trackTeam(iTeam);
		});
	}

	/**
	 * Notify the other team if another is added.
	 * @param iTeam
	 */
	public void addNewTeam(List<ArrayList<IPlayer>> iTeam) {
		iTeamOne = iTeam.get(0);
		iTeams.add((ArrayList<IPlayer>) iTeamOne);
		if (iTeam.get(1) != null) {
			iTeamTwo = iTeam.get(1);
			iTeams.add((ArrayList<IPlayer>) iTeamTwo);
		}
		notifyTeamTrackers();
		
	}
	
}
