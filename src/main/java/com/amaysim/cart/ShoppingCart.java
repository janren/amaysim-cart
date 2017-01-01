package com.amaysim.cart;

import java.math.BigDecimal;
import java.util.List;

public class ShoppingCart {

	private List<Product> itemsAdded;
	private BigDecimal expectedTotal;
	private List<Product> expectedCartItems;

	public List<Product> getItemsAdded() {
		return itemsAdded;
	}

	public void setItemsAdded(List<Product> itemsAdded) {
		this.itemsAdded = itemsAdded;
	}

	public BigDecimal getExpectedTotal() {
		return expectedTotal;
	}

	public void setExpectedTotal(BigDecimal expectedTotal) {
		this.expectedTotal = expectedTotal;
	}

	public List<Product> getExpectedCartItems() {
		return expectedCartItems;
	}

	public void setExpectedCartItems(List<Product> expectedCartItems) {
		this.expectedCartItems = expectedCartItems;
	}

	
	@Override
	public String toString() {
		StringBuilder stringbuilder = new StringBuilder();
		stringbuilder.append("*****Items Added*****.").append("\n");
		for(Product product : itemsAdded) {
			stringbuilder.append(product.toString()).append("\n");
		}
		stringbuilder.append("*****Expected Total*****").append("\n");
		stringbuilder.append(expectedTotal).append("\n");
		stringbuilder.append("*****Expected Cart Items*****").append("\n");
		for(Product product : expectedCartItems) {
			stringbuilder.append(product.toString()).append("\n");
		}
		
		return stringbuilder.toString();
	}

}
