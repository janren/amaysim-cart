package com.amaysim.cart.chain.test;

import java.math.BigDecimal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amaysim.cart.ShoppingCart;
import com.amaysim.cart.chain.DefaultPricingRuleChain;
import com.amaysim.cart.chain.ThirdPricingRuleChain;

public class DefaultPricingRuleChainTest extends ParentChainTest {

	/**
	 * for best practice, static variables can be transferred in property file,
	 * for testing purposes only
	 */
	private static final String FILE_NAME = "defaultPricingRuleChainTestData.json";
	private static final int LIST_SIZE = 3;
	private static final BigDecimal EXPECTED_CART_TOTAL = BigDecimal.valueOf(31.32);
	private static final int EXPECTED_LIST_SIZE = 2;

	private ShoppingCart shoppingCartData;

	@Before
	public void loadTestData() {
		shoppingCartData = super.loadTestData(FILE_NAME);
	}

	/**
	 * Expected results is based on Technical Test Cart.pdf rules
	 */
	@Test
	public void testExpectedTotalForDefaultPricingRule() {
		DefaultPricingRuleChain ruleChain = new DefaultPricingRuleChain();
		ShoppingCart cart = this.shoppingCartData;
		cart = ruleChain.applyPricingRule(cart);
		Assert.assertTrue(cart.getItemsAdded().size() == LIST_SIZE);
		Assert.assertTrue("cart expected total is " + cart.getExpectedTotal() + " to " + EXPECTED_CART_TOTAL,
				cart.getExpectedTotal().compareTo(EXPECTED_CART_TOTAL) == 0);
		Assert.assertTrue(cart.getExpectedCartItems().size() == EXPECTED_LIST_SIZE);
	}

}
