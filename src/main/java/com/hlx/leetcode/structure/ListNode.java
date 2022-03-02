package com.hlx.leetcode.structure;

/**
 * 链表结点
 * @author Huang Lexin
 * @date 2022年03月02日 8:04
 */
public class ListNode {
	public int val;
	public ListNode next;
	public ListNode() {}

	public ListNode(int val) {
		this(val, null);
	}

	public ListNode (int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
