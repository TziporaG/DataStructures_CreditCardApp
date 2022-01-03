package creditCardFiles;

public class Purchase extends Transaction
{
	PurchaseType purchaseType;
	
	public Purchase(PurchaseType purchaseType, double amount) {
		
		super(TransactionType.PURCHASE, amount);
		this.purchaseType = purchaseType;
	}

	public PurchaseType getPurchaseType() {
		
		return purchaseType;
	}

	@Override
	public String toString() {
		
		return super.toString() + "\nPurchase type: " + purchaseType;
	}
	

}
