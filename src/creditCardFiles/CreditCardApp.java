package creditCardFiles;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class CreditCardApp {

	private String ownerName;
	// can add in address
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

		System.out.print("1. Add a new CreditCard\r\n" + "2. Remove a CreditCard\r\n"
				+ "3. Display total outstanding balances\r\n" + "4. Display total available credit\r\n"
				+ "5. Display largest purchase \r\n" + "6. Display most recent payment\r\n"
				+ "7. Display total spent on certain category of expense\r\n"
				+ "8. For which type of Purchase was the most money spent\r\n" + "9. Manage a specific Credit Card\r\n"
				+ "10. Exit app\r\n");

		System.out.print("Your choice: ");
		int choice = input.nextInt();
		while (choice < 1 || choice > 10) {
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
			displayOutstandingBalance();
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
		// clear buffer
		input.nextLine();
		String issueCompany = input.nextLine();
		while (issueCompany == null || issueCompany.isBlank()) {
			System.out.println("Invalid.");
			System.out.print("Who is issuing you this new card? ");
			issueCompany = input.nextLine();
		}

		System.out.print("Type of Credit Card (AMEX, VISA, MASTERCARD)? ");
		String type = input.nextLine().toUpperCase();
		while (!type.equals("AMEX") && !type.equals("VISA") && !type.equals("MASTERCARD")) {
			System.out.println("Invalid.");
			System.out.print("Type of Credit Card? ");
			type = input.nextLine().toUpperCase();
		}
		CreditCardType ccType = CreditCardType.valueOf(type);

		System.out.print("Status of Credit Card (ACTIVE, CANCELLED, LOST, EXPIRED)? ");
		String status = input.nextLine().toUpperCase();
		while (!status.equals("ACTIVE") && !status.equals("CANCELLED") && !status.equals("LOST")
				&& !status.equals("EXPIRED")) {
			System.out.println("Invalid.");
			System.out.print("Status of Credit Card? ");
			status = input.nextLine().toUpperCase();
		}
		CreditCardStatus ccStatus = CreditCardStatus.valueOf(status);

		CreditCard newCard = new CreditCard(issueCompany, ccType, ccStatus);
		cards.addCard(newCard);

	}

	public void removeCreditCard() {

		System.out.print("Please enter the Credit Card Number you wish to remove: ");
		input.nextLine();
		String ccNum = input.nextLine();
		while (ccNum.length() != 16) {
			System.out.println("Invalid Credit Card length.");
			System.out.print("Please enter the Credit Card Number you wish to remove: ");
			ccNum = input.nextLine();
		}
		cards.removeCard(ccNum);

	}

	public void displayOutstandingBalance() {
		//assuming that the user would want to see all their outstanding balances not just active cards- ex. if lost
		double totalBalance = 0;

		for (CreditCard card : cards) {
			totalBalance += card.getCurrentBalance();
		}

		System.out.println();

		if (totalBalance == 0) {
			System.out.println("You have no outstanding balance.");
		} else {
			System.out.println("Total Outstanding Balance: $" + totalBalance);
		}

	}

	public void displayTotalAvailCredit() {
		double totalAvailCredit = 0;
		Iterator<CreditCard> activeCards = cards.activeCards();
		
		while(activeCards.hasNext()) {
			totalAvailCredit += activeCards.next().getAvailCredit();
		}

		System.out.println("Total Available Credit: $" + totalAvailCredit);

	}

	public void displayLargestPurchase() {
		CreditCard largestCard = cards.getFirst();
		Purchase largestPurchase = largestCard.getLargestPurchase();

		for (CreditCard card : cards) {
			if (largestPurchase.getAmount() < card.getLargestPurchase().getAmount()) {
				largestCard = card;
				largestPurchase = card.getLargestPurchase();
			}

		}
		System.out.println("Largest Purchase: " + largestPurchase);

	}

	public void displayRecentPayment() {
		CreditCard recentCard = cards.getFirst();
		Payment recentPayment = recentCard.getMostRecentPayment();

		for (CreditCard card : cards) {
			if (recentPayment.getTransactionDate().isAfter(card.getMostRecentPayment().getTransactionDate())) {
				recentCard = card;
				recentPayment = recentCard.getMostRecentPayment();
			}
		}
		System.out.println("Most Recent Payment: " + recentPayment);

	}

	public void displayTotalOfCategory() {
		double totalSpent = 0;
		System.out.println("Enter the Category you would like to see: ");
		String category = input.nextLine().toUpperCase();

		while (!category.equals("CAR") && !category.equals("CLOTHING") && !category.equals("FOOD")
				&& !category.equals("GROCERIES") && !category.equals("LODGING") && !category.equals("RESTAURANT")
				&& !category.equals("TRAVEL") && !category.equals("UTILITES") && !category.equals("MISC")) {
			System.out.println("Invalid Category. ");
			System.out.print("Please re-enter: ");
			category = input.nextLine().toUpperCase();
		}
		PurchaseType purchaseCategory = PurchaseType.valueOf(category);

		for (CreditCard card : cards) {
			// this does not get the actual purchase
			Iterator<Purchase> purchaseIterator = card.purchaseIterator();
			while (purchaseIterator.hasNext()) {
				Purchase p = purchaseIterator.next();
				if (p.getPurchaseType().equals(purchaseCategory)) {
					totalSpent += p.getAmount();
				}
			}

		}
		System.out.println("Total Spent On " + category + ": " + totalSpent);
	}

	public void displayTypeMaxPurchaseValue() {
		HashMap<PurchaseType, Double> purchaseAmounts = new HashMap<PurchaseType, Double>();
		purchaseAmounts.put(PurchaseType.CAR, 0.0);
		purchaseAmounts.put(PurchaseType.CLOTHING, 0.0);
		purchaseAmounts.put(PurchaseType.FOOD, 0.0);
		purchaseAmounts.put(PurchaseType.GROCERIES, 0.0);
		purchaseAmounts.put(PurchaseType.LODGING, 0.0);
		purchaseAmounts.put(PurchaseType.RESTAURANT, 0.0);
		purchaseAmounts.put(PurchaseType.TRAVEL, 0.0);
		purchaseAmounts.put(PurchaseType.UTILITIES, 0.0);
		purchaseAmounts.put(PurchaseType.MISC, 0.0);

		for (CreditCard card : cards) {
			// this does not get the actual purchase
			Iterator<Purchase> purchaseIterator = card.purchaseIterator();
			while (purchaseIterator.hasNext()) {
				Purchase p = purchaseIterator.next();
				purchaseAmounts.put(p.getPurchaseType(), purchaseAmounts.get(p.getPurchaseType()) + p.getAmount());
			}
		}

		Double maxValue = Collections.max(purchaseAmounts.values());
		for (PurchaseType p : purchaseAmounts.keySet()) {
			if (purchaseAmounts.get(p).equals(maxValue)) {
				System.out.println("Most money spent on: " + p);
				break;
			}

		}

	}

	public void manageSpecificCard() {

		System.out.println("Enter the Credit Card Num: ");
		String ccNum = input.nextLine();

		while (ccNum.length() != 16) {
			System.out.println("Invalid CC Number Length.");
			System.out.print("Please re-enter: ");
			ccNum = input.nextLine();

		}
//maybe try catch?
		CreditCard c = cards.findCard(ccNum);

		System.out.print("What would you like to do with the Credit Card?");
		System.out.print(
				"\t1. Display current balance\n" + "\t2. Display current credit limit\n" + "\t3. Add a Purchase\n"
						+ "\t4. Add a Payment\n" + "\t5. Add a Fee\n" + "\t6. Display most recent Purchase\n"
						+ "\t7. Display most recent Payment\n" + "\t8. Switch credit card status\n"
						+ "\t9. Back to main menu\n");
		int choice;
		System.out.print("Your choice: ");
		choice = input.nextInt();
		while (choice < 1 || choice > 9) {
			System.out.println("Invalid choice.");
			System.out.print("Your choice: ");
			choice = input.nextInt();
		}

		switch (choice) {
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
			
		case 8:
			changeStatus(c);

		default:
			displayMenu();
			break;

		}

	}

	public void displayCurrBalance(CreditCard c) {

		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Current Balance: " + c.getCurrentBalance());

		manageSpecificCard();
	}

	public void displayCreditLimit(CreditCard c) {

		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Current Credit Limit: " + c.getCreditCardLimit());

		manageSpecificCard();
	}

	public void addPurchase(CreditCard c) {
		
		System.out.print("Enter Purchase Type: ");
		String type = input.nextLine();
		
		while (!type.equals("CAR") && !type.equals("CLOTHING") && !type.equals("FOOD")
				&& !type.equals("GROCERIES") && !type.equals("LODGING") && !type.equals("RESTAURANT")
				&& !type.equals("TRAVEL") && !type.equals("UTILITES") && !type.equals("MISC")) {
			
			System.out.println("Invalid Type. ");
			System.out.print("Please re-enter: ");
			type = input.nextLine();
		}
	
		System.out.println("Enter Purchase Amount: ");
		double amnt = input.nextInt();
		while(amnt <= 0) {
			System.out.println("Invalid Amount.");
			System.out.println("Please re-enter: ");
			amnt = input.nextInt();
		}

		cards.addPurchase(c, new Purchase(PurchaseType.valueOf(type), amnt));

		manageSpecificCard();

	}

	public void addPayment(CreditCard c) {

		System.out.println("Enter Payment Type: ");
		String type = input.nextLine();
		while (!type.equals("CHECK") && !type.equals("ONLINE")) {
			System.out.println("Invalid Type.");
			System.out.print("Please re-enter: ");
			type = input.nextLine();
		}

		System.out.println("Enter Bank Account Name: ");
		String bankName = input.nextLine();

		System.out.println("Enter Bank Account ID: ");
		String bankID = input.nextLine();

		System.out.println("Enter Payment Amount: ");
		double amnt = input.nextInt();
		while(amnt <= 0) {
			System.out.println("Invalid Amount.");
			System.out.println("Please re-enter: ");
			amnt = input.nextInt();
		}

		cards.addPayment(c, new Payment(PaymentType.valueOf(type), new BankAccount(bankName, bankID), amnt));

		manageSpecificCard();

	}

	public void addFee(CreditCard c) {

		System.out.println("Enter Fee Type: ");
		String type = input.nextLine();
		while (!type.equals("LATEPAYMENT") && !type.equals("INTEREST")) {
			System.out.println("Invalid Type.");
			System.out.print("Please re-enter: ");
			type = input.nextLine();
		}

		System.out.println("Enter Fee Amount: ");
		double amnt = input.nextInt();
		while(amnt <= 0) {
			System.out.println("Invalid Amount.");
			System.out.println("Please re-enter: ");
			amnt = input.nextInt();
		}

		cards.addFee(c, new Fee(FeeType.valueOf(type), amnt));

		manageSpecificCard();

	}

	public void displayRecentPurchase(CreditCard c) {

		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Most Recent Purchase: " + c.getMostRecentPurchase());

		manageSpecificCard();

	}

	public void displayRecentPayment(CreditCard c) {

		System.out.println("Credit Card Num: " + c.getCreditCardID());
		System.out.println("Most Recent Payment: " + c.getMostRecentPayment());

		manageSpecificCard();
	}
	
	public void changeStatus(CreditCard c) {
		
		
		System.out.println("What is this card's new status? ");
		String status = input.nextLine();
		while (!status.equals("ACTIVE") && !status.equals("CANCELLED") && !status.equals("EXPIRED") && !status.equals("LOST")) {
			System.out.println("Invalid Status.");
			System.out.print("Please re-enter: ");
			status = input.nextLine();
		}
		
		if(status.equals("EXPIRED")) {
			if(c.getExpirationDate().compareTo(LocalDate.now()) > 0) {
				System.out.println("Not expired.");
				System.out.print("Returning to menu...");
				manageSpecificCard();
			}
		} 
		else if(status.equals("ACTIVE")) {
			if(c.getExpirationDate().compareTo(LocalDate.now()) < 0) {
				System.out.println("Not active.");
				System.out.print("Returning to menu...");
				manageSpecificCard();
			}
		}
		
		c.setStatus(CreditCardStatus.valueOf(status));
		input.nextLine();
		input.nextLine();
	}

}