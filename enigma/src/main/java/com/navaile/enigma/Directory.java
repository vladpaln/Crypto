/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.*;

/**
 * Loads a word directory of the 45K most frequent words in the english language.
 * The ability to build a brand new directory and to randomize it to increase
 * security are also handled by Directory.
 * 
 * To build a new directory simply run this class. To randomize run
 * Directory.randomizeDirectory(seed). Both parties must use the same seed to
 * insure same random directories for secure communications.
 *
 * @author navaile
 */
public class Directory {
	
	private static final Logger LOG = Logger.getLogger(Directory.class);
	
	/**
	 * resources class loading
	 * 
	 * access from static class:	/directory
	 *		Directory.class.getClass().getResource("/directory")
	 * 
	 * access from instance:		directory
	 *		getClass().getClassLoader().getResourceAsStream("directory")
	 * 
	 */
	
	private static Directory dir;
	
	/** Running this will build a new original directory file.	*/
	public static void main(String[] args) {
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
		
		buildDirectory();
	}
	
	private Directory() {
		loadDictionary();
	}
	
	/** Returns static instance of Directory DB.	*/
	public static Directory getInstance() {
		
		if(dir == null)		dir = new Directory();
		return dir;
	}
	
	/**
	 * Preprocess text, replace some punctuation with word equivalent.
	 * 
	 * @param plainText
	 * @return text with replaced punctuation
	 */
	private String preprocess(String plainText) {
		
		LOG.info("Directory.preprocess()");

		StrBuilder newMsg = new StrBuilder(plainText);

			newMsg.replaceAll("\\r\\n|\\r|\\n", " ")
//			.replaceAll("/", " forward slash ")
			.replaceAll("\\", " backward slash ")
//			.replaceAll("", "  ")
			.replaceAll("\\n", " ")
			.replaceAll("\\r", " ")
			.replaceAll(System.getProperty("line.separator"), " ")

//			.replaceAll("/", " division ")
//			.replaceAll("*", " asterisk ")
//			.replaceAll("-", " minus ")
//			.replaceAll("+", " plus ")
//			.replaceAll("=", " equals ")
//			.replaceAll(".", " period ")
//			.replaceAll(",", " comma ")
//			.replaceAll("<", " less than ")
//			.replaceAll(">", " greater than ")
//			
//			.replaceAll("`", " acute ")
//			.replaceAll("~", " tilde ")
//			.replaceAll("?", " question mark ")
//			.replaceAll(":", " colon ")
//			.replaceAll(";", " semicolon ")
//			.replaceAll("\"", " double quote ")
//			.replaceAll("'", " single quote ")
//			.replaceAll("|", " pipe ")
//			.replaceAll("_", " underscore ")
			
//			.replaceAll("!", " exclamation ")
			.replaceAll("@", " at ")
			.replaceAll("#", " hashtag ")
			.replaceAll("%", " percent ")
			.replaceAll("^", " caret ")
			.replaceAll("&", " ampersand ")
			.replaceAll("°", " degree ")
			
			.replaceAll("{", " open curly brace ")
			.replaceAll("}", " close curly brace ")
			.replaceAll("[", " open square bracket ")
			.replaceAll("]", " close square bracket ")
//			.replaceAll("(", " open parenthese ")
//			.replaceAll(")", " close parenthese ")

			/*		currency		*/
//			.replaceAll("$", " dollars ")
			.replaceAll("¢", " cents ")
			.replaceAll("€", " euro ")
			.replaceAll("¤", " currency sign ")
			.replaceAll("£", " pounds sterling ")
			.replaceAll("¥", " chinese/japenese yuan ")
			.replaceAll("§", " micro/section ")
			
			.replaceAll("\\s{2,}", " ")
			// .replaceAll("", "")
			.trim();
		
		return newMsg.toString();
	}

	/**
	 * Parse string into words.
	 * 
	 * @param text to parse
	 * @return returns a list of words
	 */
	public String[] parceWords(String text) {
		
		LOG.info("Directory.parceWords()");
		
		text = preprocess(text);
		return text.split(" ");
	}

	/** Converts word to keyCode.	*/
	public Integer getKeyCode(String word) {
		
		LOG.info("Directory.getKeyCode()");
		
		return freqWordKey.get(word);
	}
	
	/** Converts keyCode to word.				*/
	public String getWord(Integer keyCode) {
		
		LOG.info("Directory.getWord()");
		
		return freqKeyWord.get(keyCode);
	}
	
