package com.example.invoiceapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Getter
	@Setter
	private String address;
	
	@Getter
	@Setter
	private String city;
	
	@Getter
	@Setter
	private String state;
	
	@Getter
	@Setter
	private String postal;
	
	@Getter
	@Setter
	private String country;
	
}
