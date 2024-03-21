package com.fabrick.example;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fabrick.example.controller.OperationsController;
import com.fabrick.example.serviceImpl.OperationsServiceImpl;

@WebMvcTest(OperationsController.class)
public class TestOperationsController {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private OperationsServiceImpl operationsService;
	
	@Test
	public void shouldAccessLettura() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/operation/lettura-saldo")).andExpect(status().isOk());
	}
	
	@Test
	public void shouldAccessBonifico() throws Exception {
		this.mockMvc.perform(get("http://localhost:8080/operation/bonifico")).andExpect(status().is(405));
	}
}
