# 剑指 Offer II 004. 只出现一次的数字 



## 解法一: 快排 + 顺序查找



### 解题思路

这是我解该题的第一想法: 既然每个数字要么出现 1 次, 要么出现了 3 次; 那么只要数字是按顺序排列, 数组中元素要么重复出现三次, 要么就出现一次。

我们对数组进行 **sort** 后, 在保证索引不越界的情况下, 我们可以进行如下比较

从第1个元素开始, 查看其是否与第3个元素相等;

从第4个元素开始,查看其是否与第6个元素相等;

...

上述比较中, 如不相等, 则说明其只出现一次



### 代码

```java
class Solution {
		public int singleNumber(int[] nums) {
			Arrays.sort(nums);
            int len = nums.length;
			for (int i = 0; i < nums.length; i+=3) {
				if (i+2 < nums.length && nums[i] == nums[i+2]) {
					continue;
				}
				return nums[i];
			}
			return nums[len-1];
		}
	}
```



### 复杂度分析

- 时间复杂度：O(nlogn), 使用了快排(nlogn), 加上一次遍历(n), 总的时间复杂度为 O(n)。
- 空间复杂度：O(1)。

