package com.dev.backend.service.controller;

import java.io.IOException;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import com.dev.backend.to.InstamojoAccessTokenTO;
import com.dev.backend.to.InstamojoOrderResponseTO;
import com.dev.backend.to.InstamojoPaymentResponseTO;
import com.dev.backend.to.InstamojoPaymentTO;
import com.dev.backend.util.HttpUtil;
import com.dev.backend.util.InstamojoConstants;
import com.dev.backend.util.MailUtil;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class TDKInstamojoService {

	private static String javaClientId = "7xqNuJKyk6ackBvxZdqv7NbBMbSokQQoYJGXCI6k";// "QmzoZUaVetzKTuMcOdGw4S7LgZxwRuDihqzT3BW0";

	private static String testClientId = "QmzoZUaVetzKTuMcOdGw4S7LgZxwRuDihqzT3BW0";

	private static String testJavaSecret = "suBJDVFXQTVxZ2C7FgMqzIneuJFoU34abRYWh"
			+ "9HeF2nJhhK62LgCiyaCRxqSueusQSX0NyD5UYstyI2gj7G5gr5bQ9DjDvUKVPeCuYkd7" + "e1A6UEUNEVjUgOwrDLxY06a";

	private static InstamojoAccessTokenTO accessTokens;

	private static String javaClientSecret = "dQ9tPZnqt6VsCBecVOvkoa63chKy39H6FdUVQ"
			+ "fC7wryd95hOUx8dEA6JXNtZh0cFg5QLgy71ZZqoHdzoa8s4lsc4NXfYN4fYsfZJ68uVq" + "MiK79dGAJvjY7pcpzLfVMzD";

	private static String clientId = "euaipxFmcumKSPHpA6DhDagcizEHhaVgvpKwqW2";

	private static String clientSercret = "yXalaUv1v5EZFKPIoGqRr6y3dYziwVSFWXVQp"
			+ "PgflVedBLZEbmq0gpEiGO9riFDW1LroDZqjhJzYi9PsxIWqGHVdHsQVJNB1" + "hEOnWbAq562ieVxVJ3anTvlmvgDUHped";

	private static Date tokenExpiryTime = new Date(Instant.now().toEpochMilli());

	private static long expiryMiliSec = 36000 * 1000;

	private static ObjectMapper mapper = new ObjectMapper();

	public static void generateAccessTokens() throws JsonParseException, JsonMappingException, IOException {
		
		boolean isTest = Boolean.parseBoolean(System.getProperty("isTest"));
		
		Map<String, String> requestMap = new HashMap<String, String>();
		requestMap.put(InstamojoConstants.GRANT_TYPE_KEY, InstamojoConstants.GRANT_TYPE_VALUE);
		String tokenUrl = InstamojoConstants.test_token_URL;
		
//		if(isTest) {
			requestMap.put(InstamojoConstants.CLIENT_ID_KEY, testClientId);
			requestMap.put(InstamojoConstants.CLIENT_SALT_KEY, testJavaSecret);
			tokenUrl = "https://test.instamojo.com/oauth2/token/";
		/*} else {
			requestMap.put(InstamojoConstants.CLIENT_ID_KEY, javaClientId);
			requestMap.put(InstamojoConstants.CLIENT_SALT_KEY, javaClientSecret);
		}*/
		
		String response = HttpUtil.sendPostRequest(tokenUrl, null, requestMap);
		System.out.println(response);
		accessTokens = mapper.readValue(response, InstamojoAccessTokenTO.class);
		tokenExpiryTime = new Date(Instant.now().toEpochMilli() + expiryMiliSec);
		System.out.println(tokenExpiryTime);
	}

	public static InstamojoPaymentResponseTO createPayment(InstamojoPaymentTO paymentRequest)
			throws JsonParseException, JsonMappingException, IOException {
		if (accessTokens == null || tokenExpired()) {
			synchronized (TDKInstamojoService.class) {
				if (accessTokens == null || tokenExpired()) {
					generateAccessTokens();
				}
			}
		}
		Map<String, String> headers = new HashMap<>();
		headers.put("Authorization", accessTokens.getTokenType() + " " + accessTokens.getAccessToken());
		Map<String, String> params = new HashMap<>();

		params.put("buyer_name", paymentRequest.getBuyer_name());
		params.put("email", paymentRequest.getEmail());
		params.put("phone", paymentRequest.getPhone());
		params.put("purpose", paymentRequest.getPurpose());
		params.put("amount", String.valueOf(paymentRequest.getAmount()));
		params.put("send_email", "True");
		params.put("send_sms", "True");
		params.put("webhook", "http://107.23.59.43:8787/thekadesi/instamojo/webhook");
		params.put("redirect_url", "http://thekadesikhana.com/resources/views/thankYou.html");

		String response = HttpUtil.sendPostRequest("https://test.instamojo.com/v2/payment_requests/", headers, params);
		
		//String response = HttpUtil.sendPostRequest(InstamojoConstants.PAYMENT_API_URL, headers, params);
		System.out.println(response);
		InstamojoPaymentResponseTO responseObject = mapper.readValue(response, InstamojoPaymentResponseTO.class);
		return responseObject;
	}

	private static boolean tokenExpired() {
		return tokenExpiryTime.before(new Date(Instant.now().toEpochMilli()));
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
		String email = request.getParameter("buyer");
		String name = request.getParameter("buyer_name");
		String paymentId = request.getParameter("payment_request_id");
		System.out.println("Sending email :" + email+" :"+name+" :"+paymentId);
		MailUtil.sendMail(name,email,paymentId);
	}

}
