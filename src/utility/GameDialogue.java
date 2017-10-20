package utility;


/**
 * Contains all display dialogue.
 * 
 * @author olamcdaniel
 *
 */
public class GameDialogue {
	
	private static StringBuilder welcomeSB = new StringBuilder();
	private static StringBuilder hintSB = new StringBuilder();
	private static StringBuilder hintPntSB = new StringBuilder();
	
	public static String WELCOME_MSG = 
			welcomeSB.append("Welcome to The Guessing Game!")
			.append(Constants.NEW_LINE)
			.append("Depending on the difficulty of the question, you may earn 1, 2, or 3 points. ")
			.append(" Your point total will be updated throughout the game. ")
		    .append(Constants.NEW_LINE)
		    .append("If the available points reach 0, the question will be discarded ")
			.append("and you'll be asked to choose another.")
			.append(Constants.NEW_LINE)
			.append("The game will start after you choose a number between 1 and 15.").toString();

	public static String NUMBER_CHOICE = "Please chose a number: ";
	public static String ANSWER_CHOICE = "Please enter your answer: ";
	public static String ONE_FIFTEEN = "Please choos a number between 1 and 15";
	public static String YOUR_ANSWER = "Your answer is correct!!! ";
	
	public static String POINT_TOTAL = "POINT TOTAL = ";
	public static String TOTAL_POINTS = "TOTAL POINTS = ";
	public static String POINTS_REMAINING = "Points remaining for this question = "; 
	
	public static String HINT_MSG = 
			hintSB.append("Your answer is incorrect.")
			.append(Constants.NEW_LINE)
			.append("Would you like a hint?")
			.append(Constants.NEW_LINE)
			.append("Accepting a hint will reduce your available points for this question by 1 point. ")
			.append(" Please enter YES or NO: ").toString();
	
	public static String HINT_PNTS_MSG = 
			hintPntSB.append("Your all out of hints for this question.  ")
			.append(Constants.NEW_LINE)
			.append("Would you like to continue playing? Please enter Yes or No: ")
			.toString();
	
	public static String CONTINUE_PLAY = "Would you like to continue playing? Please enter Yes or No: ";
	public static String QUIT = "Would you like to quit?";
	
	public static String HINT_WARNING = "You only have 1 point remaining.";
}
