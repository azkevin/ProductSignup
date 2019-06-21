package app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import app.model.Payment;

@Service
public class PaymentService {

	private static final Pattern VALID_CCN_REGEX = Pattern.compile("^[0-9]{12,19}$");
	private static final Pattern VALID_EXPIRY_REGEX = Pattern.compile("^\\d{2}\\/\\d{2}$");
	private static final Pattern VALID_CVV_REGEX = Pattern.compile("^[0-9]{3,4}$");

	
	public boolean isPaymentValid(Payment payment) {
		if (payment.getCcn() == null || !ccnValidate(payment.getCcn())) {
			return false;
		}
		if (payment.getExpiry() == null || !expiryValidate(payment.getExpiry())) {
			return false;
		}
		if (payment.getCvv() == null || !cvvValidate(payment.getCvv())) {
			return false;
		}
		if (payment.getAddress() == null || payment.getAddress().equals("")) {
			return false;
		}
		return true;
	}
	
	private boolean ccnValidate(String ccn) {
        Matcher matcher = VALID_CCN_REGEX.matcher(ccn);
        return matcher.find();
	}
	
	private boolean expiryValidate(String expiry) {
        Matcher matcher = VALID_EXPIRY_REGEX.matcher(expiry);
        return matcher.find();
	}
	
	private boolean cvvValidate(String cvv) {
        Matcher matcher = VALID_CVV_REGEX.matcher(cvv);
        return matcher.find();
	}
	
}
