/**
 * 
 */
package com.utility.tracking;

import com.player.IPlayer;

/**
 * @author olamcdaniel
 *
 */
public interface IPlayerTracker extends IGameTracker {
	
	/**
	 * @param iPlayer
	 */
	void trackPlayer(IPlayer iPlayer);

}
