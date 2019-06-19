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

import app.datastore.UserRepository;
import app.model.User;

@Controller
@RequestMapping(path="/app")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		if (isUserValid(user)) {
			userRepository.save(user);
			headers.add("ok", "success adding user");
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			headers.add("error", "client sent invalid data");
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}
	}
	
	// TODO: Validation for user
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
	
	// For debug purposes only
	@GetMapping(path="/allUsers")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
