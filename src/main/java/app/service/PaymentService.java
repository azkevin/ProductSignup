package app.service;

import org.springframework.stereotype.Service;

import app.model.Payment;

@Service
public class PaymentService {

	public boolean isPaymentValid(Payment payment) {
		if (payment.getCcn() == null || payment.getCcn().equals("")) {
			return false;
		}
		if (payment.getExpiry() == null || payment.getExpiry().equals("")) {
			return false;
		}
		if ((payment.getCvv() == null || payment.getCvv().equals(""))
				|| (Integer.parseInt(payment.getCvv()) < 0)
				|| (Integer.parseInt(payment.getCvv()) > 999)) {
			return false;
		}
		if (payment.getAddress() == null || payment.getAddress().equals("")) {
			return false;
		}
		return true;
	}
	
}
