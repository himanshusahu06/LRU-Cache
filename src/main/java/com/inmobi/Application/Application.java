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
	
	
	public static void primitiveDataTypedLRU() {
		
		System.out.println("\n\n----------primitiveDataTypedLRU----------\n\n");
		
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
		
		System.out.println("LRU cache - PRIMITIVE DATA TYPE Operations Complete..");
	}
	
	public static void keyAsObjectDataTypedLRU() {
		
		System.out.println("\n\n----------keyAsObjectDataTypedLRU----------\n\n");
		
		class A {
			public String x;
			public String y;
		
			public A(String x, String y) {
				this.x = x;
				this.y = x;
			}
		
			@Override
			public int hashCode() {
				final int prime = 31;
				int result = 1;
				result = prime * result + ((x == null) ? 0 : x.hashCode());
				result = prime * result + ((y == null) ? 0 : y.hashCode());
				return result;
			}

			@Override
			public boolean equals(Object obj) {
				if (this == obj)
					return true;
				if (obj == null)
					return false;
				if (getClass() != obj.getClass())
					return false;
				A other = (A) obj;
				if (x == null) {
					if (other.x != null)
						return false;
				} else if (!x.equals(other.x))
					return false;
				if (y == null) {
					if (other.y != null)
						return false;
				} else if (!y.equals(other.y))
					return false;
				return true;
			}

			@Override
			public String toString() {
				return "A [x=" + x + ", y=" + y + "]";
			}
		}
		
		LRU<A, Integer> lru = new SimpleLRU<A, Integer>(2);
		
		lru.put(new A("a", "a"), 1);
		lru.put(new A("b", "b"), 2);
		
		System.out.println("GET 1 : " + lru.get(new A("a", "a")));
		
		lru.put(new A("c", "c"), 3);
		
		System.out.println("GET 1 : " + lru.get(new A("b", "b")));
		

		System.out.println("LRU cache - KEY AS OBJECT DATA TYPE Operations Complete..");
		
	}
	
	public static void valueAsObjectDataTypedLRU() {
		
		System.out.println("\n\n----------valueAsObjectDataTypedLRU----------\n\n");
		
		class A {
			public String x;
			public String y;
		
			public A(String x, String y) {
				this.x = x;
				this.y = x;
			}
		
			@Override
			public String toString() {
				return "A [x=" + x + ", y=" + y + "]";
			}
		}
				
		LRU<Integer, A> lru = new SimpleLRU<Integer, A>(2);
		
		lru.put(1, new A("a", "a"));
		lru.put(2, new A("b", "b"));
		
		System.out.println("GET 1 : " + lru.get(1));
		
		lru.put(3, new A("c", "c"));
		
		System.out.println("GET 1 : " + lru.get(2));
		

		System.out.println("LRU cache - VALUE AS OBJECT DATA TYPE Operations Complete..");
	}
	
	
	public static void main(String[] args) {
		primitiveDataTypedLRU();
		valueAsObjectDataTypedLRU();
		keyAsObjectDataTypedLRU();
	}
}