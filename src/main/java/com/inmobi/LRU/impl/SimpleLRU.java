package com.inmobi.LRU.impl;

import java.util.HashMap;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.DoublyLinkedList.DoublyLinkedList;
import com.inmobi.LRU.DoublyLinkedList.impl.Node;
import com.inmobi.LRU.DoublyLinkedList.impl.SimpleDoublyLinkedList;
import com.inmobi.LRU.logger.LRULogger;
import com.inmobi.LRU.logger.impl.SimpleLRULogger;

/**
 * Implementation of Least Recently Used Cache
 * @author hsahu
 *
 * @param <K>
 * @param <V>
 */
public class SimpleLRU<K, V> implements LRU<K, V> {

	private int capacity;

	private DoublyLinkedList<K, V> doublyLinkedList;
	
	private HashMap<K, Node<K, V>> lru;
	
	private LRULogger<K> logger;

	public SimpleLRU(int capacity) {
		if (capacity <= 0) {
			throw new IllegalArgumentException("LRU Cache size must be greater that 0");
		}
		this.capacity = capacity;
		this.logger = new SimpleLRULogger<K>();
		this.lru = new HashMap<K, Node<K, V>>();
		this.doublyLinkedList = new SimpleDoublyLinkedList<K, V>();
	}

	public synchronized V get(K key) {
		
		if (lru.containsKey(key)) {
			logger.logPageHit(key);
			Node<K, V> node = lru.get(key);
			doublyLinkedList.delete(node);
			doublyLinkedList.appendToHead(node);
			return node.getValue();
		}
		return null;
	}

	public synchronized void put(K key, V value) {

		if (lru.containsKey(key)) {
			
			logger.logPageHit(key);
			
			Node<K, V> node = lru.get(key);
			doublyLinkedList.delete(node);
			node.setValue(value);
			doublyLinkedList.appendToHead(node);
			lru.remove(key);
			lru.put(key, node);
		} else {

			logger.logPageFault(key);
			
			Node<K, V> node = new Node<K, V>(key, value);

			if (doublyLinkedList.getSize() == capacity) {
				
				System.out.println(doublyLinkedList.toString());
				Node<K, V> lruNode = doublyLinkedList.deleteTail();
				lru.remove(lruNode.getKey());
			}
			
			doublyLinkedList.appendToHead(node);
			lru.put(key, node);
		}
	}
}
