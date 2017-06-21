/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import com.navaile.junitreflect.*;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Crypt unit test class extends Crypt.
 *
 * @author navaile
 */
public class CryptTest extends ClassParser<Crypt> {
	
	private static int DIR_SIZE;			// ZZZ (base36)
	
	private static final String PASS = "passPhrase";
	private static final String HANDLE = "handle";
	private static final int RO_COUNT = 250;
	private static final int PB_COUNT = 100;
	private static final String MSG = "Some secret message.";
	
	private static final String MSG_ID = Crypt.genMsgID();
	
	private static final Random RND = new Random();
	
	private static final Crypt inst = new Crypt(PASS, HANDLE, MSG_ID, RO_COUNT, PB_COUNT, true);
	
	@BeforeClass
	public static void ini() {}
	
	@Before
	public void reset() {
		
		setInstance(inst);

		try {
			DIR_SIZE = getField(Integer.class, "DIR_SIZE");
//			System.out.println("DIR_SIZE: " + DIR_SIZE);
//			setField("DIR_SIZE", DIR_SIZE + 10);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
//		DIR_SIZE = 46_655;
		
		// @Before called before every @Test method
		// used to reset fields
	}

	@Test
	public void cryptTest() {

		final String cryptText = Crypt.encryptText(PASS, HANDLE, RO_COUNT,
				PB_COUNT, null, MSG);
		final String text = Crypt.decryptText(PASS, HANDLE, RO_COUNT,
				PB_COUNT, null, cryptText);

		Assert.assertEquals("Encrypt/decrypt cycle failed, plainText: " +
			MSG + ", cryptText: " + cryptText + ", decryptText: " +
			text, MSG.toLowerCase(), text.toLowerCase());
	}
	
	@Test
	public void setCountTest() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		int min = 0, max = 1000, num = 500;

		int val = T_method_args(Integer.class, "setCount", RND, num, min, max);
		assertTrue("Value outside bounds, min/max/val: " + min + "/" + max + "/" + val,
			val >= min && val <= max && val < num);

		num = -5;
		val = T_method_args(Integer.class, "setCount", RND, num, min, max);
		assertTrue("Min bound failed, min/val: " + min + "/" + val, val == min);

		num = 1250;
		val = T_method_args(Integer.class, "setCount", RND, num, min, max);
		assertTrue("Max bound failed, max/val: " + max + "/" + val, val <= max);
	}
	
	@Test
	public void iniMatrixTest() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		final int[][] matrix = T_method_args(int[][].class, "iniMatrix", RND, RO_COUNT, DIR_SIZE, true);
		assertTrue("Incorrect count, exp: " + RO_COUNT + ", act: " + matrix.length,
			matrix.length == RO_COUNT);
		assertTrue("Incorrect directory size: " + matrix[0].length, matrix[0].length == DIR_SIZE);
	}
	
	@Test
	public void genMsgIDTest() {
		assertTrue("Incorrect msgID size", Crypt.genMsgID().length() == 9);
	}
	
	@Test
	public void roSpinTest() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {

		final int[] roSpin = T_method_args(int[].class, "roSpin", RND, RO_COUNT);
		for(int i: roSpin)
			if(Math.abs(i) != 1)	fail("Spin must be 1 or -1, act: " + i);
	}
	
	@Test
	public void stepRotorsTest() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		final Random r = new Random(0);

		int[] rotorInd = {120, 132, 141, 137, 146};
		int[] spin = {1, -1, -1, 1, 1};
		int dirSize = 250;

		method_args("stepRotors", r, rotorInd, spin, dirSize);
		assertArrayEquals("Step failed.", rotorInd, new int[] {122, 130, 121, 148, 159});

		rotorInd = new int[] {249, 0, 249, 0, 249};
		method_args("stepRotors", r, rotorInd, spin, dirSize);
		assertArrayEquals("Step over bounds failed.", rotorInd, new int[] {6, 249, 244, 7, 14});
	}

	@Test
	public void padTextTest() throws IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		
		assertTrue("Pad size failed.", (T_method_args(String.class, "padText", "test", 13, "X")).length() == 13);
		assertTrue("Pad string equals failed.",
			(T_method_args(String.class, "padText", "test", 13, "X")).equalsIgnoreCase("XXXXXXXXXtest"));
		assertTrue("Pad minSize failed.", (T_method_args(String.class, "padText", "test", 2, "X")).length() == 4);
	}

	@Test
	public void emptyTest() {}
}
