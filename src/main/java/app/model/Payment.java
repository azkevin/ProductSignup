package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {
    @Id
    private String ccn;

    private String expiry;

    private String cvv;
    
    private String address;

	public Payment(String ccn, String expiry, String cvv, String address) {
		super();
		this.ccn = ccn;
		this.expiry = expiry;
		this.cvv = cvv;
		this.address = address;
	}
	
	public Payment() {}

	public String getCcn() {
		return ccn;
	}

	public void setCcn(String ccn) {
		this.ccn = ccn;
	}

	public String getExpiry() {
		return expiry;
	}

	public void setExpiry(String expiry) {
		this.expiry = expiry;
	}

	public String getCvv() {
		return cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	};

}