	/**
	 * Notes
	 * A native english speaker has a vocabulary of ~ 35K
	 * 
	 * keyCode 000 - ZZZ will provide a directory of 46655 words
	 * 
	 * english word frequency sources
	 * http://norvig.com/ngrams/
	 * http://www.insightin.com/esl/
	 * https://github.com/first20hours/google-10000-english
	 */
	
	// directory
	private static final HashMap<Integer, String> freqKeyWord = new HashMap<Integer, String>();
	private static final HashMap<String, Integer> freqWordKey = new HashMap<String, Integer>();
	
	private void loadDictionary() {
		
		LOG.info("Directory.loadDictionary()");

		InputStream is = getClass().getClassLoader().getResourceAsStream("directory");
		
		int index = 0;
		try(BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
			
			for(String word; (word = br.readLine()) != null; ) {

				if(word.length() != 0) {

					word = word.trim();
					freqKeyWord.put(index, word);
					freqWordKey.put(word, index);
					index++;
				}
			}
		}
		catch(Exception e) {	LOG.error("Error loading directory", e);	}
	}
	
	/**
	 * This method will load the directory, randomize it and save the randomized
	 * list to file. Randomizing the directory increases security however both
	 * parties must use the same seed when randomizing the directory to ensure
	 * that both parties have the same directory.
	 * 
	 */
	public static void randomizeDirectory(long seed) {
		
		LOG.info("Directory.loadDictionary()");

		ArrayList<String> wordList = new ArrayList<>();
//		InputStream is = Directory.class.getClass().getClassLoader().getResourceAsStream("/directory");
		String path = Directory.class.getClass().getResource("/directory").getPath();

		try(BufferedReader br = new BufferedReader(new FileReader(path))) {

			for(String word; (word = br.readLine()) != null; )
				wordList.add(word);
		}
		catch(Exception e) {	LOG.error("Error reading directory.", e);	}
		
		Random rnd = new Random(seed);
		String[] wordArr = wordList.toArray(new String[wordList.size()]);

		// Fisher–Yates shuffle
		for(int i = wordArr.length - 1; i > 0; i--) {

			int ind = rnd.nextInt(i + 1);
			// Simple swap
			String a = wordArr[ind];
			wordArr[ind] = wordArr[i];
			wordArr[i] = a;
		}
		
		// directory
		try {
			path = Directory.class.getClass().getResource("/directory").getPath();

			try(PrintWriter pw = new PrintWriter(path, "UTF-8")) {
				for(String word: wordArr)		pw.println(word.trim());
			}
			catch(IOException ex) {	LOG.error("Write Directory to File", ex);	}
		}
		catch(Exception e) {	LOG.error("Unable to access file.", e);	}
		
		getInstance().loadDictionary();
	}
	
	/** Builds directory from word frequency lists.			*/
	private static void buildDirectory() {
		
		LOG.info("Directory.buildDirectory()");
		
		Set<String> wordSet = new HashSet<>();
		
		String[] fileList = {
			"src/main/resources/5k_words.txt",
			"src/main/resources/10k_words.txt",
			"src/main/resources/60k_words.txt"
		};
		
		for(String p: fileList) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(p))) {
				for(String line; (line = br.readLine()) != null; ) {
					if(line.length() != 0)		wordSet.add(line.toLowerCase());
					if(wordSet.size() >= (Enigma4K.DIR_SIZE - 50))	break;
				}
			}
			catch(Exception ex) {	LOG.error("Builds Directory", ex);	}
		}
		
		// directory
		try {

			String path = Directory.class.getClass().getResource("/directory").getPath();
			
			try(PrintWriter pw = new PrintWriter(path, "UTF-8")) {

				for(String word: wordSet)		pw.println(word.trim());
				pw.println("--- CUST ---");

				// word partitioning
				pw.println("[");
				pw.println("]");

				// number partitioning
				for(int i = 0; i <= 9; i++)		pw.println(i);

	//			// symbols
				pw.println("`");
				pw.println("~");
				pw.println("?");
				pw.println(":");
				pw.println(";");
				pw.println("\"");
				pw.println("'");
				pw.println("_");
				pw.println("|");

				pw.println("/");
				pw.println("*");
				pw.println("-");
				pw.println("+");
				pw.println("=");
				pw.println(".");
				pw.println(",");
				pw.println("<");
				pw.println(">");
	//			
				pw.println("!");
	//			pw.println("@");
	//			pw.println("#");
	//			pw.println("%");
	//			pw.println("^");
	//			pw.println("&");
				pw.println("$");

				pw.println("(");
				pw.println(")");
			}
			catch(IOException ex) {	LOG.error("Write Directory to File", ex);	}
		}
		catch(Exception e) {	LOG.error("Unable to access file.", e);	}
	}
}
