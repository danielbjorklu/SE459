/**
 * 
 */
package com.score;

import com.utility.tracking.IGameTracker;

/**
 * @author olamcdaniel
 *
 */
public interface IScore extends IGameTracker {

	/**
	 * @return the teams score
	 */
	Integer getTeamScore();
	
	/**
	 * @return the players score
	 */
	Integer getPlayerScore();

	/**
	 * @return a player score of zero
	 */
	Integer noPlayerScore();

	/**
	 * @return a team score of zero
	 */
	Integer noTeamScore();

}
