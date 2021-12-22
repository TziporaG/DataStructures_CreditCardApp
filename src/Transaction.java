import java.time.LocalDate;

public class Transaction {
	
	private long transactionID;
	private long lastTransactionID;
	private LocalDate transactionDate;
	private TransactionType transactionType;
	
	private int amount;
	
	public int getAmount() {
		
		return this.amount;
	}

}
