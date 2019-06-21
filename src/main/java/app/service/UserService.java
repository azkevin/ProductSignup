package app.service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import app.model.User;

@Service
public class UserService {

	private static final Pattern VALID_EMAIL_ADDRESS_REGEX = 
		    Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
	
	public boolean isUserValid(User user) {
		if (user.getEmail() == null || !emailValidate(user.getEmail())) {
			return false;
		}
		if (user.getName() == null || user.getName().equals("")) {
			return false;
		}
		if (user.getAddress() == null || user.getAddress().equals("")) {
			return false;
		}
		return true;
	}
	
	private boolean emailValidate(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
	}
}
