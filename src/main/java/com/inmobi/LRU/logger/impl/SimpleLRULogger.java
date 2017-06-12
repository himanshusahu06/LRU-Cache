package com.inmobi.LRU.logger.impl;

import com.inmobi.LRU.logger.LRULogger;

public class SimpleLRULogger<K> implements LRULogger<K> {

	public void logPageHit(K key) {
		System.out.println("[HIT] : " + key.toString());
	}

	public void logPageFault(K key) {
		System.out.println("[FAULT] : " + key.toString());
	}
}