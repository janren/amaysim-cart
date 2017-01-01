package com.amaysim.cart.chain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;

public class TestCartMainClass {
	
	private FirstPricingRuleChain firstChain;
	
	public TestCartMainClass() {
		//initialize chain
		this.firstChain = new FirstPricingRuleChain();
		
		PricingRuleChain secondChain = new SecondPricingRuleChain();
		PricingRuleChain thirdChain = new ThirdPricingRuleChain();
		PricingRuleChain defaultChain = new DefaultPricingRuleChain();
		
		firstChain.setNextPricingRuleChain(secondChain);
		secondChain.setNextPricingRuleChain(thirdChain);
		thirdChain.setNextPricingRuleChain(defaultChain);
	}
	
	public static void main(String[] args) {
		TestCartMainClass mainClass = new TestCartMainClass();
		
		//For testing purposes only, this needs to be on the UI etc....
		
		ShoppingCart cart = mainClass.firstChain.applyPricingRule(mainClass.testScenario3());
		System.out.println(cart.toString());
	}

	
	public ShoppingCart testScenario1() {
		ShoppingCart cart = new ShoppingCart();
		
		Product product1 = new Product();
		product1.setPrice(BigDecimal.valueOf(24.90));
		product1.setProductCode("ult_small");
		product1.setProductName("Unlimited 1GB");
		product1.setQuantity(3);
		
		Product product2 = new Product();
		product2.setPrice(BigDecimal.valueOf(44.90));
		product2.setProductCode("ult_large");
		product2.setQuantity(1);
		product2.setProductName("Unlimited 5GB");
		
		List<Product> itemsAdded = new ArrayList<>();
		itemsAdded.add(product1);
		itemsAdded.add(product2);
		
		cart.setItemsAdded(itemsAdded);
		cart.setExpectedCartItems(itemsAdded);
		
		return cart;
	}
	
	public ShoppingCart testScenario2() {
		ShoppingCart cart = new ShoppingCart();
		
		Product product1 = new Product();
		product1.setPrice(BigDecimal.valueOf(24.90));
		product1.setProductCode("ult_small");
		product1.setQuantity(2);
		product1.setProductName("Unlimited 1GB");
		
		Product product2 = new Product();
		product2.setPrice(BigDecimal.valueOf(44.90));
		product2.setProductCode("ult_large");
		product2.setQuantity(4);
		product2.setProductName("Unlimited 5GB");
		
		List<Product> itemsAdded = new ArrayList<>();
		itemsAdded.add(product1);
		itemsAdded.add(product2);
		
		cart.setItemsAdded(itemsAdded);
		cart.setExpectedCartItems(itemsAdded);
		
		return cart;
	}
	
	public ShoppingCart testScenario3() {
		ShoppingCart cart = new ShoppingCart();
		
		Product product1 = new Product();
		product1.setPrice(BigDecimal.valueOf(24.90));
		product1.setProductCode("ult_small");
		product1.setQuantity(1);
		product1.setProductName("Unlimited 1GB");
		
		Product product2 = new Product();
		product2.setPrice(BigDecimal.valueOf(29.90));
		product2.setProductCode("ult_medium");
		product2.setProductName("Unlimited 2GB");
		product2.setQuantity(2);
		
		List<Product> itemsAdded = new ArrayList<>();
		itemsAdded.add(product1);
		itemsAdded.add(product2);
		
		cart.setItemsAdded(itemsAdded);
		cart.setExpectedCartItems(itemsAdded);
		
		return cart;
	}
	
	public ShoppingCart testScenario4() {
		ShoppingCart cart = new ShoppingCart();
		
		Product product1 = new Product();
		product1.setPrice(BigDecimal.valueOf(24.90));
		product1.setProductCode("ult_small");
		product1.setQuantity(1);
		product1.setProductName("Unlimited 1GB");
		
		Product product2 = new Product();
		product2.setPrice(BigDecimal.valueOf(9.90)); //product is free
		product2.setProductCode("1gb");
		product2.setProductName("1 GB Data-pack");
		product2.setQuantity(1);
		
		Product product3 = new Product();
		product3.setPrice(BigDecimal.ZERO); //product is free
		product3.setProductCode("I<3AMAYSIM");
		product3.setProductName("'I<3AMAYSIM' Promo Applied");
		product2.setQuantity(1);
		
		List<Product> itemsAdded = new ArrayList<>();
		itemsAdded.add(product1);
		itemsAdded.add(product2);
		
		List<Product> itemsAddedFirst = new ArrayList<>();
		itemsAddedFirst.addAll(itemsAdded);
		itemsAddedFirst.add(product3);
		
		cart.setItemsAdded(itemsAddedFirst);
		cart.setExpectedCartItems(itemsAdded);
		
		return cart;
	}
}
