package com.fabrick.example.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrick.example.beans.AccountTransactionsRequest;
import com.fabrick.example.beans.MoneyTransferRequest;
import com.fabrick.example.service.OperationsService;

@RestController
@RequestMapping("operation")
public class OperationsController {
	
	@Autowired
	private OperationsService operationsService;
	
	Logger logger = LoggerFactory.getLogger(OperationsController.class);
	
	/**
	 * Metodo per la lettura del saldo
	 */
	@GetMapping("/lettura-saldo")
	public ResponseEntity<?> getSaldo() {
		logger.info("Calling lettura saldo...");
		return new ResponseEntity<>(operationsService.letturaSaldo(), HttpStatus.OK);
	}
	
	/**
	 * Metodo per l'invio di un bonifico
	 */
	@PostMapping("/bonifico")
	public ResponseEntity<?> inviaBonifico(@RequestBody MoneyTransferRequest moneyTransferRequest) {
		logger.info("Calling bonifico...");
		return new ResponseEntity<>(operationsService.bonifico(moneyTransferRequest), HttpStatus.OK);
	}
	
	/**
	 * Metodo per la lettura delle transazioni
	 */
	@GetMapping("/transazioni")
	public ResponseEntity<?> letturaTransazioni(@RequestBody AccountTransactionsRequest accountTransactionsRequest) {
		logger.info("Calling lettura transazioni...");
		return new ResponseEntity<>(operationsService.letturaTransazioni(accountTransactionsRequest), HttpStatus.OK);
	}

}
