package com.inmobi.Application;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.impl.SimpleLRU;

/**
 * Main Application class to run LRU
 * @author hsahu
 */
public class Application {

	public static void main(String[] args) {

		LRU<Integer, Integer> lru = new SimpleLRU<Integer, Integer>(5);

		lru.put(1, 1);
		lru.put(2, 2);
		lru.put(3, 3);
		lru.put(4, 4);
		lru.put(5, 5);
		lru.put(6, 6);
		lru.put(7, 7);
		lru.put(4, 8);
	}


}
