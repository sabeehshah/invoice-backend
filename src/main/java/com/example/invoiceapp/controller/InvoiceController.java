package com.example.invoiceapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.invoiceapp.dao.InvoiceDAO;
import com.example.invoiceapp.model.Invoice;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin
@RestController
@RequestMapping("/api")
@Slf4j
public class InvoiceController {

	@Autowired
	InvoiceDAO invoiceDAO;
	
	
	@PostMapping("/invoices")
	public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice){
		try {
			Invoice _invoice = invoiceDAO.save(
					new Invoice(invoice.getCreatedBy(),invoice.getInvoiceFrom(),
							invoice.getInvoiceTo(),invoice.getAddress(),
							invoice.getDueDate(),invoice.getIssueDate(),
							invoice.getTaxAmtPercentage(),invoice.getTaxAmtValue(),
							invoice.getTotalAmtDue(),invoice.getLineItems()));	
			return new ResponseEntity<>(_invoice,HttpStatus.CREATED);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/invoices")
	public ResponseEntity<List<Invoice>> getAllInvoices(){
		try {
			List<Invoice> invoices = new ArrayList<Invoice>();
			
			invoiceDAO.findAll().forEach(invoices::add);
			
			if(invoices.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			log.info(invoices.toString());
			return new ResponseEntity<>(invoices,HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/invoices/createdBy/{createdBy}")
	public ResponseEntity<List<Invoice>> getInvoicesCreatedBy(@PathVariable("createdBy") String createdBy){
		try {
			List<Invoice> invoices = new ArrayList<Invoice>();
			
			invoiceDAO.findByCreatedBy(createdBy).forEach(invoices::add);
			
			if(invoices.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			
			log.info(invoices.toString());
			return new ResponseEntity<>(invoices,HttpStatus.OK);

			
		}catch(Exception e) {
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);

		}
	}
	
	@GetMapping("/invoices/{id}")
	public ResponseEntity<Invoice> getInvoiceById(
			@PathVariable("id") String id){
		Optional<Invoice> invoiceData = invoiceDAO.findById(id);
		
		if(invoiceData.isPresent()) {
			return new ResponseEntity<>(invoiceData.get(),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}
	
	@PutMapping("/invoices/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable("id") String id,
			@RequestBody Invoice invoice){
		
		Optional<Invoice> invoiceData = invoiceDAO.findById(id);
		
		if(invoiceData.isPresent()) {
			Invoice _invoice = invoiceData.get();
			_invoice.setCreatedBy(invoice.getCreatedBy());
			_invoice.setInvoiceFrom(invoice.getInvoiceFrom());
			_invoice.setInvoiceTo(invoice.getInvoiceTo());
			_invoice.setAddress(invoice.getAddress());
			_invoice.setDueDate(invoice.getDueDate());
			_invoice.setIssueDate(invoice.getIssueDate());
			_invoice.setTaxAmtPercentage(invoice.getTaxAmtPercentage());
			_invoice.setTaxAmtValue(invoice.getTaxAmtValue());
			_invoice.setTotalAmtDue(invoice.getTotalAmtDue());
			_invoice.setLineItems(invoice.getLineItems());
			
			return new ResponseEntity<>(invoiceDAO.save(_invoice),HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("/invoices/{id}")
	public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable("id") String id){
		try {
			invoiceDAO.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	
}
