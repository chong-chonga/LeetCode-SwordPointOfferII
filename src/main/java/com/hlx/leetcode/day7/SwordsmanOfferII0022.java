package com.hlx.leetcode.day7;

import com.hlx.leetcode.structure.ListNode;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Huang Lexin
 * @date 2022年03月02日 8:02
 */
public class SwordsmanOfferII0022 {

	/**
	 * 给定一个链表，返回链表开始入环的第一个节点。
	 * 从链表的头节点开始沿着 next 指针进入环的第一个节点为环的入口节点。如果链表无环，则返回 null。
	 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
	 * 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
	 *
	 * 说明：不允许修改给定的链表。
	 * 链表中节点的数目范围在范围 [0, 104] 内
	 * -105 <= Node.val <= 105
	 * pos 的值为 -1 或者链表中的一个有效索引
	 *
	 * Definition for singly-linked list.
	 * class ListNode {
	 *     int val;
	 *     ListNode next;
	 *     ListNode(int x) {
	 *         val = x;
	 *         next = null;
	 *     }
	 * }
	 *
	 */
	public class Solution {
		public ListNode detectCycle(ListNode head) {
			Set<ListNode> set = new HashSet<>();
			ListNode node = head;
			while (node != null) {
				if (set.contains(node)) {
					return node;
				}
				set.add(node);
				node = node.next;
			}
			return null;
		}
	}
}
