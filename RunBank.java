import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//Check Line 393

public class RunBank {

    public static void main(String[] args) throws FileNotFoundException {
    	// initializing csvReader and lists
        String csvFileName = "C:\\Users\\luisd\\OneDrive\\Documents\\UTEP\\adv. Programing\\Project\\BankProject\\CS 3331 - Bank Users(1).csv";
        csvReader reader = new csvReader();
        reader.readCSV(csvFileName);
        List<String> ids = reader.getIdList();
        List<String> firstNames = reader.getFirstNameList();
        List<String> lastNames = reader.getLastNameList();
        List<String> dob = reader.getDobList();
        List<String> addresses = reader.getAddressList();
        List<String> phones = reader.getPhoneList();
        List<String> checkingAccNums = reader.getCheckingAccNumList();
        List<String> checkingBalances = reader.getCheckingBalanceList();
        List<String> savingsAccNums = reader.getSavingsAccNumList();
        List<String> savingsBalances = reader.getSavingsBalanceList();
        List<String> creditAccNums = reader.getCreditAccNumList();
        List<String> creditMaxes = reader.getCreditMaxList();
        List<String> creditBalances = reader.getCreditBalanceList();
		reader.writeToCSV(csvFileName);
		String logEntry;
        
        // creating a customer list to store customers
        List<Customer> customerList = new ArrayList<>();
        int numCustomers = ids.size();
        
        // iterates over each index in every list
        for (int i = 0; i < numCustomers; i++) {
        	// create accounts for each customer
        	Checking checking = new Checking(checkingAccNums.get(i), Double.parseDouble(checkingBalances.get(i)));
            Savings savings = new Savings(savingsAccNums.get(i), Double.parseDouble(savingsBalances.get(i)));
            Credit credit = new Credit(creditAccNums.get(i), Double.parseDouble(creditBalances.get(i)), Double.parseDouble(creditMaxes.get(i)));
            
         // Create a new Customer object with all parameters
            Customer customer = new Customer(
                    ids.get(i), 
                    firstNames.get(i), 
                    lastNames.get(i), 
                    dob.get(i), 
                    addresses.get(i), 
                    phones.get(i),
                    checking, 
                    savings, 
                    credit);

            // Add the customer to the customer list
            customerList.add(customer);
        }

        // scanner for user input
        Scanner scan = new Scanner(System.in);
        boolean login = true;
        boolean proceed = true;

        System.out.println("Welcome to El Paso Miners Bank");
        Customer user = null;
        // while loop for ui to iterate until user chooses to exit
        uiLoop: while (proceed) {
        	
        	
        	// asks the user to login if true (logged out or first operation)
        	while(login) {
            	System.out.println("please enter 'A' to login by name, 'B' to login by identification number, or 'exit' to exit: ");
            	String input = scan.nextLine();
            	
            	// exit on exit command
            	if (input.equalsIgnoreCase("exit")) {
            		login = false;
            		proceed = false;
            		break uiLoop;
            	}
            	
            	// look up by name
            	if(input.equalsIgnoreCase("a")) {
            		System.out.println("Please enter your name: (add only 1 space between first and last names)");
            		input = scan.nextLine();
            		// search for the customer
            		user = Customer.searchUserByName(customerList, input);
            		if (user == null) {	// there is no customer by that name
            			System.out.println("No user with that name!");
            			continue;
            		} else {	// customer with provided name found
            			System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName() + "!");
            			login = false;
            			break;
            		}
            	} else if(input.equalsIgnoreCase("b")) {	// user chooses to enter id number
            		System.out.println("Please enter your identification number: ");
            		input = scan.nextLine();
            		
            		user = Customer.searchUserById(customerList, input);
            		if (user == null) {
            			System.out.println("No user with that ID!");
            			continue;
            		} else {
            			System.out.println("Welcome, " + user.getFirstName() + " " + user.getLastName());
            			login = false;
            			break;
            		}
            	} else {	// invalid input when asked how to log in
            		System.out.println("Please enter a valid input! ");
            		continue;
            	}
        	} // end of login block
        	
        	//user.getFirstName();
        	// ask user about action to be performed
        	System.out.println("What would you like to do? please type a number, or 'exit' to exit: ");
        	System.out.println("1. Inquire about a balance");
        	System.out.println("2. Deposit money into an account");
        	System.out.println("3. Withdraw money from an account");
        	System.out.println("4. Transfer money between accounts");
        	System.out.println("5. Pay someone");
        	int userIndex = customerList.indexOf(user);
			System.out.println(userIndex);
        	boolean validInput = false;
        	
        	// user is asked what action to be performed until a valid action is requested
        	while(!validInput) {
        		String input = scan.nextLine();
        		
        		switch (input) {
        		
        			// inquire about a balance
        			case "1":
        				// Provide user with account options
        				System.out.println("Inquire about a balance:\nWhich account would you like to inquire about? (please choose 'A', 'B', or 'C')");
        				System.out.println("A. Checking account");
        				System.out.println("B. Savings account");
        				System.out.println("C. Credit account");
						
        				boolean invalidInputForBalance = true;
        				String chosenAccountForBalance = scan.nextLine();
        				// Loop until valid input
        				while (invalidInputForBalance) {
        					if (chosenAccountForBalance.equalsIgnoreCase("A") || chosenAccountForBalance.equalsIgnoreCase("B") || chosenAccountForBalance.equalsIgnoreCase("C")) {
        						invalidInputForBalance = false;
        						break;
        					} else { // Not a valid request
        						System.out.println("Choose a valid input!");
        						chosenAccountForBalance = scan.nextLine();
        					}
        				}

        				switch (chosenAccountForBalance.toUpperCase()) {
        		        	case "A":
        		        		System.out.println("Your Checking account balance is: $" + user.getCheckingAccount().getBalance());
								logEntry = user.getFirstName() + " " + user.getLastName() + " made a balance inquiry on Checking-" + user.getCheckingAccount().getAccountNumber() + ". " + user.getFirstName() + " " + user.getLastName() + "'s Balance for Checking-" + user.getCheckingAccount().getAccountNumber() + ": $" + user.getCheckingAccount().getBalance();
								try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
									logWriter.write( logEntry + "\n");
								} catch (IOException e) {
									System.out.println("Error writing to log file: " + e.getMessage());
								}
								
								break;
        		        	case "B":
        		        		System.out.println("Your Savings account balance is: $" + user.getSavingsAccount().getBalance());
        		        		logEntry = user.getFirstName() + " " + user.getLastName() + " made a balance inquiry on Savings-" + user.getSavingsAccount().getAccountNumber() + ". " + user.getFirstName() + " " + user.getLastName() + "'s Balance for Savings-" + user.getSavingsAccount().getAccountNumber() + ": $" + user.getSavingsAccount().getBalance();
								try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
									logWriter.write( logEntry + "\n");
								} catch (IOException e) {
									System.out.println("Error writing to log file: " + e.getMessage());
								}
								break;
        		        	case "C":
        		        		System.out.println("Your Credit account balance is: $" + user.getCreditAccount().getBalance());
        		        		logEntry = user.getFirstName() + " " + user.getLastName() + " made a balance inquiry on Credit-" + user.getCreditAccount().getAccountNumber() + ". " + user.getFirstName() + " " + user.getLastName() +  "'s Balance for Credit-" + user.getCreditAccount().getAccountNumber() + ": $" + user.getCreditAccount().getBalance();

								try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
									logWriter.write( logEntry + "\n");
								} catch (IOException e) {
									System.out.println("Error writing to log file: " + e.getMessage());
								}
								break;
        		        	default:
        		        		System.out.println("Invalid account selection!");
        				}
        				validInput = true; // Mark input as valid
        				break; // End of case 1

