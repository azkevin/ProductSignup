package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import app.datastore.PaymentRepository;
import app.datastore.UserRepository;
import app.model.User;

@Controller
@RequestMapping(path="/app")
public class OrchestrationController {
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@RequestMapping(value = "/createTransaction", method = RequestMethod.POST)
	public ResponseEntity<String> createSignup(@RequestParam String name, @RequestParam String email, @RequestParam String address, 
			@RequestParam String ccn, @RequestParam String expiry, @RequestParam String cvv, 
			@RequestParam String billingAddress) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		
		// Send user data first
		String userURL = "http://localhost:8080/app/addUser";
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> userMap = new LinkedMultiValueMap<String, String>();
		userMap.add("email", email);
		userMap.add("name", name);
		userMap.add("address", address);
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<MultiValueMap<String, String>>(userMap, headers);
		ResponseEntity<String> response = restTemplate.postForEntity(userURL, request , String.class );
		if (response.getStatusCodeValue() == 400) {
			headers.add("error", "client sent invalid data");
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}
		headers.add("ok", "success adding user");
		return new ResponseEntity<String>(headers, HttpStatus.OK);
	}
}
