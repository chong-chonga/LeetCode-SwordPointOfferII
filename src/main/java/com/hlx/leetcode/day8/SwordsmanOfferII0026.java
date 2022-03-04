package com.hlx.leetcode.day8;

import com.hlx.leetcode.structure.ListNode;

/**
 * @author Huang Lexin
 * @date 2022年03月03日 11:19
 */
public class SwordsmanOfferII0026 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
	 *
	 *  L0 → L1 → … → Ln-1 → Ln
	 * 请将其重新排列后变为：
	 *
	 * L0 → Ln → L1 → Ln-1 → L2 → Ln-2 → …
	 *
	 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
	 * 链表的长度范围为 [1, 5 * 104]
	 * 1 <= node.val <= 1000
	 */
	class Solution {
		public void reorderList(ListNode head) {
			int len = 0;
			ListNode temp = head;
			while (temp != null) {
				++len;
				temp = temp.next;
			}
			// 以头结点下标为 0, 则应当反转的起始结点就是 mid
			int mid = len / 2;
			temp = head;
			int target = mid;
			if ((len & 1) == 0) {
				target--;
			}
			for (int i = 0; i < target; i++) {
				temp = temp.next;
			}
			ListNode midNode = temp;
			temp = temp.next;
			midNode.next = null;
			ListNode reverseHead = reverse(temp);
			ListNode next1;
			ListNode next2;
			for (int i = 0; i < mid; i++) {
				next1 = head.next;
				next2 = reverseHead.next;
				head.next = reverseHead;
				reverseHead.next = next1;
				head = next1;
				reverseHead = next2;
			}
			if ((len & 1) == 1) {
				head.next = null;
			}
		}

		ListNode reverse(ListNode head) {
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
