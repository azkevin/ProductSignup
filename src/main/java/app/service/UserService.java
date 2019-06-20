package app.service;

import org.springframework.stereotype.Service;

import app.model.User;

@Service
public class UserService {

	public boolean isUserValid(User user) {
		if (user.getEmail() == null || user.getEmail().equals("")) {
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
}
