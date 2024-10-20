/**
 * Concrete subclass Credit for the abstract account class
 * implements the abstract methods in the account class,
 * to deposit, withdraw, show info, and assumes a person cannot pay someone from 
 * credit account. Deposit is taken as payment towards the Principle, and 
 * user can withdraw funds as long as it does not exceed credit max
 * @author Abraham Avalos
 */
class Credit extends Account{
	private double creditMax;
	
	/**
	 * Constructor for Credit class with all parameters
	 * 
	 * @param account number the number associated with the account
	 * @param balance the (negative) balance of the account
	 * @param creditMax the maximum credit available to the user
	 */
	public Credit(String accountNumber, double balance, double creditMax) {
		super(accountNumber, balance);
		this.creditMax = creditMax;
	}

	/**
	 * method to deposit into account (make payment towards principle)
	 * 
	 * @param amount to be deposited
	 * @return true if successful, false otherwise
	 */
	public boolean deposit(double amount) {
		// making a payment towards principle
		// mainly used to transfer funds
		if (amount + balance <= 0) {
			balance += amount;
			return true;
		// there is not balance9principle) to be paid
		}else {
			System.out.println("No balance to be paid!");
			return false;
		}
	}

	/**
	 * method to withdraw funds from credit account
	 * 
	 * @param amount amount to be withdrawn
	 * @return true if successful, false otherwise
	 */
	public boolean withdraw(double amount) {
		double limit = 0 - creditMax;
		// withdrawal exceeds limit
		if ((balance - amount) < limit ) {
			System.out.println("Transaction exceeds credit limit!");
			return false;
		// withdrawal is successful
		}else {
			balance -= amount;
			return true;
		}
	}
	
	/**
	 * sets the credit maximum for the account
	 * 
	 * @param maximum the max amount to be lent
	 */
	public void setCreditMax(double maximum) {
		this.creditMax = maximum;
	}
	
	/**
	 * gets the credit maximum
	 * 
	 * @return creditMax the maximum available balance
	 */
	public double getCreditMax() {
		return creditMax;
	}

	/** method to print credit account info */
	public void accountInfo() {
		System.out.println("\nCredit Account:\nAccount Number: " + this.accountNumber + "\nBalance: " + this.balance + "\nLimit: " + creditMax);
	}
}
