package com.fabrick.example.serviceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fabrick.example.service.OperationsInferface;

@Service
public class OperationsServiceImpl implements OperationsInferface{

	@Value("${accountId}")
	private int accountId;
}
