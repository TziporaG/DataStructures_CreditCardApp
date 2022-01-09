package creditCardFiles;

import java.util.ArrayList;
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
		else {
			throw new CreditCardException("Credit Card already exists.");
		}
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
	
	public CreditCard getFirst() {
		return cards.getFirst();
	}

	@Override
	public Iterator<CreditCard> iterator() {
		return cards.iterator();
	}
	
	public Iterator<CreditCard> activeCards() {
		return new ActiveCardsIterator();
	}
	
	class ActiveCardsIterator implements Iterator<CreditCard> {
		
		private ArrayList<CreditCard> activeCards;
		private int index;
		
		public ActiveCardsIterator() {
			activeCards = new ArrayList<CreditCard>();
			Iterator<CreditCard> iter = cards.iterator();
			while(iter.hasNext()) {
				CreditCard c = iter.next();
				if(c.getStatus().equals(CreditCardStatus.ACTIVE)) {
					activeCards.add(c);
				}
			}
			
			if (activeCards.size() > 0) {
     		   index = 0;
     	   }
     	   else {
     		   index = -1;
     	   }

		}
		
		@Override
		public boolean hasNext() {
			if (index >= 0  && index < activeCards.size()) {
				return true;
			}
			return false;

		}

		@Override
		public CreditCard next() {
			if(hasNext()) {
				CreditCard currCard = activeCards.get(index);
				index++;
				return currCard;	
			}
			return null;
			
		}
		
	}
	
	public boolean isEmpty() {
		return cards.isEmpty();
	}

}