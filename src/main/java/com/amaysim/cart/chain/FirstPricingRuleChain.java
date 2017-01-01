package com.amaysim.cart.chain;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;
/**
 * @author janren
 * A 3 for 2 deal on Unlimited 1GB Sims. So for example, if you buy 3 Unlimited 1GB Sims, 
 * you will pay the price of 2 only for the first month.
 */
public class FirstPricingRuleChain implements PricingRuleChain {
	
	private static final String PRODUCT_CODE = "ult_small"; //This can be configured on the database, filesystem, property files etc.
	private static final int MAGIC_NUMBER = 3; //This is the number of quantity which pricing rules will apply for this chain, can be configured also
	private PricingRuleChain pricingRuleChain;

	@Override
	public void setNextPricingRuleChain(PricingRuleChain chain) {
		this.pricingRuleChain = chain;
		
	}

	@Override
	public ShoppingCart applyPricingRule(ShoppingCart cart) {
		System.out.println("first Pricing rule chain");
		List<Product> items = cart.getItemsAdded();
		BigDecimal expectedTotalPrice = BigDecimal.ZERO;
		BigDecimal discountPrice = BigDecimal.ZERO;
		boolean isFirstPricingRuleApplied = false;
		for(Product item : items) {
			expectedTotalPrice = expectedTotalPrice.add(item.getTotalPrice());
			if (item.getProductCode().equals(PRODUCT_CODE) && item.getQuantity() >= MAGIC_NUMBER) {
				//apply discount price
				discountPrice = item.getPrice();
				isFirstPricingRuleApplied = true;
			}
		}
		
		if (isFirstPricingRuleApplied) {
			cart.setExpectedTotal(expectedTotalPrice.subtract(discountPrice));
			return cart;
		} else {
			return this.pricingRuleChain.applyPricingRule(cart);
		}
		
	}

}