        				// deposit money
        			case "2":
        				System.out.println("Deposit money into an account:\nWhich account would you like to deposit funds into? (please choose 'A', 'B', or 'C')");
        				System.out.println("A. Checking account");
        				System.out.println("B. Savings account");
        				System.out.println("C. Credit account");

        				boolean invalidInputForDeposit = true;
        				String chosenAccountForDeposit = scan.nextLine();
        				while (invalidInputForDeposit) {
        					if (chosenAccountForDeposit.equalsIgnoreCase("A") || chosenAccountForDeposit.equalsIgnoreCase("B") || chosenAccountForDeposit.equalsIgnoreCase("C")) {
        						invalidInputForDeposit = false;
        					} else {
        						System.out.println("Choose a valid input!");
        						chosenAccountForDeposit = scan.nextLine();
        					}
        				}

        				double depositAmount = 0;
        				switch (chosenAccountForDeposit.toUpperCase()) {
        		        	case "A":
        		        		// Loop for deposit amount for checking account
        		        		boolean validDepositInputChecking = false;
        		        		while (!validDepositInputChecking) {
        		        			System.out.println("How much would you like to deposit into your Checking account?");
        		        			String strAmountChecking = scan.nextLine();

        		        			try {
        		        				depositAmount = Double.parseDouble(strAmountChecking);
        		        				if (depositAmount < 0) {
        		        					System.out.println("Deposit amount cannot be negative. Please enter a valid amount.");
        		        				} else {
        		        					validDepositInputChecking = true; // valid input, exit loop
        		        				}
        		        			} catch (NumberFormatException e) {
        		        				System.out.println("Invalid input. Please enter a numeric value for the deposit amount.");
        		        			}
        		        		}
        		        		boolean successChecking = user.getCheckingAccount().deposit(depositAmount);
        		        		if (successChecking) {
        		        			System.out.println("Successfully deposited $" + depositAmount + " into Checking account");
        		        			System.out.println("Updated balance: $" + user.getCheckingAccount().getBalance());
									checkingBalances.set(userIndex, String.valueOf(user.getCheckingAccount().getBalance()));
									reader.writeToCSV(csvFileName);
									 logEntry = user.getFirstName() + " " + user.getLastName() +" deposited $" + depositAmount + " into Checking-" + user.getCheckingAccount().getAccountNumber() + ". Updated Balance for Checking-" + user.getCheckingAccount().getAccountNumber() + ": $" +user.getCheckingAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        		        		} else {
        		        			System.out.println("Deposit failed!");
        		        		}
        		        		break;

        		        	case "B":
        		        		// Loop for deposit amount for savings account
        		        		boolean validDepositInputSavings = false;
        		        		while (!validDepositInputSavings) {
        		        			System.out.println("How much would you like to deposit into your Savings account?");
        		        			String strAmountSavings = scan.nextLine();

        		        			try {
        		        				depositAmount = Double.parseDouble(strAmountSavings);
        		        				if (depositAmount < 0) {
        		        					System.out.println("Deposit amount cannot be negative. Please enter a valid amount.");
        		        				} else {
        		        					validDepositInputSavings = true; // valid input, exit loop
        		        				}
        		        			} catch (NumberFormatException e) {
        		        				System.out.println("Invalid input. Please enter a numeric value for the deposit amount.");
        		        			}
        		        		}
        		        		boolean successSavings = user.getSavingsAccount().deposit(depositAmount);
        		        		if (successSavings) {
        		        			System.out.println("Successfully deposited $" + depositAmount + " into Savings account");
        		        			System.out.println("Updated balance: $" + user.getSavingsAccount().getBalance());
									savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
									reader.writeToCSV(csvFileName);
									logEntry = user.getFirstName() + " " + user.getLastName() +" deposited $" + depositAmount + " into Savings-" + user.getSavingsAccount().getAccountNumber() + ". Updated Balance for Savings-" + user.getSavingsAccount().getAccountNumber() + ": $" +user.getSavingsAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        		        		} else { 
        		        			System.out.println("Deposit failed!");
        		        		}
        		        		break;

        		        	case "C":
        		        		// Loop to deposit amount for credit account (pay towards principle)
        		        		boolean validDepositInputCredit = false;
        		        		while (!validDepositInputCredit) {
        		        			System.out.println("How much would you like to deposit towards your Credit account?");
        		        			String strAmountCredit = scan.nextLine();

        		        			try {
        		        				depositAmount = Double.parseDouble(strAmountCredit);
        		        				if (depositAmount < 0) {
        		        					System.out.println("Deposit amount cannot be negative. Please enter a valid amount.");
        		        				} else {
        		        					validDepositInputCredit = true; // valid input, exit loop
        		        				}
        		        			} catch (NumberFormatException e) {
        		        				System.out.println("Invalid input. Please enter a numeric value for the deposit amount.");
        		        			}
        		        		}
        		        		boolean successCredit = user.getCreditAccount().deposit(depositAmount);
        		        		if (successCredit) {
        		        			System.out.println("Successfully deposited $" + depositAmount + " towards your Credit account");
        		        			System.out.println("Updated balance: $" + user.getCreditAccount().getBalance());
									creditBalances.set(userIndex, String.valueOf(user.getCreditAccount().getBalance()));
									reader.writeToCSV(csvFileName);
									logEntry = user.getFirstName() + " " + user.getLastName() +" deposited $" + depositAmount + " into Credit-" + user.getCreditAccount().getAccountNumber() + ". Updated Balance for Credit-" + user.getCreditAccount().getAccountNumber() + ": $" +user.getCreditAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        		        		} else {
        		        			System.out.println("Deposit failed!");
        		        		}
        		        		break;

        		        	default:
        		        		System.out.println("Invalid account selection!");
        				}
        				validInput = true; // Mark input as valid
        				break; // End of case 2
        				
