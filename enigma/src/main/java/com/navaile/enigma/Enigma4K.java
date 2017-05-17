/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.navaile.enigma;

import com.zackehh.siphash.SipHash;
import com.zackehh.siphash.SipHashResult;
import java.text.NumberFormat;
import java.util.*;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A next generation Enigma based on the original design.
 * 
 * Software Design
 * The software was designed with laymen in mind. The code is design in
 * such a way as to closely resemble the original mechanical machine.
 *
 * @author navaile
 */
public class Enigma4K {
	
	private static final Logger LOG = LoggerFactory.getLogger(Enigma4K.class);

	/** Directory size.					*/
	public static final int DIR_SIZE = 46_655;				// ZZZ (base36)

	/** Number of rotors in the enigma machine.		*/
	public static final int COUNT_MIN = 97;
	public static final int ROTOR_COUNT_MAX = 46_655;		// ZZZ (base36)
	
	/** Encryption multiplier.						*/
	public static final int ENCRYPT_MULTI = 97;		// 97

	public static final int PLUGBOARD_COUNT_MAX = ROTOR_COUNT_MAX;
	
	/**
	 * Rotor/Plugboard Permutations
	 * 
	 * perm		= factorial(rotorSize)
	 *			= 46_655!
	 *			= 1.478143766589091023060 x 10^197568
	 *			= 10^10^5.295716976537452
	 */
	
	private static Random RND;
	private static long seed;
	
	private static int rotorCount = COUNT_MIN;
	private static int plugboardCount = COUNT_MIN;
	private static int matrixSize = -1;
	
	private static Directory directory;
	
	private static int[][] matrix;
	
	/** List of rotors	*/			private static final HashMap<Long, Integer> matrixMap = new HashMap<>();
	/** Rotor order		*/			private static long[] rotorOrder;
	/** Plugboard order	*/			private static long[] plugboardOrder;
	
	// diferent for each message
	/** Rotor Key		*/			private static int[] rotorKey;
	/** Rotor Direction */			private static int[] rotorDirectionSpin;
	/** Rotor Step Size */			private static int[] rotorStepSize;
	
	// rotor index starts with the key and slowly rotates
	/** Index for each rotor */		private static int[] rotorIndex;
	
	private static String plainText;
	private static String cryptText;
	

