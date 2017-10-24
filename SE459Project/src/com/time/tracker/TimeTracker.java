/**
 * 
 */
package com.time.tracker;

import java.util.Timer;

import org.apache.logging.log4j.Logger;

import com.time.Time;

import org.apache.logging.log4j.LogManager;

/**
 * @author olamcdaniel
 *
 */
public class TimeTracker extends Timer implements Time {
	public static final Logger LOG = LogManager.getLogger(TimeTracker.class);

}
