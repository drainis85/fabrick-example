package com.fabrick.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fabrick.example.response.MoneyTransferRequest;
import com.fabrick.example.service.OperationsService;

@RestController
@RequestMapping("operation")
public class OperationsController {
	
	@Autowired
	private OperationsService operationsService;
	
	/**
	 * Metodo per la lettura del saldo
	 */
	@GetMapping("/lettura-saldo")
	public ResponseEntity<?> getSaldo() {
		return new ResponseEntity<>(operationsService.letturaSaldo(), HttpStatus.OK);
	}
	
	/**
	 * Metodo per l'invio di un bonifico
	 */
	@PostMapping("/bonifico")
	public ResponseEntity<?> inviaBonifico(@RequestBody MoneyTransferRequest moneyTransferRequest) {
		return new ResponseEntity<>(operationsService.bonifico(moneyTransferRequest), HttpStatus.OK);
	}

}
