/**
 * 
 */
package com.utility.tracking;

/**
 * @author olamcdaniel
 *
 */
public interface IPlayerSubject {
	
	/**
	 * Register any item that needs to be tracked.
	 * 
	 * @param track
	 */
	void registerPlayerTracker(IPlayerTracker track);
	
	/**
	 * Remove all tracked items.
	 * 
	 * @param track
	 */
	void removePlayerTracker(IPlayerTracker track);
	
	/**
	 * After an event, notify all trackers.
	 */
	void notifyPlayerTrackers();
}
