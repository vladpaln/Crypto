/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;

import org.junit.*;

/**
 *
 * @author vladpaln
 */
public class Enigma4kTest {
	
	private Enigma4K enigma;
	
	private static String plainText = "this is a test of the emergency broadcast system.";
	private static String punct = "supercalifragilisticexpialidocious zzzz didn't ` ~ ? : ; \" ' _ | / * - + = . , < > ! $ ( ) %";
	
	@Before
	public void iniEnigma4KTest() {
		
		System.out.println("Enigma4kTest.iniEnigma4KTest()");
		String msgID = Enigma4K.genMsgID();
		enigma = new Enigma4K("password", "vladpaln", msgID, 13, 13);
	}
	
	@org.junit.Test
	public void enigmaTest() {
		
		try {
			
			Enigma4K.resetKey();
			String cryptText = enigma.encryptText(plainText);
			Enigma4K.resetKey();
			String decryptedText = enigma.decryptText(cryptText);
			
			System.out.println(cryptText);
			System.out.println(decryptedText);
			
			Assert.assertTrue(plainText.equalsIgnoreCase(decryptedText));
		}
		catch(Exception e) {
			System.err.println(e);
		}
		
		try {
			
			Enigma4K.resetKey();
			String cryptText = enigma.encryptText(punct);
			Enigma4K.resetKey();
			String decryptedText = enigma.decryptText(cryptText);
			
			System.out.println(cryptText);
			System.out.println(decryptedText);
			
			punct = "SUPERCALIFRAGILISTICEXPIALIDOCIOUS ZZZZ DIDN'T ` ~ ? : ; \" ' _ | / * - + = . , < > ! $ ( ) PERCENT";
			Assert.assertTrue(punct.equalsIgnoreCase(decryptedText));
		}
		catch(Exception e) {
			System.err.println(e);
		}
	}	
}
