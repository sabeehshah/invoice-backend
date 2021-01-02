package com.example.invoiceapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.invoiceapp.model.Invoice;

public interface InvoiceRepository extends MongoRepository <Invoice, String> {
	
	public List<Invoice> findAll();
	public Optional<Invoice> findById(String id);
	public void deleteById(String id);
	 
	
}
