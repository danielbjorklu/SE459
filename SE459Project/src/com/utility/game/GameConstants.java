/**
 * 
 */
package com.utility.game;

import java.io.File;

/**
 * @author olamcdaniel
 *
 */
public class GameConstants {
	
	private GameConstants(){}
	
	
	/*
	 * JSON Paths
	 */
	public static final String ANSWER_PATH = new File("JSON\\Answers.json").getAbsolutePath();
	public static final String QUESTION_PATH = new File("JSON\\Questions.json").getAbsolutePath();
	
	/*
	 * General GameConstants
	 */
	public static final String YES = "yes";
	public static final String NO = "no";
	public static final String EMPTY_CHAR = "";
	
	/* 
	 * Question and Answer GameConstants
	 */
	public static final String INDEX = "index";
	public static final String QUESTION = "question";
	public static final String ANSWER = "answer";
	public static final String HINT = "hint";
	public static final String WEIGHT = "weight";
	public static final String CATEGORY = "category";
	
	
	/*
	 *  Regular Expressions
	 */
	// any non-word character
	public static final String SPECIAL_CHARS = "[^\\W]+";
	public static final String LETTERS = "[a-zA-Z]+";
	// [a-zA-z_0-9]
	public static final String WORD_CHAR = "\\w+";
	// [0-9]
	public static final String NUMBERS = "\\d+";
	// whitespace
	public static final String WHITESPACE = "\\s+";
	// new line
	public static final String NEW_LINE = "\n";
	
	public static final String DOUBLE_SPACE = NEW_LINE + NEW_LINE;
}
