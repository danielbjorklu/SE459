/**
 * 
 */
package utility;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import answers.Answer;
import answers.Answers;
import questions.Question;
import questions.Questions;

/**
 * @author olamcdaniel
 *
 */
public class FileParser {
	private static ArrayList<Answers> answersList = new ArrayList<>();
	private static ArrayList<Questions> questionsList = new ArrayList<>();
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
	public static ArrayList<Questions> getQuestionInfo(String fileName, String type) {
		parser = new JSONParser();
		
		if (fileName != null && type != null) {
			try{
				object = parser.parse(new FileReader(fileName));
				jsonObject = (JSONObject) object;
				jsonArray = (JSONArray) jsonObject.get(type);
				
				Iterator<?> jsonIterator = jsonArray.iterator();
				while(jsonIterator.hasNext()) {
					JSONObject jObj = (JSONObject) jsonIterator.next();
					if (jObj.containsKey(Constants.INDEX)) {
						
						if (type.equals("questions")) {
							
							int index = Converter.convertInteger(jObj.get(Constants.INDEX));
							String question = Converter.convertString(jObj.get(Constants.QUESTION));
							String category = Converter.convertString(jObj.get(Constants.CATEGORY));
							
							questionsList.add(new Question(index, question, category));
						}
					}	
				}
			} catch (Exception e) {
				// TODO: handle exceptions better
				System.out.println(e.getMessage());
			}
		}
		return questionsList;
	}
	
	/**
	 * Process the answer data.
	 * @param fileName JSON file name
	 * @param type the key of the JSON array
	 * @return the list of answers with the associated data
	 */
	public static ArrayList<Answers> getAnswerInfo(String fileName, String type) {
		parser = new JSONParser();
		
		if (fileName != null && type != null) {
			try{
				object = parser.parse(new FileReader(fileName));
				jsonObject = (JSONObject) object;
				jsonArray = (JSONArray) jsonObject.get(type);
				
				Iterator<?> jsonIterator = jsonArray.iterator();
				while(jsonIterator.hasNext()) {
					JSONObject jObj = (JSONObject) jsonIterator.next();
					if (jObj.containsKey(Constants.INDEX)) {
						
						if (type.equals("answers")) {
							
							int index = Converter.convertInteger(jObj.get(Constants.INDEX));
							String answer = Converter.convertString(jObj.get(Constants.ANSWER));
							int weight = Converter.convertInteger(jObj.get(Constants.WEIGHT));
							String hint = Converter.convertString(jObj.get(Constants.HINT));
							
							answersList.add(new Answer(index, answer, weight, hint));
						}
					}	
				}
			} catch (Exception e) {
				//TODO: we need to figure out a way to better handle exceptions
				System.out.println(e.getMessage());
			}
		}
		return answersList;
	}
}
