package com.amaysim.cart.chain.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.amaysim.cart.Product;
import com.amaysim.cart.ShoppingCart;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ParentChainTest {

	/**
	 * 
	 * @param fileName
	 *            to be passed from child class
	 * @return
	 */
	public ShoppingCart loadTestData(String fileName) {

		ShoppingCart shoppingCartData = new ShoppingCart();
		try {

			URI uri = getClass().getClassLoader().getResource(fileName).toURI();
			String string = new String(Files.readAllBytes(Paths.get(uri)), Charset.forName("utf-8"));
			Gson gson = new Gson();
			List<Product> products = gson.fromJson(string, new TypeToken<List<Product>>() {
			}.getType());

			shoppingCartData.setItemsAdded(products);
			
			List<Product> expectedCartItems = new ArrayList<Product>();
			expectedCartItems.addAll(products);
			shoppingCartData.setExpectedCartItems(expectedCartItems);

		} catch (URISyntaxException | IOException e) {
			// error loading data from json
			e.printStackTrace();
		}

		return shoppingCartData;
	}

}