	/***************************************************************************
	 * TODO
	 * 
	 * source: http://www.cryptomuseum.com/crypto/enigma/working.htm
	 * - A letter can never be encoded into itself
	 * - Regular stepping of the wheels
	 */

	
	/**
	 * Builds a new enigma machine.
	 * 
	 * @param path
	 * @param passPhrase
	 * @param handle
	 * @param msgID
	 * @param rotorCount rotor count, ROTOR_COUNT_MIN = 256, ROTOR_COUNT_MAX = 46655
	 * @param pbCount plugboard count, specify the number of plugboards the
	 * Enigma will contain. PLUGBOARD_COUNT_MIN = 256, PLUGBOARD_COUNT_MAX = 46655
	 */
	public Enigma4K(String path, String passPhrase, String handle, String msgID, int rotorCount, int pbCount) {
		
		BasicConfigurator.configure();
//		org.apache.log4j.Logger logger4j = org.apache.log4j.Logger.getRootLogger();
//			logger4j.setLevel(org.apache.log4j.Level.toLevel("ERROR"));
		
		LOG.info("ini AdvEnigma(" + rotorCount + ", " + pbCount + ")");
		
		directory = Directory.getInstance(path);

		passPhrase = passPhrase + "_" + handle + (msgID != null ? ("_" + msgID) : "");
		LOG.info("passPhrase: " + passPhrase + ", handle: " + handle + ", msgID: " + msgID);

		seed = hashCode(passPhrase);
		RND = new Random(seed);

		if(rotorCount < COUNT_MIN)		rotorCount = COUNT_MIN;
		if(pbCount < COUNT_MIN)			pbCount = COUNT_MIN;
		
		Enigma4K.rotorCount = rotorCount;
		Enigma4K.plugboardCount = pbCount;
		Enigma4K.matrixSize = rotorCount + pbCount;
		
		LOG.info("ini AdvEnigma: genKey, copy to rotorIndex");
		Enigma4K.rotorKey = genKey();
		Enigma4K.rotorIndex = Enigma4K.rotorKey.clone();
		
		Enigma4K.rotorDirectionSpin = new int[Enigma4K.rotorKey.length];
		for(int i = 0; i < Enigma4K.rotorDirectionSpin.length; i++)
			Enigma4K.rotorDirectionSpin[i] = (nextBool() ? 1 : -1);
		
		Enigma4K.rotorStepSize = new int[Enigma4K.rotorKey.length];
		for(int i = 0; i < Enigma4K.rotorStepSize.length; i++)
			Enigma4K.rotorStepSize[i] = nextSeed(31 + 1);
		
		Enigma4K.plugboardOrder = new long[Enigma4K.plugboardCount];
		Enigma4K.rotorOrder = new long[Enigma4K.rotorCount];
		
		Enigma4K.matrix = new int[Enigma4K.matrixSize][];
		
		/**
		 * TODO
		 * 
		 * This needs to be changed, seeds will have to be generated based on
		 * pass phrase and TIMESTAMP, key/rotors/Pb seeds need to be generated
		 * from pass phrase and TIMESTAMP during encryption.
		 */
		
		LOG.info("ini AdvEnigma: generate random rotorsPb");
		for(int i = 0; i < matrix.length; i++) {
			
			Long ID = nextSeed();
			matrix[i] = genRotorPb(ID);
			matrixMap.put(ID, i);
			
			if(i < plugboardOrder.length)	plugboardOrder[i] = ID;
			else		rotorOrder[i - plugboardOrder.length] = ID;
		}
		
		memory();
		
		LOG.info("Matrix: " + matrix.length);
		
		LOG.info("Rotor Count: " + rotorCount);
		LOG.info("Rotor Key: " + Arrays.toString(rotorKey));
		LOG.info("Rotor Direction: " + Arrays.toString(Enigma4K.rotorDirectionSpin));
		LOG.info("Rotor Step Size: " + Arrays.toString(Enigma4K.rotorStepSize));
		
		LOG.info("-- Keys --");
		matrixMap.forEach((K, V) -> {	LOG.info(K + ", " + V);		});
		LOG.info("-- END Keys --");
		
		LOG.info("Plugboard Count: " + plugboardCount);
	}
	
	/**
	 * Generate message id.
	 * 
	 * @return 
	 */
	public static String genMsgID() {
		
		StringBuilder time = new StringBuilder();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			time.append(cal.get(Calendar.SECOND))
				.append(cal.get(Calendar.MINUTE))
				.append(cal.get(Calendar.HOUR_OF_DAY))
				.append(cal.get(Calendar.DAY_OF_YEAR))
				.append(cal.get(Calendar.YEAR));
			
		long msgID = Long.parseLong(time.toString());

		return padText(longToBase36(msgID).toUpperCase(), 9);
	}

	/** Steps/rotates rotor after each word encryption.		*/
	public static void stepRotors(int[] rotorIndex) {

		for(int i = 0; i < rotorIndex.length; i++) {
			
			rotorIndex[i] += rotorStepSize[i] * rotorDirectionSpin[i];

			if(rotorIndex[i] >= DIR_SIZE) rotorIndex[i] = rotorIndex[i] % DIR_SIZE;
			else if(rotorIndex[i] < 0) rotorIndex[i] = DIR_SIZE + rotorIndex[i];
		}
		
		LOG.info("stepRotors() new rotor settings: " + Arrays.toString(rotorIndex));
	}
	
	/**
	 * Generates a new rotor key.
	 * 
	 * @return a random key
	 */
	private static int[] genKey() {
		
		int[] newKey = new int[rotorCount];
		for (int i = 0; i < newKey.length; i++)
			newKey[i] = RND.nextInt(DIR_SIZE);
		
		LOG.info("Generates random rotor key.");
		
		return newKey.clone();
	}
	
