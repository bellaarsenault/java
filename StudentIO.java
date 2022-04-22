/* StudentIO.java
 * Name: bella arsenault
 * date: march 8, 2022	
 * 		purpose: creates new student record, reads thru sutdentInfo.txt, writes backup code, writes and reads .dat code 
 * 
 * methods:
 * 	void setStudentRecord
 * 	void setIncreaseCount
 * 	
 * 	void reset
 * 
 * 	void fileReadMethod(File myFile)
 *  void writeFileMethod(File myFile)
 *  void writeObjectMethod(File myFile)
 *  void objectInputMethod(File myFile)
 *  
 *  StudentRecord getStudentRecord
 *  StudentRecord getSaveObjRecord(int c)
 *  
 *  int getCounter
 *  
 *  String toString
 *  
 */
package assignment4;

import java.io.*;

public class StudentIO { 
	public final int arraySize = 10;
	private StudentRecord[] saveRecord; //read the first text file, later write that same info as a backup of the information
	private StudentRecord[] saveObjRecord; //determine if the information saved using the write object method is read back in the same as the original date saved
	private int counter;
	
	public StudentIO() {
		saveRecord = new StudentRecord[arraySize];
		saveObjRecord = new StudentRecord[arraySize];
		counter = 0;
	}	
	
	public void setStudentRecord() {
		saveRecord[counter] = new StudentRecord();
	}
	
	public StudentRecord getStudentRecord() {
		return saveRecord[counter];
	}
	
	public void setIncreaseCount() {
		counter++;
	}
	
	public int getCounter() {
		return counter;
	}
	
	public void reset() { //reset is for the menu to make sure the index is back at zero and to clear out all the old values that were in the array
		for (int i = 0; i < counter; i++) {
			saveRecord[i] = null;
			saveObjRecord[i] = null;
		}
		counter = 0;
	}
	
	public StudentRecord getSaveObjRecord(int c) {
		return saveObjRecord[c];
	}
	
	public void fileReadMethod(File myFile) { //uses FileRead class to read initial studInfo.text file provided		
		try {	
			BufferedReader buff = new BufferedReader(new FileReader(myFile));

			String line; 
			while ((line = buff.readLine()) != null) {
				saveRecord[counter] = new StudentRecord(line); 
				counter++;
			}
			buff.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error: Cannot open file for reading.");
		}catch (EOFException e) {
			System.out.println("Error: EOF encountered, file may be corrupted.");
		}catch (IOException e) {
			System.out.println("Error: Cannot read from file.");
		} 
	}
	
	public void writeFileMethod(File myFile) throws IOException { //uses FileWriter class to write the altered array to a backup file called StudentRecBackUp.txt
		FileWriter write = new FileWriter(myFile);
		for (StudentRecord s : saveRecord) {
			if (s!= null) {
				write.write(s + System.lineSeparator());
			}
		}
		write.close();
	}
	
	public void writeObjectMethod(File myFile) throws IOException { // uses the ObjectOutputStream to save the array of objects in a file called studentInfo.dat
		FileOutputStream output = new FileOutputStream(myFile);
		ObjectOutputStream out = new ObjectOutputStream(output);
		out.writeObject(saveRecord);
		out.close();
	}
	
	public void objectInputMethod(File myFile) throws IOException, ClassNotFoundException{ // uses the ObjectInputStream to read the object from the studentInfo.dat file and place it in the saveObjRecord array
		FileInputStream input = new FileInputStream(myFile);
		ObjectInputStream inStream = new ObjectInputStream(input);	
		saveObjRecord = (StudentRecord[]) inStream.readObject();
		inStream.close();
	}
	
	public String toString() {
		String fullString = "";
		boolean firstTime = true;
		for (StudentRecord r : saveRecord) {
			if (r != null) {
				if (firstTime) {
					fullString = r.toString();
					firstTime = false;

				} 
				else {
					fullString = fullString + "\n" + r;	
				}
			}	
		}
		for (StudentRecord r : saveObjRecord) {
			if (r != null) {
				if (firstTime) {
					fullString = r.toString();
					firstTime = false;

				} 
				else {
					fullString = fullString + "\n" + r;	
				}
			}	
		}
		return fullString + "\n";
	} 
	
}