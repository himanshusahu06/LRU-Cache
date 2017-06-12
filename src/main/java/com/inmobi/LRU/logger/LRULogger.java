package com.inmobi.LRU.logger;

public interface LRULogger<K> {

	/**
	 * Logs LRU page hit event
	 * @param key
	 */
	public void logPageHit(K key);

	/**
	 * Logs LRU page fault event
	 * @param key
	 */
	public void logPageFault(K key);
}