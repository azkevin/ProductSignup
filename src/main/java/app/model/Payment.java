package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Payment {
    @Id
    private String ccn;

    private String expiry;

    private int cvv;
    
    private String address;

	public Payment(String ccn, String expiry, int cvv, String address) {
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

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	};

}

