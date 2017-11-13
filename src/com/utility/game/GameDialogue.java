package com.utility.game;

/**
 * Contains all display dialogue.
 * 
 * @author olamcdaniel
 *
 */
public class GameDialogue {
	
	private static final StringBuilder welcomeSB = new StringBuilder();
	private static final StringBuilder gameRules = new StringBuilder();
	private static final StringBuilder hintSB = new StringBuilder();
	private static final StringBuilder hintPntSB = new StringBuilder();
	

	public static final String GAME_RULES = 
			gameRules
			.append(GameConstants.NEW_LINE)
			.append("Depending on the difficulty of the question, you may earn 1, 2, or 3 points. ")
			.append(GameConstants.NEW_LINE)
			.append("If you don't know the answer to the question, you'll be given the opportunity to take a hint.")
			.append(GameConstants.NEW_LINE)
			.append("Accepting a hint will reduce your available points for the current question by 1 point.")
			.append(GameConstants.NEW_LINE)
			.append("If the available points reach 0, the question will be discarded ")
			.append("and you'll be asked to choose another.")
		    .append(GameConstants.DOUBLE_SPACE)
		    .append("Your point total will be updated throughout the game. ")
			.toString();
		    // need to add a random question generator
			//.append("The game will start after you choose a number between 1 and 15.").toString();
	
	public static final String WELCOME_MSG = 
			welcomeSB		
				.append("Welcome to Agile Greatness!")
				.append(GameConstants.DOUBLE_SPACE)
				.append("You can play alone or in a team based game. ")
				.append(GameConstants.NEW_LINE)
				.append("Enter 1 for a single player game or 2 to play a team based game. ")
				.append(GameConstants.NEW_LINE)
				.append("Please enter your game type: ").toString();
						
	public static final String CHOSEN_NUMBER = "Please choose a number between 1 & 15: ";

	public static final String ENTERED_ANSWER = "Please enter your answer: ";
	public static final String WRONG_NUMBER_SIZE = "Your choice is incorrect. " + CHOSEN_NUMBER;
	public static final String CORRECT_ANSWER = "Your answer is correct!!! ";
	
	public static final String CURRENT_POINT_TOTAL = "You currently have ";
	public static final String POINTS = " points.";
	public static final String POINT = " point.";
	public static final String FINAL_POINT_TOTAL = "You finished the game with ";
	public static final String POINTS_REMAINING = "Points remaining for this question = "; 
	public static final String QUESTION_INITIAL_WEIGHT = "This question is worth ";
	public static final String QUESTION_ALREADY_ANSWERED = "This question has already been answered. Please pick a different question.";
	
	public static final String INCORRECT_HINT_ANSWER = 
			hintSB.append("Your answer is incorrect.")
			.append(GameConstants.NEW_LINE)
			.append("Would you like a hint?")
			.append(GameConstants.DOUBLE_SPACE)
			.append("Remember, accepting a hint will reduce your available points for this question by 1 point. ")
			.append(GameConstants.NEW_LINE)
			.append("Please enter YES or NO: ").toString();
	
	public static final String NO_HINTS_LEFT = 
			hintPntSB.append("Your all out of hints for this question.  ")
			.append(GameConstants.NEW_LINE)
			.append("Would you like to continue playing? Please enter Yes or No: ")
			.toString();
	
	public static final String CONTINUE_PLAY = "Would you like to continue playing? Please enter Yes or No: ";
	
	public static final String EXIT_MESSAGE = GameConstants.NEW_LINE + "Thanks for playing...";
	public static final String HINT_WARNING = "WARNING: You only have 1 point remaining. This is your last attempt at this question.";
	public static final String SINGLE_PLAYER_GAME = GameConstants.NEW_LINE + "Starting single player game..." ;
	public static final String TEAM_PLAYER_GAME = "How many players are playing today? " + GameConstants.NEW_LINE + "Please enter a number between 1 and 8: ";
	public static final String REPLAY_GAME = GameConstants.NEW_LINE + "Starting game over...";
}
