package creditCardFiles;

import java.util.Scanner;
import java.util.Iterator;
import java.util.LinkedList;

public class CreditCardApp {
	
	private String ownerName;
	//can add in address
	private CreditCards cards;
	private Scanner input;
	
	public CreditCardApp(String ownerName) {
		
		this.ownerName = ownerName;
		this.cards = new CreditCards();
		this.input = new Scanner(System.in);
		displayMenu();
	}
	
	private void displayMenu() {
		
		System.out.println("        Menu");
		
		System.out.print("1. Add a new CreditCard\r\n"
				+ "2. Remove a CreditCard\r\n"
				+ "3. Display total outstanding balances\r\n"
				+ "4. Display total available credit\r\n"
				+ "5. Display largest purchase \r\n"
				+ "6. Display most recent payment\r\n"
				+ "7. Display total spent on certain category of expense\r\n"
				+ "8. For which type of Purchase was the most money spent\r\n"
				+ "9. Manage a specific Credit Card\r\n"
				+ "10. Exit app\r\n"
				);
		
		System.out.print("Your choice: ");		
		int choice = input.nextInt();
		while(choice < 1 || choice > 10) {
			System.out.println("Invalid choice.");
			System.out.print("Your choice: ");		
			choice = input.nextInt();
		}
		
		switch (choice) {
		
		case 1:
			addCreditCard();
			break;
		
		case 2:
			removeCreditCard();
			break;
		
		case 3:
			displayOutstandingBalances();
			break;
			
		case 4:
			displayTotalAvailCredit();
			break;
			
		case 5:
			displayLargestPurchase();
			break;
			
		case 6:
			displayRecentPayment();
			break;
			
		case 7:
			displayTotalOfCategory();
			break;
			
		case 8:
			displayTypeMaxPurchaseValue();
			break;
			
		case 9:
			manageSpecificCard();
			break;
			
		default:
			System.out.print("Thank you for using!");
			System.exit(0);
		}
		
	
		
		
	}
	
	public void addCreditCard() {
		System.out.print("Who is issuing you this new card? ");
		//clear buffer
		input.nextLine();
		String issueCompany = input.nextLine();
		while(issueCompany == null) {
			System.out.println("Invalid.");
			System.out.print("Who is issuing you this new card? ");
			issueCompany = input.nextLine();
		}
		
		System.out.print("Type of Credit Card? ");
		CreditCardType type = CreditCardType.valueOf(input.nextLine());
		
		System.out.print("Status of Credit Card? ");
		CreditCardStatus status = CreditCardStatus.valueOf(input.nextLine());
		
		CreditCard newCard = new CreditCard(issueCompany, type, status);
		cards.addCard(newCard);
		
	}
	
	public void removeCreditCard() {
		
		System.out.print("Please enter the Credit Card Number you wish to remove: ");
		input.nextLine();
		String ccNum = input.nextLine();
		while(ccNum.length() < 15 || ccNum.length() > 16) {
			System.out.println("Invalid Credit Card length.");
			System.out.print("Please enter the Credit Card Number you wish to remove: ");
			ccNum = input.nextLine();
		}
		cards.removeCard(ccNum);
		
	}
	
	public void displayOutstandingBalances() {
		double totalBalance = 0;
		System.out.println("Outstanding Balances on the Following Credit Cards:");
		
		for(CreditCard card:cards) {
			if(card.getCurrentBalance() > 0) {
				System.out.println("Credit Card Number: " + card.getCreditCardID() + "\nOutstanding Balance: " + card.getCurrentBalance());
				totalBalance += card.getCurrentBalance();
			}
		}
		
		System.out.println();
		
		if(totalBalance > 0) {
			System.out.println("Total Outstanding Balances: " + totalBalance);			
		}
		else {
			System.out.println("You have no outstanding balances.");
		}
		
	}
	
	public void displayTotalAvailCredit() {
		int totalAvailCredit = 0;
		
		for(CreditCard card:cards) {
			if(card.getAvailCredit() > 0) {
				totalAvailCredit += card.getAvailCredit();
			}
		}
		
		System.out.println("Total Available Credit: " + totalAvailCredit);
		
	}
	
