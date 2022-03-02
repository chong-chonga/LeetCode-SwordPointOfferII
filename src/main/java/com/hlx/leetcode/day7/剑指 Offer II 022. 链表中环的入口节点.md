# 剑指 Offer II 022. 链表中环的入口节点



## 解法1: 哈希表

保存已走过的结点

这种解法很直观, 每次向后遍历结点的时候, 判断其是否已经走过 (哈希表中是否存储了这个结点)

如果有, 则返回该结点即可。

同时保存走过的结点到哈希表中, 终止条件是, 指针走到了 null。

1. 当有环时， 走到第一个重复结点时， 即可得到答案
2. 当没有环时， 会走到链表尾， 退出循环



###  完整代码

```java
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
```





## 解法2: 快慢指针

设慢指针 slow, 快指针 fast; 其中慢指针一次走 1 步, 快指针一次走 2 步。

**如果没有环, 那么快指针永远不会与慢指针相遇**

考虑无环的情况, 那么 fast 必定先走到 null, 返回 null 即可

**如果有环, 那么必定会在一个时间点相遇**

如果有环, 设第 n 步时, fast 和 slow 相遇;

fast  至今走了 2n 步, slow 至今走了 n 步

所以 fast 比 slow 多走了 **2n - n = n** 步

设环前的链表长度为 a, 在环内的相遇点长度为 b, 环的剩余长度为 c

```mathematica
n = a + b;
2n = a + b + k(b+c) = a + (k+1)b + kc = 2(a + b)
a = (k-1)(b+c) + c
```

再让一个指针 p3 从头结点出发, 同时, slow 也每次走1步, 当 p3 走到环的起始点时(走了长度 a 的距离)

**b + (k - 1)(b+c) + c = k(b+c)**

**slow 指针会到环的起始点, 同时 p3 也会到达环的起始点, 两个指针相遇!**



### 完整代码

```java
	public class Solution {
		public ListNode detectCycle(ListNode head) {
            if (head == null) {
                return null;
            }
			ListNode slow = head;
			ListNode fast = slow;
			do {
				slow = slow.next;
				fast = fast.next;
				if (fast == null || fast.next == null) {
					return null;
				}
				fast = fast.next;
			} while (slow != fast);
            ListNode p3 = head;
			while (p3 != slow) {
				p3 = p3.next;
				slow = slow.next;
			}
			return p3;

		}
	}
```



