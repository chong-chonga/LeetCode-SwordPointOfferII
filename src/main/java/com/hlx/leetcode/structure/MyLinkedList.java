package com.hlx.leetcode.structure;

/**
 * @author Huang Lexin
 * @date 2022年03月07日 12:52
 */
public class MyLinkedList {

	int size;

	Node first;

	Node last;

	public class Node {
		int key;
		int value;
		Node prev;
		Node next;

		public Node(int key, int value) {
			this.key = key;
			this.value = value;
		}
	}

	/**
	 * 从链表中移除该结点
	 */
	public void unlink(Node node) {
		Node prev = node.prev;
		Node next = node.next;

		node.prev = null;
		if (prev == null) {
			first = next;
		} else {
			prev.next = next;
		}

		node.next = null;
		if (next == null) {
			last = prev;
		} else {
			next.prev = prev;
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


}
