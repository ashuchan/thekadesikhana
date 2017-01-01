package com.dev.backend.service.controller;

/*
 * Sample Request
 * 
 * curl https://www.instamojo.com/api/1.1/payment-requests/ \
  --header "X-Api-Key: d82016f839e13cd0a79afc0ef5b288b3" \
  --header "X-Auth-Token: 3827881f669c11e8dad8a023fd1108c2" \
  --data "allow_repeated_payments=False
  &amount=2500&buyer_name=John+Doe&purpose=FIFA+16
  &redirect_url=http%3A%2F%2Fwww.example.com%2Fredirect%2F
  &phone=9999999999
  &send_email=True
  &webhook=http%3A%2F%2Fwww.example.com%2Fwebhook%2F
  &send_sms=True&email=foo%40example.com"
 * 
 * Sample Response
 * {
    "payment_request": {
        "id": "d66cb29dd059482e8072999f995c4eef",
        "phone": "+919999999999",
        "email": "foo@example.com",
        "buyer_name": "John Doe",
        "amount": "2500",
        "purpose": "FIFA 16",
        "status": "Pending",
        "send_sms": true,
        "send_email": true,
        "sms_status": "Pending",
        "email_status": "Pending",
        "shorturl": null,
        "longurl": "https://www.instamojo.com/@ashwch/d66cb29dd059482e8072999f995c4eef/",
        "redirect_url": "http://www.example.com/redirect/",
        "webhook": "http://www.example.com/webhook/",
        "created_at": "2015-10-07T21:36:34.665Z",
        "modified_at": "2015-10-07T21:36:34.665Z",
        "allow_repeated_payments": false
    },
    "success": true
}

 */
public class TDKInstamojoService {

	private String accessToken = "cd7bd4d4307f12059aabe70c806990a0";
	
	private String salt = "0b1110c5d2bc457abd35e10758c45a9e";
	
	
}