	/** Resets key.							*/
	public static void resetKey() {
		Enigma4K.rotorIndex = Enigma4K.rotorKey.clone();
		LOG.info("AdvEnigma.resetKey() key: " + Arrays.toString(Enigma4K.rotorKey));
	}
	
	/**
	 * Encode
	 * 
	 * @param plainText
	 * @return 
	 */
	public String encryptText(String plainText) throws Exception {
		
		LOG.error("Enigma4K.encryptText(" + plainText + ")");
		
		StringBuilder log = new StringBuilder();

		String[] wordList = directory.parceWords(plainText.toLowerCase());
		ArrayList<Integer> wordCodeList = new ArrayList<>();
		
		LOG.error("Enigma4K.encryptText().wordList.length: " + wordList.length);
		
		for(int i = 0; i < wordList.length; i++) {
			
			String word = wordList[i];
			Integer wordCode = directory.getKeyCode(word);
				log.append(word).append(": ").append(wordCode).append("\t");
				
			if(wordCode == null) {
				
				LOG.error("Enigma4K.encryptText word|wordCode: " + word + "|" + wordCode);
				
				char[] letters = word.toCharArray();
				
				LOG.error("Enigma4K.encryptText letter arr: " + Arrays.toString(letters));
				
				wordCodeList.add(directory.getKeyCode("["));
				for(char l: letters) {
					
					Integer keyCode = directory.getKeyCode(String.valueOf(l));
					wordCodeList.add(keyCode);

					if(keyCode == null)
						System.out.println("null keyCode for char: " + Character.getNumericValue(l));
				}
				wordCodeList.add(directory.getKeyCode("]"));
			}
			else {		wordCodeList.add(wordCode);						}
		}
		
		LOG.error(log.toString());
		log = new StringBuilder();
		
		String[] base36List = new String[wordCodeList.size()];
		
		LOG.error("Enigma4K.encryptText.wordCodeList: " + wordCodeList.size());
		LOG.error("Enigma4K.encryptText.wordCodeList.data: " + Arrays.toString(wordCodeList.toArray()));
		
		// wordCodeList check
		LOG.error("wordCodeList: " + Arrays.toString(wordCodeList.toArray()));
		
		for(Integer wordCode: wordCodeList)
			if(wordCode == null)	throw new Exception("Unable to encrypt null.");

		int i = 0;
		for(int wordCode: wordCodeList) {
			
			int pbWordCode = -1, rWordCode = -1;
			for(int m = 0; m < ENCRYPT_MULTI; m++) {
				pbWordCode = pbEncrypt(wordCode, i);
					log.append("pbE:").append(pbWordCode).append("\t");
				rWordCode = rotorEncrypt(pbWordCode);
					log.append("rE:").append(rWordCode).append("\t");
			}
				pbWordCode = pbEncrypt(rWordCode, i);
					log.append("pbE:").append(pbWordCode).append("\t");

			String base36Str = padText(intToBase36(pbWordCode), 3).toUpperCase();
			base36List[i] = base36Str;
				log.append("base36: ").append(base36Str);
				
			i++;
			
			LOG.info(log.toString());
		}
		
		StringBuilder strBuild = new StringBuilder();
		for(String base36: base36List)	strBuild.append(base36);
		
		LOG.info("Encrypts plain text using the plugboard, then using the rotors.");
				
		return strBuild.toString();
	}
	
	/**
	 * Plugboard encryption.
	 * 
	 * @param wordCode
	 * @param wordIndex
	 * @return 
	 */
	private int pbEncrypt(int wordCode, int wordIndex) {
		
		/* plugboard substitution
		 * each word is substituted using a different plugboard		*/
		long pbID = plugboardOrder[wordIndex % plugboardCount];
		int[] plugboard = matrix[matrixMap.get(pbID)];
		
		int nWordCode = plugboard[wordCode];
		
		LOG.info("Encrypt text string using plugboard ID: " + pbID + ", wordCode: " +
			wordCode + ", nWordCode: " + nWordCode + ", wordIndex: " + wordIndex);
		
		return nWordCode;
	}
	
