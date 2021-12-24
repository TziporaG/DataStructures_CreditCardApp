package creditCardFiles;


public class Fee extends Transaction {
	
	private FeeType feeType;
	
	public Fee (FeeType feeType, double amount) {
		
		super(TransactionType.Fee, amount);
		this.feeType = feeType;
	}

	public FeeType getFeeType() {
		
		return feeType;
	}

	@Override
	public String toString() {
		
		return super.toString() + "\nFee type: " + feeType;
	}
}
