/**
 * 
 */
package com.questions;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
/**
 * @author olamcdaniel
 *
 */
public class Question implements Questions {

	private static Logger LOG = LogManager.getLogger(Question.class);
	
	
	private String question;
	private String category;
	private int index;
	
	public Question(int index, String question, String category) {
		this.index = index;
		this.question = question;
		this.category = category;

		LOG.info("Parsing questions");
	}
	@Override
	public String getQuestion() {
		
		return question;
	}

	@Override
	public String getCategory() {
		
		return category;
	}

	@Override
	public int getQuestionIndex() {
		
		return index;
	}

	@Override
	public boolean isQuestionCurrent() {
		
		return false;
	}

}
