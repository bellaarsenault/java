/* Account.java
 * Name: Bella Arsenault
 * Date: Feb 21, 2022
 * 		purpose: this class takes input from the arguments passed in to the methods where the user would be allowed to 
 * 				 would have a first name and a last name as well as the amount that they are opening their account with.
 * 				 the user would then be able to add an amount to the balance as long as it is greater than zero and could 
 * 				 withdraw an amount that does not exceed the amount that they have in their account.
 * 
 * methods: (return types of the methods are listed before the method)
 * void setFirstName   
 * void setLastName  
 * String getName  
 * void credit 
 * void credit - overloaded method in case a float is passed in, instead of a double (no typecasting would need to occur) 
 * void debit 
 * void debit - (same function as the 2nd void credit)
 * float getBalance  
 * String toString 
 * 		
 * 
 */

package assignment2;

public class Account {
	private String lastName;
	private String firstName;
	private float balance;
	
	public Account() { //constructor
		lastName  = "none";
		firstName = "none";
		balance = 0;
	}
	public Account(String str1, String str2, double num1 ) { //overloaded constructor - needs to make sure that initial money entered is not negative
		lastName = str1;
		firstName = str2;
		if (num1 < 0) {
			System.out.println("You cannot create an account with a negative number, your balance has been set to 0");
			balance = 0;
		}
		else {
			balance = (float)num1;
		}
	}
	
	public void setFirstName(String firstName) { //allow to edit first name
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) { //allow to edit last name
		this.lastName = lastName;
	}
	
	public String getName() { //return first and last name
		return lastName + firstName;
	}
	
	public void credit(double amount) { //adds amount to the balance
		float addAmount;
		addAmount = (float)amount;
		if (addAmount <= 0) {
			System.out.println("You cannot enter a value that is equal to or less than 0. Nothign has been added to your account");
		}
		else {
			balance = balance + addAmount;
		}
	}
	
	public void credit(float amount) { //overloaded method in case you were to switch the input from main to float it would still work
		float addAmount;
		addAmount = amount;
		if (addAmount <= 0) {
			System.out.println("You cannot enter a value that is equal to or less than 0. Nothign has been added to your account");
		}
		else {
			balance = balance + addAmount;
		}
	}
	
	public void debit(double amount) { //subtracts amount to the balance, must not exceed the amount that has been inputted
		float subtractAmount;
		subtractAmount = (float)amount;
		if (subtractAmount > balance) {
			System.out.println("You cannot withdraw an amount that exceeds your balance, your transaction did not go through");
			
		}
		else {
			balance = balance - subtractAmount;
		}
	}
	
	public void debit(float amount) { //subtracts amount to the balance, must not exceed the amount that has been inputted
		float subtractAmount;
		subtractAmount = amount; //overloaded method again like above
		if (subtractAmount > balance) {
			System.out.println("You cannot withdraw an amount that exceeds your balance, your transaction did not go through");
			
		}
		else {
			balance = balance - subtractAmount;
		}
	}
	
	public float getBalance() { //returns the current balance
		return balance;
	}
	
	
	public String toString() { //shows account statistics
		return firstName + " " + lastName + " " + balance;
	} 
}
