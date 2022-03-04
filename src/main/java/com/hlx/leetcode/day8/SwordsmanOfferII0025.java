package com.hlx.leetcode.day8;

import com.hlx.leetcode.structure.ListNode;

/**
 * @author Huang Lexin
 * @date 2022年03月03日 11:19
 */
public class SwordsmanOfferII0025 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode() {}
	 *     ListNode(int val) { this.val = val; }
	 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	 * }
	 *
	 * 给定两个 非空链表 l1和 l2 来代表两个非负整数。数字最高位位于链表开始位置。
	 * 它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
	 *
	 * 可以假设除了数字 0 之外，这两个数字都不会以零开头。
	 * 链表的长度范围为 [1, 100]
	 * 0 <= node.val <= 9
	 * 输入数据保证链表代表的数字无前导 0
	 * 进阶：如果输入链表不能修改该如何处理？换句话说，不能对列表中的节点进行翻转。
	 */
	static class Solution {
		public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
			int[] arr1 = new int[100];
			int[] arr2 = new int[100];
			int[] sums = new int[100];
			int len1 = 0;
			int len2 = 0;
			int index = 0;
			while (l1 != null) {
				arr1[index++] = l1.val;
				l1 = l1.next;
				++len1;
			}
			index = 0;
			while (l2 != null) {
				arr2[index++] = l2.val;
				l2 = l2.next;
				++len2;
			}
			int cl = 0;
			int index1 = len1 - 1;
			int index2 = len2 - 1;
			int right = Math.max(len1, len2) - 1;
			int sum;
			while (index1 >= 0 || index2 >= 0) {
				sum = cl;
				if (index1 >= 0) {
					sum += arr1[index1--];
				}
				if (index2 >= 0) {
					sum += arr2[index2--];
				}

				sums[right--] = sum > 9 ? sum - 10 : sum;
				cl = sum > 9 ? 1 : 0;
			}

			ListNode head = null;
			if (cl == 1) {
				head = new ListNode(1);
			}
			ListNode cur = head;
			right = Math.max(len2, len1) - 1;
			for (int i = 0; i <= right; i++) {
				if (cur == null) {
					head = new ListNode(sums[i]);
					cur = head;
				} else {
					cur.next = new ListNode(sums[i]);
					cur = cur.next;
				}
			}
			return head;
		}
	}

}
