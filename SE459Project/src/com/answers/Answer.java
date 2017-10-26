/**
 * 
 */
package com.answers;

/**
 * @author olamcdaniel
 *
 */
public class Answer implements Answers {

	private String answer;
	private String hint;
	private int index;
	private int weight;
	
	/**
	 * @param index
	 * @param answer
	 * @param weight
	 * @param hint
	 */
	public Answer (int index, String answer, int weight, String hint) {
		this.index = index;
		this.answer = answer;
		this.weight = weight;
		this.hint = hint;
	}
	
	@Override
	public int getAnswerIndex() {
		
		return index;
	}

	@Override
	public String getAnswer() {
		
		return answer;
	}

	@Override
	public int getAnswerWeight() {
		
		return weight;
	}

	@Override
	public String getAnswerHint() {
		
		return hint;
	}

	@Override
	public boolean isIndexedWithQuestion() {
		
		return false;
	}

}