	/**
	 * Rotor encryption.
	 * 
	 * @param wordCode
	 * @return 
	 */
	private int rotorEncrypt(int wordCode) {
		
		LOG.info("START rotorEncrypt() wordCode: " + wordCode);

		// rotor encrypt
		for(int rotorNum = 0; rotorNum < rotorOrder.length; rotorNum++) {
			
			long rotorID = rotorOrder[rotorNum];
			int[] rotor = matrix[matrixMap.get(rotorID)];

			int index = ((rotorIndex[rotorNum] + wordCode) % DIR_SIZE);
			int eWordCode = rotor[index];
			
			LOG.info("rotorID: " + rotorID + ", rotorIndex[" + rotorNum + "]: " +
				rotorIndex[rotorNum] + ", wordCode: " + wordCode + ", index: " +
				index + ", eWordCode: " + eWordCode);
			wordCode = eWordCode;
		}
		
		LOG.info("STOP rotorEncrypt() eWordCode: " + wordCode);
		
		stepRotors(rotorIndex);
		
		return wordCode;
	}
	
	private boolean wSpace = true;			// white space
	
	/**
	 * Decrypt crypt text.
	 * 
	 * @param cryptText
	 * @return plaint text
	 */
	public String decryptText(String cryptText) throws Exception {
		
		StringBuilder log;
		
		if(cryptText.length() % 3 != 0) {
			LOG.error("Encrypted text must be a multiple of three, cryptText: " + (cryptText.length() % 3));
			throw new Exception("cryptText incorrect length");
		}

		String[] cryptTextArr = cryptText.split("(?<=\\G.{3})");
		StringBuilder text = new StringBuilder();
		int[] base10List = new int[cryptTextArr.length];
			
		for(int i = 0; i < cryptTextArr.length; i++) {
			
			log = new StringBuilder();
			
				log.append("cryptText: ").append(cryptTextArr[i]).append("\t");
			base10List[i] = base36ToInt(cryptTextArr[i]);
				log.append("base36ToInt: ").append(base10List[i]).append("\t");
			
			int pbWordCode = -1, rWordCode = -1;
			for(int m = 0; m < ENCRYPT_MULTI; m++) {
				pbWordCode = pbDecrypt(base10List[i], i);
					log.append("pbD:").append(pbWordCode).append("\t");
				rWordCode = rotorDecrypt(pbWordCode);
					log.append("rD:").append(rWordCode).append("\t");
			}
				pbWordCode = pbDecrypt(rWordCode, i);
					log.append("rD:").append(pbWordCode).append("\t");

			String word = directory.getWord(pbWordCode);
				log.append("direcWord: ").append(word);

			if(word.equals("["))	wSpace = false;
			if(word.equals("]"))	wSpace = true;

			text.append(word.replace("[", "").replace("]", ""));
			if(wSpace)	text.append(" ");

			LOG.info(log.toString());
		}
		
		LOG.info("Decrypt encrypted text.");
		
		return text.toString().trim();
	}
	
	/**
	 * Plugboard decryption.
	 * 
	 * @param wordCode
	 * @param wordIndex word index in text
	 * @return 
	 */
	private int pbDecrypt(int wordCode, int wordIndex) {
		
		/* plugboard substitution
		 * each word is substituted using a different plugboard		*/
		long pbID = plugboardOrder[wordIndex % plugboardCount];
		int[] plugboard = matrix[matrixMap.get(pbID)];
		
		int wordCodeIndex = -1;
		for(int i = 0; i < plugboard.length; i++)
			if(plugboard[i] == wordCode) {
				wordCodeIndex = i;
				break;
			}
		
		LOG.info("Decrypt text string using plugboard ID: " + pbID + ", wordCode: " +
			wordCode + ", wordCodeIndex: " + wordCodeIndex + ", wordIndex: " + wordIndex);
		
		return wordCodeIndex;
	}
	
