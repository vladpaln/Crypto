/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.BreakIterator;
import java.util.*;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vladpaln
 */
public class Directory {
	
	private static final Logger LOG = LoggerFactory.getLogger(Directory.class);
	
	private static String path;
	
	/** Word count reserved for custom vocab.		*/
	public static final int CUST_VOCAB_SIZE = 5_000;		// custom vocabulary size
	
	public Integer version = null;
	
	private static Directory dir;
	
	public static void main(String[] args) {
		BasicConfigurator.configure();
		buildDirectory();
	}
	
	private Directory() {
		loadDictionary();
	}
	
	/** Returns static instance of Directory DB.	*/
	public static Directory getInstance(String path) {
		
		Directory.path = path;
		
		if(dir == null)		dir = new Directory();
		return dir;
	}
	
	/**
	 * Preprocess text, replace all digits and symbols with word equivalent.
	 * 
	 * @param msg
	 * @return 
	 */
	private String preprocess(String msg) {

		StrBuilder newMsg = new StrBuilder(msg);

			newMsg
			
			.replaceAll("\\r\\n|\\r|\\n", " ")
			.replaceAll("/", " forward slash ")
			.replaceAll("\\", " backward slash ")
			.replaceAll("", "  ")
			.replaceAll("\\n", " ")
			.replaceAll("\\r", " ")
			.replaceAll(System.getProperty("line.separator"), " ")

			.replaceAll("*", " asterisk ")
			.replaceAll("-", " minus ")
			.replaceAll("+", " plus ")
			.replaceAll("=", " equals ")
			.replaceAll(".", " period ")
			.replaceAll(",", " comma ")
			.replaceAll("<", " less than ")
			.replaceAll(">", " greater than ")
			
			.replaceAll("`", " acute ")
			.replaceAll("~", " tilde ")
			.replaceAll("?", " question mark ")
			.replaceAll(":", " colon ")
			.replaceAll(";", " semicolon ")
			.replaceAll("\"", " double quote ")
			.replaceAll("'", " single quote ")
			.replaceAll("|", " pipe ")
			.replaceAll("_", " underscore ")
			
			.replaceAll("!", " exclamation ")
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
			.replaceAll("(", " open parenthese ")
			.replaceAll(")", " close parenthese ")

			/*		currency		*/
			.replaceAll("$", " dollars ")
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
		
		text = preprocess(text);	// remove punctuation
		return text.split(" ");

//		ArrayList<String> words = new ArrayList<>();
//		BreakIterator breakIterator = BreakIterator.getWordInstance();
//			breakIterator.setText(text);
//			
//		int lastIndex = breakIterator.first();
//		while(BreakIterator.DONE != lastIndex) {
//			int firstIndex = lastIndex;
//			lastIndex = breakIterator.next();
//
//			if(lastIndex != BreakIterator.DONE && Character.isLetterOrDigit(text.charAt(firstIndex))) {
//				words.add(text.substring(firstIndex, lastIndex));
//			}
//		}
//
//		return words.toArray(new String[words.size()]);
	}

	/** Converts word to keyCode.	*/
	public Integer getKeyCode(String word) {
		return freqWordKey.get(word);
	}
	
	/** Converts keyCode to word.				*/
	public String getWord(Integer keyCode) {
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
	 * 
	 */
	
	// directory
	private static final HashMap<Integer, String> freqKeyWord = new HashMap<Integer, String>();
	private static final HashMap<String, Integer> freqWordKey = new HashMap<String, Integer>();
	
	private void loadDictionary() {
		
		LOG.info("Directory.loadDictionary()");
			
		try(BufferedReader br = new BufferedReader(new FileReader(path))) {
			
			for(String line; (line = br.readLine()) != null; ) {
				
				String[] splitStr = line.split("\\|");

				if(line.length() != 0 && !line.contains("##")) {

					int index = Integer.parseInt(splitStr[0]);
					String word = splitStr[1];

					freqKeyWord.put(index, word);
					freqWordKey.put(word, index);
				}
				else if(line.contains("## ver")) {
					version = Integer.parseInt(splitStr[1]);
				}
			}
		}
		catch(Exception ex) {	LOG.error("Error Loading Directory", ex);	}
	}
	
	/** Builds directory from word frequency lists.			*/
	public static void buildDirectory() {
		
		Set<String> wordSet = new HashSet<>();
		
		String[] fileList = {
			"src/main/resources/5k_words.txt",
			"src/main/resources/10k_words.txt",
			"src/main/resources/60k_words.txt"
		};
		
		for(String path: fileList) {
			
			try(BufferedReader br = new BufferedReader(new FileReader(path))) {
				for(String line; (line = br.readLine()) != null; ) {
					if(line.length() != 0)		wordSet.add(line.toLowerCase());
					if(wordSet.size() >= (Enigma4K.DIR_SIZE - 50))	break;
				}
			}
			catch(Exception ex) {	LOG.error("Builds Directory", ex);	}
		}
		
		// directory
		try(PrintWriter pw = new PrintWriter("src/main/resources/directory", "UTF-8")) {
			
			int index = 0;
			for(String word: wordSet)		pw.println(index++ + "|" + word);
			pw.println(index++ + "|--- CUST ---");
			
			// word partitioning
			pw.println(index++ + "|[");
			pw.println(index++ + "|]");
			
			// number partitioning
			for(int i = 0; i <= 9; i++)		pw.println(index++ + "|" + i);
			
//			// symbols
//			pw.println(index++ + "|`");
//			pw.println(index++ + "|~");
//			pw.println(index++ + "|?");
//			pw.println(index++ + "|:");
//			pw.println(index++ + "|\"");
//			pw.println(index++ + "|'");
//			pw.println(index++ + "|_");		
//			
//			pw.println(index++ + "|*");
//			pw.println(index++ + "|-");
//			pw.println(index++ + "|+");
//			pw.println(index++ + "|=");
//			pw.println(index++ + "|.");
//			pw.println(index++ + "|,");
//			pw.println(index++ + "|<");
//			pw.println(index++ + "|>");
//			
//			pw.println(index++ + "|!");
//			pw.println(index++ + "|@");
//			pw.println(index++ + "|#");
//			pw.println(index++ + "|%");
//			pw.println(index++ + "|^");
//			pw.println(index++ + "|&");
//			pw.println(index++ + "|$");

		}
		catch(IOException ex) {	LOG.error("Write Directory to File", ex);	}
	}

//	/**
//	 * Adds to custom dictionary.
//	 * 
//	 * @param word to add to custom dictionary
//	 */
//	public static Integer addCustDirectory(String word) {
//		
//		int index = freqKeyWord.size();
//
//		freqKeyWord.put(index, word);
//		freqWordKey.put(word, index);
//		
//		writeCustDirectory(index, word);
//		
////		System.out.println("Custom Dictionary: " + index + ", " + word);
//		
//		return index;
//	}
	
//	public static void writeCustDirectory(int index, String word) {
//		
//		try {
//			Files.write(Paths.get("src/main/resources/directory"),
//				(index + "|" + word + "\n").getBytes(), StandardOpenOption.APPEND);
//		}
//		catch (IOException ex) {
//			LOG.error("Write Custom Directory Word", ex);
//		}
//	}
}
