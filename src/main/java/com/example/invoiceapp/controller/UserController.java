package com.example.invoiceapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoiceapp.dao.UserDAO;
import com.example.invoiceapp.model.User;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/authenticate")
@CrossOrigin
@Slf4j
public class UserController {

	private UserDAO userDAO;

	
	public UserController(UserDAO userDAO) {
		this.userDAO = userDAO;
		
	}
	
	@PostMapping("/signup")
	public void signUp(@RequestBody User user) {
		user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
		userDAO.save(user);
	}
	
}
