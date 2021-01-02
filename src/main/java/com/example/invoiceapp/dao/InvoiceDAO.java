package com.example.invoiceapp.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.invoiceapp.model.Invoice;
import com.example.invoiceapp.repository.InvoiceRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class InvoiceDAO {
	private InvoiceRepository invoiceRepository;
	
	public List<Invoice> findAll(){
		return invoiceRepository.findAll();
	}
	
	public Invoice save(Invoice invoice) {
		return invoiceRepository.save(invoice);
	}
	
	public Optional<Invoice> findById(String id) {
		return invoiceRepository.findById(id);
		
	}
	
	public void deleteById(String id) {
		invoiceRepository.deleteById(id);
	}
	
	
}
