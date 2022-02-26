/* CaesarCipherDecrypt.java
 * Name: bella arsenault
 * Date: feb 23, 2022
 * 
 * 		purpose: class's purpose is to decrypt a user's message using a key that is passed in and chosen by the user. The method decrypt will then decrypt the message and that value will be returned to the main to be printed.
 * 
 * 	Methods:
 *  setKey - returns void
 *  decrypt - returns a string
 */
package assignment3;

public class CaesarCipherDecrypt {
	private int ascii;
	private int key; 
	private String normalMessage;
	private char holderForAscii;
	private int holderForShift;
	private String holderForDecryptTextFull;
	private char alphaAfterShift;
	private int aValForMapping;
	
	public CaesarCipherDecrypt() {
		ascii = ' ';
		key = 0; 
		normalMessage = " ";
		holderForAscii = ' ';
		holderForShift = 0;
		holderForDecryptTextFull = " ";
		alphaAfterShift = ' ';
		aValForMapping = 0;
	}
	
	public void setKey(int key) { 
		this.key = key;
	}
	
	public String decrypt(String userInput) {
		userInput.trim();
		normalMessage = userInput.toLowerCase();
		for (int x = 0; x < normalMessage.length(); x++) {
			holderForAscii = normalMessage.charAt(x);
			if (holderForAscii == ' ') {
				alphaAfterShift = ' ';
			}
			else {
				ascii = (int)holderForAscii;
				aValForMapping = (int)'a'; //having the a value allows to use mod 26 - if it isn't used the mod woudn't work because lowercase characters dont start at 0
				ascii = ascii - aValForMapping;
				holderForShift = ((ascii - key) % 26 + 26) % 26; //To be completely honest I wasn't sure how to fix the modulus function with negative numbers, this solution was taken from stack overflow and personalized
				holderForShift = holderForShift + aValForMapping;
				alphaAfterShift = (char)holderForShift;
			}

			holderForDecryptTextFull = holderForDecryptTextFull + alphaAfterShift;
		}
		return holderForDecryptTextFull;
	}

}


