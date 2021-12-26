package creditCardFiles;

import java.util.Scanner;

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
				);
		
		System.out.print("Your choice: ");		
		int choice = input.nextInt();
		while(choice < 1 || choice > 9) {
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
			System.out.print("+ \"a. Display current balance\\r\\n\"\r\n"
					+ "				+ \"b. Display current credit limit\\r\\n\"\r\n"
					+ "				+ \"c. Add a Purchase\\r\\n\"\r\n"
					+ "				+ \"d. Add a Payment\\r\\n\"\r\n"
					+ "				+ \"e. Add a Fee\\r\\n\"\r\n"
					+ "				+ \"f. Display most recent Purchase\\r\\n\"\r\n"
					+ "				+ \"g. Display most recent Payment\\r\\n\"");
		
		}
		
		/***/
		
		
	}
	
	public void addCreditCard() {
		
	}
	
	public void removeCreditCard() {
		
	}
	
	public void displayOutstandingBalances() {
		
	}
	
	public void displayTotalAvailCredit() {
		
	}
	
	public void displayLargestPurchase() {
		
	}
	
	public void displayRecentPayment() {
		
	}
	
	public void displayTotalOfCategory() {
		
	}
	
	public void displayTypeMaxPurchaseValue() {
		
	}

}
