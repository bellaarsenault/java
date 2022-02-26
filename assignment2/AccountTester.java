package assignment2;

public class AccountTester {

	public static void main(String args[]) {

		Account client1 = new Account();
		Account client2 = new Account("Smith", "George", 300);
		Account client3 = new Account("Thomas", "Sally", -20); 
		Account client4 = new Account("Jefferson", "Trish", 0);

		  System.out.println(client1); 
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);

		  client1.setFirstName("Fred");
		  client1.setLastName("Booth");
		  client1.credit(400);

		  String client1Name = client1.getName(); //just making sure getters work
		  float client1Balance = client1.getBalance();

		  client2.credit(20);
		  client3.credit(200);
		  client4.credit(40); 

		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);

		  client1.debit(60);
		  client2.debit(400);
		  client3.debit(200);
		  client4.debit(20);

		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);
		  
		  client1.credit(15);
		  client2.credit(42);
		  client3.credit(23.70);
		  client4.credit(55);

		  System.out.println(client1);
		  System.out.println(client2);
		  System.out.println(client3);
		  System.out.println(client4);;
       }
 
}

