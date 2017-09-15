/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.io.*;
import java.util.*;
import org.apache.commons.lang3.text.StrBuilder;
import org.slf4j.*;

/**
 * Loads a word directory of the 45K most frequent words in the english language.
 * The ability to build a brand new directory and to randomize it to increase
 * security are also handled by the Directory.
 * 
 * To build a new directory simply run this class. To randomize run
 * getInstance().randomizeDirectory(seed). Both parties must use the same
 * seed to insure same random directories for secure communications.
 *
 * @author vladpaln
 */
public class Directory {
	
	private static final Logger LOG = LoggerFactory.getLogger(Directory.class);
	
	/**
	 * resources class loading
	 * 
	 * access from static class:	/directory
	 *		Directory.class.getClass().getResource("/directory")
	 * 
	 * access from instance:		directory
	 *		getClass().getClassLoader().getResourceAsStream("directory")
	 * 
	 * 
	 */
	
	/**
	 * Notes
	 * A avg native english speaker has a vocabulary of ~ 35K
	 * 
	 * keyCode 000 - ZZZ will provide a directory of 46655 words
	 * 
	 * english word frequency sources
	 * http://norvig.com/ngrams/
	 * http://www.insightin.com/esl/
	 * https://github.com/first20hours/google-10000-english
	 */
	
	private volatile static Directory dir;
	
	private static boolean ordered = true;		// directory ordered flag
	private static String[] wordArr;			// holds word directory
	
	/** Running this will build a new original directory file.	*/
	public static void main(String[] args) {
//		BasicConfigurator.configure();
//		Logger.getRootLogger().setLevel(Level.ERROR);
		
		buildDirectory();
	}
	
	private Directory() {
		loadDictionary();
	}
	
	/** 
	 * Returns static instance of Directory DB.
	 * 
	 * @return Directory Singleton
	 */
	public static Directory getInstance() {

		Directory dir = Directory.dir;
		
		if(dir == null) {
			synchronized(Dictionary.class) {
				dir = Directory.dir;
				if(dir == null) {
					Directory.dir = dir = new Directory();
				}
			}
		}
		return dir;
	}
	
	/**
	 * Preprocess text, replace some punctuation with word equivalent.
	 * 
	 * @param plainText text to preprocess
	 * @return text with replaced punctuation
	 */
	private String preprocess(String plainText) {
		
		LOG.info("Directory.preprocess()");

		final StrBuilder newMsg = new StrBuilder(plainText);

			newMsg
			.replaceAll(System.getProperty("line.separator"), " ")
//			.replaceAll("", "  ")
					
			.replaceAll(".", " .")
			.replaceAll(",", " ,")
			.replaceAll(":", " :")
			.replaceAll(";", " ;")
					
			.replaceAll("(", " ( ")
			.replaceAll(")", " ) ")

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
//			.replaceAll("@", " at ")
//			.replaceAll("#", " hashtag ")
//			.replaceAll("%", " percent ")
//			.replaceAll("^", " caret ")
//			.replaceAll("&", " ampersand ")
			.replaceAll("°", " degree ")
			
//			.replaceAll("{", " open curly brace ")
//			.replaceAll("}", " close curly brace ")
//			.replaceAll("[", " open square bracket ")
//			.replaceAll("]", " close square bracket ")
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
			.trim();
			
		final String pattern = "[^\\s\\w\\p{Punct}]";		// disallowed chars
			
		plainText = newMsg.toString()
			.replaceAll("\\r\\n|\\r|\\n", " ")
			.replaceAll("\\s", " ")
			.replaceAll("\\s{2,}", " ")
				
			// removes nonstandard characters
			.replaceAll(pattern, " ");
		
		return plainText;
	}

	/**
	 * Parse string into words.
	 * 
	 * @param text to parse
	 * @return returns a list of words
	 */
	public String[] parceWords(final String text) {
		
		LOG.info("Directory.parceWords()");
		return preprocess(text).split(" ");
	}

