package com.hlx.leetcode.day7;

import com.hlx.leetcode.structure.ListNode;

/**
 * @author Huang Lexin
 * @date 2022年03月02日 8:03
 */
public class SwordsmanOfferII0023 {
	/**
	 * Definition for singly-linked list.
	 * public class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 * 给定两个单链表的头节点 headA 和 headB ，请找出并返回两个单链表相交的起始节点。如果两个链表没有交点，返回 null
	 * listA 中节点数目为 m
	 * listB 中节点数目为 n
	 * 0 <= m, n <= 3 * 104
	 * 1 <= Node.val <= 105
	 * 0 <= skipA <= m
	 * 0 <= skipB <= n
	 * 如果 listA 和 listB 没有交点，intersectVal 为 0
	 * 如果 listA 和 listB 有交点，intersectVal == listA[skipA + 1] == listB[skipB + 1]
	 */
	public class Solution {
		public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
			ListNode p1 = headA;
			ListNode p2 = headB;
			int count = 2;
			while (p1 != null && p2 != null) {
				if (p1 == p2) {
					return p1;
				}
				p1 = p1.next;
				if (p1 == null && count != 0) {
					--count;
					p1 = headB;
				}
				p2 = p2.next;
				if (p2 == null && count != 0) {
					--count;
					p2 = headA;
				}
			}
			return null;
		}
	}
}
