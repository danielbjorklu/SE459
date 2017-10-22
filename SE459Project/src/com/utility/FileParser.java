/**
 * 
 */
package com.utility;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import java.io.FileReader;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.answers.Answer;
import com.answers.Answers;
import com.questions.Question;
import com.questions.Questions;
import com.utility.game.GameConstants;

/**
 * @author olamcdaniel
 *
 */
public class FileParser {
	private static final Logger LOG = LogManager.getLogger(FileParser.class);

	private static Map<Integer, Questions> questionMap = new HashMap<>();
	private static Map<Integer, Answers> answerMap = new HashMap<>();
	
	private static JSONParser parser = null;
	private static JSONArray jsonArray = null;
	private static JSONObject jsonObject = null;
	private static Object object = null;
	
	/**
	 * Process the question data.
	 * @param fileName the Question.JSON file
	 * @param type the key of the JSON array
	 * @return the list questions with the associated data
	 */
	public static Map<Integer, Questions> getQuestionInfo(String fileName, String type) {
		parser = new JSONParser();
		
		LOG.trace("Parsing questions.");
		if (fileName != null && type != null) {
			try{
				object = parser.parse(new FileReader(fileName));
				jsonObject = (JSONObject) object;
				jsonArray = (JSONArray) jsonObject.get(type);
				
				Iterator<?> jsonIterator = jsonArray.iterator();
				while(jsonIterator.hasNext()) {
					JSONObject jObj = (JSONObject) jsonIterator.next();
					if (jObj.containsKey(GameConstants.INDEX)) {
						
						if (type.equals("questions")) {
							
							int index = Converter.convertInteger(jObj.get(GameConstants.INDEX));
							String question = Converter.convertString(jObj.get(GameConstants.QUESTION));
							String category = Converter.convertString(jObj.get(GameConstants.CATEGORY));
							
							questionMap.put(index, new Question(index, question, category));
						}
					}	
				}
			} catch (Exception e) {
				
				LOG.error("Error parsing Questions.json file. Message: {}", e.getMessage());
			}
		}
		return questionMap;
	}

	
	/**
	 * Process the answer data.
	 * @param fileName JSON file name
	 * @param type the key of the JSON array
	 * @return the list of answers with the associated data
	 */
	public static Map<Integer, Answers> getAnswerInfo(String fileName, String type) {
		parser = new JSONParser();
		
		LOG.trace("Parsing answers.");
		if (fileName != null && type != null) {
			try{
				object = parser.parse(new FileReader(fileName));
				jsonObject = (JSONObject) object;
				jsonArray = (JSONArray) jsonObject.get(type);
				
				Iterator<?> jsonIterator = jsonArray.iterator();
				while(jsonIterator.hasNext()) {
					JSONObject jObj = (JSONObject) jsonIterator.next();
					if (jObj.containsKey(GameConstants.INDEX)) {
						
						if (type.equals("answers")) {
							
							int index = Converter.convertInteger(jObj.get(GameConstants.INDEX));
							String answer = Converter.convertString(jObj.get(GameConstants.ANSWER));
							int weight = Converter.convertInteger(jObj.get(GameConstants.WEIGHT));
							String hint = Converter.convertString(jObj.get(GameConstants.HINT));
							
							answerMap.put(index, new Answer(index, answer, weight, hint));
						}
					}	
				}
			} catch (Exception e) {
				LOG.error("Error parsing Answers.json file. Message: {}", e.getMessage());
			}
		}
		return answerMap;
	}
}
