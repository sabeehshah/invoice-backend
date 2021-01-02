package com.example.invoiceapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"id"})
@Document(collection = "Users")
public class User {

	@Id
	String id;
	
	@Getter
	@Setter
	String email;
	
	@Getter
	@Setter
	String username;
	

	@Getter
	@Setter
	String password;
	
	


	public User(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}
	
	
	
}
