package vigenereCipher;

public class InitializeTesting implements VigenereCipher
{
	private static void printOut(String printString)
	{
		System.out.println(printString);
	}
	
	public static void main(String[] args) 
	{
		printOut("Testing getMatrixEntry");
		char temp1 = VigenereCipher.getMatrixEntry('e','g');
		printOut("Test1 should be K: " + temp1);
		char temp2 = VigenereCipher.getMatrixEntry('j','v');
		printOut("Test2 should be E: " + temp2);
		char temp3 = VigenereCipher.getMatrixEntry('x','w');
		printOut("Test3 should be T: " + temp3);
		char temp4 = VigenereCipher.getMatrixEntry('q','z');
		printOut("Test4 should be P: " + temp4);
		
		printOut("Testing getColumn");
		char temp5 = VigenereCipher.getColumn('p','K');
		printOut("Test5 should be v: " + temp5);
		char temp6 = VigenereCipher.getColumn('q','O');
		printOut("Test6 should be y: " + temp6);
		char temp7 = VigenereCipher.getColumn('j','V');
		printOut("Test7 should be m: " + temp7);
		char temp8 = VigenereCipher.getColumn('d','J');
		printOut("Test8 should be g: " + temp8);
		
		printOut("Testing encrypt");
		String encrypt1 = VigenereCipher.encrypt("worm", "fron");
		printOut("Should be: BFFZ \n" + "Encrypted: " + encrypt1);
		String encrypt2 = VigenereCipher.encrypt("cryptography", "abcd");
		printOut("Should be: CSASTPIUAQJB \n" + "Encrypted: " + encrypt2);
		
		String[] tempAlpha = {"crypto","is", "short", "for", "cryptography"};
		
		String encrypt3 = VigenereCipher.encrypt(tempAlpha, "abcd");
		printOut("Should be: CSASTPKVSIQUTGQUCSASTPIUAQJB \n" + "Encrypted: " + encrypt3);
		
		printOut("Testing decrypt");		
		String[] tempD = {"CSASTP", "IT", "SIQUT", "FPT", "CSASTPIUAQJB"};
		String decrypt1 = VigenereCipher.decrypt(tempD,"abcd");
		printOut("Should be: crypto is short for cryptography \n" + "Decrypted: " + decrypt1);

	
	}

}
