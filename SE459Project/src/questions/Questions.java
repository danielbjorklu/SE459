/**
 * 
 */
package questions;

/**
 * @author olamcdaniel
 *
 */
public interface Questions {
	
	/**
	 * 
	 * @return A random question
	 */
	public String getQuestion();
	
	/**
	 * 
	 * @return The questions category.
	 */
	public String getCategory();
	
	/**
	 * 
	 * @return The questions index.
	 */
	public int getQuestionIndex();
	
	/**
	 * Once this question is chosen, this should be set to true.
	 * If <code>true</code> then skip this question.
	 * 
	 * @return true if the question has been asked, false otherwise
	 */
	public boolean isQuestionCurrent();

}
