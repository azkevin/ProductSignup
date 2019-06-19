package app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import app.datastore.PaymentRepository;
import app.model.Payment;

@Controller
@RequestMapping(path="/app")
public class PaymentController {
	@Autowired
	private PaymentRepository paymentRepository;
	
	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
		// TODO: Validation for payment
		paymentRepository.save(payment);
		HttpHeaders headers = new HttpHeaders();
		headers.add("redirect", "/thanks"); 
		return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	// For debug purposes only
	@GetMapping(path="/allPayments")
	public @ResponseBody Iterable<Payment> getAllPayments() {
		// This returns a JSON or XML with the users
		return paymentRepository.findAll();
	}
}
