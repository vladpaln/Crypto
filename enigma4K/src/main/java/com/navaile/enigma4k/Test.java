/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma4k;

import com.zackehh.siphash.SipHash;
import com.zackehh.siphash.SipHashCase;
import com.zackehh.siphash.SipHashResult;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TimeZone;
import org.apache.commons.lang3.text.StrBuilder;
import org.apache.log4j.*;

/**
 *
 * @author vladpaln
 */
public class Test {
	
	private static final Logger LOG = Logger.getLogger(Test.class);

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		
		BasicConfigurator.configure();
		Logger.getRootLogger().setLevel(Level.ERROR);
		
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
		
//		String text = "1PWLI6 3M9        YZW";
//		System.out.println(text.substring(0, 9));
//		System.out.println(text.substring(9, text.length()));

//		String path = "src/main/resources/directory";
//		Directory dic = Directory.getInstance(path);

//		try {
//			
//			System.out.println(Test.class.getResource("text.txt"));
//			System.out.println(Test.class.getResource("/directory"));
//			System.out.println(Test.class.getClass().getResource("10k_words.txt"));
//			
//			Test test = new Test();
//			System.out.println(test.getClass().getResource("web/zztt"));
//			System.out.println(test.getClass().getResource("src/main/resources/directory"));
//		}
//		catch(Exception e) {	System.out.println(e);			}

//		System.out.println(text.replaceAll("\\s{2,}", " "));


//		Directory dirOne = Directory.getInstance();
//			System.out.println("test: " + dirOne.getKeyCode("test"));
//		Directory.randomizeDirectory(123456789);
//			System.out.println("test: " + dirOne.getKeyCode("test"));

//		String text = "The generated seed is used to spawn random rotors , plugboards , rotor step direction , and rotor step size .";
//		System.out.println(text.replaceAll(" ,", ",").replaceAll(" \\.", "."));

		
//		// initializing unsorted int array
//		String[] strArr = {"one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten"};
//		Arrays.sort(strArr);		// sorting array
//
//		System.out.println(Arrays.toString(strArr));
//
//		// entering the value to be searched
//		String searchVal = "four";
//		int retVal = Arrays.binarySearch(strArr, searchVal);
//		System.out.println("The index of element " + searchVal + " is : " + retVal);


//		Random rnd = new Random();
//
//		int count = 100;
//
//		for(int x = 0; x < 100; x++)
//			System.out.println(
//				(int)(	(count * .84) + (count * (rnd.nextFloat() * .16))	)
//			);


//		ListNode newHead  = null;
//		
//		white(head != null) {
//		
//			ListNode next = head.next;
//			head.next = newHead;
//			newHead = head;
//			
//			head = next;
//		}
//		
//		return newHead;

//		System.out.println(Integer.valueOf(new String("test")));

//		long startTime = System.nanoTime();
//		System.out.println(Arrays.toString(primeFactors(20).toArray()));
//		System.out.format("Exec Time: %d", System.nanoTime() - startTime);
		
		// 3578874
		// 3974816
		// 3096875
		// 399889
		// 534107
		
//		System.out.println(Integer.MIN_VALUE);
//		System.out.println(Integer.MAX_VALUE);
//		
//		System.out.format("NegFlip: %d => %d%n", Integer.MIN_VALUE, (Integer.MIN_VALUE * -1));
//		System.out.format("PosFlip: %d => %d%n", Integer.MAX_VALUE, (Integer.MAX_VALUE * -1));

		System.out.println(
			"this is a test".contains(" ")
		);
	}
	
	public static List<Integer> primeFactors(int number) {

		List<Integer> factors = new ArrayList<>();
		for (int i = 2; i <= number/i; i++) {
			while(number % i == 0) {
				factors.add(i);
				number /= i;
			}
		}

		if(number > 1)	factors.add(number);

		return factors;
    }
	
//	public static List<Integer> insertSort(final List<Integer> numbers) {
//		
//		final List<Integer> sortedList = new LinkedList<>();
//		
//		originalList: for(Integer number : numbers) {
//			for (int i = 0; i < sortedList.size(); i++) {
//				if (number < sortedList.get(i)) {
//					sortedList.add(i, number);
//					continue originalList;
//				}
//			}
//
//			sortedList.add(sortedList.size(), number);
//		}
//		return sortedList;
//	}
	
	
	/*
	
	
	10^14=2^x
	x=14*ln(10)/ln(2)
	*/

}
