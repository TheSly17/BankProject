import java.util.List;

/**
 * Concrete subclass for the person abstract class. Represents a customer
 * and creates a customer object with the given attributes. Provides a search
 * by ID method to search for a customer based on Id, and a search by Username
 * function to search by name.includes setters and getters for all the attributes.
 * @author Abraham Avalos 
 */
class Customer extends Person {
	private Checking checkingAccount;
	private Savings savingsAccount;
	private Credit creditAccount;
	
	/** constructor that creates a customer object with all attributes */
	public Customer(String idNumber, String firstName, String lastName, String dob,
					String address, String phoneNum,Checking checkingAccount,
					Savings savingsAccount, Credit creditAccount) {
		
		super(idNumber, firstName, lastName, dob, address, phoneNum);
		this.checkingAccount = checkingAccount;
		this.savingsAccount = savingsAccount;
		this.creditAccount = creditAccount;
	}
	
	/** Constructor that creates a customer object and sets all attributes to null*/
	public Customer() {
        super();  // Calls the no-argument constructor of Person
        this.checkingAccount = null;
        this.savingsAccount = null;
        this.creditAccount = null;
    }
	
	/**
	 * method to search for a user by id number
	 * @param customers a list of customers to search
	 * @param id the id of the customer to search
	 * @return customer if found, null otherwise
	 */
	public static Customer searchUserById(List<Customer> customers, String id) {
		for (Customer customer : customers) {
			if (customer.idNumber.equals(id)) {
				return customer; // Return the found user
            }
        }
		// returns null if no customer is found
        return null;
	}
	
	/**
	 * method to search for a user by name
	 * @param customers a list of customers to search
	 * @param userName the name of the customer
	 * @return customer if found, null otherwise 
	 */
	public static Customer searchUserByName(List<Customer> customers, String userName) {
		for (Customer customer : customers) {
			String customerName = customer.getFirstName() + " " + customer.getLastName();
			if (customerName.equalsIgnoreCase(userName)) {
				return customer; // Return the found user
            }
        }
		// returns null if no customer is found
        return null;
		
	}
	 
	 /**
	  * setter for checking account attribute
	  * 
	  * @param account the checking account to connect to the user
	  */
	public void setCheckingAccount(Checking account) {
		this.checkingAccount = account;
	}
	 
	/**
	 * setter for savings account
	 * 
	 * @param account the savings account to connect to the user
	 */
	public void setSavingsAccount(Savings account) {
		this.savingsAccount = account;
	}
	 
	/**
	 * setter for credit account
	 * 
	 *  @param account the credit account to connect to the user
	 */
	public void setCreditAccount(Credit account) {
		this.creditAccount = account;
	}
	 
	/**
	 * getter for checking account
	 * 
	 * @return checkingAccount the checking account connected to the user
	 */
	public Checking getCheckingAccount() {
		return checkingAccount;
	}
	
	/**
	 * getter for savings account
	 * 
	 * @return savingsAccount the savings account connected to the user
	 */
	public Savings getSavingsAccount() {
		return savingsAccount;
	}
	
	/**
	 * getter for credit account
	 * 
	 * @return creditAccount the credit account connected to the user
	 */
	public Credit getCreditAccount() {
		return creditAccount;
	}
	
	public void transferFunds(String fromAccount, String toAccount, double amount) {
		Account fromAcc = getAccount(fromAccount);
		Account toAcc = getAccount(toAccount);
		
		boolean success = fromAcc.withdraw(amount);
		if(success) {
			toAcc.deposit(amount);
		} else {
			System.out.println("Not enough funds to transfer in desired account");
		}
	}
	
	public Account getAccount(String accountType) {
		switch(accountType.toUpperCase()) {
			case "A":
				return checkingAccount;
			case "B":
				return savingsAccount;
			case "C":
				return creditAccount;
			default:
				return null;
		}
	}
	 
	/** method to display the info of a customer */
	public void displayInfo() {
		System.out.println("Identification Number: " + idNumber);
		System.out.println("Name: " + firstName + " " + lastName);
		System.out.println("Address: " + address);
		System.out.println("Phone number: " + phoneNum);
	}
	
	public void printOptions(){
    	System.out.println("A. Checking Account");
    	System.out.println("B. Savings Account");
    	System.out.println("C. Credit Account");
    }
	
}
