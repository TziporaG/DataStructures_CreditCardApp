

public class Fee extends Transaction {
	private FeeType feeType;
	
	public Fee(FeeType feeType) {
		this.feeType = feeType;
	}

	public FeeType getFeeType() {
		return feeType;
	}

	public void setFeeType(FeeType feeType) {
		this.feeType = feeType;
	}
}