	/** 
	 * Converts text word to keyCode.
	 * 
	 * @param word text word
	 * @return integer keyCode representing that word
	 */
	public Integer getKeyCode(String word) {
		
		LOG.info("Directory.getKeyCode()");
		
		/**
		 * If the directory is ordered, binarySearch is used, very fast,
		 * if the directory has been randomized then a slower loop is used.
		 */
		
		word = word.toLowerCase();
		
		if(ordered) {
			int i =  Arrays.binarySearch(wordArr, word);
			if(i >= 0) {	return i;										}
		}
		else {	// randomized directory
			for(int i = 0; i < wordArr.length; i++) {
				if(wordArr[i].equals(word)) {	return i;					}
			}
		}
		
		return null;
	}
	
	/**
	 * Converts keyCode to text word.
	 * 
	 * @param keyCode directory keyCode integer (index)
	 * @return text word
	 */
	public String getWord(final Integer keyCode) {
		
		LOG.info("Directory.getWord()");
		if(keyCode >= 0 && keyCode < wordArr.length) {
			return wordArr[keyCode];
		}
		else return null;
	}
	
	/** Loads directory from resource.		*/
	private void loadDictionary() {
		
		LOG.info("Directory.loadDictionary()");

		final List<String> arrList = new ArrayList<>(Crypt.DIR_SIZE);
		
		final InputStream is = getClass().getClassLoader().getResourceAsStream("directory");
		try( final BufferedReader br = new BufferedReader(new InputStreamReader(is)) ) {
			
			for(String word; (word = br.readLine()) != null; ) {

				if(word.length() != 0) {
					arrList.add(word.toLowerCase().trim());
				}
			}
			
			wordArr = arrList.toArray(new String[arrList.size()]);
			Arrays.sort(wordArr);
			ordered = true;

		} catch(Exception e) {	LOG.error("Error loading directory", e);	}
	}
	
	/**
	 * This method will randomize the directory based on the seed supplied.
	 * Randomizing the directory increases security however both parties must
	 * use the same seed when randomizing the directory to ensure that both
	 * parties have the same directory.
	 * 
	 * @param seed directory randomization seed
	 */
	public void randomizeDirectory(final Long seed) {
		
		LOG.info("Directory.randomizeDirectory({})", seed);
		
		ordered = seed == null;
		Arrays.sort(wordArr);
		
		if(!ordered) {	Util.shuffle(new Random(seed), wordArr);			}
	}

	/** Builds directory from word frequency lists.			*/
	private static void buildDirectory() {
		
		LOG.info("Directory.buildDirectory()");
		
		final String[] fileList = {
			"src/main/resources/5k_words.txt",
			"src/main/resources/10k_words.txt",
			"src/main/resources/60k_words.txt"
		};
		
		final Set<String> wordSet = new HashSet<>(Crypt.DIR_SIZE);
		for(String p: fileList) {

			try(final BufferedReader br = new BufferedReader(new FileReader(p))) {
				
				for(String line; (line = br.readLine()) != null; ) {
					
					if(line.length() != 0) {
						wordSet.add(line.toLowerCase());
					}
					
					if(wordSet.size() >= (Crypt.DIR_SIZE - 50)) {	break;	}
				}

			} catch(Exception ex) {	LOG.error("Builds Directory", ex);	}
		}
		
		// directory
		try {

			final String path = Directory.class.getClass().getResource("/directory").getPath();
			
			try(final PrintWriter pw = new PrintWriter(path, "UTF-8")) {

				wordSet.forEach((word) -> {		pw.println(word.trim());	});
				
				// value never used denotes custom additions to directory
				pw.println("--- CUST ---");

				// word/number/symbol partitioning
				pw.println("<%");
				pw.println("%>");

				// number partitioning
				for(int i = 0; i <= 9; i++) {	pw.println(i);				}

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
				pw.println("\\");

				pw.println("/");
				pw.println("*");
				pw.println("-");
				pw.println("+");
				pw.println("=");
				pw.println(".");
				pw.println(",");
				pw.println("<");
				pw.println(">");

				pw.println("!");
				pw.println("@");
				pw.println("#");
				pw.println("%");
				pw.println("^");
				pw.println("&");
				pw.println("$");

				pw.println("(");
				pw.println(")");
				pw.println("[");
				pw.println("]");
				pw.println("{");
				pw.println("}");
			} catch(IOException e) {	LOG.error("Write Directory to File", e);	}
			
		} catch(Exception e) {	LOG.error("Unable to access file.", e);	}
	}
}
