/**
 * 
 */
package com.score.tracker;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.score.IScore;

/**
 * 
 * @author olamcdaniel
 *
 */
public class ScoreTracker implements IScore {
	private static final Logger LOG = LogManager.getLogger(ScoreTracker.class);

	@Override
	public void track() {
		// do nothing
		
	}



	@Override
	public Integer getTeamScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getPlayerScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer noPlayerScore() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer noTeamScore() {
		// TODO Auto-generated method stub
		return null;
	}

}
