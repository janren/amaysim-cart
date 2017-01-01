package com.amaysim.cart;

import java.math.BigDecimal;

public class Product {

	private String productCode;
	private String productName;
	private BigDecimal price;
	private int quantity;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public BigDecimal getTotalPrice() {
		return this.price.multiply(new BigDecimal(quantity));
	}
	
	@Override
	public String toString() {
		return String.valueOf(quantity).concat(" X ").concat(productName);
	}

}
