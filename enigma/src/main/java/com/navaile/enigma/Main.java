/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;

/**
 *
 * @author vladpaln
 */
public class Main {
	
	private static final Logger LOG = LoggerFactory.getLogger(Main.class);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
			logger4j.setLevel(org.apache.log4j.Level.toLevel("ERROR"));
		
		String msgID = Enigma4K.genMsgID();
		String path = "src/main/resources/directory";
		Enigma4K enigma = new Enigma4K(path, "password", "vladpaln", msgID,
			Enigma4K.COUNT_MIN, Enigma4K.COUNT_MIN);
		
		String textLine = "this is a test of the emergency broadcast system.";
		String repeat = "this this this this this zzzz";
		
		String encTextLine = null;
		String encRepeat = null;
		try {
			encTextLine = enigma.encryptText(textLine);
			encRepeat = enigma.encryptText(repeat);
		}
		catch(Exception e) {
			LOG.error("Encrypt Error", e);
		}
		
		String decTextLine = null, decRepeat = null;

		try {
			Enigma4K.resetKey();
			decTextLine = enigma.decryptText(encTextLine);
			decRepeat = enigma.decryptText(encRepeat);
		}
		catch(Exception ex) {	LOG.error("Decrypt Text", ex);				}

		System.out.println("original: " + textLine);
		System.out.println("encoded: " + encTextLine);
		System.out.println("decoded: " + decTextLine);
		
		System.out.println("original: " + repeat);
		System.out.println("encoded: " + encRepeat);
		System.out.println("decoded: " + decRepeat);
	}
}
