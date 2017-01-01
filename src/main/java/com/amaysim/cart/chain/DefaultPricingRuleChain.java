package com.amaysim.cart.chain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;

/**
 * 
 * @author janren default chain if none of the pricing rules apply, can be
 *         removed also. for now we will apply here the 4th scenario Adding the
 *         promo code 'I<3AMAYSIM' will apply a 10% discount across the board.
 *
 */
public class DefaultPricingRuleChain implements PricingRuleChain {

	private static final String PROMO_CODE = "I<3AMAYSIM"; // can be configurable
	private static final BigDecimal DISCOUNT = BigDecimal.valueOf(0.10); // can be configurable
																		
	private PricingRuleChain pricingRuleChain;

	@Override
	public void setNextPricingRuleChain(PricingRuleChain chain) {
		this.pricingRuleChain = chain;

	}

	@Override
	public ShoppingCart applyPricingRule(ShoppingCart cart) {
		System.out.println("Default Pricing Rule Chain");
		List<Product> items = cart.getItemsAdded();
		List<Product> expectedItems = new ArrayList<Product>();
		boolean isPricingRuleApplied = false;
		BigDecimal expectedTotalPrice = BigDecimal.ZERO;

		for (Product item : items) {
			if (item.getProductCode().equals(PROMO_CODE)) {
				// apply discount price
				isPricingRuleApplied = true;
			} else {
				expectedItems.add(item);
			}

			expectedTotalPrice = expectedTotalPrice.add(item.getTotalPrice());
		}

		if (isPricingRuleApplied) {

			cart.setExpectedTotal(computeDiscountedPrice(expectedTotalPrice));
		} else {
			cart.setExpectedTotal(expectedTotalPrice);
		}

		cart.setExpectedCartItems(expectedItems);
		return cart;
	}

	private BigDecimal computeDiscountedPrice(BigDecimal expectedTotalPrice) {
		BigDecimal discountTotal = expectedTotalPrice.multiply(DISCOUNT);
		return expectedTotalPrice.subtract(discountTotal);
	}

}
