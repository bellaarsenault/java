/* StudentRecord.java
 * Name: bella arsenault
 * date: march 8, 2022	
 * 		purpose: setting variable types and returning values for all information (name grade, address, marks) in the student record and calculates average of all marks
 * 
 * methods:
 * 	String getName
 * 	String getFName
 * 	String getLName
 * 	String getAddress
 * 	String toString
 * 	
 * 	int getGrade
 * 	int getMathMark
 * 	int getEngMark
 * 	int getSciMark
 * 	int getCompMark
 * 	
 * 	void setName(String newName)
 * 	void setName(String firstName, String lastName)
 * 	void setAddress(String address)
 * 	void setGrade(int grade)
 * 	void setMathMark(int mathMark)
 * 	void setEngMark(int engMark)
 * 	void setSciMark(int sciMark)
 * 	void setCompMark(int compMark)
 * 
 * 	float calcAver
 */
package assignment4;

import java.io.*; //don't need to import all the other classes if using the star

public class StudentRecord implements Serializable {
	public static final long serialVersionUID = 1L; //fixes warning that comes with implements serializable; solution found in oracles documentation of serializable
	private String firstName, lastName, address;
	private int grade, mathMark, engMark, sciMark, compMark;
	private String[] holder = new String[1]; //holder array for first/last name splitting
	
	public StudentRecord() {
		firstName = "";
		lastName = "";
		address = "no address found";
		grade = 0; 
		mathMark = 0;
		engMark = 0;
		sciMark = 0;
		compMark = 0;
	}
	public StudentRecord(String line) {
		String[] holderSplit = line.split(", ");
		firstName = holderSplit[0];
		lastName = holderSplit[1];
		address = holderSplit[2];
		grade = Integer.parseInt(holderSplit[3]); 
		mathMark = Integer.parseInt(holderSplit[4]);
		engMark = Integer.parseInt(holderSplit[5]);
		sciMark = Integer.parseInt(holderSplit[6]);
		compMark = Integer.parseInt(holderSplit[7]);
	}
	
	public String getName() {
		return firstName + " " + lastName;
	}
	public String getFName() {
		return firstName;
	}
	public String getLName() {
		return lastName;
	}
	public String getAddress() {
		return address;
	}
	public int getGrade() {
		return grade;
	}
	public int getMathMark() {
		return mathMark;
	}
	public int getEngMark() {
		return engMark;
	}
	public int getSciMark() {
		return sciMark;
	}
	public int getCompMark() {
		return compMark;
	}
	
	
	public void setName(String newName) {
		holder = newName.split(" ");
		this.firstName = holder[0];
		this.lastName = holder[1];
	}
	public void setName(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	public void setAddress(String newAddress) {
		this.address = newAddress;
	}
	public void setGrade(int newGrade) {
		this.grade = newGrade;
	}
	public void setMathMark(int newMathMark) {
		this.mathMark = newMathMark;
	}
	public void setEngMark(int newEngMark) {
		this.engMark = newEngMark;
	}
	public void setSciMark(int newSciMark) {
		this.sciMark = newSciMark;
	}
	public void setCompMark(int newCompMark) {
		this.compMark = newCompMark;
	}
	
	
	public float calcAver() { //calculate the average of marks
		return (float)(getMathMark() + getEngMark() + getSciMark() + getCompMark()) / 4; 
	}

	
	public String toString() {
		return getFName() + ", " + getLName() + ", " + getAddress() + ", " + getGrade() + ", " + getMathMark() 
		+ ", " + getEngMark() + ", " + getSciMark() + ", " + getCompMark();
	}
}