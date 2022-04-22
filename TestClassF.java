/* TestClassF.java 
 * 
 * 	purpose: to test the ability to read and write information 
 *  from files and place them in objects 
 *  
 *  method:
 *  
 *  void providedTesting(File currFile, StudentIO info)
 *  
 *  note: for the challenge part of this assignment I wanted to 
 *  clean up the main function so both testing types will be in separate classes
 * 
 */
package assignment4;

import java.io.*;

public class TestClassF {
	
	public void providedTesting(File currFile, StudentIO info) throws IOException, ClassNotFoundException {
		//StudentIO info = new StudentIO(); //this was part of the main code provided but because it is used in both this class and in the my test class I wanted to declare it in the main and pass it in because there was no point having two of the same thing

		// define the File to open	
		//File currFile = new File("studInfo.txt"); //this is the commented out for the same reason as above

		/***********************************************************/ 	
		// open and read the original text file and instantiate
		// the objects for the arrays in the StudentIO class
		/**********************************************************/

		info.reset();
		
		info.fileReadMethod(currFile);


		// add another record object into the student record array saveRecord[]
		info.setStudentRecord();

		//retrieve the object and set the new values in the record.
		info.getStudentRecord().setName("Curious George"); 
		//info.getStudentRecord().setAddress("123 Monkey St.");
		info.getStudentRecord().setGrade(10);
		info.getStudentRecord().setMathMark(76);
		info.getStudentRecord().setSciMark(59);
		info.getStudentRecord().setEngMark(56);
		info.getStudentRecord().setCompMark(78);

		info.setIncreaseCount(); //counter in StudentIO class needs to be increased.

		//print out all of the  entries using the toString()
		System.out.println(info);


		/*************************************************************/
		// write the array out as a text file with the new addition
		/*************************************************************/
		
		File backFile = new File("studentRecBackup.txt");

		info.writeFileMethod(backFile);
		
		/************************************************************/
		//read the backupfile and see if you can import it too
		/************************************************************/
		
		info.fileReadMethod(backFile);  // produces the student list twice.

		
		//print out all of the  entries using the toString()
		System.out.println(info);

		/*************************************************************/
		// export file as a class file.
		/*************************************************************/
		
		File newFile = new File("studentInfo.dat");

		info.writeObjectMethod(newFile);


		/*************************************************************/
		// import the file as a class
		// to prove you have written the file,  load and then show
		/************************************************************/

		//the second array saveObjData was made for this part read in the object

		info.objectInputMethod(newFile); 

		//Final check of your results.

		System.out.println("There are "+ info.getCounter() +" student records saved in the array.\n" + "The student records are: ");

		for(int c=0; c<info.getCounter(); c++){
			System.out.print(c+":\n");
			System.out.print(info.getSaveObjRecord(c));
			System.out.print("\t\tAnd the average is: " + info.getSaveObjRecord(c).calcAver());
			System.out.println();
		}


	}
}
