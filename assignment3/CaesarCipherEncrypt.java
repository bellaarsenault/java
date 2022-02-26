/* CaesarCipherEncrypt.java
 * Name: bella arsenault
 * Date: feb 23, 2022
 * 
 * 		purpose: class's purpose is to encrypt a user's message using a key that is passed in and chosen by the user. The method encrypt will then encrypt the message and that value will be returned to the main to be printed.
 * 
 * 	Methods:
 *  setKey - returns void
 *  encrypt - returns a string
 */
package assignment3;

public class CaesarCipherEncrypt {

	private int ascii;
	private int key; 
	private String normalMessage;
	private char holderForAscii;
	private int holderForShift;
	private String holderForEncryptTextFull;
	private char alphaAfterShift;
	private int aValForMapping;
	
	public CaesarCipherEncrypt() {
		ascii = ' ';
		key = 0; 
		normalMessage = " ";
		holderForAscii = ' ';
		holderForShift = 0;
		holderForEncryptTextFull = " ";
		alphaAfterShift = ' ';
		aValForMapping = 0;
	}
	
	public void setKey(int key) { 
		this.key = key;
	}
	
	public String encrypt(String userInput) {
		userInput.trim();
		normalMessage = userInput.toLowerCase();
		for (int x = 0; x < normalMessage.length(); x++) {
			holderForAscii = normalMessage.charAt(x);
			if (holderForAscii == ' ') {
				alphaAfterShift = ' ';
			}
			else {
				ascii = (int)holderForAscii;
				aValForMapping = (int)'a'; //explanation is the same as what was said in decrypt - need the value of ascii a because the ascii values dont start at 0
				ascii = ascii - aValForMapping;
				holderForShift = (key + ascii) % 26;
				holderForShift = holderForShift + aValForMapping;
				alphaAfterShift = (char)holderForShift;
			}

			holderForEncryptTextFull = holderForEncryptTextFull + alphaAfterShift;
		}
		return holderForEncryptTextFull;
	}

}
