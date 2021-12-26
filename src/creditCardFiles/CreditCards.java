package creditCardFiles;

import java.util.Iterator;
import java.util.LinkedList;

public class CreditCards implements Iterable<CreditCard>{

	// using linked list so most efficient to remove from middle
	private LinkedList<CreditCard> cards;
	private int modCount;

	public CreditCards() {

		cards = new LinkedList<CreditCard>();
		modCount = 0;
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

				totalBalance += card.getCurrentBalance();
			
		}

		return totalBalance;
	}

	public double totalAvailCredit() {

		double totalAvailCredit = 0;
		// iterate and add to totalAvailCredit

		return totalAvailCredit;

	}

	public void addCard(CreditCard newCard) {
		
		if(!cards.contains(newCard)) {
			cards.addFirst(newCard);
			modCount++;
		}
		throw new CreditCardException("Credit Card already exists.");
	}

	public void removeCard(String ccNum) {

		CreditCard c = this.findCard(ccNum);
		cards.remove(c);
		modCount++;

	}

	public CreditCard findCard(String ccNum) {

		for(CreditCard c:cards) {
			if(c.getCreditCardID().equals(ccNum)) {
				return c;
			}
		}
		throw new CreditCardException("Credit Card does not exist.");

	}

	public void addPurchase(CreditCard card, Purchase purchase) {

		
		if(cards.contains(card)) {
			
			card.addPurchase(purchase);
		}
	}

	public void addPayment(CreditCard card, Payment payment) {

		if(cards.contains(card)) {
			
			card.addPayment(payment);
		}
	}

	public void addFee(CreditCard card, Fee fee) {


		if(cards.contains(card)) {
			
			card.addFee(fee);
		}
	}

	@Override
	public Iterator<CreditCard> iterator() {
		return cards.iterator();
	}

}
