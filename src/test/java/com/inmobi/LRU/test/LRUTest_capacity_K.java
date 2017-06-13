package com.inmobi.LRU.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.impl.SimpleLRU;

public class LRUTest_capacity_K {
	
	int k = 10;

	private LRU<String, String> lru;

	@BeforeClass
	private void init() {
		lru = new SimpleLRU<String, String>(k);
	}
	
	/**
	 * TEST CASE: insert till capacity
	 */
	@Test
	public void A_test_fill_cache() {
		SoftAssert softAssert = new SoftAssert();
		for (int i = 1 ; i <= k ; i++) {
			lru.put(Integer.toString(i), Integer.toString(i));
		}
		for (int i = 1 ; i <= k ; i++) {
			softAssert.assertEquals(lru.get(Integer.toString(i)), Integer.toString(i));
		}
		
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: lru key removal
	 */
	@Test
	public void B_test_key_addition_after_cache_full() {
		SoftAssert softAssert = new SoftAssert();
		lru.put(Integer.toString(k+1), Integer.toString(k+1));
		softAssert.assertNull(lru.get(Integer.toString(1)));
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: lru key update
	 */
	@Test
	public void C_test_lru_key_update() {
		SoftAssert softAssert = new SoftAssert();
		lru.put(Integer.toString(k+1), Integer.toString(2*(k+1)));
		softAssert.assertEquals(lru.get(Integer.toString(k+1)), Integer.toString(2*(k+1)));
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: updated node should be MRU
	 */
	@Test
	public void D_test_updated_node_should_be_MRU() {
		SoftAssert softAssert = new SoftAssert();
		lru.put(Integer.toString(k+2), Integer.toString(2*(k+2)));
		softAssert.assertNull(lru.get("2"));
		softAssert.assertAll();
	}
}
