/**
 * 
 */
package com.utility.tracking;

/**
 * @author olamcdaniel
 *
 */
public interface ITeamSubject {

	/**
	 * Register any item that needs to be tracked.
	 * 
	 * @param track
	 */
	void registerTeamTracker(ITeamTracker track);
	
	/**
	 * Remove all tracked items.
	 * 
	 * @param track
	 */
	void removeTeamTracker(ITeamTracker track);
	
	/**
	 * After an event, notify all trackers.
	 */
	void notifyTeamTrackers();
}
