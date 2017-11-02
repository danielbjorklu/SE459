/**
 * 
 */
package com.utility.tracking;

/**
 * @author olamcdaniel
 *
 */
public interface IGameSubject {
	/**
	 * Register any item that needs to be tracked.
	 * 
	 * @param track
	 */
	void registerGameTracker(IGameTracker track);
	
	/**
	 * Remove all tracked items.
	 * 
	 * @param track
	 */
	void removeGameTracker(IGameTracker track);
	
	/**
	 * After an event, notify all trackers.
	 */
	void notifyGameTrackers();

}
