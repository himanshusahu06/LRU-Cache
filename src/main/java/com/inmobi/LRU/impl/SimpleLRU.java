package com.inmobi.LRU.impl;

import java.util.HashMap;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.DoublyLinkedList.DoublyLinkedList;
import com.inmobi.LRU.DoublyLinkedList.impl.Node;
import com.inmobi.LRU.DoublyLinkedList.impl.SimpleDoublyLinkedList;

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

	public SimpleLRU(int capacity) {
		this.capacity = capacity;
		this.lru = new HashMap<K, Node<K, V>>();
		this.doublyLinkedList = new SimpleDoublyLinkedList<K, V>();
	}

	public V get(K key) {
		if (lru.containsKey(key)) {
			Node<K, V> node = lru.get(key);
			doublyLinkedList.delete(node);
			doublyLinkedList.appendToHead(node);
			return node.getValue();
		}
		return null;
	}

	public void put(K key, V value) {

		if (lru.containsKey(key)) {
			Node<K, V> node = lru.get(key);
			doublyLinkedList.delete(node);
			node.setValue(value);
			doublyLinkedList.appendToHead(node);
			lru.remove(key);
			lru.put(key, node);
		} else {

			Node<K, V> node = new Node<K, V>(key, value);

			if (doublyLinkedList.getSize() == capacity) {
				Node<K, V> lruNode = doublyLinkedList.deleteTail();
				lru.remove(lruNode.getKey());
			}
			
			doublyLinkedList.appendToHead(node);
			lru.put(key, node);
		}
	}
}
