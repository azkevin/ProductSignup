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
import app.service.PaymentService;

@Controller
@RequestMapping(path="/app")
public class PaymentController {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private PaymentService paymentService;
	
	@RequestMapping(value = "/addPayment", method = RequestMethod.POST)
	public ResponseEntity<String> addPayment(@RequestBody Payment payment) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		if (paymentService.isPaymentValid(payment)) {
			paymentRepository.save(payment);
			headers.add("ok", "success adding payment");
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			headers.add("error", "client sent invalid data");
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}
	}
	
//	// For debug purposes only
//	@GetMapping(path="/allPayments")
//	public @ResponseBody Iterable<Payment> getAllPayments() {
//		// This returns a JSON or XML with the users
//		return paymentRepository.findAll();
//	}
}
