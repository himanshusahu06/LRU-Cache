package com.inmobi.LRU.DoublyLinkedList.impl;

import com.inmobi.LRU.DoublyLinkedList.DoublyLinkedList;

/**
 * Simple Doubly Linked List Implementation
 * @author hsahu
 *
 * @param <K>
 * @param <V>
 */
public class SimpleDoublyLinkedList<K, V> implements DoublyLinkedList<K, V> {

	private Node<K, V> head;
	private Node<K, V> tail;
	private int size;

	public SimpleDoublyLinkedList() {
		this.size = 0;
		this.head = null;
		this.tail = null;
	}

	public int getSize() {
		return size;
	}

	public void appendToHead(Node<K, V> node) {
		if (this.head == null) {
			head = node;
			tail = node;
		} else {
			node.setNext(head);
			head.setPrev(node);
			head = node;
		}
		this.size++;
	}

	private void delinkNode(Node<K, V> node) {
		node.setNext(null);
		node.setPrev(null);
	}

	public void delete(Node<K, V> node) {

		if (node.getNext() == null) {
			deleteTail();
		} else if (node.getPrev() == null) {
			deleteHead();
		} else {
			deleteIntermediateNode(node);
		}
	}

	private void deleteIntermediateNode(Node<K, V> node) {

		this.size--;
		
		Node<K, V> prevNode = node.getPrev();
		Node<K, V> nextNode = node.getNext();

		prevNode.setNext(nextNode);
		nextNode.setPrev(prevNode);

		delinkNode(node);
	}
	
	private void deleteHead() {
		
		Node<K, V> headNode = null;
		
		if (head != null) {
			this.size--;
			headNode = head;
			head = head.getNext();
			if (head != null) {
				head.setPrev(null);
			}
			delinkNode(headNode);
		}
	}

	public Node<K, V> deleteTail() {
		
		Node<K, V> node = null;
		
		if (tail != null) {
			this.size--;
			node = tail;
			tail = tail.getPrev();
			if (tail != null) {
				tail.setNext(null);
			}
			delinkNode(node);
		}
		
		return node;
	}

	@Override
	public String toString() {
		return "DoublyLinkedList [head=" + head + ", tail=" + tail + ", size=" + size + "]";
	}
}
