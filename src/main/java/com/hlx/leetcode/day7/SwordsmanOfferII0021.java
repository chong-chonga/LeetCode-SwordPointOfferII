package com.hlx.leetcode.day7;

import com.hlx.leetcode.structure.ListNode;

import java.lang.reflect.WildcardType;

/**
 * @author Huang Lexin
 * @date 2022年03月02日 8:02
 */
public class SwordsmanOfferII0021 {
	/**
	 * 给定一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 *
	 * 链表中结点的数目为 sz
	 * 1 <= sz <= 30
	 * 0 <= Node.val <= 100
	 * 1 <= n <= sz
	 */
	class Solution {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			ListNode p1 = head;
			ListNode p2 = head;
			// 此题的思想在于: 倒数第 n 个结点, 也就是正数 (len - n + 1) 个结点
			// 只要让一个指针从头结点(正数第一个)开始, 往前走 len - n 步 即可
			// 利用 n + len - n = len
			for (int i = 1; i < n; i++) {
				p1 = p1.next;
			}
			ListNode back = null;
			while (p1.next != null) {
				p1 = p1.next;
				back = p2;
				p2 = p2.next;
			}
			if (back == null) {
				return head.next;
			}
			back.next = p2.next;
			return head;
		}
	}
}
