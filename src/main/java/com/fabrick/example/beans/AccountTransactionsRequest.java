package com.fabrick.example.beans;

public class AccountTransactionsRequest {

	private String fromAccountingDate;
	private String toAccountingDate;

	public String getFromAccountingDate() {
		return fromAccountingDate;
	}

	public void setFromAccountingDate(String fromAccountingDate) {
		this.fromAccountingDate = fromAccountingDate;
	}

	public String getToAccountingDate() {
		return toAccountingDate;
	}

	public void setToAccountingDate(String toAccountingDate) {
		this.toAccountingDate = toAccountingDate;
	}

}
