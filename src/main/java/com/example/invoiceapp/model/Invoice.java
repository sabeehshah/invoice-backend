package com.example.invoiceapp.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatTypes;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = {"id"})
@NoArgsConstructor
@Document(collection = "invoices")
public class Invoice {
	
	@Id
	private String id;
	
	@Getter
	@Setter
	private String createdBy;
	
	@Getter
	@Setter
	private String invoiceFrom;
	
	@Getter
	@Setter
	private String invoiceTo;
	
	
	@Getter
	@Setter
	private String companyPhone;
	
	@Getter
	@Setter
	private String companyAddress;
	
	@Getter
	@Setter
	private String billingAddress;
	
	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date dueDate;
	
	@Getter
	@Setter
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private Date issueDate;
	
	@Getter
	@Setter
	private int taxAmtPercentage;
	
	@Getter
	@Setter
	private BigDecimal taxAmtValue;
	
	@Getter
	@Setter
	private BigDecimal total;
	
	

	@Getter
	@Setter
	private List<LineItem> lineItems;



	public Invoice(String createdBy, String invoiceFrom, String invoiceTo, String companyPhone,String companyAddress, String billingAddress, Date dueDate, Date issueDate,
			int taxAmtPercentage, BigDecimal taxAmtValue, BigDecimal totalAmtDue, List<LineItem> lineItems) {
		super();
		this.createdBy = createdBy;
		this.invoiceFrom = invoiceFrom;
		this.invoiceTo = invoiceTo;
		this.companyPhone = companyPhone;
		this.companyAddress = companyAddress;
		this.billingAddress = billingAddress;
		this.dueDate = dueDate;
		this.issueDate = issueDate;
		this.taxAmtPercentage = taxAmtPercentage;
		this.taxAmtValue = taxAmtValue;
		this.total = totalAmtDue;
		this.lineItems = lineItems;
	}
	
	
}
