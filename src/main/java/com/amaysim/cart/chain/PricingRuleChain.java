package com.amaysim.cart.chain;

import com.amaysim.cart.ShoppingCart;

public interface PricingRuleChain {
	
	public void setNextPricingRuleChain(PricingRuleChain chain);
	
	/**
	 * 
	 * @param cart
	 * This method will implement Pricing rules 
	 */
	public ShoppingCart applyPricingRule(ShoppingCart cart);

}
