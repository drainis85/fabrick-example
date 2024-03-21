package com.fabrick.example.service;

import com.fabrick.example.beans.AccountTransactionsRequest;
import com.fabrick.example.beans.MoneyTransferRequest;

public interface OperationsService {

	public String letturaSaldo();
	
	public String bonifico(MoneyTransferRequest request);

	public String letturaTransazioni(AccountTransactionsRequest accountTransactionsRequest);
}
