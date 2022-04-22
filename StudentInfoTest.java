/*************************************************
//	File name:  StudentInfoTest.java
//	name: bella arsenault
// 	date: march 8, 2022
// 
//	Purpose: main class with method running the menu to allow user to choose which option of the program they would like to run
// 			main method will pass in the instance variable, file and scanner into the classes that need them
//
**************************************************/

package assignment4;

import java.io.*;
import java.util.Scanner;

public class StudentInfoTest {
	
	public static void main (String args[]) throws IOException, ClassNotFoundException{ //throwing the exceptions instead of surrounding function with try catch
		
		Scanner scanner = new Scanner(System.in); //only one scanner is made in this program - was running into issues because there were two scanners running at the same time so to fix this, the program uses one scanner and passes it into the class that need it
		int choiceHolder = 0; //variable used for menu choice
		boolean firstTime = true; //used to choose whether to print welcome to assignment or continue using program prompt
		
		StudentIO info = new StudentIO(); //instantiating StudentIO and creating file outside of loop because they are used within both test classes
		File currFile = new File("studInfo.txt"); //cont from above - are passed in as parameters so the classes can use them
		
		
		do { //do while loop to continue using program if user isn't finished yet, also to continue to get only numbers between 1 and 3
			menu(firstTime); //selecting which menu to print
			firstTime = false;
			choiceHolder = scanner.nextInt();
			scanner.nextLine(); //making sure program doesn't skip over the first name scanner when using case 2
			
			switch(choiceHolder) {
			case 1: 
				TestClassF providedTest = new TestClassF();
				providedTest.providedTesting(currFile, info);
				break;
				
			case 2:
				TestClassBA createdTest = new TestClassBA();
				createdTest.createdTesting(currFile, info, scanner);
				break;
				
			case 3:
				break;
				
			default:
				System.out.println("The number you entered was invalid; please re-enter a number between 1 and 3.");
				break;
			}
			
			
		} while(choiceHolder != 3); //3 is option for program to end
		
		scanner.close();
		System.out.println("Thank you for using this program!");
		System.exit(0);
	}
	
	public static void menu(boolean firstTime) {
		if (firstTime) {
			System.out.println("Welcome to assignment 4, please select which testing version you "
					+ "would like to use. For the testing provided to use type 1, for the created "
					+ "testing type 2 and to terminate the program type 3: ");
		}
		else {
			System.out.println("Would you like to continue using the program? (1 - provided test code, 2 - created test code (using scanners), 3 - terminate program)");

		}
		
	} 
}


/*
 * 	/*if ((gameEntry.get(x).getName().equals(name)) && (gameEntry.get(x).getLevel().equals(lvl))) {
				gameEntry.remove(x);
			} */
			//if (equals(gameEntry.get(x).getName() && gameEntry.get(x).getLevel())) {
			//	gameEntry.remove(x);
			//}
