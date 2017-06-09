/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import java.util.Random;

/**
 * General utility class.
 *
 * @author navaile
 */
public class Util {
	
	/** Fisher-Yates shuffle.				*/
	public static void shuffle(Random rnd, Object[] arr) {

		for(int i = arr.length - 1; i > 0; i--) {

			int ind = rnd.nextInt(i + 1);
			// Simple swap
			Object a = arr[ind];
			arr[ind] = arr[i];
			arr[i] = a;
		}
	}
	
	/** Fisher-Yates shuffle.				*/
	public static void shuffle(Random rnd, int[] arr) {

		for(int i = arr.length - 1; i > 0; i--) {

			int ind = rnd.nextInt(i + 1);
			// Simple swap
			int a = arr[ind];
			arr[ind] = arr[i];
			arr[i] = a;
		}
	}
	
	// redundant
	/** Fisher-Yates shuffle.				*/
	public static void shuffle(Random rnd, String[] arr) {

		for(int i = arr.length - 1; i > 0; i--) {

			int ind = rnd.nextInt(i + 1);
			// Simple swap
			String a = arr[ind];
			arr[ind] = arr[i];
			arr[i] = a;
		}
	}
}
