package com.hlx.leetcode.day9;

/**
 * @author Huang Lexin
 * @date 2022年03月04日 12:45
 */
public class SwordsmanOfferII0029 {
	class Node {
		public int val;
		public Node next;

		public Node() {}

		public Node(int _val) {
			val = _val;
		}

		public Node(int _val, Node _next) {
			val = _val;
			next = _next;
		}
	}


	/**
	 * 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
	 *
	 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
	 *
	 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
	 *
	 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。
	 *
	 * 0 <= Number of Nodes <= 5 * 10^4
	 * -10^6 <= Node.val <= 10^6
	 * -10^6 <= insertVal <= 10^6
	 */
	class Solution {
		public Node insert(Node head, int insertVal) {
			// 特殊情况, 链表为空
			if (head == null) {
				Node n = new Node(insertVal);
				n.next = n;
				return n;
			}
			// insertVal 比所有链表的元素值小 ---> 找到循环链表的尾结点插入
			// insertVal 比所有链表的元素值大 ---> 找到循环链表的尾结点插入
			// insertVal 找到一个比其小的元素, 且后一个元素大于它的位置插入即可
			Node L = null;
			Node R = null;
			Node last = head;
			Node next;
			Node temp = head;
			int count = 2;
			while (count != 0) {
				if (temp == head) {
					--count;
				}
				if (temp.val >= insertVal) {
					R = temp;
				} else {
					L = temp;
					R = null;
				}
				if (L != null && R != null) {
					break;
				}
				next = temp.next;
				if (temp.val >= next.val) {
					last = temp;
				}
				temp = next;

			}

			if (L == null || R == null) {
				next = last.next;
				last.next = new Node(insertVal, next);
			} else {
				L.next = new Node(insertVal, R);
			}
			return head;
		}
	}
}
