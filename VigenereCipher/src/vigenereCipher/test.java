package vigenereCipher;


import static org.junit.Assert.*;
import org.junit.Test;


class test {

		public void test() 
		{
			assertEquals("K", VigenereCipher.getMatrixEntry('e','g'));
			assertEquals("g", VigenereCipher.getColumn('e','K'));
			
			assertEquals("E", VigenereCipher.getMatrixEntry('j','v'));
			assertEquals("V", VigenereCipher.getColumn('j','E'));
			
			assertEquals("X", VigenereCipher.getMatrixEntry('n','k'));
			assertEquals("k", VigenereCipher.getColumn('n','K'));
			
			assertEquals("B", VigenereCipher.getMatrixEntry('p','m'));
			assertEquals("m", VigenereCipher.getColumn('p','B'));
			
			assertEquals("K", VigenereCipher.getMatrixEntry('y','m'));
			assertEquals("m", VigenereCipher.getColumn('y','K'));
			
			assertEquals("BFFZ", VigenereCipher.encrypt("worm", "fron"));
			
			String[] tempE = null;
			tempE[0] = "crypto";
			tempE[1] = "is";
			tempE[2] = "short";
			tempE[3] = "for";
			tempE[4] = "cryptography";
			
			String[] tempD = null;
			tempD[0] = "CSASTP";
			tempD[1] = "KV";
			tempD[2] = "SIQUT";
			tempD[3] = "GQU";
			tempD[4] = "CSASTPIUAQJB";
			
			assertEquals("CSASTP KV SIQUT GQU CSASTPIUAQJB", VigenereCipher.encrypt (tempE,"abcd"));
			assertEquals("crypto is short for cryptography",  VigenereCipher.decrypt (tempD,"abcd"));
			
			assertEquals("CSASTP KV SIQUT GQU CSASTPIUAQJB", VigenereCipher.encrypt (tempE,"abcd"));
			assertEquals("crypto is short for cryptography",  VigenereCipher.decrypt ("CSASTPKVSIQUTGQUCSASTPIUAQJB","abcd"));

			
		}

	}


