package com.dev.backend.service.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dev.backend.to.InstamojoAccessTokenTO;
import com.dev.backend.to.InstamojoOrderResponseTO;
import com.dev.backend.to.InstamojoPaymentResponseTO;
import com.dev.backend.to.InstamojoPaymentTO;
import com.dev.backend.util.HttpUtil;
import com.dev.backend.util.InstamojoConstants;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TDKInstamojoService {

	private static String javaClientId = "7xqNuJKyk6ackBvxZdqv7NbBMbSokQQoYJGXCI6k";// "QmzoZUaVetzKTuMcOdGw4S7LgZxwRuDihqzT3BW0";

	private static InstamojoAccessTokenTO accessTokens;

	private static String javaClientSecret = "dQ9tPZnqt6VsCBecVOvkoa63chKy39H6FdUVQfC7"
			+ "wryd95hOUx8dEA6JXNtZh0cFg5QLgy71ZZqoHdzoa8s4lsc4NXfYN4fYsfZJ68uVqMiK79d"
			+ "GAJvjY7pcpzLfVMzD";/*
									 * "suBJDVFXQTVxZ2C7FgMqzIneuJFoU34abRYWh" +
									 * "9HeF2nJhhK62LgCiyaCRxqSueusQSX0NyD5UYstyI2gj7G5gr5bQ9DjDvUKVPeCuYkd7"
									 * + "e1A6UEUNEVjUgOwrDLxY06a";
									 */

	private static String clientId = "euaipxFmcumKSPHpA6DhDagcizEHhaVgvpKwqW2";

	private static String clientSercret = "yXalaUv1v5EZFKPIoGqRr6y3dYziwVSFWXVQp"
			+ "PgflVedBLZEbmq0gpEiGO9riFDW1LroDZqjhJzYi9PsxIWqGHVdHsQVJNB1" + "hEOnWbAq562ieVxVJ3anTvlmvgDUHped";
	
	private static ObjectMapper mapper = new ObjectMapper();

	public static void generateAccessTokens() throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put(InstamojoConstants.CLIENT_ID_KEY, javaClientId);
		requestMap.put(InstamojoConstants.CLIENT_SALT_KEY, javaClientSecret);
		requestMap.put(InstamojoConstants.GRANT_TYPE_KEY, InstamojoConstants.GRANT_TYPE_VALUE);
		String response = HttpUtil.sendPostRequest(InstamojoConstants.test_token_URL, null, requestMap);
		System.out.println(response);
		accessTokens = mapper.readValue(response, InstamojoAccessTokenTO.class);
	}

	public static InstamojoPaymentResponseTO createPayment(InstamojoPaymentTO paymentRequest)
			throws JsonParseException, JsonMappingException, IOException {
		if (accessTokens == null) {
			synchronized (TDKInstamojoService.class) {
				if (accessTokens == null) {
					generateAccessTokens();
				}
			}
		}
		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Authorization", accessTokens.getTokenType() + " " + accessTokens.getAccessToken());
		headers.add("Content-Type", "application/json");

		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

		HttpEntity<InstamojoPaymentTO> request = new HttpEntity<InstamojoPaymentTO>(paymentRequest, headers);

		String response = restTemplate.postForObject(InstamojoConstants.PAYMENT_API_URL, request,
				String.class);
		InstamojoPaymentResponseTO responseObject = mapper.readValue(response, InstamojoPaymentResponseTO.class);
		return responseObject;
	}

	public static String createOrder(String paymentId) {
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put("id", paymentId);
		RestTemplate template = new RestTemplate();
		InstamojoOrderResponseTO response = template.postForObject(InstamojoConstants.ORDER_API_URL, requestMap,
				InstamojoOrderResponseTO.class);
		return response.getOrder_id();
	}

	@RequestMapping(value = "/instamojo/webhook", method = RequestMethod.POST)
	public void instamojoWebhook(HttpServletRequest request, HttpServletResponse response) {
		/*
		 * Extract payload from response Parse payment Status Update in database
		 * Client poll should get updated
		 */
	}

}
