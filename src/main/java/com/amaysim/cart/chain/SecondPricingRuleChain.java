package com.amaysim.cart.chain;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;

/**
 * @author janren
 * The Unlimited 5GB Sim will have a bulk discount applied; 
 * whereby the price will drop to $39.90 each for the first month, if the customer buys more than 3.
 */
public class SecondPricingRuleChain implements PricingRuleChain {

	private static final String PRODUCT_CODE = "ult_large"; //This can be configured on the database, filesystem, property files etc.
	private static final int MAGIC_NUMBER = 3; //This is the number of quantity which pricing rules will apply for this chain, can be configured also
	private static final BigDecimal NEW_PRICE = BigDecimal.valueOf(39.90); //new price can be configured also
	private PricingRuleChain pricingRuleChain;

	@Override
	public void setNextPricingRuleChain(PricingRuleChain chain) {
		this.pricingRuleChain = chain;
		
	}

	@Override
	public ShoppingCart applyPricingRule(ShoppingCart cart) {
		System.out.println("Second Pricing Rule Chain");
		List<Product> items = cart.getItemsAdded();
		BigDecimal expectedTotalPrice = BigDecimal.ZERO;
		boolean isPricingRuleApplied = false;
		
		for(Product item : items) {
			if (item.getProductCode().equals(PRODUCT_CODE) && item.getQuantity() > MAGIC_NUMBER) {
				//apply discount price
				item.setPrice(NEW_PRICE);
				isPricingRuleApplied = true;
			}
			
			expectedTotalPrice = expectedTotalPrice.add(item.getTotalPrice());
		}
		
		if (isPricingRuleApplied) {
			
			cart.setExpectedTotal(expectedTotalPrice);
			System.out.println("expected total is " + cart.getExpectedTotal());
			return cart;
		} else {
			return this.pricingRuleChain.applyPricingRule(cart);
		}
	}

	
}
