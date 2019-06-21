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
import app.service.UserService;

@Controller
@RequestMapping(path="/app")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_TYPE, "application/json; charset=UTF-8");
		if (userService.isUserValid(user)) {
			userRepository.save(user);
			headers.add("ok", "success adding user");
			return new ResponseEntity<String>(headers, HttpStatus.OK);
		} else {
			headers.add("error", "client sent invalid data");
			return new ResponseEntity<String>(headers, HttpStatus.BAD_REQUEST);
		}
	}
	
//	// For debug purposes only
//	@GetMapping(path="/allUsers")
//	public @ResponseBody Iterable<User> getAllUsers() {
//		// This returns a JSON or XML with the users
//		return userRepository.findAll();
//	}
}
