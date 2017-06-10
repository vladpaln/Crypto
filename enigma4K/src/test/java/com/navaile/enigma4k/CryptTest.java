/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.util.Random;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Crypt unit test class extends Crypt.
 *
 * @author navaile
 */
public class CryptTest extends Crypt {
	
	private static final String PASS = "passPhrase";
	private static final String HANDLE = "handle";
	private static final int RO_COUNT = 250;
	private static final int PB_COUNT = 100;
	private static final String MSG = "Some secret message.";
	
	private static final String MSG_ID = Crypt.genMsgID();
	
	private static final Random RND = new Random();
	
	public CryptTest() {
		super(PASS, HANDLE, MSG_ID, RO_COUNT, PB_COUNT, true);
	}
	
	@Test
	public void cryptTest() {
		
		try {
			final String cryptText = Crypt.encryptText(PASS, HANDLE, RO_COUNT,
					PB_COUNT, null, MSG);
			final String text = Crypt.decryptText(PASS, HANDLE, RO_COUNT,
					PB_COUNT, null, cryptText);
			
			Assert.assertEquals("Encrypt/decrypt cycle failed, plainText: " +
				MSG + ", cryptText: " + cryptText + ", decryptText: " +
				text, MSG.toLowerCase(), text.toLowerCase());
		}
		catch(Exception ex) {	ex.printStackTrace();					}
	}
	
	@Test
	public void setCountTest() {

		int min = 0, max = 1000, num = 500;
		
		int val = this.setCount(RND, num, min, max);
		assertTrue("Value outside bounds, min/max/val: " + min + "/" + max + "/" + val,
			val >= min && val <= max && val < num);
		
		num = -5;
		val = this.setCount(RND, num, min, max);
		assertTrue("Min bound failed, min/val: " + min + "/" + val, val == min);
		
		num = 1250;
		val = this.setCount(RND, num, min, max);
		assertTrue("Max bound failed, max/val: " + max + "/" + val, val <= max);
	}
	
	@Test
	public void iniMatrixTest() {
		
		final int[][] matrix = this.iniMatrix(RND, RO_COUNT, DIR_SIZE, true);
		assertTrue("Incorrect count, exp: " + RO_COUNT + ", act: " + matrix.length,
			matrix.length == RO_COUNT);
		assertTrue("Incorrect directory size: " + matrix[0].length, matrix[0].length == DIR_SIZE);
	}
	
	@Test
	public void genMsgIDTest() {
		assertTrue("Incorrect msgID size", Crypt.genMsgID().length() == 9);
	}
	
	@Test
	public void roSpinTest() {
		final int[] roSpin = this.roSpin(RND, RO_COUNT);
		for(int i: roSpin)
			if(Math.abs(i) != 1)	fail("Spin must be 1 or -1, act: " + i);
	}
	
	@Test
	public void stepRotorsTest() {
		
		final Random r = new Random(0);
		
		int[] rotorInd = {120, 132, 141, 137, 146};
		int[] spin = {1, -1, -1, 1, 1};
		int dirSize = 250;
		
		this.stepRotors(r, rotorInd, spin, dirSize);
		assertArrayEquals("Step failed.", rotorInd, new int[] {122, 130, 121, 148, 159});
		
		rotorInd = new int[] {249, 0, 249, 0, 249};
		this.stepRotors(r, rotorInd, spin, dirSize);
		assertArrayEquals("Step over bounds failed.", rotorInd, new int[] {6, 249, 244, 7, 14});
	}

	@Test
	public void padTextTest() {
		assertTrue("Pad size failed.", Crypt.padText("test", 13, "X").length() == 13);
		assertTrue("Pad string equals failed.",
			Crypt.padText("test", 13, "X").equalsIgnoreCase("XXXXXXXXXtest"));
		assertTrue("Pad minSize failed.", Crypt.padText("test", 2, "X").length() == 4);
	}

	@Test
	public void emptyTest() {
		
	}
}
