package com.inmobi.LRU.DoublyLinkedList;

import com.inmobi.LRU.DoublyLinkedList.impl.Node;

/**
 * Doubly Linked List interface
 * @author hsahu
 *
 * @param <K>
 * @param <V>
 */
public interface DoublyLinkedList<K, V> {

	/**
	 * Size of the Doubly Linked List
	 * @return
	 */
	public int getSize();
	
	/**
	 * Append a new node at the start of doubly linked list
	 * @param node
	 */
	public void appendToHead(Node<K, V> node);

	/**
	 * Delete any node from doubly linked list
	 * @param node
	 */
	public void delete(Node<K, V> node);

	/**
	 * Delete tail node from doubly linked list
	 * @return
	 */
	public Node<K, V> deleteTail();
}
