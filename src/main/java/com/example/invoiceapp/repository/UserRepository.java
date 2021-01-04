package com.example.invoiceapp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.invoiceapp.model.User;

public interface UserRepository extends MongoRepository<User, String> {
	
	public User findByUsername(String username);
	public User findByEmail(String email);
}
