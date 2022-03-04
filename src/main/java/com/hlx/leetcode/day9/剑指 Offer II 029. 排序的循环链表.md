# 剑指 Offer II 029. 排序的循环链表



## 原题

给定**循环单调非递减列表**中的一个点，写一个函数向这个列表中插入一个新元素 `insertVal` ，使这个列表仍然是循环升序的。

给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。

如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。

如果列表为空（给定的节点是 `null`），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点。

- `0 <= Number of Nodes <= 5 * 10^4`
- `-10^6 <= Node.val <= 10^6`
- `-10^6 <= insertVal <= 10^6`

[剑指 Offer II 029. 排序的循环链表](https://leetcode-cn.com/problems/4ueAj6/)



## 解题思路

先考虑特殊情况： 链表为空。此时需要新创建一个结点，并将其指向自己。

现在来分析一般的情况。

由于给定的链表是**循环单调非递减列表**。

我们设该链表的**尾结点**存储的是**列表最大值**, 链表的**头结点**存储的是**列表最小值**。

除了 尾结点 -> 头结点 这个连接是递减的， 其他的连接都是非递减的。为了保持插入新结点后，还是这样的顺序，我们就必须分析下结点应当插入的位置。

在一般情况里， 可以根据 **insertVal** 的值分成以下三种情况。

| 序号 | insertVal 与链表所有元素值相比 |         插入位置          |
| :--: | :----------------------------: | :-----------------------: |
|  1   |              最小              |    循环链表**尾**结点     |
|  2   |              最大              |    循环链表**尾**结点     |
|  3   |     既不是最大，也不是最小     | 左结点 L, 右结点 R 的中间 |

1. 第一、二种情况：由于 insertVal 找不到比其小的/大的结点, 所以为了保持非递减，将 insertVal 作为**新的头结点/尾结点**
2. 第三种情况：存在 L.val <= insertVal && R.val >= insertVal, 因此插入中间能保持非递减顺序。



我们至多遍历一圈链表, 枚举出所有可能的 L, R。

假如遍历完后, 只存在 R/L, 说明 insertVal 属于 (最大/最小)。

否则, insertVal 既不是最大, 也不是最小。



## 完整代码

```java
class Solution {
		public Node insert(Node head, int insertVal) {
			// 特殊情况, 链表为空
			if (head == null) {
				Node n = new Node(insertVal);
				n.next = n;
				return n;
			}
            
			Node L = null;
			Node R = null;
			Node last = head;
			Node next;
			Node temp = head;
            // temp 必须走过两次 head
            // 第一次是枚举作为 L 结点的时候
            // 第二次是枚举作为 R 结点的时候
            // 以此保证枚举过所有的 L, R 结点
			int count = 2;
			while (count != 0) {
				if (temp == head) {
					--count;
				}
				if (temp.val >= insertVal) {
					R = temp;
				} else {
                    // 出现 L 结点, 则需要将之前的 R 结点重置
					L = temp;
					R = null;
				}
				if (L != null && R != null) {
					break;
				}
				next = temp.next;
				if (temp.val > next.val) {
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
```

