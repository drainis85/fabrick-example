package com.fabrick.example.service;

import com.fabrick.example.response.MoneyTransferRequest;

public interface OperationsService {

	public String letturaSaldo();
	
	public String bonifico(MoneyTransferRequest request);
}
