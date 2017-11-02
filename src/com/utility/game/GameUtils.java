/**
 * 
 */
package com.utility.game;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.player.IPlayer;

/**
 * @author olamcdaniel
 *
 */
public class GameUtils {
	private static final Logger LOG = LogManager.getLogger(GameUtils.class);
	
	private Scanner scanner;

	private boolean isGameOver;
	
	/**
	 * Instantiate scanners.
	 */
	public GameUtils() {}
	
	/**
	 * Input method for String arguments.
	 * 
	 * @param in the string argument
	 * 
	 * @return the validated argument value
	 */
	public String stringScannerIn(String in) {
		try {
			scanner = new Scanner(System.in);
			in = scanner.nextLine();
		} catch (InputMismatchException ime) {
			LOG.error("Player {} entered an incorrect value. Message: {}", "NEED_TO_IMPLEMENT", ime.getMessage());
			System.out.println("Please only enter letters.");
		}
		return in;
	}

	/**
	 * Input method for Integer arguments.
	 * 
	 * @param in the integer argument
	 * 
	 * @return the validated argument value
	 */
	public int intScannerIn(int in) {
		try {
			scanner = new Scanner(System.in);
			in = scanner.nextInt();
		} catch (InputMismatchException ime) {
			LOG.error("IPlayer {} entered an incorrect value. Message: {}", "NEED_TO_IMPLEMENT", ime.getMessage());
			System.out.println("Please only enter numbers.");
			
		}
		return in;
	}
	
	/**
	 * Handles the game continuation logic.
	 * 
	 * @param qAnswer yes or no
	 * @param iPlayer the current player
	 * @return true if the player wishes to continue playing, false otherwise
	 */
	public boolean keepPlaying(String qAnswer, IPlayer iPlayer) {
		if (qAnswer.toLowerCase().equals(GameConstants.YES)) {
			LOG.debug("Starting game over.");
			System.out.println(GameConstants.NEW_LINE + "Setting up next round..." + GameConstants.NEW_LINE);
			isGameOver = false;
		} else if (qAnswer.toLowerCase().equals(GameConstants.NO)){	
			LOG.debug("{} has chosen to quit the game.", iPlayer.getPlayerId());
			exitGame(iPlayer.getPlayerScore());
		} else {
			System.out.println(qAnswer + " isn't an option.  Please try again");
			keepPlaying(stringScannerIn(qAnswer), iPlayer);
		}
		return isGameOver;
	}
	
	/**
	 * Exit the game.  
	 * 
	 * @param pointTotal the player/team point total for display on exit.
	 */
	private void exitGame(Integer pointTotal) {
		
		scanner.close();
		
		if (pointTotal == null) {
			System.out.println("You scored no points during the current game.");			
		} else if (pointTotal == 1) {
			System.out.println(GameDialogue.FINAL_POINT_TOTAL + pointTotal + GameDialogue.POINT);
		} else {
			System.out.println(GameDialogue.FINAL_POINT_TOTAL + pointTotal + GameDialogue.POINTS);
		}
		
		System.exit(1);
	}

}
