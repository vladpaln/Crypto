/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;

import com.zackehh.siphash.SipHash;
import com.zackehh.siphash.SipHashCase;
import com.zackehh.siphash.SipHashResult;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author vladpaln
 */
public class Test {
	
	private static final Logger LOG = LoggerFactory.getLogger(Test.class);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		
//		ArrayList<String> rotorList = new ArrayList<>();
//		
//			rotorList.add("a");
//			rotorList.add("b");
//			rotorList.add("c");
//			rotorList.add("d");
//			rotorList.add("e");
//			
//		System.out.print(rotorList);
//		
//			rotorList.add(rotorList.remove(0));
//			rotorList.add(rotorList.remove(0));
//			
//		System.out.print(rotorList);
//		
//			rotorList.add(0, rotorList.remove(rotorList.size() - 1));
//			
//		System.out.print(rotorList);
//		
//		
//		Set<String> set = new HashSet<String>();
//			set.add("a");
//			set.add("b");
//			set.add("c");
//			set.add("d");
//			set.add("ff");
//			set.add("ff");
//			set.add("FF");
//			set.add("z");
//			
//		System.out.print(set);
		
//		String msg = "this is a test of the emergency broadcast system; msg ID # \\12300456";
//		
//		StrBuilder ttest = new StrBuilder(msg);
//			ttest.replaceAll("\\", " backward slash ");
//			
//		System.out.print(ttest);

//		System.out.println(AdvEnigma.hashSHA3_256(""));

//		Integer[] rotorIndex = {0, 0, 0, 0, 0};
//		AdvEnigma.stepRotors(rotorIndex);
//		System.out.println("Array: " + Arrays.toString(rotorIndex));
//		
//		for(int i = 0; i < 10; i++)
//			AdvEnigma.stepRotors(rotorIndex);
//		System.out.println("Array: " + Arrays.toString(rotorIndex));
//		
//		for(int i = 0; i < AdvEnigma.ROTOR_SIZE; i++)
//			AdvEnigma.stepRotors(rotorIndex);
//		System.out.println("Array: " + Arrays.toString(rotorIndex));
//		
//		for(int i = 0; i < AdvEnigma.ROTOR_SIZE; i++)
//			AdvEnigma.stepRotors(rotorIndex);
//		System.out.println("Array: " + Arrays.toString(rotorIndex));

//		Integer[] arr0 = new Integer[] {1, 2, 3, 4, 5};
//		Integer[] arr1 = arr0.clone();
//			arr1[0] = 88;
//
//
//		System.out.println(Arrays.toString(arr0));
//		System.out.println(Arrays.toString(arr1));

//		System.out.println(Arrays.toString(RotorPb.genRotorPb(-10)));
//		System.out.println(Arrays.toString(RotorPb.genRotorPb(0)));
//		System.out.println(Arrays.toString(RotorPb.genRotorPb(99999)));
//		System.out.println(Arrays.toString(RotorPb.genRotorPb(99999)));
//		System.out.println(Arrays.toString(RotorPb.genRotorPb(123456)));

		LOG.debug("Logger Debug Test");
		
		/**
		 * pass phrase => phraseSeed(long)
		 * 
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		
//		System.out.println("HashCode".length());
//		System.out.println("HashCode".toCharArray().length);
//		System.out.println(AdvEnigma.hashCode("HashCode"));
//		System.out.println(AdvEnigma.hashCode("HashCodf"));
//		System.out.println(AdvEnigma.hashCode("HashCodg"));
		
		
//		// 46_655
//		int ROTOR_SIZE = 46_655;
//		int rotorIndex = 46_655 / 2;
//		int wordCode = 1;
//		
//		// wordCode = rotor[(rotorIndex[rotorNum] + wordCode) % ROTOR_SIZE];
//		// wordCode = (wordCode - rotorIndex[rotorNum]) % ROTOR_SIZE;
//
//		int newWordCode = (rotorIndex + wordCode) % ROTOR_SIZE;
//		System.out.println(rotorIndex + wordCode);
//		System.out.println(newWordCode);
//		
//		// (((-1 % 2) + 2) % 2)
//		System.out.println(
//			((((newWordCode - rotorIndex) % ROTOR_SIZE) + ROTOR_SIZE) % ROTOR_SIZE)
//		);


//		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
//				int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
//				int year = calendar.get(Calendar.YEAR);
//				int hour = calendar.get(Calendar.HOUR_OF_DAY);
//				int min = calendar.get(Calendar.MINUTE);
//				int sec = calendar.get(Calendar.SECOND);
//			
//		System.out.println(hour + " " + min  + " " + sec + " " + dayOfYear + " " + year);
		
		// "0123456789ABCDEF"
//		SipHash hasher = new SipHash("0123456789ABCDEF".getBytes());
//
//		SipHashResult result = hasher.hash("0".getBytes());
//
//		System.out.println(result.get());                             // 182795880124085484 <-- this can overflow
//		System.out.println(result.getHex());                          //  "2896be26d3374ec"
//		System.out.println(result.getHex(true));                      // "02896be26d3374ec"
//		System.out.println(result.getHex(SipHashCase.UPPER));         //  "2896BE26D3374EC"
//		System.out.println(result.getHex(true, SipHashCase.UPPER));   // "02896BE26D3374EC"
		
		String text = "1PWLI63M9        YZW";
//		System.out.println(text.substring(0, 9));
//		System.out.println(text.substring(9, text.length()));

//		String path = "src/main/resources/directory";
//		Directory dic = Directory.getInstance(path);
//		
//		char[] charArr = new char[] {
//			'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '`',
//			'~', '?', ':', '"', '\'', '_', '*', '-', '+', '=',
//			'.', ',', '<', '>', '!', '@', '#', '%', '^', '&', '$'
//		};
//		
//		for(char c: charArr)
//			System.out.println("directory test: " + c + "|" + dic.getKeyCode(String.valueOf(c)));

		System.out.println(text.replaceAll("\\s{2,}", " "));
	}
	
}
