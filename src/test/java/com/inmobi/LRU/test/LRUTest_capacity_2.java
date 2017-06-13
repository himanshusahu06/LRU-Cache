package com.inmobi.LRU.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.impl.SimpleLRU;

public class LRUTest_capacity_2 {
	
	private LRU<String, String> lru;

	@BeforeClass
	private void init() {
		SoftAssert softAssert = new SoftAssert();
		lru = new SimpleLRU<String, String>(2);
		softAssert.assertNull(lru.get("1"));
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: insert till capacity
	 */
	@Test
	public void A_test_fill_cache() {
		SoftAssert softAssert = new SoftAssert();
		lru.put("1", "1");
		lru.put("2", "2");
		softAssert.assertEquals(lru.get("1"), "1");
		softAssert.assertEquals(lru.get("2"), "2");
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: lru key removal
	 */
	@Test
	public void B_test_key_addition_after_cache_full() {
		SoftAssert softAssert = new SoftAssert();
		lru.put("3", "3");
		softAssert.assertEquals(lru.get("3"), "3");
		softAssert.assertEquals(lru.get("2"), "2");
		softAssert.assertNull(lru.get("1"));
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: lru key update
	 */
	@Test
	public void C_test_lru_key_update() {
		SoftAssert softAssert = new SoftAssert();
		lru.put("3", "4");
		softAssert.assertEquals(lru.get("3"), "4");
		softAssert.assertAll();
	}
	
	/**
	 * TEST CASE: updated node should be MRU
	 */
	@Test
	public void D_test_updated_node_should_be_MRU() {
		SoftAssert softAssert = new SoftAssert();
		lru.put("5", "5");
		softAssert.assertEquals(lru.get("3"), "4");
		softAssert.assertEquals(lru.get("5"), "5");
		softAssert.assertNull(lru.get("2"));
		softAssert.assertAll();
	}
}
