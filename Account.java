/**
 * This abstract account class holds a general account type
 * accounts can be of checking, savings, or credit. It provides
 * basic functionality such as getting and setting account
 * number and balance, and provides abstract methods to be 
 * implemented in the subclasses.
 * @author Abraham Avalos
 */
public abstract class Account {
	protected String accountNumber;
	protected double balance;
	
	/** Constructor for account that passes all attributes 
	 * 
	 * @param accountNumber the number associated with the account
	 * @param balance the current balance of the account
	 */
	public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
	
	/** abstract method to deposit 
	 * @param amount the amount to be deposited
	 */
	public abstract boolean deposit(double amount);
	/** abstract method to withdraw 
	 * @param amount the amount to be withdrawn
	 */
    public abstract boolean withdraw(double amount);
    /** abstract method to display account info */
    public abstract void accountInfo();
    
    /** gets the balance of the account 
     * @return balance the current balance of the account
     */
    public double getBalance() {
        return balance;
    }

    /** sets the balance of the account 
     * @param balance the balance of the account
     */
    protected void setBalance(double balance) {
        this.balance = balance;
    }
    
    /** gets the account number 
     * @return accountNumber the number associated with the account
     */
    public String getAccountNumber() {
        return accountNumber;
    }

    /** sets the account number 
     * @param accountNumber the number to associate with the account
     */
    protected void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }
    
    
}
