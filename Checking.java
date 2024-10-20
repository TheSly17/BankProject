
/**
 * Concrete subclass Checking for the abstract account class
 * implements the abstract methods in the account class,
 * to deposit, withdraw, show info, and adds a pay
 * method to be used for the pay someone functionality
 * within the project
 * @author Abraham Avalos
 */
class Checking extends Account {
	
	/** Constructor for checking class with all parameters
	 * 
	 * @param account number the number of the account
	 * @param balance the current balance of the account
	 */
	public Checking(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

	/** deposits an amount into the account 
	 * 
	 * @param amount the amount to be deposited
	 * @return true if successful, false otherwise
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

	/** Withdraws funds from the account 
	 * 
	 * @param amount the amount to be withdrawn
	 * @return true if successful, false otherwise
	 */
	public boolean withdraw(double amount) {

		// Assuming no overdraft ability
		if (amount > 0 && amount <= this.balance) {
			this.balance -= amount;
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
	
	/** method to print account number and balance */
	public void accountInfo() {
		System.out.println("\nChecking Account:\nAccount Number: " + this.accountNumber + "\nBalance: " + this.balance);
	}
}
