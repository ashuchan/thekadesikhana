package com.dev.backend.service.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dev.backend.to.InstamojoAccessTokenTO;
import com.dev.backend.to.InstamojoOrderResponseTO;
import com.dev.backend.to.InstamojoPaymentResponseTO;
import com.dev.backend.to.InstamojoPaymentTO;
import com.dev.backend.util.InstamojoConstants;

public class TDKInstamojoService {

	private static String javaClientId = "QmzoZUaVetzKTuMcOdGw4S7LgZxwRuDihqzT3BW0";

	private static String javaClientSecret = "suBJDVFXQTVxZ2C7FgMqzIneuJFoU34abRYWh"
			+ "9HeF2nJhhK62LgCiyaCRxqSueusQSX0NyD5UYstyI2gj7G5gr5bQ9DjDvUKVPeCuYkd7"
			+ "e1A6UEUNEVjUgOwrDLxY06a";

	private static String clientId = "euaipxFmcumKSPHpA6DhDagcizEHhaVgvpKwqW2";

	private static String clientSercret = "yXalaUv1v5EZFKPIoGqRr6y3dYziwVSFWXVQp"
			+ "PgflVedBLZEbmq0gpEiGO9riFDW1LroDZqjhJzYi9PsxIWqGHVdHsQVJNB1"
			+ "hEOnWbAq562ieVxVJ3anTvlmvgDUHped";

	public static InstamojoAccessTokenTO generateAccessTokens() {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put(InstamojoConstants.CLIENT_ID_KEY, javaClientId);
		requestMap.put(InstamojoConstants.CLIENT_SALT_KEY, javaClientSecret);
		requestMap.put(InstamojoConstants.GRANT_TYPE_KEY,
				InstamojoConstants.GRANT_TYPE_VALUE);
		RestTemplate template = new RestTemplate();
		InstamojoAccessTokenTO accessTokens = template.postForObject(
				InstamojoConstants.test_token_URL, requestMap,
				InstamojoAccessTokenTO.class);
		return accessTokens;
	}

	public static InstamojoPaymentResponseTO createPayment(
			InstamojoPaymentTO paymentRequest) {
		RestTemplate template = new RestTemplate();
		InstamojoPaymentResponseTO response = template.postForObject(
				InstamojoConstants.PAYMENT_API_URL, paymentRequest,
				InstamojoPaymentResponseTO.class);
		return response;
	}

	public static String createOrder(String paymentId) {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("id", paymentId);
		RestTemplate template = new RestTemplate();
		InstamojoOrderResponseTO response = template.postForObject(
				InstamojoConstants.ORDER_API_URL, requestMap,
				InstamojoOrderResponseTO.class);
		return response.getOrder_id();
	}
	
	@RequestMapping(value="/instamojo/webhook",method=RequestMethod.POST)
	public void instamojoWebhook(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Extract payload from response
		 * Parse payment Status
		 * Update in database
		 * Client poll should get updated
		 */
	}

}
