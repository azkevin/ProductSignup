package app.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping(path="/app")
public class OrchestrationController {

	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> createSignup(@RequestParam String name, @RequestParam String email, @RequestParam String address, 
			@RequestParam String ccn, @RequestParam String expiry, @RequestParam String cvv, 
			@RequestParam String billingAddress) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		try {
			// Send user data first
			String userURL = "http://localhost:8080/app/addUser";
			RestTemplate restTemplate = new RestTemplate();
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("email", email);
			userMap.put("name", name);
			userMap.put("address", address);
			HttpEntity<Map<String, String>> request = new HttpEntity<Map<String, String>>(userMap, headers);
			restTemplate.postForEntity(userURL, request , String.class );
			
			// Send payment data after
			String paymentURL = "http://localhost:8080/app/addPayment";
			Map<String, String> paymentMap = new HashMap<String, String>();
			paymentMap.put("ccn", ccn);
			paymentMap.put("expiry", expiry);
			paymentMap.put("cvv", cvv);
			paymentMap.put("address", billingAddress);
			request = new HttpEntity<Map<String, String>>(paymentMap, headers);
			restTemplate.postForEntity(paymentURL, request , String.class );
			
			// Return a success
			headers.add("ok", "successful transaction");
			return new ResponseEntity<String>(headers, HttpStatus.OK);
			
		} catch (final HttpClientErrorException httpClientErrorException) {
			headers.add("error", "client sent invalid data");
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		} 
	
	}
}
