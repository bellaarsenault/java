/* StringManipTest.java
 * Name: bella arsenault
 * Date: feb 23, 2022
 * 
 * 		purpose: the main function has a menu that will let the user choose which function that they want to use and then when the option is chosen it will instantiate the class and use the methods from that class to either encode/decode using the caesar cipher, translate into pig latin or create an email adress for the user
 * 
 * methods:
 * 	main - static void
 * 	printMenu - static void
 * 
 */
package assignment3;
import java.util.Scanner;

public class StringManipTest {
	
	public static void main(String args[]) {

		Scanner scanner = new Scanner(System.in);
		String userInput;
		String userInput2;
		int userInputInt;
		int menuChoice; 
		
		do {
			printMenu(); //static method - code can be found below the main method
			menuChoice = scanner.nextInt(); //scanner needs to be cleared after ints are entered or the scanner will try and take the whitespace and breaks the string scanners
			scanner.nextLine();
			switch (menuChoice) {
			case 1: 
				CaesarCipherEncrypt encrypt = new CaesarCipherEncrypt();
				System.out.println("1 - Welcome to the caesar encryptor. Please enter the key you are using to encrypt your message (any number): ");
				userInputInt = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Please enter the message you would like to encode: ");
				userInput = scanner.nextLine();
				encrypt.setKey(userInputInt);
				System.out.println("Your encoded message is: " + encrypt.encrypt(userInput));
				break;
			
			case 2: 
				CaesarCipherDecrypt decrypt = new CaesarCipherDecrypt();
				System.out.println("2 - Welcome to the caesar decryptor. Please enter the key you are using to encrypt your message (any number): ");
				userInputInt = scanner.nextInt();
				scanner.nextLine();
				System.out.println("Please enter the message you would like to decode: ");
				userInput = scanner.nextLine();
				decrypt.setKey(userInputInt);
				System.out.println("Your decoded message is: " + decrypt.decrypt(userInput));
				break;
				
			case 3: 
				System.out.print("3 - Welcome to the pig latin translator. Please enter a word or a sentence that you would like translated into pig latin: ");
				userInput = scanner.nextLine();
				PigLatinSW pigLatin = new PigLatinSW();
				pigLatin.setInput(userInput);
				System.out.println(pigLatin.deleteCharAtPos1()); 
				break;
				
			case 4: 
				System.out.println("4 - Welcome to the email generator. In the line below, please type your first name in the first line, click enter and then type your last name on that line");
				userInput = scanner.nextLine();
				userInput2 = scanner.nextLine();
				EmailGenerator email = new EmailGenerator();
				email.setNames(userInput, userInput2);
				System.out.println(email.emailGenerator());
				break;
				
			case 5: //this is here because 5 is still a valid entry - the switch just needs to break and then the program can terminate 
				break;

			default:
				System.out.println("The number entered was not a valid entry for this program, please re-enter a number"); 
				break;
			}
		} while (menuChoice != 5); 
			
		System.out.println("Thank you for using this program!");
		scanner.close(); //fixes ressource leak
		System.exit(0); 
	}
	
	public static void printMenu() { // i didnt want to repeat these lines of code so I made it into a static method below the main -  i didn't think it really needed its own class 
		System.out.println("This string manipulation program has 3 options that it can run: \n 1 - Caesar Cipher Encoder \n 2 - Caesar Cipher Decoder \n 3 - Pig Latin Encoder \n 4 - Email Generator \n 5 - Quit Program");
		System.out.print("Please insert the number associated with the option you would like to run: ");
	}
}
