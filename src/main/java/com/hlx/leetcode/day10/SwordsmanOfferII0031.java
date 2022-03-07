package com.hlx.leetcode.day10;

/**
 * @author Huang Lexin
 * @date 2022年03月06日 21:21
 */
public class SwordsmanOfferII0031 {

		class LRUCache {

			int capacity;

			Node[] nodeMap;

			MyLinkedList cache;

			class Node {
				int key;
				int value;
				Node prev;
				Node next;

				public Node(int key, int value) {
					this.key = key;
					this.value = value;
				}

			}


			class MyLinkedList {

				int size;

				Node first;

				Node last;

				/**
				 * 从链表中移除该结点
				 */
				public void unlink(Node node) {
					Node prev = node.prev;
					Node next = node.next;

					if (prev == null) {
						first = next;
					} else {
						prev.next = next;
						node.prev = null;
					}

					if (next == null) {
						last = prev;
					} else {
						next.prev = prev;
						node.next = null;
					}
					--size;
				}

				/**
				 * 弹出链表的尾部结点
				 */
				public int pollLast() {
					int key = last.key;
					Node prev = last.prev;
					// GC
					last.prev = null;
					last = prev;
					if (prev == null) {
						first = null;
					} else {
						prev.next = null;
					}
					--size;
					return key;
				}

				/**
				 * 将结点插入到链表的头部
				 */
				public void offerFirst(Node node) {
					node.next = first;
					if (first == null) {
						last = node;
					} else {
						first.prev = node;
					}
					first = node;
					++size;
				}


				public int size() {
					return size;
				}
			}

			public LRUCache(int capacity) {
				this.capacity = capacity;
				this.nodeMap = new Node[10000];
				cache = new MyLinkedList();
			}

			public int get(int key) {
				Node node = nodeMap[key];
				if (node == null) {
					return -1;
				}
				// 解读 remove 的源码, node != null 的时候, 是遍历了 cache, 调用 equals 方法判断, 然后再 unlink() 的
				// 这也是为什么超级慢的原因
				// 我们想要做的是直接 unlink
				cache.unlink(node);
				cache.offerFirst(node);
				return node.value;
			}


			public void put(int key, int value) {
				Node node = nodeMap[key];
				if (node == null) {
					node = new Node(key, value);
					if (cache.size() == capacity) {
						int lastKey = cache.pollLast();
						nodeMap[lastKey] = null;
					}
					nodeMap[key] = node;
				} else {
					cache.unlink(node);
					node.value = value;
				}
				cache.offerFirst(node);
			}

		}


}