        			// withdraw money
        			case "3":
        				 System.out.println("Withdraw money from an account:\nWhich account would you like to withdraw from? (please choose 'A', 'B', or 'C')");
        				 System.out.println("A. Checking account");
        				 System.out.println("B. Savings account");
        				 System.out.println("C. Credit account");

        				 boolean invalidInputForWithdraw = true;
        				 String chosenAccountForWithdraw = scan.nextLine();
        				 while (invalidInputForWithdraw) {
        					 if (chosenAccountForWithdraw.equalsIgnoreCase("A") || chosenAccountForWithdraw.equalsIgnoreCase("B") || chosenAccountForWithdraw.equalsIgnoreCase("C")) {
        						 invalidInputForWithdraw = false;
        					 } else {
        						 System.out.println("Choose a valid input!");
        						 chosenAccountForWithdraw = scan.nextLine();
        					 }
        				 }
        				 
        				 double withdrawAmount = 0;
        				 switch (chosenAccountForWithdraw.toUpperCase()) {
        				 	case "A":
        				 		// Loop for withdrawal amount for checking account
        				 		boolean validWithdrawInputChecking = false;
        				 		while (!validWithdrawInputChecking) {
        				 			System.out.println("How much would you like to withdraw from your Checking account?");
        				 			String strAmountChecking = scan.nextLine();

        				 			try {
        				 				withdrawAmount = Double.parseDouble(strAmountChecking);
        				 				if (withdrawAmount < 0) {
        				 					System.out.println("Withdrawal amount cannot be negative. Please enter a valid amount.");
        				 				} else if (withdrawAmount > user.getCheckingAccount().getBalance()) {
        				 					System.out.println("Insufficient funds! Please enter an amount less than or equal to your Checking balance.");
        				 				} else {
        				 					validWithdrawInputChecking = true; // valid input, exit loop
        				 				}
        				 			} catch (NumberFormatException e) {
        				 				System.out.println("Invalid input. Please enter a numeric value for the withdrawal amount.");
        				 			}
        				 		}
        				 		boolean successCheckingWithdraw = user.getCheckingAccount().withdraw(withdrawAmount);
        				 		if (successCheckingWithdraw) {
        				 			System.out.println("Successfully withdrew $" + withdrawAmount + " from Checking account");
        				 			System.out.println("Updated balance: $" + user.getCheckingAccount().getBalance());
									checkingBalances.set(userIndex,String.valueOf(user.getCheckingAccount().getBalance()));
									reader.writeToCSV(csvFileName);
									logEntry = user.getFirstName() + " " + user.getLastName() +" withdrew $" + withdrawAmount + " into Checking-" + user.getCheckingAccount().getAccountNumber() + ". Updated Balance for Checking-" + user.getCheckingAccount().getAccountNumber() + ": $" +user.getCheckingAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        				 		} else {
        				 			System.out.println("Withdrawal failed!");
        				 		}
        				 		break;

        				 	case "B":
        				 		// Loop for withdrawal amount for savings account
        				 		boolean validWithdrawInputSavings = false;
        				 		while (!validWithdrawInputSavings) {
        				 			System.out.println("How much would you like to withdraw from your Savings account?");
        				 			String strAmountSavings = scan.nextLine();

        				 			try {
        				 				withdrawAmount = Double.parseDouble(strAmountSavings);
        				 				if (withdrawAmount < 0) {
        				 					System.out.println("Withdrawal amount cannot be negative. Please enter a valid amount.");
        				 				} else if (withdrawAmount > user.getSavingsAccount().getBalance()) {
        				 					System.out.println("Insufficient funds! Please enter an amount less than or equal to your Savings balance.");
        				 				} else {
        				 					validWithdrawInputSavings = true; // valid input, exit loop
        				 				}
        				 			} catch (NumberFormatException e) {
        				 				System.out.println("Invalid input. Please enter a numeric value for the withdrawal amount.");
        				 			}
        				 		}
        				 		boolean successSavingsWithdraw = user.getSavingsAccount().withdraw(withdrawAmount);
        				 		if (successSavingsWithdraw) {
        				 			System.out.println("Successfully withdrew $" + withdrawAmount + " from Savings account");
        				 			System.out.println("Updated balance: $" + user.getSavingsAccount().getBalance());
									 savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
									 reader.writeToCSV(csvFileName);
									 logEntry = user.getFirstName() + " " + user.getLastName() +" withdrew $" + withdrawAmount + " into Savings-" + user.getSavingsAccount().getAccountNumber() + ". Updated Balance for Savings-" + user.getSavingsAccount().getAccountNumber() + ": $" +user.getSavingsAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        				 		} else {
        				 			System.out.println("Withdrawal failed!");
        				 		}
        				 		break;

        				 	case "C":
        				 		// Loop for credit account withdrawal (increasing credit)
        				 		boolean validWithdrawInputCredit = false;
        				 		while (!validWithdrawInputCredit) {
        				 			System.out.println("How much would you like to withdraw from your Credit account?");
        				 			String strAmountCredit = scan.nextLine();

        				 			try {
        				 				
										withdrawAmount = Double.parseDouble(strAmountCredit);
        				 				// cannot withdraw a negative number
        				 				if (withdrawAmount < 0) {
        				 					System.out.println("Withdrawal amount cannot be negative. Please enter a valid amount.");
        				 				// 
        				 				} else if (user.getCreditAccount().withdraw(withdrawAmount) == false) {
        				 					System.out.println("Your credit balance is not high enough for this amount. Enter a lower amount.");
        				 				} else {
        				 					validWithdrawInputCredit = true; // valid input, exit loop
        				 				}
        				 			} catch (NumberFormatException e) {
        				 				System.out.println("Invalid input. Please enter a numeric value for the withdrawal amount.");
        				 			}
        				 		}
        				 		// withdrawing credit is successful
        				 		boolean successCreditWithdraw = user.getCreditAccount().withdraw(withdrawAmount);
        				 		if (successCreditWithdraw) {
        				 			System.out.println("Successfully withdrew $" + withdrawAmount + " from your Credit account.");
        				 			System.out.println("Updated balance: $" + user.getCreditAccount().getBalance());
									 creditBalances.set(userIndex, String.valueOf(user.getCreditAccount().getBalance()));
									 reader.writeToCSV(csvFileName);
									 logEntry = user.getFirstName() + " " + user.getLastName() +" withdrew $" + withdrawAmount + " into Credit-" + user.getCreditAccount().getAccountNumber() + ". Updated Balance for Credit-" + user.getCreditAccount().getAccountNumber() + ": $" +user.getCreditAccount().getBalance();
									try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
										logWriter.write( logEntry + "\n");
									} catch (IOException e) {
										System.out.println("Error writing to log file: " + e.getMessage());
									}
        				 		} else {
        				 			System.out.println("Withdrawal failed!");
        				 		}
        				 		break;

