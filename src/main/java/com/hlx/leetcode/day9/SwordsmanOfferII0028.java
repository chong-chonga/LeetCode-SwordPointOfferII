package com.hlx.leetcode.day9;

/**
 * @author Huang Lexin
 * @date 2022年03月04日 10:47
 */
public class SwordsmanOfferII0028 {

	class Node {
		public int val;
		public Node prev;
		public Node next;
		public Node child;
	}
	/**
	 // Definition for a Node.
	 class Node {
	 public int val;
	 public Node prev;
	 public Node next;
	 public Node child;
	 };
	 */
	/**
	 * 多级双向链表中，除了指向下一个节点和前一个节点指针之外，它还有一个子链表指针，可能指向单独的双向链表。
	 * 这些子列表也可能会有一个或多个自己的子项，依此类推，生成多级数据结构，
	 * <p>
	 * 给定位于列表第一级的头节点，请扁平化列表，即将这样的多级双向链表展平成普通的双向链表，使所有结点出现在单级双链表中。
	 * 节点数目不超过 1000
	 * 1 <= Node.val <= 10^5
	 */
	class Solution {
		public Node flatten(Node head) {
			f(head);
			return head;
		}

		Node f(Node head) {
			Node next;
			Node cur = head;
			Node child;
			Node last = null;
			while (cur != null) {
				if ((next = cur.next) == null) {
					last = cur;
				}
				if ((child = cur.child) != null) {
					Node node = f(child);
					node.next = next;
					if (next != null) {
						next.prev = node;
					} else {
						// 此层已无后续结点了, 所以此层展开后的最后一个结点是 node
						last = node;
					}
					child.prev = cur;
					cur.next = child;
					cur.child = null;

				}
				cur = next;
			}

			return last;
		}
	}
}
