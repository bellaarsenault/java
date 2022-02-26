/* EmailGenerator.java
 * Name: bella arsenault
 * Date:feb 23, 2022
 * 
 * 		purpose: emailGenerator takes strings passed in from the user and creates an email adress that starts with the first 3 letters of the last name, the length of last name (a number) and the last 2 letter of the first name, it will then return the result
 * 
 * methods:
 * 	emailGenerator - returns String
 * 
 */
package assignment3;

public class EmailGenerator {
	private String firstName;
	private String lastName;
	private String email;
	private String endEmail;
	private int lastNameLength;
	
	public EmailGenerator() {
		firstName = " ";
		lastName = " ";
		email = "";
		endEmail = "@gmail.com";
		lastNameLength = 0;
	}
	
	public void setNames(String firstName, String lastName) {
		this.firstName = firstName.toLowerCase();
		this.lastName = lastName.toLowerCase();
	}
	public String emailGenerator() { //first 3 of last name, num of last name legnth, 2 letters of first ex - ars9la@gmail.com 
		
		lastNameLength = lastName.length();		
		
		for (int x = 0; x < 3; x++) {
			email = email + lastName.charAt(x);
		}
		
		email = email + lastNameLength;
		
		for (int y = 2; y >= 1; y--) {
			email = email + firstName.charAt(firstName.length() - y);
		}
		
		email = email + endEmail;
		
		return email;
	}

}
