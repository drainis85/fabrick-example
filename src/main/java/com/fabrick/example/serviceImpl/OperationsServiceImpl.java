package com.fabrick.example.serviceImpl;

import java.util.Collections;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fabrick.example.response.ErrorResponse;
import com.fabrick.example.response.FabrickResponse;
import com.fabrick.example.response.MoneyTransferRequest;
import com.fabrick.example.service.OperationsService;
import com.google.gson.Gson;

@Service
public class OperationsServiceImpl implements OperationsService{

	@Value("${accountId}")
	private Long accountId;
	
	@Value("${authSchema}")
	private String authSchema;
	
	@Value("${baseURL}")
	private String baseURL;
	
	@Value("${apiKey}")
	private String apiKey;
	
	private String baseUri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/";
	private String balanceUri = "{accountId}/balance";
	private String moneyTransferUri = "{accountId}/payments/money-transfers";
	
	@Override
	public String letturaSaldo() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", authSchema);
		headers.set("BaseUrl", baseURL);
		headers.set("Api-Key", apiKey);
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		
		Map<String, Long> params = Collections.singletonMap("accountId", accountId);
		
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<FabrickResponse> tempResponse = restTemplate.exchange(
				baseUri + balanceUri,
		        HttpMethod.GET,
		        entity,
		        FabrickResponse.class,
		        params
		);
		
		return tempResponse.getBody().getPayload().getBalance();
		
	}

	@Override
	public String bonifico(MoneyTransferRequest request) {
		HttpEntity<String> serviceResponse = null;
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", authSchema);
		headers.set("BaseUrl", baseURL);
		headers.set("Api-Key", apiKey);
		headers.set("X-Time-Zone", "Europe/Rome");
		
		request.setAccountId(accountId);
		
		JSONObject requestJson = new JSONObject(request);
		HttpEntity<String> entity = new HttpEntity<String>(requestJson.toString(), headers);

		try {
			Map<String, Long> params = Collections.singletonMap("accountId", accountId);
			
			RestTemplate restTemplate = new RestTemplate();
			serviceResponse = restTemplate.exchange(
					baseUri + moneyTransferUri, HttpMethod.POST, entity, String.class,
			        params
			);
		}catch (Exception e) {
			ErrorResponse error = new ErrorResponse("API000", "Errore tecnico  La condizione BP049 non e' prevista per il conto id 14537780");
			return new Gson().toJson(error);
		}		
		
		return serviceResponse.getBody(); 
		
	}
	
}
