package com.amaysim.cart.chain;

import java.math.BigDecimal;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;

/**
 * 
 * @author janren
 *We will bundle in a free 1 GB Data-pack free-of-charge with every Unlimited 2GB sold.
 */
public class ThirdPricingRuleChain implements PricingRuleChain {

	private static final String PRODUCT_CODE = "ult_medium"; //This can be configured on the database, filesystem, property files etc.
	private PricingRuleChain pricingRuleChain;

	@Override
	public void setNextPricingRuleChain(PricingRuleChain chain) {
		this.pricingRuleChain = chain;
	}


	@Override
	public ShoppingCart applyPricingRule(ShoppingCart cart) {
		System.out.println("Third Pricing Rule Chain");
		List<Product> items = cart.getItemsAdded();
		BigDecimal expectedTotalPrice = BigDecimal.ZERO;
		boolean isPricingRuleApplied = false;
		int quantityToBundle = 0;
		
		for(Product item : items) {
			if (item.getProductCode().equals(PRODUCT_CODE)) {
				quantityToBundle = item.getQuantity();
				isPricingRuleApplied = true;
			}
			expectedTotalPrice = expectedTotalPrice.add(item.getTotalPrice());
		}
		
		if (isPricingRuleApplied) {
			cart.getExpectedCartItems().add(bundleProduct(quantityToBundle));
			cart.setExpectedTotal(expectedTotalPrice);
			return cart;
		} else {
			return this.pricingRuleChain.applyPricingRule(cart);
		}
	}
	
	/**
	 * 
	 * @param quantity
	 * @return
	 * 
	 * This can be a database call or configure thru xml etc...
	 */
	private Product bundleProduct(int quantity) {
		Product product = new Product();
		product.setPrice(BigDecimal.ZERO); //product is free
		product.setProductCode("1gb");
		product.setProductName("1 GB Data-pack");
		product.setQuantity(quantity);
		
		return product;
		
	}

}
