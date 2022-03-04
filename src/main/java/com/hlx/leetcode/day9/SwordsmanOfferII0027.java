package com.hlx.leetcode.day9;

import com.hlx.leetcode.structure.ListNode;

/**
 * @author Huang Lexin
 * @date 2022年03月04日 9:14
 */
public class SwordsmanOfferII0027 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 * 给定一个链表的 头节点 head ，请判断其是否为回文链表。
	 *
	 * 如果一个链表是回文，那么链表节点序列从前往后看和从后往前看是相同的。
	 *
	 * 提示：
	 *
	 * 链表 L 的长度范围为 [1, 105]
	 * 0 <= node.val <= 9
	 *
	 * 进阶：能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
	 */
	class Solution {

		public boolean isPalindrome(ListNode head) {
			int len = lengthOfList(head);
			if (1 == len) {
				return true;
			}
			int mid = len / 2;
			ListNode reverseHead = head;
			for (int i = 0; i < mid; i++) {
				reverseHead = reverseHead.next;
			}
			if ((len & 1) == 1) {
				reverseHead = reverseHead.next;
			}
			reverseHead = reverseList(reverseHead);
			boolean result = true;
			ListNode tempLeft = head;
			ListNode tempRight = reverseHead;
			for (int i = 0; i < mid; i++) {
				if (tempLeft.val != tempRight.val) {
					result = false;
					break;
				}
				tempLeft = tempLeft.next;
				tempRight = tempRight.next;
			}
			reverseHead = reverseList(reverseHead);
			if ((len & 1) == 1) {
				tempLeft = tempLeft.next;
			}
			tempLeft.next = reverseHead;

			return result;
		}

		int lengthOfList(ListNode head) {
			int len = 0;
			while (head != null) {
				head = head.next;
				++len;
			}
			return len;
		}

		ListNode reverseList(ListNode head) {
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
