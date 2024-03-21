package com.fabrick.example.serviceImpl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fabrick.example.beans.AccountTransactionsRequest;
import com.fabrick.example.beans.ErrorResponse;
import com.fabrick.example.beans.FabrickResponse;
import com.fabrick.example.beans.MoneyTransferRequest;
import com.fabrick.example.beans.PropertySourceResolver;
import com.fabrick.example.service.OperationsService;
import com.google.gson.Gson;

@Service
public class OperationsServiceImpl implements OperationsService {

	private String baseUri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/";
	private String balanceUri = "{accountId}/balance";
	private String moneyTransferUri = "{accountId}/payments/money-transfers";
	private String readTransactionsUri = "{accountId}/transactions";
	
	@Autowired
	private PropertySourceResolver propertyResolver;

	Logger logger = LoggerFactory.getLogger(OperationsServiceImpl.class);

	@Override
	public String letturaSaldo() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", propertyResolver.getAuthSchema());
		headers.set("BaseUrl", propertyResolver.getBaseUrl());
		headers.set("Api-Key", propertyResolver.getApiKey());
		HttpEntity<String> entity = new HttpEntity<>(headers);

		Map<String, Long> params = Collections.singletonMap("accountId", Long.parseLong(propertyResolver.getAccountId()));

		RestTemplate restTemplate = new RestTemplate();

		logger.info("Invoco servizio esterno");
		HttpEntity<FabrickResponse> tempResponse = restTemplate.exchange(baseUri + balanceUri, HttpMethod.GET, entity,
				FabrickResponse.class, params);

		return tempResponse.getBody().getPayload().getBalance();

	}

	@Override
	public String bonifico(MoneyTransferRequest request) {
		HttpEntity<String> serviceResponse = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", propertyResolver.getAuthSchema());
		headers.set("BaseUrl", propertyResolver.getBaseUrl());
		headers.set("Api-Key", propertyResolver.getApiKey());
		headers.set("X-Time-Zone", "Europe/Rome");

		JSONObject requestJson = new JSONObject(request);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(), headers);

		try {
			Map<String, Long> params = Collections.singletonMap("accountId", Long.parseLong(propertyResolver.getAccountId()));

			logger.info("Invoco servizio esterno");
			RestTemplate restTemplate = new RestTemplate();
			serviceResponse = restTemplate.exchange(baseUri + moneyTransferUri, HttpMethod.POST, entity, String.class,
					params);
		} catch (Exception e) {
			logger.trace("Errore durante l'invocazione del servizio esterno");
			ErrorResponse error = new ErrorResponse("API000",
					"Errore tecnico La condizione BP049 non e' prevista per il conto id 14537780");
			return new Gson().toJson(error);
		}

		return serviceResponse.getBody();

	}
	
	//METODO FABRICK NON FUNZIONANTE!
	@Override
	public String letturaTransazioni(AccountTransactionsRequest accountTransactionsRequest) {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", propertyResolver.getAuthSchema());
		headers.set("BaseUrl", propertyResolver.getBaseUrl());
		headers.set("Api-Key", propertyResolver.getApiKey());
		HttpEntity<String> entity = new HttpEntity<>(headers);

		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountId", propertyResolver.getAccountId());
		params.put("fromAccountingDate", accountTransactionsRequest.getFromAccountingDate());
		params.put("toAccountingDate", accountTransactionsRequest.getToAccountingDate());

		RestTemplate restTemplate = new RestTemplate();

		logger.info("Invoco servizio esterno");
		HttpEntity<FabrickResponse> tempResponse = restTemplate.exchange(baseUri + readTransactionsUri, HttpMethod.GET,
				entity, FabrickResponse.class, params);

		return tempResponse.getBody().toString();

	}

}
