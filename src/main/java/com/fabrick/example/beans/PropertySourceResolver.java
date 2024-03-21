package com.fabrick.example.beans;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropertySourceResolver {

	@Value("${accountId}")
	private String accountId;

	@Value("${authSchema}")
	private String authSchema;

	@Value("${baseURL}")
	private String baseUrl;

	@Value("${apiKey}")
	private String apiKey;

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAuthSchema() {
		return authSchema;
	}

	public void setAuthSchema(String authSchema) {
		this.authSchema = authSchema;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

}
