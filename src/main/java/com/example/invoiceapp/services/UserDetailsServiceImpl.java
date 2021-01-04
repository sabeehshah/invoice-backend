package com.example.invoiceapp.services;


import java.util.Collections;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.example.invoiceapp.dao.UserDAO;
import com.example.invoiceapp.model.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	private UserDAO userDAO;

	public UserDetailsServiceImpl(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		final User tUser = userDAO.findByEmail(username);
		if (tUser == null) {
			throw new UsernameNotFoundException(username);
		}

		
//		org.springframework.security.core.userdetails.User springUser = new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
//		return springUser;
		
		UserDetails user = org.springframework.security.core.userdetails.User.withUsername(tUser.getEmail()).password(tUser.getPassword()).authorities(Collections.emptyList()).build();
		return user;
	}

}
