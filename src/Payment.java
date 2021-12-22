

public class Payment {
	private PaymentType paymentType;
	private BankAccount account;
	
	public Payment(PaymentType type, BankAccount account) {
		this.paymentType = type;
		this.account = account;
	}

	public PaymentType getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(PaymentType paymentType) {
		this.paymentType = paymentType;
	}

	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
}
