package com.fabrick.example.serviceImpl;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fabrick.example.response.FabrickResponse;
import com.fabrick.example.service.OperationsService;

@Service
public class OperationsServiceImpl implements OperationsService{

	@Value("${accountId}")
	private Long accountId;

	private String uri = "https://sandbox.platfr.io/api/gbs/banking/v4.0/accounts/{accountId}/balance";
	
	@Override
	public String letturaSaldo() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Auth-Schema", "S2S");
		headers.set("BaseUrl", "https://sandbox.platfr.io");
		headers.set("Api-Key", "FXOVVXXHVCPVPBZXIJOBGUGSKHDNFRRQJP");
		HttpEntity<String> entity = new HttpEntity<>("body", headers);
		
		Map<String, Long> params = Collections.singletonMap("accountId", accountId);
		
		RestTemplate restTemplate = new RestTemplate();

		HttpEntity<FabrickResponse> tempResponse = restTemplate.exchange(
				uri,
		        HttpMethod.GET,
		        entity,
		        FabrickResponse.class,
		        params
		);
		
		return tempResponse.getBody().getPayload().getBalance();
		
	}
}
