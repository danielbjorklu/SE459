/**
 * 
 */
package com.utility;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
/**
 * @author olamcdaniel
 *
 */
public class Converter {
	
	public static final Logger LOG = LogManager.getLogger(Converter.class);
	
	public static final String convertString(final Object object) {
		
		//LOG.trace("Converting String: {}", object.toString());
		return object.toString();
	}
	
	public static final Integer convertInteger(final Object object) {
		
		//LOG.trace("Converting Integer: {}", object.toString());
		return Integer.parseInt(object.toString());
	}

}
