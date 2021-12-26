package creditCardFiles;

import java.util.LinkedList;

public class CreditCards {

	// using linked list so most efficient to remove from middle
	private LinkedList<CreditCard> cards;

	public CreditCards() {

		cards = new LinkedList<CreditCard>();
	}

	public void activeCards() {

		for (CreditCard card : cards) {

			if (card.getStatus() == CreditCardStatus.ACTIVE) {
				System.out.println(card.toString());
			}
		}
	}

	public double totalBalance() {

		double totalBalance = 0;
		
		for (CreditCard card : cards) {

				System.out.println(card.toString());
			
		}

		return totalBalance;
	}

	public double totalAvailCredit() {

		double totalAvailCredit = 0;
		// iterate and add to totalAvailCredit

		return totalAvailCredit;

	}

	public void addCard(CreditCard newCard) {
		
		if(!this.findCard(newCard)) {
			cards.addFirst(newCard);
		}
		throw new CreditCardException("Credit Card already exists.");
	}

	public void removeCard(CreditCard card) {

		if(this.findCard(card)) {
			cards.remove(card);
		}
		throw new CreditCardException("Credit Card does not exist.");

	}

	public boolean findCard(CreditCard card) {

		for(CreditCard c:cards) {
			if(card.equals(c)) {
				return true;
			}
		}
		return false;

	}

	public void addPurchase(CreditCard card, PurchaseType type, double purchaseAmount) {

		
		if(cards.contains(card)) {
			
			card.addPurchase(new Purchase (type, purchaseAmount));
		}
	}

	public void addPayment(CreditCard card, PaymentType type, BankAccount bankAccount, double paymentAmount) {

		if(cards.contains(card)) {
			
			card.addPayment(new Payment(type, bankAccount, paymentAmount));
		}
	}

	public void addFee(CreditCard card, FeeType type, double feeAmount) {


		if(cards.contains(card)) {
			
			card.addFee(new Fee(type, feeAmount));
		}
	}

}
