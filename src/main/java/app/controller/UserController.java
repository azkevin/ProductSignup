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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import app.datastore.UserRepository;
import app.model.User;

@Controller
@RequestMapping(path="/app")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	
	@RequestMapping(value = "/addUser", method = RequestMethod.POST)
	public ResponseEntity<String> addUser(@RequestParam String name
			, @RequestParam String email, @RequestParam String address) {
		// Validation: if user is valid
		System.out.println(email);
		System.out.println(name);
		System.out.println(address);
		User n = new User();
		n.setEmail(email);
		n.setName(name);
		n.setAddress(address);
		userRepository.save(n);
		HttpHeaders headers = new HttpHeaders();
		headers.add("redirect", "/greeting"); 
		return new ResponseEntity<String>(headers, HttpStatus.ACCEPTED);
		// return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}
	
	// For debug purposes only
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
		// This returns a JSON or XML with the users
		return userRepository.findAll();
	}
}