        				 	default:
        				 		System.out.println("Invalid account selection!");
        				 }
        				 validInput = true; // Mark input as valid
        				 break; // End of case 3
        				 
        			// transfer money between accounts
        			case "4":
        				System.out.println("Transfer money Between accounts:\nWhich account would you like to transfer from?");
        				System.out.println("A. Checking account");
       				 	System.out.println("B. Savings account");
       				 	System.out.println("C. Credit account");
       				 	
       				 	// requests valid input account type
        				boolean invalidTransfer = true;
        				String transferAccount = scan.nextLine();
        				while(invalidTransfer) {
        					if(transferAccount.equalsIgnoreCase("A") || transferAccount.equalsIgnoreCase("B") || transferAccount.equalsIgnoreCase("C")) {
        						invalidTransfer = false;
        					} else {
        						System.out.println("Please choose a vaild account!");
        						transferAccount = scan.nextLine();
        					}
        				}
        				
        				System.out.println("Which account would you like to transfer to?");
        				System.out.println("A. Checking account");
       				 	System.out.println("B. Savings account");
       				 	System.out.println("C. Credit account");
        				
       				 	// requests valid input for recievers account
       				 	invalidTransfer = true;
       				 	String receiverAccount = scan.nextLine();
       				 	while(invalidTransfer) {
       				 		if(transferAccount.equalsIgnoreCase("A") || transferAccount.equalsIgnoreCase("B") || transferAccount.equalsIgnoreCase("C")) {
       				 			invalidTransfer = false;
       				 		} else {
       				 			System.out.println("Please choose a vaild account!");
       				 			receiverAccount = scan.nextLine();
       				 		}
       				 	}
       				 	
       				 	System.out.println("How much would you like to transfer?");
       				 	String takeAmountReq = scan.nextLine();
       				 	boolean invalidAmt = true;
       				 	Double takeAmount = 0.0;
       				 	while(invalidAmt) {
       				 		try {
       				 			takeAmount = Double.parseDouble(takeAmountReq);
       				 			if (takeAmount < 0) {
       				 				System.out.println("Withdrawal amount cannot be negative. Please enter a valid amount.");
       				 			} else if (takeAmount > user.getSavingsAccount().getBalance()) {
       				 				System.out.println("Insufficient funds! Please enter an amount less than or equal to your Savings balance.");
       				 			} else {
       				 				invalidAmt = false; // valid input, exit loop
       				 			}
       				 		} catch (NumberFormatException e) {
       				 			System.out.println("Invalid input. Please enter a numeric value for the withdrawal amount.");
       				 		}
       				 	}
       				 	
       				 	// transfers funds between requested accounts
       				 	System.out.println("Successfully transferred funds!");
       				 	user.transferFunds(transferAccount, receiverAccount, takeAmount);
       				
  
        System.out.println("Successfully transferred $" + takeAmount + " from your " + transferAccount + " account to " + receiverAccount + " account.");

        // Get account details for logging
        String transferAccountNumber = "";
        String receiverAccountNumber = "";
        double transferAccountBalance = 0.0;
        double receiverAccountBalance = 0.0;

        // Determine which accounts to use for logging purposes
        if (transferAccount.equalsIgnoreCase("A")) {
			checkingBalances.set(userIndex, String.valueOf(user.getCheckingAccount().getBalance()));
			transferAccountNumber = user.getCheckingAccount().getAccountNumber();
            transferAccountBalance = user.getCheckingAccount().getBalance();
        } else if (transferAccount.equalsIgnoreCase("B")) {
            savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
			transferAccountNumber = user.getSavingsAccount().getAccountNumber();
            transferAccountBalance = user.getSavingsAccount().getBalance();
        } else if (transferAccount.equalsIgnoreCase("C")) {
            savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
			transferAccountNumber = user.getCreditAccount().getAccountNumber();
            transferAccountBalance = user.getCreditAccount().getBalance();
        }

        if (receiverAccount.equalsIgnoreCase("A")) {
			checkingBalances.set(userIndex, String.valueOf(user.getCheckingAccount().getBalance()));
			receiverAccountNumber = user.getCheckingAccount().getAccountNumber();
            receiverAccountBalance = user.getCheckingAccount().getBalance();
        } else if (receiverAccount.equalsIgnoreCase("B")) {
			savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
			receiverAccountNumber = user.getSavingsAccount().getAccountNumber();
            receiverAccountBalance = user.getSavingsAccount().getBalance();
        } else if (receiverAccount.equalsIgnoreCase("C")) {
			creditBalances.set(userIndex, String.valueOf(user.getCreditAccount().getBalance()));
			receiverAccountNumber = user.getCreditAccount().getAccountNumber();
            receiverAccountBalance = user.getCreditAccount().getBalance();
        }
		reader.writeToCSV(csvFileName);
        // Log the transaction
        logEntry = user.getFirstName() + " " + user.getLastName() + " transferred $" + takeAmount + " from " + transferAccount + "-" + transferAccountNumber + " to "  + receiverAccount + "-" + receiverAccountNumber + ". "  + user.getFirstName() + " " + user.getLastName() + "'s Balance for "  + transferAccount + "-" + transferAccountNumber + ": $" + transferAccountBalance  + ". " + user.getFirstName() + " " + user.getLastName() + "'s Balance for "  + receiverAccount + "-" + receiverAccountNumber + ": $" + receiverAccountBalance;

        try (FileWriter logWriter = new FileWriter("log.txt", true)) {
            logWriter.write(logEntry + "\n");
        } catch (IOException e) {
            System.out.println("Error writing to log file: " + e.getMessage());
        }
     
						validInput = true;
						
        				break;// end of case 4
        				
        			// pay someone
        			case "5":
        				System.out.println("Pay someone:\nWho would you like to pay?");
        				System.out.println("Search user by: (choose 'A' or 'B')\nA. Name\nB. Account Number");
        				String lookBy = scan.nextLine();
        				boolean notValid = true;
        				
        				while(notValid) {
        					if(lookBy.equalsIgnoreCase("A") || lookBy.equalsIgnoreCase("B")) {
        						notValid = false;
        					}
        					else {
        						System.out.println("Not a valid input!");
        						lookBy = scan.nextLine();
        					}
        				}
        				
        				Customer recipient = null;
        				boolean invalidPerson = true;
        				
        				while(invalidPerson) {
        					if (lookBy.equalsIgnoreCase("A")) {
        				        // Search by Name
        				        System.out.println("Enter the recipient's name:");
        				        String name = scan.nextLine();
        				        
        				        // Search the customer list by name (you need a method for this)
        				        recipient = Customer.searchUserByName(customerList, name);
        				        
        				        if (recipient != null) {
        				            invalidPerson = false; // Valid person found
        				        } else {
        				            System.out.println("No user found with that name. Please try again.");
        				        }

        					} else if (lookBy.equalsIgnoreCase("B")) {
        				        // Search by Account Number
        				        System.out.println("Enter the recipient's account number:");
        				        String accountNumber = scan.nextLine();
        				        
        				        // Search the customer list by account number (you need a method for this)
        				        recipient = Customer.searchUserById(customerList, accountNumber);
        				        
        				        if (recipient != null) {
        				            invalidPerson = false; // Valid person found
        				        } else {
        				            System.out.println("No user found with that account number. Please try again.");
        				        }
        				    }
        				}
        				int userIndexPay = customerList.indexOf(recipient);
						System.out.println(userIndexPay);
						System.out.println("Which account do you want to pay from?");
        				user.printOptions();
        				String payerInput = validateInput(scan);
        				
        				System.out.println("Which account do you want to pay to?");
        				recipient.printOptions();
        				String payeeInput = validateInput(scan);
        				
        				System.out.println("How much would you like to pay?");
        				double payment = getValidAmount(scan);
        				
        				Account payer = user.getAccount(payerInput);
        				Account payee = recipient.getAccount(payeeInput);
        				
        				boolean withdrawSuccess = payer.withdraw(payment);
        				if(withdrawSuccess) {
        					payee.deposit(payment);
        					System.out.println("Successfully payed " + recipient.getFirstName() + " " + recipient.getLastName() + " $" + payment);
							switch(payeeInput.toUpperCase()){
								case "A":
							checkingBalances.set(userIndexPay, String.valueOf(recipient.getCheckingAccount().getBalance()));
							reader.writeToCSV(csvFileName);
							checkingBalances.set(userIndex, String.valueOf(user.getCheckingAccount().getBalance()));
							reader.writeToCSV(csvFileName);
							logEntry = user.getFirstName() + " " + user.getLastName() + " paid $" + payment + " from Checking-" + user.getCheckingAccount().getAccountNumber() + " to " + recipient.getFirstName() + " " + recipient.getLastName() + " (Checking-" + recipient.getCheckingAccount().getAccountNumber() + "). " + user.getFirstName() + " " + user.getLastName() + "'s New Balance for Checking-" + user.getCheckingAccount().getAccountNumber() + ": $" + user.getCheckingAccount().getBalance();
							try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
								logWriter.write( logEntry + "\n");
							} catch (IOException e) {
								System.out.println("Error writing to log file: " + e.getMessage());
							}	break;
							case "B":
								
							savingsBalances.set(userIndexPay, String.valueOf(recipient.getSavingsAccount().getBalance()));
							reader.writeToCSV(csvFileName);
							savingsBalances.set(userIndex, String.valueOf(user.getSavingsAccount().getBalance()));
							reader.writeToCSV(csvFileName);
							logEntry = user.getFirstName() + " " + user.getLastName() + " paid $" + payment + " from Savings-" + user.getSavingsAccount().getAccountNumber() + " to " + recipient.getFirstName() + " " + recipient.getLastName() + " (Savings-" + recipient.getSavingsAccount().getAccountNumber() + "). " + user.getFirstName() + " " + user.getLastName() + "'s New Balance for Savings-" + user.getSavingsAccount().getAccountNumber() + ": $" + user.getSavingsAccount().getBalance();

							try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
								logWriter.write( logEntry + "\n");
							} catch (IOException e) {
								System.out.println("Error writing to log file: " + e.getMessage());
							}	break;
								case "C":
							creditBalances.set(userIndexPay, String.valueOf(recipient.getCreditAccount().getBalance()));
							reader.writeToCSV(csvFileName);
							creditBalances.set(userIndex, String.valueOf(user.getCreditAccount().getBalance()));
							reader.writeToCSV(csvFileName); 
							logEntry = user.getFirstName() + " " + user.getLastName() + " paid $" + payment + " from Credit-" + user.getCreditAccount().getAccountNumber() + " to " + recipient.getFirstName() + " " + recipient.getLastName() + " (Credit-" + recipient.getCreditAccount().getAccountNumber() + "). " + user.getFirstName() + " " + user.getLastName() + "'s New Balance for Credit-" + user.getCreditAccount().getAccountNumber() + ": $" + user.getCreditAccount().getBalance();
							try (FileWriter logWriter = new FileWriter("log.txt", true)) {
									
								logWriter.write( logEntry + "\n");
							} catch (IOException e) {
								System.out.println("Error writing to log file: " + e.getMessage());
							}
							break;
							}
						} else {
        					System.out.println("Insufficient funds!");
        				}
        				validInput = true;
        				break;
        			// exit the system
        			case "exit":
        				break uiLoop;
        			// invalid input
        			default:
        				System.out.println("Invalid input! please try again!");
        				validInput = false;
        				break;
        	
        	} // end of switch statements
        	}// end of validation loop
        	
        	
        } // end of ui loop
        
        System.out.println("Hope to see you soon!");
        
        
    } // ends main method
    
    public static String validateInput(Scanner scan){
    	String transferAccount = "";
        boolean invalidTransfer = true;

        while (invalidTransfer) {
            transferAccount = scan.nextLine(); // Read user input
            if (transferAccount.equalsIgnoreCase("A") || transferAccount.equalsIgnoreCase("B") || transferAccount.equalsIgnoreCase("C")) {
                invalidTransfer = false; // Valid input, exit loop
            } else {
                System.out.println("Please choose a valid account! (A, B, or C)");
            }
        }
        return transferAccount; // Return the valid account type
    }
    
    public static double getValidAmount(Scanner scan) {
        double amount = 0.0;
        boolean validAmount = false;

        while (!validAmount) {
            String strAmountCredit = scan.nextLine();

            try {
                amount = Double.parseDouble(strAmountCredit);
                if (amount < 0) {
                    System.out.println("Amount cannot be negative. Please enter a valid amount.");
                } else {
                    validAmount = true; // valid input, exit loop
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric value for the deposit amount.");
            }
        }

        return amount; // Return the validated deposit amount
    }
    
} // ends class