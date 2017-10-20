/**
 * 
 */
package com.answers;

/**
 * @author olamcdaniel
 *
 */
public interface Answers {
	
	/**
	 * 
	 * @return the answers index
	 */
	public int getAnswerIndex();
	
	
	/**
	 * 
	 * @return the corresponding answer
	 */
	public String getAnswer();
	
	/**
	 * 
	 * @return the points available for the question
	 */
	public int getAnswerWeight();
	
	/**
	 * 
	 * @return a hint for that answer
	 */
	public String getAnswerHint();
	
	/**
	 * 
	 * @return <code>true</code> if the answer index matches the question index,
	 * <code>false</code> otherwise
	 * 
	 */
	public boolean isIndexedWithQuestion();
	
	
	

}
