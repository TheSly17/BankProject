/**
 * Concrete subclass Savings for the abstract account class
 * implements the abstract methods in the account class,
 * to deposit, withdraw, show info, and adds a pay
 * method to be used for the pay someone functionality
 * within the project
 * @author Abraham Avalos
 */
class Savings extends Account {
	
	/** Constructor for savings object that takes all parameters
	 * @param account number the number associated with the account
	 * @param balance the current balance of the account
	 */
	public Savings(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

	/** method to deposit funds into account
	 * 
	 * @param amount the amount to be deposited
	 */
	public boolean deposit(double amount) {
		
		// amount must be positive
		if (amount > 0) {
			balance += amount;
			return true;
		}else {
			System.out.println("Cannot add a negative / Zero balance!");
			return false;
		}
	}

	/** methdo to withdraw funds from account 
	 * 
	 * @param amount the amount to withdraw
	 */
	public boolean withdraw(double amount) {

		// Assuming no overdraft ability
		if (amount > 0 && amount <= this.balance) {
			this.balance -= amount;
			System.out.println("Successfully withdrew $" + amount + "from savings account");
			return true;
		}else {
			System.out.println("Insufficient Balance or invalid amount!");
			return false;
		}
	}
	
	/** pays someone, takes an account and amount as parameters
	 * 
	 * @param recieverAccount the account to receive funds
	 * @param amount the amount to be paid
	 * @return true if successful, false otherwise
	 */
	public boolean pay(Account receiverAccount, double amount){
		// checks for sufficient balance
		if(this.balance >= amount) {
			this.withdraw(amount);
			receiverAccount.deposit(amount);
			return true;
		// insufficient funds
		}else {
			System.out.println("Transaction Failed! Insufficient funds.");
			return false;
		}
	}

	/** method to display the info for the account */
	public void accountInfo() {
		System.out.println("\nSavings Account:\nAccount Number: " + this.accountNumber + "\nBalance: " + this.balance);
	}
}
