package com.inmobi.LRU.test;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.impl.SimpleLRU;

public class LRUTest_capacity_1 {
	
	private LRU<String, String> lru;

	@BeforeClass
	private void init() {
		SoftAssert softAssert = new SoftAssert();
		lru = new SimpleLRU<String, String>(1);
		String result = lru.get("1");
		softAssert.assertEquals(result, null);
	}
	
	@Test
	public void test_0() {
		SoftAssert softAssert = new SoftAssert();
		String key = "1";
		String value = "1";
		lru.put(key, value);
		String actualValue = lru.get(key);
		softAssert.assertEquals(actualValue, value);
		softAssert.assertAll();
	}
	
	@Test
	public void test_1() {
		SoftAssert softAssert = new SoftAssert();
		String key = "1";
		String value = "2";
		lru.put(key, value);
		String actualValue = lru.get(key);
		softAssert.assertEquals(actualValue, value);
		softAssert.assertAll();
	}
	
	@Test
	public void test_2() {
		SoftAssert softAssert = new SoftAssert();
		String key = "2";
		String value = "3";
		lru.put(key, value);
		softAssert.assertEquals(lru.get("1"), null);
		softAssert.assertEquals(lru.get(key), value);
		softAssert.assertAll();
	}
}
