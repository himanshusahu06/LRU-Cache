package com.inmobi.LRU.DoublyLinkedList.impl;

/**
 * Single Node DTO of a Doubly Linked List
 * @author hsahu
 *
 * @param <K>
 * @param <V>
 */
public class Node<K, V> {

	private K key;
	private V value;
	private Node<K, V> next;
	private Node<K, V> prev;

	public Node(K key, V value) {
		this.value = value;
		this.key = key;
	}

	public V getValue() {
		return value;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public void setKey(K key) {
		this.key = key;
	}

	public Node<K, V> getNext() {
		return next;
	}

	public void setNext(Node<K, V> next) {
		this.next = next;
	}

	public Node<K, V> getPrev() {
		return prev;
	}

	public void setPrev(Node<K, V> prev) {
		this.prev = prev;
	}

	@Override
	public String toString() {
		return "Node [key=" + key + ", value=" + value + "]";
	}

}
