

import java.time.LocalDate;

public class CreditCard {
	
	private String creditCardID;
	private LocalDate issueDate;
	private LocalDate expirationDate;
	private String issueCompany;
	private CreditCardType creditCardType;
	private CreditCardStatus status;
	private double creditCardLimit;
	private double currentBalance;
	private double availCredit;
	private Transaction[] transactions;
	
	//constructors?
	
	public void addPurchase() {
		
	}
	
	public void addPayment() {
		
	}
	
	public void addFee() {
		
	}
	
	public double getCurrentBalance() {
		return currentBalance;
	}
	
	public double getAvailCredit() {
		return availCredit;
	}
	
	public Purchase getLargestPurchase() {
		
	}
	
	public double getTotalFees() {
		//fill in correct return
		return 0;
	}
	
	public Purchase getMostRecentPurchase() {
		
	}
	
	public Payment getMostRecentPayment() {
		
	}

}
