/* PigLatinSW.java
 * Name: bella arsenault
 * Date: feb 23, 2022
 * 
 * 		purpose: the PigLatinSW class's function is to convert a sentence or word passed in by the user into pig latin and than return the value so it can be displayed
 * 
 * 	Methods:
 *  setInput - returns void
 *  deleteCharAtPos1 - returns a string (this does more than the method name describes but I am a bit nervous to rename stuff so am leaving it like this - a more suitable name would be pigLatinConverter)
 */
package assignment3;

public class PigLatinSW {
	private String userInput;
	private String pLAy;
	private String deletedCharString;
	private char[] posAt0 = new char[10]; //should I have initialized the amount of slots to assign to the arrays in the constructor? - wasnt sure so I just left them here
	private String[] sentenceSplit = new String[10];
	private StringBuilder[] holderStr = new StringBuilder[10];
	
	public PigLatinSW() {
		userInput = " ";
		pLAy = "ay";
		deletedCharString = " ";

	}
	
	public void setInput(String userInput) { 
		this.userInput = userInput;
	}
	
	public String deleteCharAtPos1() { //function deletes char at pos 1, reassembles the pig latin and returns a string value
		userInput.toLowerCase();
		userInput.trim();
		sentenceSplit = userInput.split(" ");
		deletedCharString = "";
		for(int x = 0; x < sentenceSplit.length; x++) {
			posAt0[x] = sentenceSplit[x].charAt(0);
			holderStr[x] = new StringBuilder(sentenceSplit[x]); //using a stringbuilder to utilize the function deleteCharAt
			holderStr[x].deleteCharAt(0);
			deletedCharString = deletedCharString + (holderStr[x].toString() + posAt0[x] + pLAy) + " ";
			
		}

		return deletedCharString;
		}

}
