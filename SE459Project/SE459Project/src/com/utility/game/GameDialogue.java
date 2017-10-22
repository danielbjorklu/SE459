package com.utility.game;

/**
 * Contains all display dialogue.
 * 
 * @author olamcdaniel
 *
 */
public class GameDialogue {
	
	private static final StringBuilder welcomeSB = new StringBuilder();
	private static final StringBuilder hintSB = new StringBuilder();
	private static final  StringBuilder hintPntSB = new StringBuilder();
	
	public static final String WELCOME_MSG = 
			welcomeSB.append("Welcome to The Guessing Game!")
			.append(GameConstants.NEW_LINE)
			.append("Depending on the difficulty of the question, you may earn 1, 2, or 3 points. ")
			.append(" Your point total will be updated throughout the game. ")
		    .append(GameConstants.NEW_LINE)
		    .append("If the available points reach 0, the question will be discarded ")
			.append("and you'll be asked to choose another.")
			.append(GameConstants.NEW_LINE)
			.append("The game will start after you choose a number between 1 and 15.").toString();

	public static final String NUMBER_CHOICE = "Please choose a number between 1 & 15: ";
	public static final String ANSWER_CHOICE = "Please enter your answer: ";
	public static final String WRONG_NUMBER_SIZE = "Your choice is incorrect. " + NUMBER_CHOICE;
	public static final String YOUR_ANSWER = "Your answer is correct!!! ";
	
	public static final String POINT_TOTAL = "You currently have ";
	public static final String POINTS = " points.";
	public static final String POINT = " point.";
	public static final String FINAL_POINT_TOTAL = "You finished the game with ";
	public static final String POINTS_REMAINING = "Points remaining for this question = "; 
	
	public static final String HINT_MSG = 
			hintSB.append("Your answer is incorrect.")
			.append(GameConstants.NEW_LINE)
			.append("Would you like a hint?")
			.append(GameConstants.NEW_LINE)
			.append("Accepting a hint will reduce your available points for this question by 1 point. ")
			.append(" Please enter YES or NO: ").toString();
	
	public static final String HINT_PNTS_MSG = 
			hintPntSB.append("Your all out of hints for this question.  ")
			.append(GameConstants.NEW_LINE)
			.append("Would you like to continue playing? Please enter Yes or No: ")
			.toString();
	
	public static final String CONTINUE_PLAY = "Would you like to continue playing? Please enter Yes or No: ";
	public static final String QUIT = "Would you like to quit?  Please enter Yes or No: ";
	public static final String EXIT_MESSAGE = GameConstants.NEW_LINE + "Thanks for playing...";
	public static final String HINT_WARNING = "You only have 1 point remaining. This is your last attempt at this question.";
}
