/**
 * 
 */
package com.utility.tracking;

/**
 * @author omcda
 *
 */
public interface ISubject {
	
	/**
	 * Register all trackable items.
	 * 
	 * @param track
	 */
	void registerTracker(ITracker track);
	
	/**
	 * Remove all trackable items.
	 * 
	 * @param track
	 */
	void removeTracker(ITracker track);
	
	/**
	 * After an event, notify all trackers.
	 */
	void notifyTrackers();
}
