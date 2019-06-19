package app.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private String email;

    private String name;

    private String address;

    public User(String email, String name, String address) {
    	this.email = email;
    	this.name = name;
    	this.address = address;
    }

    public User() {}
    
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}    
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}

