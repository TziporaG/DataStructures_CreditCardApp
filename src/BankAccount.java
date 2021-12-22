

public class BankAccount {
	private String BankName;
	private String AccountID;
	
	public BankAccount(String BankName, String AccountID) {
		this.BankName = BankName;
		this.AccountID = AccountID;
	}

	public String getBankName() {
		return BankName;
	}

	public void setBankName(String bankName) {
		BankName = bankName;
	}

	public String getAccountID() {
		return AccountID;
	}

	public void setAccountID(String accountID) {
		AccountID = accountID;
	}
	
}
