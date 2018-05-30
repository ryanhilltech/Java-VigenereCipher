//Ryan Hill COSC 2329 Dr. Kart 
//Challenge #4 Vigen\'e9re Cipher

//Apr 10 2017 rsh
package vigenereCipher;

import java.util.Arrays;
import java.util.List;

public interface VigenereCipher 

{
	public final static List<Character> ENGLISH_LOWERCASE_LETTERS_LIST = Arrays.asList('a','b','c','d','e','f','g','h','i','j','k','l','m','n',
					'o','p','q','r','s','t','u','v','w','x','y','z');
	public final static List<Character> ENGLISH_UPPERCASE_LETTERS_LIST = Arrays.asList('A','B','C','D','E','F','G','H','I','J','K','L','M','N',
					'O','P','Q','R','S','T','U','V','W','X','Y','Z');
	
	//part of pre: plainMessage[i] != null, for i in [0, plainMessage.length())
	//part of pre: plainMessage[i].charAt(j) is in \{'a','b','c'... 'z'\}, for i in 
	//		[0, plainMessage.length() and j in [0, plainMessage[i].length)
	//part of pre: key != null
	//part of pre: key.length() > 0
	//part of pre: checkForCaps(plainMessage)
	
	//part of post: rv.charAt(j) is in \{'A','B','C'... 'Z'\} U \{' '\}, for j in [0, rv.length())
	public static String encrypt(String[] plainMessage, String key)
	{
		assert key != null: "The key is empty!! " + key;
		assert key.length() > 0: "The key is too small!! " + key;
		assert plainMessage != null: "The message is empty!! " + plainMessage;
		assert plainMessage.length > 0: "The message is too short!! " + plainMessage;
		assert checkForCaps(plainMessage): "Message is not formatted correctly!! " + plainMessage;
		
		String encrypted = "";
		for ( int i = 0; i < plainMessage.length; i++)
		{
			String temp = plainMessage[i];
			encrypted += encrypt(temp, key) + " ";
		}
		return encrypted;
	}//end encrypt(String[] plainMessage, String key)
	
	

	//part of pre: key != null	
	//part of pre: plainMessage[i].charAt(j) is in \{'a','b','c'... 'z'\}, for i in 
	//		[0, plainMessage.length() and j in [0, plainMessage[i].length())
	//part of pre: key.length() > 0
	
	//part of post: rv.charAt(j) is in \{'A','B','C'... 'Z'\} U \{' '\}, for j in [0, rv.length())
	public static String encrypt(String plainMessage, String key)
	{		assert key.length() > 0: "The key is too small!! " + key;
			assert plainMessage != null: "The message is empty!! " + plainMessage;
			assert plainMessage.length() > 0: "The message is too short!! " + plainMessage;
			assert checkForCaps(plainMessage): "Message is not formatted correctly!! " + plainMessage;
			
			String encrypted = "";
			for (int i = 0, j = 0; i < plainMessage.length(); i++)
			{
				encrypted += getMatrixEntry(key.charAt(j),plainMessage.charAt(i));
				j = ++j % key.length();
			}
			return encrypted;
	}// end encrypt(String plainMessage, String key)
	

	
	//part of pre: encryptedMessage[i] != null, for i in [0, encyptedMessage.length())
	//part of pre: encryptedMessage[i].charAt(j) is in \{'a','b','c'... 'z'\} , 
	// 				for i in [0, encryptedMessage.length()) and j in [0, encyptedMessage.length())
	//part of pre: key.length() > 0
	//part of pre: key != null	
	
	//part of post: left out, but need to express exactly where the spaces are in rv
	
	public static String decrypt (String[] encryptedText, String key)
	{
		assert key != null: "The key is empty!! " + key;
		assert key.length() > 0: "The key is too small!! " + key;
		assert encryptedText != null: "The message is empty!! " + encryptedText;
		assert encryptedText.length > 0: "The message is too short!! " + encryptedText;
		assert checkForCaps(encryptedText): "Message is not formatted correctly!! " + encryptedText;
		
		String decrypted = "";
		for ( int i = 0; i < encryptedText.length; i++)
		{
			String temp = encryptedText[i];
			decrypted += decrypt(temp, key) + " ";
		}
		return decrypted;
	}// end decrypt (String[] encryptedText, String key)
	
	//part of pre: encryptedMessage[i] != null\
	//part of pre: encryptedText.charAt(j) is in \{'a','b','c'... 'z'\} , 
	// 				for j in [0, encryptedText.length())
	//part of pre: key != null	
	//part of pre: key.length() > 0
	
	//part of post: rv.charAt(j) is in \{'a','b','c'... 'z'\} U \{' '\}, for j in [0, rv.length())
	public static String decrypt(String encryptedText, String key)
	{
		String decrypted = "";
		for (int i = 0, j = 0; i < encryptedText.length(); i++)
		{
			decrypted += getColumn(key.charAt(j), encryptedText.charAt(i));
			j = ++j % key.length();
		}
		return decrypted;
	}// end decrypt(String encryptedText, String key)
	
	
	
