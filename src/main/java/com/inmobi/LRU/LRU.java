package com.inmobi.LRU;

/**
 * Least Recently Used (LRU) Cache Library
 * @author hsahu
 *
 * @param <K>
 * @param <V>
 */
public interface LRU<K, V> {

	/**
	 * Get the value associated with key
	 * If key is not found then null will be returned
	 * @param key
	 * @return
	 */
	public V get(K key);

	/**
	 * Add an entry to LRU based on Least recently Used Cache Strategy
	 * @param key
	 * @param value
	 */
	public void put(K key, V value);
}
