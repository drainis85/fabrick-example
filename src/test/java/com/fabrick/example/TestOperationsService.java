package com.fabrick.example;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fabrick.example.beans.MoneyTransferRequest;
import com.fabrick.example.controller.OperationsController;


@RunWith(SpringRunner.class)
@SpringBootTest
public class TestOperationsService {

	@Autowired
	private OperationsController operationController;
	
	@Test
	public void letturaSaldo() {
		assertEquals("-35.57", operationController.getSaldo().getBody());
	}

	@Test
	public void bonificoError() {
		MoneyTransferRequest mtr = new MoneyTransferRequest(14537780L, "Davide R", "Pagamento Test", "EUR", "1",
				"2024-03-11");
		assertEquals("{\"code\":\"API000\",\"description\":\"Errore tecnico La condizione BP049 non e\\u0027 prevista per il conto id 14537780\"}", operationController.inviaBonifico(mtr).getBody());
	}
}