	//part of pre: row is in \{'a','b','c'... 'z'\} 
	//part of pre: colomn is in \{'a','b','c'... 'z'\} 
	
	//part of post: rv is in \{'A','B','C'... 'Z'\}\
	public static char getMatrixEntry(char row, char column)
	{//if (plainMessageCheck.contains((CharSequence) ENGLISH_UPPEERCASE_LETTERS_LIST))
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(row): "Row is not correct!! " + row;
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(column): "Column is not correct!! " + column;
		
		int rowNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(row);
		int columnNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(column);
		int matrixEntryNumber = Math.abs((rowNumber + columnNumber)% 26);
		
		return ENGLISH_UPPERCASE_LETTERS_LIST.get(matrixEntryNumber);
	}// end getMatrixEntry(char row, char column)\
	
	//part of pre: row is in \{'a','b','c'... 'z'\} \
	//part of pre: matrixEntry is in \{'A','B','C'... 'Z'\}\
	
	//part of post: rv is in \{'a','b','c'... 'z'\}\
	public static char getColumn(char row, char matrixEntry)
	{
		assert ENGLISH_LOWERCASE_LETTERS_LIST.contains(row): "Row is not correct!! " + row;
		assert ENGLISH_UPPERCASE_LETTERS_LIST.contains(matrixEntry): "MatrixEntry is not correct!! " + matrixEntry;
		
		int rowNumber = ENGLISH_LOWERCASE_LETTERS_LIST.indexOf(row);
		int matrixEntryNumber = ENGLISH_UPPERCASE_LETTERS_LIST.indexOf(matrixEntry);
		int columnNumber = Math.abs(((matrixEntryNumber - rowNumber) + 26) % 26);
		return ENGLISH_LOWERCASE_LETTERS_LIST.get(columnNumber);
	}// end getColumn(char row, char matrixEntry)
	
	//part of pre: plainMessage[i] != null, for i in [0, plainMessage.length())
	//part of pre: plainMessage[i].charAt(j) is in \{'a','b','c'... 'z'\}, for i in 
	//		[0, plainMessage.length() and j in [0, plainMessage[i].length)
	//part of post: 
	static boolean checkForCaps (String[] plainMessageCheckArrayCheck)
	{
		assert plainMessageCheckArrayCheck != null: "The message is empty!! " + plainMessageCheckArrayCheck;
		assert plainMessageCheckArrayCheck.length > 0: "The message is too short!! " + plainMessageCheckArrayCheck;
		
		//go through array of Strings
		for (int i = 0; i < plainMessageCheckArrayCheck.length; i++)
		{
			String temp = plainMessageCheckArrayCheck[i];
			
			for (int j = 0; j < temp.length(); j++)
			{
				if (temp.contains((CharSequence) ENGLISH_UPPERCASE_LETTERS_LIST))
					return false;
			}
		}
		return true;
	}// end checkForCaps  (String[] plainMessageCheckArrayCheck)
	
	
	//part of pre: plainMessage[i].charAt(j) is in \{'a','b','c'... 'z'\}, for i in 
	//		[0, plainMessage.length() and j in [0, plainMessage[i].length())
	static boolean checkForCaps (String plainMessageCheck)
	{
		for (int j = 0; j <= plainMessageCheck.length(); j++)
		{
			if (plainMessageCheck.contains((CharSequence) ENGLISH_UPPERCASE_LETTERS_LIST))
				return false;
		}
	return true;
	}// end checkForCaps (String plainMessageCheck)
	
	//part of pre: plainMessage[i].charAt(j) is in \{'a','b','c'... 'z'\}, for i in 
	//		[0, plainMessage.length() and j in [0, plainMessage[i].length())
	static boolean checkForLowercase (String encryptedTextCheck)
	{
		for (int j = 0; j <= encryptedTextCheck.length(); j++)
		{
			if (encryptedTextCheck.contains((CharSequence) ENGLISH_LOWERCASE_LETTERS_LIST))
				return false;
		}
	return true;
	}// end checkForCaps (String plainMessageCheck)
	
	//pre: none
	//post: 
	static boolean checkForLowercase (String[] encryptedTextArrayCheck)
	{
		assert encryptedTextArrayCheck != null: "The message is empty!! " + encryptedTextArrayCheck;
		assert encryptedTextArrayCheck.length > 0: "The message is too short!! " + encryptedTextArrayCheck;
		
		//go through array of Strings
		for (int i = 0; i < encryptedTextArrayCheck.length; i++)
		{
			String temp = encryptedTextArrayCheck[i];
			for (int j = 0; j < temp.length(); j++)
			{
				if (temp.contains((CharSequence) ENGLISH_LOWERCASE_LETTERS_LIST))
					return false;
			}
		}
		return true;
	}// end checkForLowercase (String[] encryptedTextArrayCheck)
}