	public void displayLargestPurchase() {
		
	}
	
	public void displayRecentPayment() {
		
	}
	
	public void displayTotalOfCategory() {
		
	}
	
	public void displayTypeMaxPurchaseValue() {
		
	}
	
	public void manageSpecificCard() {
		
		System.out.println("Enter the Credit Card Num: ");
		String ccNum = input.nextLine();
		
		while(ccNum.length() != 16) {
			System.out.println("Invalid CC Number Length.");
			System.out.print("Please re-enter: ");		
			ccNum = input.nextLine();
			
		}
		
		CreditCard c = cards.findCard(ccNum);
		
		System.out.print("What would you like to do with the Credit Card?");
		System.out.print("\t1. Display current balance\n"
				+ "\t2. Display current credit limit\n"
				+ "\t3. Add a Purchase\n"
				+ "\t4. Add a Payment\n"
				+ "\t5. Add a Fee\n"
				+ "\t6. Display most recent Purchase\n"
				+ "\t7. Display most recent Payment\n"
            	+ "\t8. Back to main menu\n");
		int choice;
		System.out.print("Your choice: ");		
		choice = input.nextInt();
	    while(choice < 1 || choice > 8) {
		       System.out.println("Invalid choice.");
		       System.out.print("Your choice: ");		
		       choice = input.nextInt();
		  }
	         	
	         	switch(choice) {
	         		case 1:
	         			displayCurrBalance(c);
	         			break;
	         			
	         		case 2:
	         			displayCreditLimit(c);
	         			break;
	         			
	         		case 3:
	         			addPurchase(c);
	         			break;
	         		
	         		case 4:
	         			addPayment(c);
	         			break;
	         			
	         		case 5:
	         			addFee(c);
	         			break;
	         			
	         		case 6:
	         			displayRecentPurchase(c);
	         			break;
	         			
	         		case 7:
	         			displayRecentPayment(c);
	         			break;
	         		
	         		default:
	         			displayMenu();
	         			break;
	         			
	         	}
		
	}
	
	public void displayCurrBalance(CreditCard c) {
		
		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Current Balance: " + c.getCurrentBalance());
		
		//go back to main menu or back to manage CC?
		manageSpecificCard();
	}
	
	public void displayCreditLimit(CreditCard c) {
		
		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Current Credit Limit: " + c.getCreditCardLimit());

		manageSpecificCard();
	}
	
	public void addPurchase(CreditCard c) {
		
		System.out.println("Enter Purchase Type: ");
		String type = input.nextLine();
		
		System.out.println("Enter Purchase Amount: ");
		double amnt = input.nextInt();
		
		cards.addPurchase(c, new Purchase(PurchaseType.valueOf(type), amnt));
		
		manageSpecificCard();
		
	}
	
	public void addPayment(CreditCard c) {
		
		System.out.println("Enter Payment Type: ");
		String type = input.nextLine();
		
		System.out.println("Enter Bank Account Name: ");
		String bankName = input.nextLine();
		
		System.out.println("Enter Bank Account ID: ");
		String bankID = input.nextLine();
		
		System.out.println("Enter Payment Amount: ");
		double amnt = input.nextInt();
		
		cards.addPayment(c, new Payment(PaymentType.valueOf(type), new BankAccount(bankName, bankID), amnt));
		
		manageSpecificCard();
		
	}
	
	public void addFee(CreditCard c) {
		
		System.out.println("Enter Fee Type: ");
		String type = input.nextLine();
		
		System.out.println("Enter Fee Amount: ");
		double amnt = input.nextInt();
		
		cards.addFee(c, new Fee(FeeType.valueOf(type), amnt));
		
		manageSpecificCard();
		
	}
	
	public void displayRecentPurchase(CreditCard c) {
		
		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Most Recent Purchase: " + c.getMostRecentPurchase());
		
		//go back to main menu or back to manage CC?
		manageSpecificCard();
		
	}
	
	public void displayRecentPayment(CreditCard c) {
		
		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Most Recent Payment: " + c.getMostRecentPayment());
		
		//go back to main menu or back to manage CC?
		manageSpecificCard();
	}
	
	

}
