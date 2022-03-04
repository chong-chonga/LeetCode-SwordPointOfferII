package com.hlx.leetcode.structure;

import java.util.Map;
import java.util.Objects;

/**
 * @author Huang Lexin
 * @date 2022年03月02日 16:21
 */
public class MyHashMap<K,V> {

	private static final int DEFAULT_CAPACITY = 1 << 4;

	private static final int MAX_CAPACITY = 1 << 30;

	private static final float DEFAULT_LOAD_FACTOR = 0.75f;

	/**
	 * 当前 map 存储的元素个数
	 */
	int size;

	/**
	 * map 存储多少个元素时进行扩容
	 */
	int threshold;

	/**
	 * 负载因子
	 */
	float loadFactor;

	/**
	 * map 存储元素所用的数组
	 */
	Entry<K,V>[] tab;


	/**
	 * 存储在数组中的对象类型, 是数组中的链表结点
	 */
	static class Entry<K,V> implements Map.Entry<K, V> {
		K key;
		V val;
		int hash;
		Entry<K,V> next;

		public Entry(K key, V val, int hash) {
			this(key, val, hash, null);
		}

		public Entry(K key, V val, int hash, Entry<K, V> next) {
			this.key = key;
			this.val = val;
			this.hash = hash;
			this.next = next;
		}

		@Override
		public K getKey() {
			return key;
		}

		@Override
		public V getValue() {
			return val;
		}

		@Override
		public V setValue(V value) {
			V ret = this.val;
			this.val = value;
			return ret;
		}
	}


	public MyHashMap () {
		this.loadFactor = DEFAULT_LOAD_FACTOR;
	}

	public V put(K key, V val) {
		return putVal(hash(key), key, val);
	}

	/**
	 * HashMap 用到的二次 hash 方法
	 */
	private int hash(K key) {
		int hash = Objects.hashCode(key);
		return (hash >>> 16) ^ hash;
	}

	@SuppressWarnings("unchecked")
	private V putVal(int hash, K key, V val) {
		if (tab == null || tab.length == 0) {
			tab = resize();
		}
		int n = tab.length;
		int index = hash & (n - 1);
		Entry<K, V> head = tab[index];
		Entry<K, V> ret = null;
		if (head == null) {
			tab[index] = new Entry<>(key, val, hash);
		} else {
			Entry<K,V> temp = head;
			while (temp != null) {
				if (temp.key == key || (temp.hash == hash && temp.key.equals(key))) {
					temp.val = val;
					ret = temp;
					break;
				}
				temp = temp.next;
			}
			if (temp == null) {
				tab[index] = new Entry<>(key, val, hash, head);
			}
		}
		if (ret == null) {
			++size;
			return null;
		}
		return ret.getValue();
	}

	private Entry<K,V>[] resize() {
		Entry<K,V>[] oldTab = tab;
		int oldCap = null == oldTab ? 0 : oldTab.length;
		int oldThr = threshold;
		int newCap;
		int newThr = 0;
		if (oldCap > 0) {
			 if (oldCap >= MAX_CAPACITY) {
				 threshold = Integer.MAX_VALUE;
				 return oldTab;
			 } else if ((newCap = oldCap << 1) < MAX_CAPACITY) {
				 newThr = oldThr << 1;
			 }
		} else  {
			newCap = DEFAULT_CAPACITY;
			newThr = (int)(DEFAULT_CAPACITY * DEFAULT_LOAD_FACTOR);
		}
		if (0 == newThr) {
			float ft = newCap * loadFactor;
			newThr = newCap < MAX_CAPACITY && (ft < MAX_CAPACITY) ? (int)ft : Integer.MAX_VALUE;
		}
		@SuppressWarnings("unchecked")
		Entry<K,V>[] newTab = (Entry<K,V>[]) new Entry[newCap];
		threshold = newThr;
		tab = newTab;
		return newTab;
	}

	public static void main(String[] args) {
		int x = 0;
		System.out.println(x + ": " + Integer.numberOfLeadingZeros(x));
		x = 1;
		for (int i = 0; i < 31; i++) {
			System.out.println(x + ": " + Integer.numberOfLeadingZeros(x));
			x = x << 1;
		}
	}
}