	/**
	 * Rotor decryption.
	 * 
	 * @param eWordCode
	 * @return 
	 */
	private int rotorDecrypt(int eWordCode) {
		
		LOG.info("START rotorDecrypt() eWordCode: " + eWordCode);
		
		// rotor decrypt
		for(int rotorNum = rotorOrder.length - 1; rotorNum >= 0; rotorNum--) {
			
			long rotorID = rotorOrder[rotorNum];
			int[] rotor = matrix[matrixMap.get(rotorID)];

			for(int index = 0; index < rotor.length; index++)
				if(rotor[index] == eWordCode) {
					
					int newWordCode = ((((index - rotorIndex[rotorNum]) % DIR_SIZE) + DIR_SIZE) % DIR_SIZE);
					LOG.info("rotorID: " + rotorID + ", eWordCode: " + eWordCode +
						", index: " + index + ", rotorIndex[" + rotorNum + "]: " +
						rotorIndex[rotorNum] + ", newWordCode: " + newWordCode);
					
					eWordCode = newWordCode;
					break;
				}
		}
		
		LOG.info("STOP rotorDecrypt() eWordCode: " + eWordCode);
		
		stepRotors(rotorIndex);
		
		return eWordCode;
	}
	
	/** Base 10 to Base 36.		*/
	private static String intToBase36(int base10) {
		return Integer.toString(base10, Character.MAX_RADIX);
	}
	
	/** Base 36 to Base 10.		*/
	private static int base36ToInt(String base36) {
		return Integer.parseInt(base36, Character.MAX_RADIX);
	}
	
	public static String longToBase36(long base10) {
		return Long.toString(base10, Character.MAX_RADIX);
	}

	/**
	 * Text to pad.
	 * 
	 * @param str string to pad
	 * @param length desired string length
	 * @return padded string
	 */
	private static String padText(String str, int length) {

		if((length - str.length()) > 0)		return padText("0" + str, length);
		return str;
	}
	
	/** String to long hash using SipHash 64bit.			*/
	private static long hashCode(String str) {

		// java jashCode
//		long hash = 0;
//		
//		if(str.length() > 0) {
//			char val[] = str.toCharArray();
//			for(int i = 0; i < str.length(); i++)
//				hash = 31 * hash + val[i];
//		}
//
//		return hash;

		SipHash hasher = new SipHash("navaile_Enigma4K".getBytes());
		return hasher.hash(str.getBytes()).get();
	}

	private long nextSeed() {			return RND.nextLong();				}
	private int nextSeed(int bound) {	return RND.nextInt(bound);			}
	private boolean nextBool() {		return RND.nextBoolean();			}
	
	private static int[] seqRotor;		// sequential rotor 0,1,2,3, ...
	
	/**
	 * Builds a random rotor or plugboard.
	 * 
	 * @return a new rotor
	 */
	private static int[] genRotorPb(long seed) {
		
		Random rnd = new Random(seed);

		if(seqRotor == null) {
			seqRotor = new int[Enigma4K.DIR_SIZE];		// 46655, ZZZ
			for(int i = 0; i < seqRotor.length; i++)		seqRotor[i] = i;
		}
		
		int[] arr = seqRotor.clone();
		
		// Fisher–Yates shuffle
		// [0, 1, 2, 3, 4]
		for(int i = arr.length - 1; i > 0; i--) {
			
			int index = rnd.nextInt(i + 1);
			// Simple swap
			int a = arr[index];
			arr[index] = arr[i];
			arr[i] = a;
		}

		return arr;
	}
	
	private static void memory() {
		
		long MEGABYTE = 1024L * 1024L;

		Runtime runtime = Runtime.getRuntime();
		NumberFormat format = NumberFormat.getInstance();

		StringBuilder sb = new StringBuilder();
		long maxMemory = runtime.maxMemory();
		long allocatedMemory = runtime.totalMemory();
		long freeMemory = runtime.freeMemory();

		sb.append("free memory: ").append(format.format(freeMemory / MEGABYTE)).append("\n");
		sb.append("allocated memory: ").append(format.format(allocatedMemory / MEGABYTE)).append("\n");
		sb.append("max memory: ").append(format.format(maxMemory / MEGABYTE)).append("\n");
		sb.append("total free memory: ").append(format.format((freeMemory + (maxMemory - allocatedMemory)) / MEGABYTE)).append("\n");
		
		System.out.println(sb.toString());
	}
}

