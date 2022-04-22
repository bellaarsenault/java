/* TestClassBA.java
 * Name: bella arsenault
 * date: march 8, 2022	
 * 		purpose: to test the ability to read and write information from files and place them in objects as well as using scanners to input other student records alongside the records that the file reader is reading
 * 
 * method: 
 * 	void createdTesting(File currFile, StudentIO info, Scanner scanner)		
 * 
 * note: I didn't change the array size just so it was easier and not needed to input as many things
 */
package assignment4;

import java.io.*;
import java.util.Scanner;

public class TestClassBA {
	
	public void createdTesting(File currFile, StudentIO info, Scanner scanner) throws IOException, ClassNotFoundException { //same as in studentinfotest.java - throwing instead of surrounding with try/catch
		String firstName, lastName, address;
		int grade, mathMark, engMark, sciMark, compMark;
		
		info.reset();
		
		info.fileReadMethod(currFile);
		System.out.println("Here are the student records that are current in the file you are accessing: ");
		System.out.println(info);
		System.out.println("You have 6 more slots to fill with student information, please fill in all the prompts. ");
		
		do {
			info.setStudentRecord();
			
			System.out.print("\nPlease enter first name: ");
			firstName = scanner.nextLine();
			System.out.print("Please enter last name: ");
			lastName = scanner.nextLine();
			System.out.print("Please enter address: ");
			address = scanner.nextLine(); 
			System.out.print("Please enter what grade you are in: ");
			grade = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Please enter you current math mark: ");
			mathMark = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Please enter your current english mark: ");
			engMark = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Please enter your current science mark: ");
			sciMark = scanner.nextInt();
			scanner.nextLine();
			System.out.print("Please enter your current comp sci mark: ");
			compMark = scanner.nextInt();
			scanner.nextLine();
			
			System.out.println("You are currently on record : " + info.getCounter());
			
			info.getStudentRecord().setName(firstName, lastName); 
			info.getStudentRecord().setGrade(grade);
			info.getStudentRecord().setAddress(address);
			info.getStudentRecord().setMathMark(mathMark);
			info.getStudentRecord().setSciMark(sciMark);
			info.getStudentRecord().setEngMark(engMark);
			info.getStudentRecord().setCompMark(compMark);


			info.setIncreaseCount();
			
			
		} while (info.getCounter() < info.arraySize);
		
		System.out.println("You have reached the maximum number of slots for student records\n");
		System.out.println(info);
		
		
		File backfile = new File("StudentRecBackupBA.txt");
		info.writeFileMethod(backfile);
		System.out.println(info);
		
		File newFile = new File("StudentInfoBA.dat");
		info.writeObjectMethod(newFile);
		
		info.objectInputMethod(newFile);
		
		System.out.println(info.getSaveObjRecord(0));
		
	 	System.out.println("There are " + info.getCounter() + " records in this file");
			for(int c=0; c<info.getCounter(); c++){
				System.out.print(c+":\n");
				System.out.print(info.getSaveObjRecord(c));
				System.out.print("\t\tAnd the average is: " + info.getSaveObjRecord(c).calcAver());
				System.out.println(); 
		}
		
		
	}
}
