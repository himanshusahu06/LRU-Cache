package com.inmobi.Application;

import com.inmobi.LRU.LRU;
import com.inmobi.LRU.impl.SimpleLRU;

/**
 * Main Application class with LRU integration
 * Output may be different in each execution because of RACE CONDITION
 * 
 * @author hsahu
 */
public class Application {

	public static void main(String[] args) {

		final LRU<Integer, Integer> lru = new SimpleLRU<Integer, Integer>(5);

		Thread threadA = new Thread(new Runnable() {
			public void run() {
				lru.put(1, 1);
				System.out.println("GET 1 : " + lru.get(1));
				lru.put(2, 2);
				lru.put(7, 7);
				System.out.println("GET 1 : " + lru.get(2));
				lru.put(4, 8);
			}
		});

		Thread threadB = new Thread(new Runnable() {
			public void run() {
				lru.put(3, 3);
				lru.put(2, 20);
				lru.put(3, 30);
				System.out.println("GET 2 : " + lru.get(3));
				lru.put(6, 6);
				System.out.println("GET 1 : " + lru.get(6));
			}
		});

		threadA.start();
		threadB.start();

		try {
			threadA.join();
			threadB.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("LRU cache Operations Complete..");
	}

}
