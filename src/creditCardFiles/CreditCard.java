package creditCardFiles;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Stack;
import java.util.Random;

public class CreditCard {

	private String creditCardID;
	private Random numGenerator = new Random();

	static Set<String> usedCreditCardIds = new HashSet<String>();

	private LocalDate issueDate;
	private LocalDate expirationDate;
	private String issueCompany;
	private CreditCardType creditCardType;
	private CreditCardStatus status;
	private double creditCardLimit;
	private double currentBalance;
	private double availCredit;
	private Stack<Transaction> transactions;

	public CreditCard(String issueCompany, CreditCardType creditCardType, CreditCardStatus status) {

		do {
			this.creditCardID = String.valueOf(numGenerator.nextInt(9000) + 1000) + " "
					+ String.valueOf(numGenerator.nextInt(9000) + 1000) + " "
					+ String.valueOf(numGenerator.nextInt(9000) + 1000) + " "
					+ String.valueOf(numGenerator.nextInt(9000) + 1000);
		}

		while (this.usedCreditCardIds.contains(creditCardID));
		usedCreditCardIds.add(creditCardID);

		this.issueDate = LocalDate.of(issueDate.getYear(), issueDate.getMonth(), issueDate.getDayOfMonth());

		this.expirationDate = LocalDate.of(issueDate.getYear() + 3, issueDate.getMonth(), issueDate.getDayOfMonth());

		this.issueCompany = issueCompany;

		this.creditCardType = creditCardType;

		this.status = status;

		this.creditCardLimit = 100000;

		this.currentBalance = 0;

		this.availCredit = creditCardLimit;

		this.transactions = new Stack<Transaction>();

	}

	public void addPurchase(Purchase purchase) {

		if (!transactions.contains(purchase)) {

			if (currentBalance + purchase.getAmount() < creditCardLimit) {

				currentBalance += purchase.getAmount();
				availCredit -= purchase.getAmount();
			} else {
				throw new IllegalTransactionException("Purchase amount exceeds credit limit");
			}

			transactions.push(purchase);

		}

		else {
			throw new DuplicateTransactionException("Purchase is already recorded");
		}
	}

	public void addPayment(Payment payment) {

		if (!transactions.contains(payment)) {

			// allow all payments to be made, even those that make the balance negative
			currentBalance -= payment.getAmount();
			availCredit += payment.getAmount();

			transactions.push(payment);
		}

		throw new DuplicateTransactionException("Payment is already recorded");

	}

	public void addFee(Fee fee) {

		if (!transactions.contains(fee)) {

			// allow fees even to be added even if will exceed the credit limit

			currentBalance += fee.getAmount();
			availCredit -= fee.getAmount();

			transactions.push(fee);
		}

		throw new DuplicateTransactionException("Fee is already recorded");

	}

	public double getCurrentBalance() {

		return currentBalance;
	}

	public double getAvailCredit() {

		return availCredit;
	}

	public Purchase getLargestPurchase() {

		if (!transactions.isEmpty()) {
			PriorityQueue<Purchase> purchases = new PriorityQueue<Purchase>(Collections.reverseOrder());
			for (Transaction t : transactions) {

				if (t.getClass() == Purchase.class) {

					purchases.add((Purchase) t);
				}
			}

			return purchases.peek();
		}

		return null;
	}

	public double getTotalFees() {

		double totalFees = 0;
		if (!transactions.isEmpty()) {

			for (Transaction t : transactions) {

				if (t.getClass() == Fee.class) {

					Fee currFee = (Fee) t;
					totalFees += currFee.getAmount();
				}
			}
		}

		return totalFees;
	}

	// maybe reorganize this method
	public Purchase getMostRecentPurchase() {

		if (!transactions.isEmpty()) {
			Stack<Transaction> copyStack = new Stack<Transaction>();
			copyStack.addAll(transactions);

			while (copyStack.peek().getClass() != Purchase.class) {

				copyStack.pop();

				if (copyStack.isEmpty()) {

					return null;
				}
			}
			return (Purchase) copyStack.peek();
		}

		return null;
	}

	// fill in correct return
	public Payment getMostRecentPayment() {

		if (!transactions.isEmpty()) {
			Stack<Transaction> copyStack = new Stack<Transaction>();
			copyStack.addAll(transactions);

			while (copyStack.peek().getClass() != Payment.class) {

				copyStack.pop();

				if (copyStack.isEmpty()) {

					return null;
				}
			}
			return (Payment) copyStack.peek();
		}

		return null;
	}

	public CreditCardStatus getStatus() {
		return status;
	}

	public void setStatus(CreditCardStatus status) {
		this.status = status;
	}

	public String getCreditCardID() {
		return creditCardID;
	}

	public LocalDate getIssueDate() {
		return issueDate;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public String getIssueCompany() {
		return issueCompany;
	}

	public CreditCardType getCreditCardType() {
		return creditCardType;
	}

	public double getCreditCardLimit() {
		return creditCardLimit;
	}

	public Stack<Transaction> getTransactions() {
		return transactions;
	}

	
	
}
