package com.hlx.leetcode.day8;

import com.hlx.leetcode.structure.ListNode;

/**
 * @author Huang Lexin
 * @date 2022年03月03日 11:19
 */
public class SwordsmanOfferII0024 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 * 给定单链表的头节点 head ，请反转链表，并返回反转后的链表的头节点。
	 * 链表中节点的数目范围是 [0, 5000]
	 * -5000 <= Node.val <= 5000
	 */
	class Solution {
		public ListNode reverseList(ListNode head) {
			ListNode pre = null;
			ListNode cur = head;
			ListNode next;
			while (cur != null) {
				next = cur.next;
				cur.next = pre;
				pre = cur;
				cur = next;
			}
			return pre;
		}
	}
}
