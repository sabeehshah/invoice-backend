package com.example.invoiceapp.model;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
public class LineItem {
	
	@Getter
	@Setter
	private String itemName;
	
	@Getter
	@Setter
	private int itemQty;
	
	@Getter
	@Setter
	private BigDecimal itemPrice;
	
	@Getter
	@Setter
	private BigDecimal subTotal;
	
}
